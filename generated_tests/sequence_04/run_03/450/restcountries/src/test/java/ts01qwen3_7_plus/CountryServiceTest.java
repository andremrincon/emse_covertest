package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class CountryServiceTest {

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_EU() {
        given()
            .baseUri("http://localhost:8080/rest")
        .when()
            .get("/v2/regionalbloc/EU")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_NAFTA() {
        given()
            .baseUri("http://localhost:8080/rest")
        .when()
            .get("/v2/regionalbloc/NAFTA")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc_NotFound() {
        given()
            .baseUri("http://localhost:8080/rest")
        .when()
            .get("/v2/regionalbloc/123")
        .then()
            .statusCode(404);
    }
}