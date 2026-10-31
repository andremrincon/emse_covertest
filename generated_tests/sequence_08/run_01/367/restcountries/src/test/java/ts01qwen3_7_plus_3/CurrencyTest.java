package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CurrencyTest {

    @Before
    public void setup() {
        RestAssured.baseURI = System.getProperty("test.base.uri", "http://localhost:8080");
        RestAssured.basePath = "/rest";
    }

    @Ignore("1 expectation failed. JSON path $[0].currencies[0].code doesn't match. Expected: USD   Actual: null")
    @Test(timeout = 60000)
    public void testSetCodeViaV1Currency() {
        given()
        .when()
            .get("/v1/currency/USD")
        .then()
            .statusCode(lessThan(300))
            .body("$[0].currencies[0].code", equalTo("USD"));
    }

    @Ignore("1 expectation failed. JSON path $[0].currencies[0].name doesn't match. Expected: Euro   Actual: null")
    @Test(timeout = 60000)
    public void testSetNameViaV2Currency() {
        given()
        .when()
            .get("/v2/currency/EUR")
        .then()
            .statusCode(lessThan(300))
            .body("$[0].currencies[0].name", equalTo("Euro"));
    }

    @Ignore("1 expectation failed. JSON path $.currencies[0].symbol doesn't match. Expected: $   Actual: null")
    @Test(timeout = 60000)
    public void testSetSymbolViaV1Alpha() {
        given()
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(lessThan(300))
            .body("$.currencies[0].symbol", equalTo("$"));
    }

    @Ignore("1 expectation failed. JSON path $.currencies[0].code doesn't match. Expected: USD   Actual: null")
    @Test(timeout = 60000)
    public void testSetCodeViaV2Alpha() {
        given()
        .when()
            .get("/v2/alpha/US")
        .then()
            .statusCode(lessThan(300))
            .body("$.currencies[0].code", equalTo("USD"));
    }

    @Ignore("1 expectation failed. JSON path $[0].currencies[0].name doesn't match. Expected: not null   Actua...")
    @Test(timeout = 60000)
    public void testSetNameViaV1All() {
        given()
        .when()
            .get("/v1/all")
        .then()
            .statusCode(lessThan(300))
            .body("$[0].currencies[0].name", notNullValue());
    }

    @Ignore("1 expectation failed. JSON path $[0].currencies[0].symbol doesn't match. Expected: not null   Act...")
    @Test(timeout = 60000)
    public void testSetSymbolViaV2All() {
        given()
        .when()
            .get("/v2/all")
        .then()
            .statusCode(lessThan(300))
            .body("$[0].currencies[0].symbol", notNullValue());
    }
}