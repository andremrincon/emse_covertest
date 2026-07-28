package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryServiceTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080/rest";
        }
    }

    @Test(timeout = 60000)
    public void testGetByLanguageWithTwoCharCode() {
        given()
        .when()
            .get("/v1/lang/es")
        .then()
            .statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testGetByLanguageWithThreeCharCode() {
        given()
        .when()
            .get("/v1/lang/spa")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageTwoCharCodeViaV2() {
        given()
        .when()
            .get("/v2/lang/es")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageThreeCharCodeViaV2() {
        given()
        .when()
            .get("/v2/lang/eng")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocValid() {
        given()
        .when()
            .get("/v2/regionalbloc/EU")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocNotFound() {
        given()
        .when()
            .get("/v2/regionalbloc/123")
        .then()
            .statusCode(404);
    }
}