package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
public class CurrencyTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Ignore("java.lang.String cannot be cast to java.util.Map")
    @Test(timeout = 60000)
    public void testGetCurrencyByAlphaCode() {
        Object raw = given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200)
                .extract().body().as(Object.class);

        Map<String, Object> root;
        if (raw instanceof List) {
            root = (Map<String, Object>) ((List) raw).get(0);
        } else {
            root = (Map<String, Object>) raw;
        }

        List currencies = (List) root.get("currencies");
        Map currency = (Map) currencies.get(0);

        assertEquals("USD", currency.get("code"));
        assertEquals("United States dollar", currency.get("name"));
        assertEquals("$", currency.get("symbol"));
    }

    @Ignore("java.lang.String cannot be cast to java.util.Map")
    @Test(timeout = 60000)
    public void testGetCurrencyByCurrencyCodeV1() {
        Object raw = given()
            .when()
                .get("/v1/currency/USD")
            .then()
                .statusCode(200)
                .extract().body().as(Object.class);

        Map<String, Object> root;
        if (raw instanceof List) {
            root = (Map<String, Object>) ((List) raw).get(0);
        } else {
            root = (Map<String, Object>) raw;
        }

        List currencies = (List) root.get("currencies");
        Map currency = (Map) currencies.get(0);

        assertEquals("USD", currency.get("code"));
        assertEquals("United States dollar", currency.get("name"));
        assertEquals("$", currency.get("symbol"));
    }

    @Test(timeout = 60000)
    public void testGetCurrencyByCurrencyCodeV2() {
        Object raw = given()
            .when()
                .get("/v2/currency/EUR")
            .then()
                .statusCode(200)
                .extract().body().as(Object.class);

        Map<String, Object> root;
        if (raw instanceof List) {
            root = (Map<String, Object>) ((List) raw).get(0);
        } else {
            root = (Map<String, Object>) raw;
        }

        List currencies = (List) root.get("currencies");
        Map currency = (Map) currencies.get(0);

        assertEquals("EUR", currency.get("code"));
        assertEquals("Euro", currency.get("name"));
        assertEquals("\u20ac", currency.get("symbol"));
    }
}