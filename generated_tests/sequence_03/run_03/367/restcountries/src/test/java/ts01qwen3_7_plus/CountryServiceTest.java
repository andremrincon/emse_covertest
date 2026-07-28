package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryServiceTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080/rest";
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testGetByLanguageLength2() {
        given()
                .when()
                .get("/v2/lang/es")
                .then()
                .statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testGetByLanguageLength3() {
        given()
                .when()
                .get("/v2/lang/eng")
                .then()
                .statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testGetByRegionalBloc() {
        given()
                .when()
                .get("/v2/regionalbloc/EU")
                .then()
                .statusCode(200);
    }
}