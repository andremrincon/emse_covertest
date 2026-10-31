package ts01qwen3_7_plus_3;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testGetMessage404Name() {
        given()
            .when()
            .get("/v1/name/123")
            .then()
            .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testGetStatus404Name() {
        given()
            .when()
            .get("/v1/name/123")
            .then()
            .body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testGetMessage404Capital() {
        given()
            .when()
            .get("/v1/capital/123")
            .then()
            .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testGetStatus404Capital() {
        given()
            .when()
            .get("/v1/capital/123")
            .then()
            .body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testGetMessage500Name() {
        given()
            .when()
            .get("/v1/name/True")
            .then()
            .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testGetStatus500Name() {
        given()
            .when()
            .get("/v1/name/True")
            .then()
            .body("status", equalTo(404));
    }
}