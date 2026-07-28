package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

public class CountryTranslationsTest {

    private final String baseUri = System.getProperty("base.url", "http://localhost:8080/rest");

    @Test(timeout = 60000)
    public void testSetDe() {
        given().baseUri(baseUri).when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().baseUri(baseUri).when().get("/v1/alpha/US");
        response.then().body("translations.de", notNullValue());
    }

    @Test(timeout = 60000)
    public void testSetEs() {
        given().baseUri(baseUri).when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().baseUri(baseUri).when().get("/v1/alpha/US");
        response.then().body("translations.es", notNullValue());
    }

    @Test(timeout = 60000)
    public void testSetFr() {
        given().baseUri(baseUri).when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().baseUri(baseUri).when().get("/v1/alpha/US");
        response.then().body("translations.fr", notNullValue());
    }

    @Test(timeout = 60000)
    public void testSetJa() {
        given().baseUri(baseUri).when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().baseUri(baseUri).when().get("/v1/alpha/US");
        response.then().body("translations.ja", notNullValue());
    }

    @Test(timeout = 60000)
    public void testSetIt() {
        given().baseUri(baseUri).when().get("/v1/all").then().statusCode(lessThan(300));
        Response response = given().baseUri(baseUri).when().get("/v1/alpha/US");
        response.then().body("translations.it", notNullValue());
    }
}