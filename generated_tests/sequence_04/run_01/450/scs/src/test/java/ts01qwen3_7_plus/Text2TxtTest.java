package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Text2TxtTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.trim().isEmpty()) {
            baseUrl = System.getProperty("baseUrl");
        }
        if (baseUrl != null && !baseUrl.trim().isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testAre() {
        given().when().get("/api/text2txt/are/x/y").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSeeYou() {
        given().when().get("/api/text2txt/see/you/y").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSeeNotYou() {
        given().when().get("/api/text2txt/see/x/y").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testByTheWay() {
        given().when().get("/api/text2txt/by/the/way").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testByTheNotWay() {
        given().when().get("/api/text2txt/by/the/x").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTwo() {
        given().when().get("/api/text2txt/two/x/y").then().statusCode(200);
    }
}