package ts01qwen3_7_plus_3;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    private final String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testSeeYou() {
        given().baseUri(baseUrl).when().get("/api/text2txt/see/you/x").then().statusCode(lessThan(300));
        Response response = given().baseUri(baseUrl).when().get("/api/text2txt/see/you/x");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSeeNotYou() {
        given().baseUri(baseUrl).when().get("/api/text2txt/see/me/x").then().statusCode(lessThan(300));
        Response response = given().baseUri(baseUrl).when().get("/api/text2txt/see/me/x");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testByTheWay() {
        given().baseUri(baseUrl).when().get("/api/text2txt/by/the/way").then().statusCode(lessThan(300));
        Response response = given().baseUri(baseUrl).when().get("/api/text2txt/by/the/way");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testByTheNotWay() {
        given().baseUri(baseUrl).when().get("/api/text2txt/by/the/home").then().statusCode(lessThan(300));
        Response response = given().baseUri(baseUrl).when().get("/api/text2txt/by/the/home");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testByNotTheWay() {
        given().baseUri(baseUrl).when().get("/api/text2txt/by/me/way").then().statusCode(lessThan(300));
        Response response = given().baseUri(baseUrl).when().get("/api/text2txt/by/me/way");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAre() {
        given().baseUri(baseUrl).when().get("/api/text2txt/are/x/y").then().statusCode(lessThan(300));
        Response response = given().baseUri(baseUrl).when().get("/api/text2txt/are/x/y");
        response.then().statusCode(200);
    }
}