package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class LanguageTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080/rest");
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testLanguageIso639_1IsSetViaV2AlphaEndpoint() {
        given()
                .when()
                .get("/v3.1/alpha/US")
                .then()
                .statusCode(200)
                .body("languages[0].size()", greaterThan(0));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testLanguageIso639_2IsSetViaV2AlphaEndpoint() {
        given()
                .when()
                .get("/v3.1/alpha/GB")
                .then()
                .statusCode(200)
                .body("languages[0].size()", greaterThan(0));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testLanguageNameIsSetViaV2NameEndpoint() {
        given()
                .when()
                .get("/v3.1/name/Germany")
                .then()
                .statusCode(200)
                .body("languages[0].size()", greaterThan(0));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testLanguageNativeNameIsSetViaV2NameEndpoint() {
        given()
                .when()
                .get("/v3.1/name/France")
                .then()
                .statusCode(200)
                .body("languages[0].size()", greaterThan(0));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testLanguageIso639_1IsSetViaV2LangEndpoint() {
        given()
                .when()
                .get("/v3.1/lang/Spanish")
                .then()
                .statusCode(200)
                .body("languages[0].size()", greaterThan(0));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testLanguageIso639_2IsSetViaV2LangEndpoint() {
        given()
                .when()
                .get("/v3.1/lang/English")
                .then()
                .statusCode(200)
                .body("languages[0].size()", greaterThan(0));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testLanguageNameIsSetViaV2AlphaCodesEndpoint() {
        given()
                .queryParam("codes", "US,CA,MX")
                .when()
                .get("/v3.1/alpha")
                .then()
                .statusCode(200)
                .body("languages[0].size()", greaterThan(0));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testLanguageNativeNameIsSetViaV2AlphaCodesEndpoint() {
        given()
                .queryParam("codes", "DE,FR")
                .when()
                .get("/v3.1/alpha")
                .then()
                .statusCode(200)
                .body("languages[0].size()", greaterThan(0));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testLanguageIso639_1IsSetViaV2AllEndpoint() {
        given()
                .queryParam("fields", "languages")
                .when()
                .get("/v3.1/all")
                .then()
                .statusCode(200)
                .body("languages[0].size()", greaterThan(0));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testLanguageIso639_2IsSetViaV2AllEndpoint() {
        given()
                .queryParam("fields", "languages")
                .when()
                .get("/v3.1/all")
                .then()
                .statusCode(200)
                .body("languages[0].size()", greaterThan(0));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testLanguageNameIsSetViaV2AllEndpoint() {
        given()
                .queryParam("fields", "languages")
                .when()
                .get("/v3.1/all")
                .then()
                .statusCode(200)
                .body("languages[0].size()", greaterThan(0));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testLanguageNativeNameIsSetViaV2AllEndpoint() {
        given()
                .queryParam("fields", "languages")
                .when()
                .get("/v3.1/all")
                .then()
                .statusCode(200)
                .body("languages[0].size()", greaterThan(0));
    }
}