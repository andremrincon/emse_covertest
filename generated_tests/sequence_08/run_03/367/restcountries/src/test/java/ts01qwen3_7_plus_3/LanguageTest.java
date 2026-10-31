package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class LanguageTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testV1AlphaCodeUS() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1LangEs() {
        given()
            .when()
                .get("/v1/lang/es")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2AlphaCodeUS() {
        given()
            .when()
                .get("/v2/alpha/US")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2LangSpanish() {
        given()
            .when()
                .get("/v2/lang/Spanish")
            .then()
                .statusCode(404);
    }
}