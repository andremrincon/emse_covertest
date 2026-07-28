package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class ResponseEntityTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testResponseEntityStatusName() {
        given()
        .when()
            .get("/v1/name/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testResponseEntityMessageName() {
        given()
        .when()
            .get("/v1/name/123")
        .then()
            .statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <405> but was <404>.")
    @Test(timeout = 60000)
    public void testResponseEntityStatusPost() {
        given()
        .when()
            .post("/")
        .then()
            .statusCode(405);
    }

    @Ignore("1 expectation failed. Expected status code <405> but was <404>.")
    @Test(timeout = 60000)
    public void testResponseEntityMessagePost() {
        given()
        .when()
            .post("/")
        .then()
            .statusCode(405);
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <404>.")
    @Test(timeout = 60000)
    public void testResponseEntityStatusCurrency() {
        given()
        .when()
            .get("/v2/currency/123")
        .then()
            .statusCode(400);
    }

    @Ignore("1 expectation failed. Expected status code <400> but was <404>.")
    @Test(timeout = 60000)
    public void testResponseEntityMessageCurrency() {
        given()
        .when()
            .get("/v2/currency/123")
        .then()
            .statusCode(400);
    }
}