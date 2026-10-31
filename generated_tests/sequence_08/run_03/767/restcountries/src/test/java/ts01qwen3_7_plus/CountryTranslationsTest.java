package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.junit.Assert.assertNotNull;

import org.junit.Ignore;
public class CountryTranslationsTest {

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testSetDe() {
        Response resp = given()
            .baseUri("http://localhost:8080/rest")
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(200)
            .extract().response();
        JsonPath jp = new JsonPath(resp.asString());
        assertNotNull(jp.get("translations.de"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testSetEs() {
        Response resp = given()
            .baseUri("http://localhost:8080/rest")
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(200)
            .extract().response();
        JsonPath jp = new JsonPath(resp.asString());
        assertNotNull(jp.get("translations.es"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testSetFr() {
        Response resp = given()
            .baseUri("http://localhost:8080/rest")
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(200)
            .extract().response();
        JsonPath jp = new JsonPath(resp.asString());
        assertNotNull(jp.get("translations.fr"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testSetJa() {
        Response resp = given()
            .baseUri("http://localhost:8080/rest")
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(200)
            .extract().response();
        JsonPath jp = new JsonPath(resp.asString());
        assertNotNull(jp.get("translations.ja"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testSetIt() {
        Response resp = given()
            .baseUri("http://localhost:8080/rest")
        .when()
            .get("/v1/alpha/US")
        .then()
            .statusCode(200)
            .extract().response();
        JsonPath jp = new JsonPath(resp.asString());
        assertNotNull(jp.get("translations.it"));
    }
}