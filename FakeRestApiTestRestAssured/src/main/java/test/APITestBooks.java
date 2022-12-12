package test;

import core.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class APITestBooks extends BaseTest {

    @Test
    public void t01_deveCriarLivroComSucesso() {
        Book newBook = new Book(500, "New Title", "New Description", 500, "An excerpt", "2022-12-12T13:45:14.785Z");

        given()
            .body(newBook)
        .when()
            .post("/Books")
        .then()
            .statusCode(200)
            .body("id", is(500))
            .body("title", is("New Title"))
            .body("description", is("New Description"))
            .body("pageCount", is(500))
            .body("excerpt", is("An excerpt"))
            .body("publishDate", is("2022-12-12T13:45:14.785Z"))
        ;
    }

    @Test
    public void t02_naoDeveCriarLivroSemReceberBody() {
        given()
        .when()
            .post("/Books")
        .then()
            .statusCode(400)
            .body("errors.''", hasItem("A non-empty request body is required."))
        ;
    }

    @Test
    public void t03_deveMostrarTodosOsLivrosComSucesso() {
        given()
        .when()
            .get("/Books")
        .then()
            .statusCode(200)
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("book.json"))
        ;
    }

    @Test
    public void t04_deveMostrarUmLivroEspecificoComSucesso() {
        given()
            .pathParam("id", 10)
        .when()
            .get("/Books/{id}")
        .then()
            .statusCode(200)
            .body("id", is(10))
            .body("title", is("Book 10"))
        ;
    }

    @Test
    public void t05_naoDeveEncontrarLivroComIdInexistente() {
        given()
            .pathParam("id", 1000)
        .when()
            .get("/Books/{id}")
        .then()
            .statusCode(404)
            .body("title", is("Not Found"))
        ;
    }

    @Test
    public void t06_deveAlterarUmLivroEspecificoComSucesso() {
        Book bookChanged = new Book(500, "Title Changed", "Description Changed", 500, "An excerpt changed", "2022-12-12T13:45:14.785Z");

        given()
            .body(bookChanged)
            .pathParam("id", 500)
        .when()
            .put("/Books/{id}")
        .then()
            .statusCode(200)
            .body("id", is(500))
            .body("title", is("Title Changed"))
            .body("description", is("Description Changed"))
            .body("pageCount", is(500))
            .body("excerpt", is("An excerpt changed"))
            .body("publishDate", is("2022-12-12T13:45:14.785Z"))
        ;
    }

    @Test
    public void t07_naoDeveAlterarLivroSemReceberDadosObrigatorios() {
        given()
            .pathParam("id", 500)
        .when()
            .put("/Books/{id}")
        .then()
            .statusCode(400)
            .body("errors.''", hasItem("A non-empty request body is required."))
        ;
    }

    @Test
    public void t08_deveDeletarUmLivroEspecificoComSucesso() {
        given()
            .pathParam("id", 15)
        .when()
            .delete("/Books/{id}")
        .then()
            .statusCode(200)
        ;
    }

    @Test
    public void t09_naoDeveDeletarLivroComIdInvalido() {
        given()
            .pathParam("id", "a")
        .when()
            .delete("/Books/{id}")
        .then()
            .statusCode(400)
            .body("errors.id", hasItem("The value 'a' is not valid."))
        ;
    }
}
