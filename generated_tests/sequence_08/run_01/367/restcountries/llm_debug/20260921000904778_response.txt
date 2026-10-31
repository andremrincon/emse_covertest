package ts01qwen3_7_plus_2;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class CountryServiceTest {

    @Test(timeout = 60000)
    public void testGetByRegionalBlocEU() {
        given()
            .when()
            .get("/v2/regionalbloc/EU")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocNAFTA() {
        given()
            .when()
            .get("/v2/regionalbloc/NAFTA")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocNotFound() {
        given()
            .when()
            .get("/v2/regionalbloc/INVALIDBLOC")
            .then()
            .statusCode(404);
    }
}