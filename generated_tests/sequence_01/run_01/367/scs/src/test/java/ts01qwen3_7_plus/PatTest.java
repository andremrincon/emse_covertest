package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    private static String encode(String s) {
        return s.replace(" ", "%20");
    }

    @Test(timeout = 60000)
    public void testNeitherPatNorReverseFound() {
        Response response = given()
            .when()
            .get("/api/pat/{txt}/{pat}", encode("hello world"), "xyz");

        response.then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseNotFound() {
        Response response = given()
            .when()
            .get("/api/pat/{txt}/{pat}", encode("hello abc world"), "abc");

        response.then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatNotFound() {
        Response response = given()
            .when()
            .get("/api/pat/{txt}/{pat}", encode("hello cba world"), "abc");

        response.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testPatAndReverseFoundNotAdjacent() {
        Response response = given()
            .when()
            .get("/api/pat/{txt}/{pat}", encode("xyz abc cba"), "abc");

        response.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testPatAndReverseFoundAdjacent() {
        Response response = given()
            .when()
            .get("/api/pat/{txt}/{pat}", encode("w abccba"), "abc");

        response.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testReverseAndPatFoundAdjacent() {
        Response response = given()
            .when()
            .get("/api/pat/{txt}/{pat}", encode("q cbaabc"), "abc");

        response.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testReverseAndPatFoundNotAdjacent() {
        Response response = given()
            .when()
            .get("/api/pat/{txt}/{pat}", encode("m cba abc"), "abc");

        response.then().body(equalTo("2"));
    }
}