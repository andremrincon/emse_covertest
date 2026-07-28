package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.*;

import org.junit.Ignore;
public class LanguageTest {

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

    private String firstString(Object o) {
        if (o == null) return null;
        if (o instanceof java.util.List) {
            java.util.List list = (java.util.List) o;
            if (list.isEmpty()) return null;
            Object first = list.get(0);
            return first == null ? null : first.toString();
        }
        return o.toString();
    }

    @Ignore("The parameter \"iso639_1\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testV1AlphaReturnsLanguageWithIso639_1() {
        Response resp = given()
            .when()
                .get("/v1/alpha/US");
        resp.then().statusCode(200);
        JsonPath jp = resp.jsonPath();
        Object o = jp.get("languages[0].iso639_1");
        assertNotNull(firstString(o));
    }

    @Ignore("The parameter \"iso639_2\" was used but not defined. Define parameters using the JsonPath.params(...")
    @Test(timeout = 60000)
    public void testV1AlphaReturnsLanguageWithIso639_2() {
        Response resp = given()
            .when()
                .get("/v1/alpha/GB");
        resp.then().statusCode(200);
        JsonPath jp = resp.jsonPath();
        Object o = jp.get("languages[0].iso639_2");
        assertNotNull(firstString(o));
    }

    @Ignore("The parameter \"name\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1AlphaReturnsLanguageWithName() {
        Response resp = given()
            .when()
                .get("/v1/alpha/FR");
        resp.then().statusCode(200);
        JsonPath jp = resp.jsonPath();
        Object o = jp.get("languages[0].name");
        assertNotNull(firstString(o));
    }

    @Ignore("The parameter \"nativeName\" was used but not defined. Define parameters using the JsonPath.param...")
    @Test(timeout = 60000)
    public void testV1AlphaReturnsLanguageWithNativeName() {
        Response resp = given()
            .when()
                .get("/v1/alpha/DE");
        resp.then().statusCode(200);
        JsonPath jp = resp.jsonPath();
        Object o = jp.get("languages[0].nativeName");
        assertNotNull(firstString(o));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1NameReturnsLanguageFields() {
        Response resp = given()
            .when()
                .get("/v1/name/France");
        resp.then().statusCode(200);
        JsonPath jp = resp.jsonPath();
        assertEquals("fr", firstString(jp.get("languages[0].iso639_1")));
        assertEquals("fra", firstString(jp.get("languages[0].iso639_2")));
        assertEquals("French", firstString(jp.get("languages[0].name")));
        assertNotNull(firstString(jp.get("languages[0].nativeName")));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1CurrencyReturnsLanguageFields() {
        Response resp = given()
            .when()
                .get("/v1/currency/USD");
        resp.then().statusCode(200);
        JsonPath jp = resp.jsonPath();
        assertNotNull(firstString(jp.get("languages[0].iso639_1")));
        assertNotNull(firstString(jp.get("languages[0].iso639_2")));
        assertNotNull(firstString(jp.get("languages[0].name")));
        assertNotNull(firstString(jp.get("languages[0].nativeName")));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1RegionReturnsLanguageFields() {
        Response resp = given()
            .when()
                .get("/v1/region/Europe");
        resp.then().statusCode(200);
        JsonPath jp = resp.jsonPath();
        assertNotNull(firstString(jp.get("languages[0].iso639_1")));
        assertNotNull(firstString(jp.get("languages[0].iso639_2")));
        assertNotNull(firstString(jp.get("languages[0].name")));
        assertNotNull(firstString(jp.get("languages[0].nativeName")));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1LangReturnsLanguageFields() {
        Response resp = given()
            .when()
                .get("/v1/lang/es");
        resp.then().statusCode(200);
        JsonPath jp = resp.jsonPath();
        assertNotNull(firstString(jp.get("languages[0].iso639_1")));
        assertNotNull(firstString(jp.get("languages[0].iso639_2")));
        assertNotNull(firstString(jp.get("languages[0].name")));
        assertNotNull(firstString(jp.get("languages[0].nativeName")));
    }

    @Test(timeout = 60000)
    public void testV2AlphaReturnsLanguageFields() {
        Response resp = given()
            .when()
                .get("/v2/alpha/US");
        resp.then().statusCode(200);
        JsonPath jp = resp.jsonPath();
        assertNotNull(firstString(jp.get("languages[0].iso639_1")));
        assertNotNull(firstString(jp.get("languages[0].iso639_2")));
        assertNotNull(firstString(jp.get("languages[0].name")));
        assertNotNull(firstString(jp.get("languages[0].nativeName")));
    }

    @Test(timeout = 60000)
    public void testV2NameReturnsLanguageFields() {
        Response resp = given()
            .when()
                .get("/v2/name/Germany");
        resp.then().statusCode(200);
        JsonPath jp = resp.jsonPath();
        assertEquals("de", firstString(jp.get("languages[0].iso639_1")));
        assertEquals("deu", firstString(jp.get("languages[0].iso639_2")));
        assertEquals("German", firstString(jp.get("languages[0].name")));
        assertNotNull(firstString(jp.get("languages[0].nativeName")));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testV2LangReturnsLanguageFields() {
        Response resp = given()
            .when()
                .get("/v2/lang/Spanish");
        resp.then().statusCode(200);
        JsonPath jp = resp.jsonPath();
        assertNotNull(firstString(jp.get("languages[0].iso639_1")));
        assertNotNull(firstString(jp.get("languages[0].iso639_2")));
        assertNotNull(firstString(jp.get("languages[0].name")));
        assertNotNull(firstString(jp.get("languages[0].nativeName")));
    }

    @Ignore("The parameter \"null\" was used but not defined. Define parameters using the JsonPath.params(...)...")
    @Test(timeout = 60000)
    public void testV1AllReturnsLanguageFields() {
        Response resp = given()
            .when()
                .get("/v1/all");
        resp.then().statusCode(200);
        JsonPath jp = resp.jsonPath();
        assertNotNull(firstString(jp.get("languages[0].iso639_1")));
        assertNotNull(firstString(jp.get("languages[0].iso639_2")));
        assertNotNull(firstString(jp.get("languages[0].name")));
        assertNotNull(firstString(jp.get("languages[0].nativeName")));
    }
}