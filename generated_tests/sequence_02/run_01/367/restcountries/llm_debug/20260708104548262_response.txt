package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceTest {

    @Test(timeout = 60000)
    public void testGetByLanguageTwoLetterCode() {
        given()
            .baseUri("http://localhost:8080/rest")
        .when()
            .get("/v2/lang/es")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageThreeLetterCode() {
        given()
            .baseUri("http://localhost:8080/rest")
        .when()
            .get("/v2/lang/eng")
        .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc() {
        given()
            .baseUri("http://localhost:8080/rest")
        .when()
            .get("/v2/regionalbloc/EU")
        .then()
            .statusCode(404);
    }
}