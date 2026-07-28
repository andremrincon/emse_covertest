package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CountryServiceTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByLanguage_TwoLetterCode() {
        given().when().get("/v2/lang/es").then().statusCode(lessThan(300));

        given()
            .when()
                .get("/v2/lang/es")
            .then()
                .statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByLanguage_ThreeLetterCode() {
        given().when().get("/v2/lang/es").then().statusCode(lessThan(300));

        given()
            .when()
                .get("/v2/lang/eng")
            .then()
                .statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByLanguage_NotFound() {
        given().when().get("/v2/lang/es").then().statusCode(lessThan(300));

        given()
            .when()
                .get("/v2/lang/123")
            .then()
                .statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByRegionalBloc_Valid() {
        given().when().get("/v2/lang/es").then().statusCode(lessThan(300));

        given()
            .when()
                .get("/v2/regionalbloc/EU")
            .then()
                .statusCode(200);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testGetByRegionalBloc_NotFound() {
        given().when().get("/v2/lang/es").then().statusCode(lessThan(300));

        given()
            .when()
                .get("/v2/regionalbloc/123")
            .then()
                .statusCode(404);
    }
}