package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceTest {

    private final String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080/rest";

    @Test(timeout = 60000)
    public void testGetByRegionalBlocEU() {
        given().when().get(baseUrl + "/v2/all").then().statusCode(lessThan(300));

        given()
            .baseUri(baseUrl)
        .when()
            .get("/v2/regionalbloc/EU")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocNAFTA() {
        given().when().get(baseUrl + "/v2/all").then().statusCode(lessThan(300));

        given()
            .baseUri(baseUrl)
        .when()
            .get("/v2/regionalbloc/NAFTA")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBlocNotFound() {
        given().when().get(baseUrl + "/v2/all").then().statusCode(lessThan(300));

        given()
            .baseUri(baseUrl)
        .when()
            .get("/v2/regionalbloc/INVALIDBLOC")
        .then()
            .statusCode(404);
    }
}