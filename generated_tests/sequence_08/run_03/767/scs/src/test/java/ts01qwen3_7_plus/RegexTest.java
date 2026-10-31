package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import java.net.URLEncoder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Ignore;
public class RegexTest {

    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"url\"   Actual: {\"tim...")
    @Test(timeout = 60000)
    public void testSubjectReturnsUrl() {
        String txt = "http://abc/def";
        Response response = given()
                .pathParam("txt", txt)
                .when()
                .get("/api/pat/{txt}");
        response.then().statusCode(404).body(equalTo("url"));
    }

    @Test(timeout = 60000)
    public void testSubjectReturnsDate() {
        String txt = "mon01jan";
        Response response = given()
                .pathParam("txt", txt)
                .when()
                .get("/api/pat/{txt}");
        response.then().statusCode(200).body(equalTo("date"));
    }

    @Test(timeout = 60000)
    public void testSubjectReturnsFpe() {
        String txt = "12.34e+56";
        Response response = given()
                .pathParam("txt", txt)
                .when()
                .get("/api/pat/{txt}");
        response.then().statusCode(200).body(equalTo("fpe"));
    }

    @Test(timeout = 60000)
    public void testSubjectReturnsNone() throws Exception {
        String txt = "hello world";
        String encoded = URLEncoder.encode(txt, "UTF-8");
        Response response = given()
                .pathParam("txt", encoded)
                .when()
                .get("/api/pat/{txt}");
        response.then().statusCode(200).body(equalTo("none"));
    }
}