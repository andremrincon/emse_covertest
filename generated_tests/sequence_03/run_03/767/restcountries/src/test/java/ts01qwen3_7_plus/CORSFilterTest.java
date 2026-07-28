package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGetAllV1() {
        given()
            .when()
            .get("/v1/all")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetAlphaV1() {
        given()
            .when()
            .get("/v1/alpha/US")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetNameV1() {
        given()
            .when()
            .get("/v1/name/France")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCurrencyV1() {
        given()
            .when()
            .get("/v1/currency/USD")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCapitalV1() {
        given()
            .when()
            .get("/v1/capital/London")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetRegionV1() {
        given()
            .when()
            .get("/v1/region/Europe")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetCallingCodeV1() {
        given()
            .when()
            .get("/v1/callingcode/1")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetAllV2() {
        given()
            .when()
            .get("/v2/all")
            .then()
            .statusCode(404);
    }
}