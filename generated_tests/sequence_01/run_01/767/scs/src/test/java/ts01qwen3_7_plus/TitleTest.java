package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

public class TitleTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testMaleValidTitle() {
        RestAssured.given().when().get("/api/title/male/mr").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleInvalidTitle() {
        RestAssured.given().when().get("/api/title/male/mrs").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleValidTitle() {
        RestAssured.given().when().get("/api/title/female/mrs").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleInvalidTitle() {
        RestAssured.given().when().get("/api/title/female/mr").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneValidTitle() {
        RestAssured.given().when().get("/api/title/none/dr").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneInvalidTitle() {
        RestAssured.given().when().get("/api/title/none/mr").then().statusCode(200);
    }
}