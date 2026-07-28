package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class TitleTest {

    private static String baseUrl;

    @BeforeClass
    public static void setup() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testMaleWithProfTitle() {
        String sex = "male";
        String title = "prof";
        Response response = given().when().get("/api/title/" + sex + "/" + title);
        assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFemaleWithProfTitle() {
        String sex = "female";
        String title = "prof";
        Response response = given().when().get("/api/title/" + sex + "/" + title);
        assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testNoneWithProfTitle() {
        String sex = "none";
        String title = "prof";
        Response response = given().when().get("/api/title/" + sex + "/" + title);
        assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testMaleWithInvalidTitle() {
        String sex = "male";
        String title = "invalid_title";
        Response response = given().when().get("/api/title/" + sex + "/" + title);
        assertEquals(200, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testInvalidSexWithProfTitle() {
        String sex = "alien";
        String title = "prof";
        Response response = given().when().get("/api/title/" + sex + "/" + title);
        assertEquals(200, response.getStatusCode());
    }
}