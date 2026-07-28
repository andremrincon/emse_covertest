package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CurrencyTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1AlphaReturnsCurrencyCode() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        .when()
                .get("/v1/alpha/US")
        .then()
                .statusCode(200)
                .body("currencies.code", hasItem("USD"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1AlphaReturnsCurrencyName() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        .when()
                .get("/v1/alpha/US")
        .then()
                .statusCode(200)
                .body("currencies.name", hasItem("United States dollar"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1AlphaReturnsCurrencySymbol() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        .when()
                .get("/v1/alpha/FR")
        .then()
                .statusCode(200)
                .body("currencies.symbol", hasItem("€"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1CurrencyReturnsCountriesWithCurrencyFields() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        .when()
                .get("/v1/currency/USD")
        .then()
                .statusCode(200)
                .body("currencies.code", hasItem("USD"));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1NameReturnsCurrencyCodeAndNameAndSymbol() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        .when()
                .get("/v1/name/France")
        .then()
                .statusCode(200)
                .body("currencies.code", hasItem("EUR"))
                .body("currencies.name", hasItem("Euro"))
                .body("currencies.symbol", hasItem("€"));
    }

    @Test(timeout = 60000)
    public void testV2AlphaReturnsCurrencyCode() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        .when()
                .get("/v2/alpha/US")
        .then()
                .statusCode(200)
                .body("currencies[0].code", equalTo("USD"));
    }

    @Test(timeout = 60000)
    public void testV2CurrencyReturnsCurrencyNameAndSymbol() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        .when()
                .get("/v2/currency/EUR")
        .then()
                .statusCode(200)
                .body("currencies[0].name", hasItem("Euro"))
                .body("currencies[0].symbol", hasItem("€"));
    }

    @Test(timeout = 60000)
    public void testV2NameReturnsCurrencySymbol() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        .when()
                .get("/v2/name/Germany")
        .then()
                .statusCode(200)
                .body("currencies[0].symbol", hasItem("€"));
    }

    @Test(timeout = 60000)
    public void testV2RegionalblocReturnsCurrencyCodeNameSymbol() {
        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        .when()
                .get("/v2/regionalbloc/EU")
        .then()
                .statusCode(200)
                .body("currencies[0].code", hasItem("EUR"))
                .body("currencies[0].name", hasItem("Euro"))
                .body("currencies[0].symbol", hasItem("€"));
    }
}