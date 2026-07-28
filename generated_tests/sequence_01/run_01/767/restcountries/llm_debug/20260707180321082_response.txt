package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CountryServiceTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testGetByLanguageTwoChar() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));

        given()
                .when().get("/v2/lang/es")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByLanguageThreeChar() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));

        given()
                .when().get("/v2/lang/eng")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetByRegionalBloc() {
        given().when().get("/v2/all").then().statusCode(lessThan(300));

        given()
                .when().get("/v2/regionalbloc/EU")
                .then()
                .statusCode(200);
    }
}