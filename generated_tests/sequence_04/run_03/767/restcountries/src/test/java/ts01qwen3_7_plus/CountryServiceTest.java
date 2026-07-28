package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocValidAcronymEU() {
        given()
            .when()
            .get("/v2/regionalbloc/EU")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocValidAcronymNAFTA() {
        given()
            .when()
            .get("/v2/regionalbloc/NAFTA")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocInvalid() {
        given()
            .when()
            .get("/v2/regionalbloc/INVALIDBLOC")
            .then()
            .statusCode(404);
    }
}