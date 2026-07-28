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
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetMessageViaNameNotFound() {
        given()
            .when()
                .get("/v1/name/{name}", "123")
            .then()
                .statusCode(404)
                .body("message", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetStatusViaCapitalNotFound() {
        given()
            .when()
                .get("/v1/capital/{capital}", "123")
            .then()
                .statusCode(404)
                .body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testGetMessageViaRegionNotFound() {
        given()
            .when()
                .get("/v1/region/{region}", "123")
            .then()
                .statusCode(404)
                .body("message", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetStatusViaCallingcodeNotFound() {
        given()
            .when()
                .get("/v1/callingcode/{callingcode}", "abc")
            .then()
                .statusCode(404)
                .body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testGetMessageViaSubregionNotFound() {
        given()
            .when()
                .get("/v1/subregion/{subregion}", "123")
            .then()
                .statusCode(404)
                .body("message", notNullValue());
    }

    @Test(timeout = 60000)
    public void testGetStatusViaNameNotFound() {
        given()
            .when()
                .get("/v1/name/{name}", "NonExistentCountryXYZ")
            .then()
                .statusCode(404)
                .body("status", equalTo(404));
    }
}