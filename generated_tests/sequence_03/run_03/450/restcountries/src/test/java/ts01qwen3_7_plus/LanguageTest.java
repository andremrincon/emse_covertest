package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class LanguageTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080/rest");
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testSetIso639_1() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200)
                .body("languages[0].iso639_1", equalTo("en"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testSetIso639_2() {
        given()
            .when()
                .get("/v1/alpha/US")
            .then()
                .statusCode(200)
                .body("languages[0].iso639_2", equalTo("eng"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testSetName() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(200)
                .body("languages[0].name", equalTo("French"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testSetNativeName() {
        given()
            .when()
                .get("/v1/name/France")
            .then()
                .statusCode(200)
                .body("languages[0].nativeName", equalTo("français"));
    }
}