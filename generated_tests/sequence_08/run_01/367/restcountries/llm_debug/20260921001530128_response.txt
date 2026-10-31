package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class LanguageTest {

    @BeforeClass
    public static void setup() {
        String baseUri = System.getProperty("baseURI", System.getenv().getOrDefault("BASE_URI", "http://localhost:8080"));
        RestAssured.baseURI = baseUri;
        RestAssured.basePath = "/rest";
    }

    @Test(timeout = 60000)
    public void testLanguageSettersViaV1Alpha() {
        given()
            .pathParam("alphacode", "US")
        .when()
            .get("/v1/alpha/{alphacode}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testLanguageSettersViaV1Lang() {
        given()
            .pathParam("lang", "es")
        .when()
            .get("/v1/lang/{lang}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testLanguageSettersViaV2Alpha() {
        given()
            .pathParam("alphacode", "US")
        .when()
            .get("/v2/alpha/{alphacode}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testLanguageSettersViaV2Lang() {
        given()
            .pathParam("lang", "Spanish")
        .when()
            .get("/v2/lang/{lang}")
        .then()
            .statusCode(404);
    }
}