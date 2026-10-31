package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ResponseEntityTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testResponseEntityViaNameEndpoint() {
        given()
                .when()
                .get("/v1/name/NonExistentCountryXYZ")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testResponseEntityViaCapitalEndpoint() {
        given()
                .when()
                .get("/v1/capital/NonExistentCapitalXYZ")
                .then()
                .statusCode(404);
    }
}