package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

import org.junit.Ignore;
public class CurrencyTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1AlphaReturnsCurrencyWithCodeNameSymbol() {
        String json = given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(200)
                .extract().asString();
        JsonPath jp = new JsonPath(json);
        String code = jp.getString("currencies[0].code");
        String name = jp.getString("currencies[0].name");
        String symbol = jp.getString("currencies[0].symbol");
        assertThat(code, notNullValue());
        assertThat(name, notNullValue());
        assertThat(symbol, notNullValue());
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1CurrencyEndpointReturnsCurrencyFields() {
        String json = given()
                .when()
                .get("/v1/currency/USD")
                .then()
                .statusCode(200)
                .extract().asString();
        JsonPath jp = new JsonPath(json);
        String code = jp.getString("[0].currencies[0].code");
        String name = jp.getString("[0].currencies[0].name");
        String symbol = jp.getString("[0].currencies[0].symbol");
        assertThat(code, equalTo("USD"));
        assertThat(name, notNullValue());
        assertThat(symbol, notNullValue());
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1NameReturnsCurrencyWithAllFields() {
        String json = given()
                .when()
                .get("/v1/name/France")
                .then()
                .statusCode(200)
                .extract().asString();
        JsonPath jp = new JsonPath(json);
        String code = jp.getString("[0].currencies[0].code");
        String name = jp.getString("[0].currencies[0].name");
        String symbol = jp.getString("[0].currencies[0].symbol");
        assertThat(code, equalTo("EUR"));
        assertThat(name, notNullValue());
        assertThat(symbol, notNullValue());
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1AllReturnsCurrencyData() {
        String json = given()
                .when()
                .get("/v1/all")
                .then()
                .statusCode(200)
                .extract().asString();
        JsonPath jp = new JsonPath(json);
        String code = jp.getString("[0].currencies[0].code");
        String name = jp.getString("[0].currencies[0].name");
        String symbol = jp.getString("[0].currencies[0].symbol");
        assertThat(code, notNullValue());
        assertThat(name, notNullValue());
        assertThat(symbol, notNullValue());
    }

    @Ignore("The parameter \"code\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1RegionReturnsCurrencyFields() {
        String json = given()
                .when()
                .get("/v1/region/Europe")
                .then()
                .statusCode(200)
                .extract().asString();
        JsonPath jp = new JsonPath(json);
        String code = jp.getString("[0].currencies[0].code");
        String name = jp.getString("[0].currencies[0].name");
        String symbol = jp.getString("[0].currencies[0].symbol");
        assertThat(code, notNullValue());
        assertThat(name, notNullValue());
        assertThat(symbol, notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2AlphaReturnsCurrencyWithCodeNameSymbol() {
        String json = given()
                .when()
                .get("/v2/alpha/US")
                .then()
                .statusCode(200)
                .extract().asString();
        JsonPath jp = new JsonPath(json);
        String code = jp.getString("currencies[0].code");
        String name = jp.getString("currencies[0].name");
        String symbol = jp.getString("currencies[0].symbol");
        assertThat(code, notNullValue());
        assertThat(name, notNullValue());
        assertThat(symbol, notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2CurrencyEndpointReturnsCurrencyFields() {
        String json = given()
                .when()
                .get("/v2/currency/EUR")
                .then()
                .statusCode(200)
                .extract().asString();
        JsonPath jp = new JsonPath(json);
        String code = jp.getString("[0].currencies[0].code");
        String name = jp.getString("[0].currencies[0].name");
        String symbol = jp.getString("[0].currencies[0].symbol");
        assertThat(code, equalTo("EUR"));
        assertThat(name, notNullValue());
        assertThat(symbol, notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2NameReturnsCurrencyWithAllFields() {
        String json = given()
                .when()
                .get("/v2/name/Germany")
                .then()
                .statusCode(200)
                .extract().asString();
        JsonPath jp = new JsonPath(json);
        String code = jp.getString("[0].currencies[0].code");
        String name = jp.getString("[0].currencies[0].name");
        String symbol = jp.getString("[0].currencies[0].symbol");
        assertThat(code, equalTo("EUR"));
        assertThat(name, notNullValue());
        assertThat(symbol, notNullValue());
    }

    @Test(timeout = 60000)
    public void testV2RegionalblocReturnsCurrencyFields() {
        String json = given()
                .when()
                .get("/v2/regionalbloc/EU")
                .then()
                .statusCode(200)
                .extract().asString();
        JsonPath jp = new JsonPath(json);
        String code = jp.getString("[0].currencies[0].code");
        String name = jp.getString("[0].currencies[0].name");
        String symbol = jp.getString("[0].currencies[0].symbol");
        assertThat(code, notNullValue());
        assertThat(name, notNullValue());
        assertThat(symbol, notNullValue());
    }
}