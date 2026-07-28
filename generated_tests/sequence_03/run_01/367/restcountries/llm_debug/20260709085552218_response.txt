package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class LanguageTest {

    @Test(timeout = 60000)
    public void testSetIso639_1() {
        given()
            .baseUri("http://localhost:8080/rest")
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSetIso639_2() {
        given()
            .baseUri("http://localhost:8080/rest")
        .when()
            .get("/v1/name/{name}", "United%20States")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSetName() {
        given()
            .baseUri("http://localhost:8080/rest")
        .when()
            .get("/v1/lang/en")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSetNativeName() {
        given()
            .baseUri("http://localhost:8080/rest")
        .when()
            .get("/v1/currency/USD")
        .then()
            .statusCode(200);
    }
}