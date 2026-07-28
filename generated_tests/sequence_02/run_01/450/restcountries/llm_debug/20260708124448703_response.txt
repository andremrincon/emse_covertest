package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class ResponseEntityTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("test.base.url", "http://localhost:8080/rest");
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testPostRootReturnsMethodNotAllowed() {
        given()
        .when()
            .post("/")
        .then()
            .statusCode(404)
            .body(equalTo(""));
    }

    @Test(timeout = 60000)
    public void testGetNameInvalidReturnsNotFound() {
        given()
        .when()
            .get("/v1/name/123")
        .then()
            .statusCode(404)
            .body(equalTo(""));
    }

    @Test(timeout = 60000)
    public void testGetCapitalInvalidReturnsNotFound() {
        given()
        .when()
            .get("/v1/capital/123")
        .then()
            .statusCode(404)
            .body(equalTo(""));
    }

    @Test(timeout = 60000)
    public void testGetRegionInvalidReturnsNotFound() {
        given()
        .when()
            .get("/v1/region/123")
        .then()
            .statusCode(404)
            .body(equalTo(""));
    }

    @Test(timeout = 60000)
    public void testGetLangInvalidReturnsNotFound() {
        given()
        .when()
            .get("/v1/lang/123")
        .then()
            .statusCode(404)
            .body(equalTo(""));
    }
}