package ts01qwen3_7_plus_2;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class CountryTranslationsTest {

    @Test(timeout = 60000)
    public void testSettersViaAlphaCode() {
        given()
            .baseUri("http://localhost:8080/rest")
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSettersViaName() {
        given()
            .baseUri("http://localhost:8080/rest")
        .when()
            .get("/v1/name/France")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSettersViaCurrency() {
        given()
            .baseUri("http://localhost:8080/rest")
        .when()
            .get("/v1/currency/USD")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSettersViaCapital() {
        given()
            .baseUri("http://localhost:8080/rest")
        .when()
            .get("/v1/capital/London")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSettersViaRegion() {
        given()
            .baseUri("http://localhost:8080/rest")
        .when()
            .get("/v1/region/Europe")
        .then()
            .statusCode(200);
    }
}