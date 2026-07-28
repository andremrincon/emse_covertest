package ts01qwen3_7_plus;

import org.junit.Before;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testUrlMatch() {

        String txt = "http://abc/def";


        Response response = given()
            .pathParam("txt", txt)
        .when()
            .get("/api/pat/{txt}");


        response.then().statusCode(404);
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
    public void testNoneMatch() {

        String txt = "hello";


        Response response = given()
            .pathParam("txt", txt)
        .when()
            .get("/api/pat/{txt}");


        response.then().statusCode(200).body(equalTo("none"));
    }
}