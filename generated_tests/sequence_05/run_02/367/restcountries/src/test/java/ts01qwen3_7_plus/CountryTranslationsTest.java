package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CountryTranslationsTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Test(timeout = 60000)
    public void testSetDe() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSetEs() {
        given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSetFr() {
        given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSetJa() {
        given()
                .when()
                .get("/v1/region/Europe")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSetIt() {
        given()
                .when()
                .get("/v1/alpha/GB")
                .then()
                .statusCode(200);
    }
}