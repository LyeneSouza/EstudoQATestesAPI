package test;

import core.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class APITestCoverPhotos extends BaseTest {

    @Test
    public void t01_deveCriarFotoCapaComSucesso() {
        CoverPhoto newCoverPhoto = new CoverPhoto(1000, 1000, "New url");

        given()
            .body(newCoverPhoto)
        .when()
            .post("/CoverPhotos")
        .then()
            .statusCode(200)
            .body("id", is(1000))
            .body("idBook", is(1000))
            .body("url", is("New url"))
        ;
    }

    @Test
    public void t02_naoDeveCriarFotoCapaSemReceberBody() {
        given()
        .when()
            .post("/CoverPhotos")
        .then()
            .statusCode(400)
            .body("errors.''", hasItem("A non-empty request body is required."))
        ;
    }

    @Test
    public void t03_deveMostrarTodasAsFotosCapaComSucesso() {
        given()
        .when()
            .get("/CoverPhotos")
        .then()
            .statusCode(200)
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("coverPhoto.json"))
        ;
    }

    @Test
    public void t04_deveMostrarUmaFotoCapaEspecificaComSucesso() {
        given()
            .pathParam("id", 10)
        .when()
            .get("/CoverPhotos/{id}")
        .then()
            .statusCode(200)
            .body("id", is(10))
            .body("idBook", is(10))
        ;
    }

    @Test
    public void t05_naoDeveEncontrarFotoCapaComIdInexistente() {
        given()
            .pathParam("id", 500)
        .when()
            .get("/CoverPhotos/{id}")
        .then()
            .statusCode(404)
            .body("title", is("Not Found"))
        ;
    }

    @Test
    public void t06_deveMostrarFotoCapaDeLivroEspecificoComSucesso() {
        given()
            .pathParam("idBook", 100)
        .when()
            .get("/CoverPhotos/books/covers/{idBook}")
        .then()
            .statusCode(200)
            .body("idBook", hasItem(100))
            .body("find{it.idBook != 100}", is(nullValue()))
        ;
    }

    @Test
    public void t07_deveAlterarUmaFotoCapaEspecificaComSucesso() {
        CoverPhoto coverPhotoChanged = new CoverPhoto(1000, 1000, "Url Changed");

        given()
            .body(coverPhotoChanged)
            .pathParam("id", 1000)
        .when()
            .put("/CoverPhotos/{id}")
        .then()
            .statusCode(200)
            .body("id", is(1000))
            .body("idBook", is(1000))
            .body("url", is("Url Changed"))
        ;
    }

    @Test
    public void t08_naoDeveAlterarFotoCapaSemReceberDadosObrigatorios() {
        given()
            .pathParam("id", 1000)
        .when()
            .put("/CoverPhotos/{id}")
        .then()
            .statusCode(400)
            .body("errors.''", hasItem("A non-empty request body is required."))
        ;
    }

    @Test
    public void t09_deveDeletarUmaFotoCapaEspecificaComSucesso() {
        given()
            .pathParam("id", 15)
        .when()
            .delete("/CoverPhotos/{id}")
        .then()
            .statusCode(200)
        ;
    }

    @Test
    public void t10_naoDeveDeletarFotoCapaComIdInvalido() {
        given()
            .pathParam("id", "a")
        .when()
            .delete("/CoverPhotos/{id}")
        .then()
            .statusCode(400)
            .body("errors.id", hasItem("The value 'a' is not valid."))
        ;
    }
}
