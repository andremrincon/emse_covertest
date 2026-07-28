package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = baseUrl != null ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testSubjectMatchesUrl() {
        Response response = given()
            .pathParam("txt", "http://abc/def")
        .when()
            .get("/api/pat/{txt}");
        response.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testSubjectMatchesDate() {
        Response response = given()
            .pathParam("txt", "mon01jan")
        .when()
            .get("/api/pat/{txt}");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectMatchesFpe() {
        Response response = given()
            .pathParam("txt", "12.34e-56")
        .when()
            .get("/api/pat/{txt}");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectMatchesNone() {
        Response response = given()
            .pathParam("txt", "invalid-input")
        .when()
            .get("/api/pat/{txt}");
        response.then().statusCode(200);
    }
}