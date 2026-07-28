package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ResponseEntityTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080/rest";
        }
    }

    @Test(timeout = 60000)
    public void testGetMessageAndStatusViaAlphaCodeNotFound() {
        given()
            .when()
                .get("/v1/alpha/{alphacode}", "XYZ")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetMessageAndStatusViaNameNotFound() {
        given()
            .when()
                .get("/v1/name/{name}", "123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetMessageAndStatusViaCapitalNotFound() {
        given()
            .when()
                .get("/v1/capital/{capital}", "123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetMessageAndStatusViaRegionNotFound() {
        given()
            .when()
                .get("/v1/region/{region}", "123")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetMessageAndStatusViaCallingCodeNotFound() {
        given()
            .when()
                .get("/v1/callingcode/{callingcode}", "abc")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetMessageAndStatusViaCurrencyNotFound() {
        given()
            .when()
                .get("/v1/currency/{currency}", "XYZ")
            .then()
                .statusCode(404);
    }
}