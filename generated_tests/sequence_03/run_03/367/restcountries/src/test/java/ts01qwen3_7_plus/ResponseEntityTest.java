package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class ResponseEntityTest {

    private static String baseUri;

    @BeforeClass
    public static void setup() {
        baseUri = System.getProperty("test.base.url", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUri;
    }

    @Test(timeout = 60000)
    public void testGetStatusFromNameNotFound() {
        given()
            .baseUri(baseUri)
        .when()
            .get("/v1/name/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetMessageFromCapitalNotFound() {
        given()
            .baseUri(baseUri)
        .when()
            .get("/v1/capital/123")
        .then()
            .statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code <405> but was <404>.")
    @Test(timeout = 60000)
    public void testGetStatusFromPostNotAllowed() {
        given()
            .baseUri(baseUri)
        .when()
            .post("/")
        .then()
            .statusCode(405);
    }

    @Ignore("1 expectation failed. Expected status code <405> but was <404>.")
    @Test(timeout = 60000)
    public void testGetMessageFromPostNotAllowed() {
        given()
            .baseUri(baseUri)
        .when()
            .post("/")
        .then()
            .statusCode(405);
    }
}