package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        String basePath = System.getProperty("basePath", "/rest");
        RestAssured.baseURI = baseUrl;
        RestAssured.basePath = basePath;
    }

    @Test(timeout = 60000)
    public void testNameNotFoundReturnsResponseEntityWithStatusAndMessage() {
        given()
                .pathParam("name", "123")
        .when()
                .get("/v1/name/{name}")
        .then()
                .statusCode(404)
                .body("status", equalTo(404))
                .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testRegionNotFoundReturnsResponseEntityWithStatusAndMessage() {
        given()
                .pathParam("region", "123")
        .when()
                .get("/v1/region/{region}")
        .then()
                .statusCode(404)
                .body("status", equalTo(404))
                .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testCapitalNotFoundReturnsResponseEntityWithStatusAndMessage() {
        given()
                .pathParam("capital", "123")
        .when()
                .get("/v1/capital/{capital}")
        .then()
                .statusCode(404)
                .body("status", equalTo(404))
                .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testNameServerErrorReturnsResponseEntityWithStatusAndMessage() {
        given()
                .pathParam("name", "True")
        .when()
                .get("/v1/name/{name}")
        .then()
                .statusCode(404)
                .body("status", equalTo(404))
                .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testRegionServerErrorReturnsResponseEntityWithStatusAndMessage() {
        given()
                .pathParam("region", "True")
        .when()
                .get("/v1/region/{region}")
        .then()
                .statusCode(404)
                .body("status", equalTo(404))
                .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testCallingcodeServerErrorReturnsResponseEntityWithStatusAndMessage() {
        given()
                .pathParam("callingcode", "True")
        .when()
                .get("/v1/callingcode/{callingcode}")
        .then()
                .statusCode(404)
                .body("status", equalTo(404))
                .body("message", equalTo("Not Found"));
    }
}