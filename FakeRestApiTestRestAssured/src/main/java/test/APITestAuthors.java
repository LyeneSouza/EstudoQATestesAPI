package test;

import core.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import test.models.Author;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class APITestAuthors extends BaseTest {

    @Test
    public void t01_deveCriarAutorComSucesso() {
        Author newAuthor = new Author(1000, 1000, "Name New Author", "Last Name New Author");

        given()
            .body(newAuthor)
        .when()
            .post("/Authors")
        .then()
            .statusCode(200)
            .body("id", is(1000))
            .body("idBook", is(1000))
            .body("firstName", is("Name New Author"))
            .body("lastName", is("Last Name New Author"))
        ;
    }

    @Test
    public void t02_naoDeveCriarAutorSemReceberBody() {
        given()
        .when()
            .post("/Authors")
        .then()
            .statusCode(400)
            .body("errors.''", hasItem("A non-empty request body is required."))
        ;
    }
    @Test
    public void t03_deveMostrarTodosOsAutoresComSucesso() {
        given()
            .when()
        .get("/Authors")
        .then()
            .statusCode(200)
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("author.json"))
        ;
    }

    @Test
    public void t04_deveMostrarUmAutorEspecificoComSucesso() {
        given()
            .pathParam("id", 10)
        .when()
            .get("/Authors/{id}")
        .then()
            .statusCode(200)
            .body("id", is(10))
            .body("firstName", is("First Name 10"))
            .body("lastName", is("Last Name 10"))
        ;
    }

    @Test
    public void t05_naoDeveEncontrarAutorComIdInexistente() {
        given()
            .pathParam("id", 5000)
        .when()
            .get("/Authors/{id}")
        .then()
            .statusCode(404)
            .body("title", is("Not Found"))
        ;
    }

    @Test
    public void t06_deveMostrarAutoresLivroEspecificoComSucesso() {
        given()
            .pathParam("idBook", 100)
        .when()
            .get("/Authors/authors/books/{idBook}")
        .then()
            .statusCode(200)
            .body("idBook", hasItem(100))
            .body("find{it.idBook != 100}", is(nullValue()))
        ;
    }

    @Test
    public void t07_deveAlterarUmAutorEspecificoComSucesso() {
        Author authorChanged = new Author(1000, 1000, "Name Author Changed", "Last Name Author Changed");

        given()
            .body(authorChanged)
            .pathParam("id", 1000)
        .when()
            .put("/Authors/{id}")
        .then()
            .statusCode(200)
            .body("id", is(1000))
            .body("idBook", is(1000))
            .body("firstName", is("Name Author Changed"))
            .body("lastName", is("Last Name Author Changed"))
        ;
    }

    @Test
    public void t08_naoDeveAlterarAutorSemReceberDadosObrigatorios() {
        given()
            .pathParam("id", 1000)
        .when()
            .put("/Authors/{id}")
        .then()
            .statusCode(400)
            .body("errors.''", hasItem("A non-empty request body is required."))
        ;
    }

    @Test
    public void t09_deveDeletarUmAutorEspecificoComSucesso() {
        given()
            .pathParam("id", 15)
        .when()
            .delete("/Authors/{id}")
        .then()
            .statusCode(200)
        ;
    }

    @Test
    public void t10_naoDeveDeletarAutorComIdInvalido() {
        given()
            .pathParam("id", "a")
        .when()
            .delete("/Authors/{id}")
        .then()
            .statusCode(400)
            .body("errors.id", hasItem("The value 'a' is not valid."))
        ;
    }
}
