package apitest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

public class APITest {


    @BeforeClass
    public static void startUp() {
        RestAssured.baseURI = "http://localhost:8001/tasks-backend";
    }

    @Test
    public void testeum() {
        RestAssured.given()
                .when()
                .get("/todo")
                .then()
                .statusCode(200)
                .log().all();
    }


    @Test
    public void deveSalvarComSucesso() {
        RestAssured.given()
                .body("{ \"task\":\"teste de via api\",\"dueDate\":\"2021-12-30\"}")
                .contentType(ContentType.JSON)
                .when()
                .post("/todo")
                .then()
                .statusCode(201)
                .log().all();
    }

    @Test
    public void naoDeveSalvarTarefaInvalida() {
        RestAssured.given()
                .body("{ \"task\":\"teste de via api\",\"dueDate\":\"2010-12-30\"}")
                .contentType(ContentType.JSON)
                .when()
                .post("/todo")
                .then()
                .statusCode(400)
                .body("message", CoreMatchers.is("Due date must not be in past"))
                .log().all();
    }
}



