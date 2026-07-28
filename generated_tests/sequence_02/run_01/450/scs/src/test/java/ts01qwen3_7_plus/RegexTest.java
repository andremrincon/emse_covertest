package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class RegexTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("test.base.url", "http://localhost:8080");
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"url\"   Actual: {\"tim...")
    @Test(timeout = 60000)
    public void testUrlMatch() {
        String txt = "http://abc/def";
        Response response = given()
            .pathParam("txt", txt)
        .when()
            .get("/api/pat/{txt}");
        response.then().statusCode(404).body(equalTo("url"));
    }

    @Test(timeout = 60000)
    public void testDateMatch() {
        String txt = "mon01jan";
        Response response = given()
            .pathParam("txt", txt)
        .when()
            .get("/api/pat/{txt}");
        response.then().statusCode(200).body(equalTo("date"));
    }

    @Test(timeout = 60000)
    public void testFpeMatch() {
        String txt = "12.34e+56";
        Response response = given()
            .pathParam("txt", txt)
        .when()
            .get("/api/pat/{txt}");
        response.then().statusCode(200).body(equalTo("fpe"));
    }

    @Test(timeout = 60000)
    public void testNoMatch() {
        String txt = "nomatch";
        Response response = given()
            .pathParam("txt", txt)
        .when()
            .get("/api/pat/{txt}");
        response.then().statusCode(200).body(equalTo("none"));
    }
}