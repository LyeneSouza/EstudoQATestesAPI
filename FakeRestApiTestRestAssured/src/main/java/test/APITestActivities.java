package test;

import core.BaseTest;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class APITestActivities extends BaseTest {

    @Test
    public void t01_deveCriarAtividadeComSucesso() {
        Activity newActivity = new Activity(100, "New Activity", "2022-12-08T12:10:53.777Z", true);

        given()
            .body(newActivity)
        .when()
            .post("/Activities")
        .then()
            .statusCode(200)
            .body("id", is(100))
            .body("title", is("New Activity"))
            .body("dueDate", is("2022-12-08T12:10:53.777Z"))
            .body("completed", is(true))
        ;
    }

    @Test
    public void t02_naoDeveCriarAtividadeSemReceberBody() {
        given()
        .when()
            .post("/Activities")
        .then()
            .statusCode(400)
            .body("errors.''", hasItem("A non-empty request body is required."))
        ;
    }

    @Test
    public void t03_deveMostrarTodasAsAtividadesComSucesso() {
        given()
        .when()
            .get("/Activities")
        .then()
            .statusCode(200)
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("activity.json"))
        ;
    }

    @Test
    public void t04_deveMostrarUmaAtividadeEspecificaComSucesso() {
        given()
            .pathParam("id", 10)
        .when()
            .get("/Activities/{id}")
        .then()
            .statusCode(200)
            .body("id", is(10))
            .body("title", is("Activity 10"))
            .body("completed", is(true))
        ;
    }

    @Test
    public void t05_naoDeveEncontrarAtividadeComIdInexistente() {
        given()
            .pathParam("id", 200)
        .when()
            .get("/Activities/{id}")
        .then()
            .statusCode(404)
            .body("title", is("Not Found"))
        ;
    }

    @Test
    public void t06_deveAlterarUmaAtividadeEspecificaComSucesso() {
        Activity activityChanged = new Activity(100, "Activity Changed", "2022-12-12T12:10:53.777Z", true);

        given()
            .body(activityChanged)
            .pathParam("id", 100)
        .when()
            .put("/Activities/{id}")
        .then()
            .statusCode(200)
            .body("id", is(100))
            .body("title", is("Activity Changed"))
            .body("dueDate", is("2022-12-12T12:10:53.777Z"))
            .body("completed", is(true))
        ;
    }

    @Test
    public void t07_naoDeveAlterarAtividadeSemReceberDadosObrigatorios() {
        given()
            .pathParam("id", 100)
        .when()
            .put("/Activities/{id}")
        .then()
            .statusCode(400)
            .body("errors.''", hasItem("A non-empty request body is required."))
        ;
    }

    @Test
    public void t08_deveDeletarUmaAtividadeEspecificaComSucesso() {
        given()
            .pathParam("id", 15)
        .when()
            .delete("/Activities/{id}")
        .then()
            .statusCode(200)
        ;
    }

    @Test
    public void t09_naoDeveDeletarAtividadeComIdInvalido() {
        given()
            .pathParam("id", "a")
        .when()
            .delete("/Activities/{id}")
        .then()
            .statusCode(400)
            .body("errors.id", hasItem("The value 'a' is not valid."))
        ;
    }
}
