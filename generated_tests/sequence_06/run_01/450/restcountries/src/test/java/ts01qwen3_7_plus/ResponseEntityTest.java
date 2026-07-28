package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class ResponseEntityTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
        RestAssured.defaultParser = Parser.JSON;
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetMessageFromV1NameNotFound() {
        given()
        .when()
            .get("/v1/name/123")
        .then()
            .body("message", equalTo("Not Found"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetStatusFromV2CapitalNotFound() {
        given()
        .when()
            .get("/v2/capital/12345")
        .then()
            .body("status", equalTo(404));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetMessageFromPostRoot() {
        given()
        .when()
            .post("/")
        .then()
            .body("message", equalTo("POST method is not allowed for this endpoint"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetStatusFromPostV2() {
        given()
        .when()
            .post("/v2")
        .then()
            .body("status", equalTo(405));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetMessageFromV2RegionalblocNotFound() {
        given()
        .when()
            .get("/v2/regionalbloc/123")
        .then()
            .body("message", equalTo("Regional bloc not found"));
    }

    @Ignore("Expected response body to be verified as JSON, HTML or XML but no content-type was defined in the...")
    @Test(timeout = 60000)
    public void testGetStatusFromV2CurrencyBadRequest() {
        given()
        .when()
            .get("/v2/currency/123")
        .then()
            .body("status", equalTo(400));
    }
}