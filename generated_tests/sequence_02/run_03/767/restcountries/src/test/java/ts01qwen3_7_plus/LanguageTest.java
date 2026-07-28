package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class LanguageTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("test.base.uri", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testLanguageDeserializationViaV1Alpha() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLanguageDeserializationViaV1Name() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLanguageDeserializationViaV1Lang() {
        given()
            .when()
                .get("/v1/lang/es")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLanguageDeserializationViaV2Alpha() {
        given()
            .when()
                .get("/v2/alpha/US")
            .then()
                .statusCode(200);
    }
}