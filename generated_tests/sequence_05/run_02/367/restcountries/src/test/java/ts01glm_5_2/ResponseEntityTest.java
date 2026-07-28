package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetNameNotFoundReturnsResponseEntityWithStatusAndMessage_1() {
        given()
            .when()
                .get("/v1/name/{name}", "123")
            .then()
                .statusCode(404)
                .body("status", equalTo(404))
                .body("message", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetCapitalNotFoundReturnsResponseEntityWithStatusAndMessage_2() {
        given()
            .when()
                .get("/v1/capital/{capital}", "123")
            .then()
                .statusCode(404)
                .body("status", equalTo(404))
                .body("message", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetRegionNotFoundReturnsResponseEntityWithStatusAndMessage_3() {
        given()
            .when()
                .get("/v1/region/{region}", "123")
            .then()
                .statusCode(404)
                .body("status", equalTo(404))
                .body("message", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetCallingCodeNotFoundReturnsResponseEntityWithStatusAndMessage_4() {
        given()
            .when()
                .get("/v1/callingcode/{callingcode}", "abc")
            .then()
                .statusCode(404)
                .body("status", equalTo(404))
                .body("message", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetNameServerErrorReturnsResponseEntityWithStatusAndMessage_5() {
        given()
            .when()
                .get("/v1/name/{name}", "True")
            .then()
                .statusCode(404)
                .body("status", equalTo(404))
                .body("message", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetRegionServerErrorReturnsResponseEntityWithStatusAndMessage_6() {
        given()
            .when()
                .get("/v1/region/{region}", "True")
            .then()
                .statusCode(404)
                .body("status", equalTo(404))
                .body("message", notNullValue());
    }
}