package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class NotyPevarTest {

    @Test(timeout = 60000)
    public void testNotyPevarInitAndBranch1() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;

        Response response = given()
                .pathParam("i", 28)
                .pathParam("s", "world")
                .when()
                .get("/api/notypevar/{i}/{s}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevarBranch2() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;

        Response response = given()
                .pathParam("i", 7)
                .pathParam("s", "a")
                .when()
                .get("/api/notypevar/{i}/{s}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevarBranch3() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;

        Response response = given()
                .pathParam("i", 2)
                .pathParam("s", "a")
                .when()
                .get("/api/notypevar/{i}/{s}");

        response.then().statusCode(200);
    }
}