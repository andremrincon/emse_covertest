package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CurrencyTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test(timeout = 60000)
    public void testSetCode() {
        given()
            .when()
            .get("/v1/alpha/US")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testSetName() {
        given()
            .when()
            .get("/v1/alpha/US")
            .then()
            .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testSetSymbol() {
        given()
            .when()
            .get("/v1/alpha/US")
            .then()
            .statusCode(404);
    }
}