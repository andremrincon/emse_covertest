package ts01qwen3_7_plus_2;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    private static String baseUri;

    @BeforeClass
    public static void setup() {
        baseUri = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";
        RestAssured.baseURI = baseUri;
    }

    @Test(timeout = 60000)
    public void testResponseStatusV1Name() {
        given()
            .baseUri(baseUri)
        .when()
            .get("/v1/name/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testResponseMessageV2Currency() {
        given()
            .baseUri(baseUri)
        .when()
            .get("/v2/currency/123")
        .then()
            .body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testResponseStatusV1Region() {
        given()
            .baseUri(baseUri)
        .when()
            .get("/v1/region/123")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testResponseMessageV2Name() {
        given()
            .baseUri(baseUri)
        .when()
            .get("/v2/name/123")
        .then()
            .body("status", equalTo(404));
    }
}