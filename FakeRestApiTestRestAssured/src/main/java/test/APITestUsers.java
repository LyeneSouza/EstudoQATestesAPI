package test;

import core.BaseTest;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import test.models.User;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class APITestUsers extends BaseTest {

    @Test
    public void t01_deveCriarUsuarioComSucesso() {
        User newUser = new User(100, "New User", "New Password");

        given()
            .body(newUser)
        .when()
            .post("/Users")
        .then()
            .statusCode(200)
            .body("id", is(100))
            .body("userName", is("New User"))
            .body("password", is("New Password"))
        ;
    }

    @Test
    public void t02_naoDeveCriarUsuarioSemReceberBody() {
        given()
        .when()
            .post("/Users")
        .then()
            .statusCode(400)
            .body("errors.''", hasItem("A non-empty request body is required."))
        ;
    }

    @Test
    public void t03_deveMostrarTodosOsUsuariosComSucesso() {
        given()
        .when()
            .get("/Users")
        .then()
            .statusCode(200)
            .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("user.json"))
        ;
    }

    @Test
    public void t04_deveMostrarUmUsuarioEspecificoComSucesso() {
        given()
            .pathParam("id", 5)
        .when()
            .get("/Users/{id}")
        .then()
            .statusCode(200)
            .body("id", is(5))
            .body("userName", is("User 5"))
            .body("password", is("Password5"))
        ;
    }

    @Test
    public void t05_naoDeveEncontrarUsuarioComIdInexistente() {
        given()
            .pathParam("id", 20)
        .when()
            .get("/Users/{id}")
        .then()
            .statusCode(404)
            .body("title", is("Not Found"))
        ;
    }

    @Test
    public void t06_deveAlterarUmUsuarioEspecificoComSucesso() {
        User userChanged = new User(100, "User Changed", "Password Changed");

        given()
            .body(userChanged)
            .pathParam("id", 100)
        .when()
            .put("/Users/{id}")
        .then()
            .statusCode(200)
            .body("id", is(100))
            .body("userName", is("User Changed"))
            .body("password", is("Password Changed"))
        ;
    }

    @Test
    public void t07_naoDeveAlterarUsuarioSemReceberDadosObrigatorios() {
        given()
            .pathParam("id", 100)
        .when()
            .put("/Users/{id}")
        .then()
            .statusCode(400)
            .body("errors.''", hasItem("A non-empty request body is required."))
        ;
    }

    @Test
    public void t08_deveDeletarUmUsuarioEspecificoComSucesso() {
        given()
            .pathParam("id", 10)
        .when()
            .delete("/Users/{id}")
        .then()
            .statusCode(200)
        ;
    }

    @Test
    public void t09_naoDeveDeletarUsuarioComIdInvalido() {
        given()
            .pathParam("id", "a")
        .when()
            .delete("/Users/{id}")
        .then()
            .statusCode(400)
            .body("errors.id", hasItem("The value 'a' is not valid."))
        ;
    }
}
