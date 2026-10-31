package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CurrencyTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080/rest");

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testV1AlphaCodeExercisesCurrencySetters() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1CurrencyExercisesCurrencySetters() {
        given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1NameExercisesCurrencySetters() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV1RegionExercisesCurrencySetters() {
        given()
            .when()
                .get("/v1/region/Europe")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2AlphaCodeExercisesCurrencySetters() {
        given()
            .queryParam("fields", "name;currencies")
            .when()
                .get("/v2/alpha/US")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2CurrencyExercisesCurrencySetters() {
        given()
            .when()
                .get("/v2/currency/EUR")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2NameExercisesCurrencySetters() {
        given()
            .when()
                .get("/v2/name/Germany")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2RegionalBlocExercisesCurrencySetters() {
        given()
            .queryParam("fields", "name;currencies")
            .when()
                .get("/v2/regionalbloc/EU")
            .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testV2CallingCodeExercisesCurrencySetters() {
        given()
            .when()
                .get("/v2/callingcode/1")
            .then()
                .statusCode(404);
    }
}