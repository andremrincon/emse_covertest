package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class GammqTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
    }

    @Test(timeout = 60000)
    public void testGcfPath() {
        given().when().get("/api/gammq/5.5/1000.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserPath() {
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserXZero() {
        given().when().get("/api/gammq/5.5/0.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidA() {
        given().when().get("/api/gammq/-1.0/2.3").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testInvalidX() {
        given().when().get("/api/gammq/5.5/-1.0").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testInvalidType() {
        given().when().get("/api/gammq/abc/2.3").then().statusCode(400);
    }
}