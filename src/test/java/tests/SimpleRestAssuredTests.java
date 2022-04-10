package tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class SimpleRestAssuredTests {
    private String baseUrl = "https://reqres.in";

    @Test
    public void createUser() {
        given()
                .body("{\n" +
                        "    \"name\": \"bob\",\n" +
                        "    \"job\": \"racer\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post(baseUrl + "/api/users")
                .then()
                .statusCode(201)
                .body("job", is("racer"));
    }

    @Test
    public void putUser() {
        given()
                .body("{\n" +
                        "    \"name\": \"bob\",\n" +
                        "    \"job\": \"trainer\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .put(baseUrl + "/api/users")
                .then()
                .statusCode(200)
                .body("job", is("trainer"));
    }

    @Test
    public void patchUser() {
        given()
                .body("{\n" +
                        "    \"name\": \"bob\",\n" +
                        "    \"job\": \"pension\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .patch(baseUrl + "/api/users")
                .then()
                .statusCode(200)
                .body("job", is("pension"));
    }

    @Test
    public void deleteUser(){
        given()
                .body("{\n" +
                        "    \"name\": \"bob\",\n" +
                        "    \"job\": \"pension\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .delete(baseUrl + "/api/users")
                .then()
                .statusCode(204);
    }

    @Test
    public void getNonExistentList(){
        given()
                .when()
                .get(baseUrl + "/api/unknown/123")
                .then()
                .statusCode(404)
                .body(is("{}"));
    }
}
