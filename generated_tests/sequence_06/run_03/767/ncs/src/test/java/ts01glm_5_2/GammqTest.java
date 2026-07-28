package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GammqTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testGserNormalPath() {
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGcfNormalPath() {
        given().when().get("/api/gammq/0.001/1000.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserXZero() {
        given().when().get("/api/gammq/5.5/0.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExeInvalidXNegative() {
        given().when().get("/api/gammq/5.5/-1.0").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExeInvalidAZero() {
        given().when().get("/api/gammq/0.0/2.3").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExeInvalidANegative() {
        given().when().get("/api/gammq/-1.0/2.3").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGcfAnotherCombo() {
        given().when().get("/api/gammq/1.0/10.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGserLargeAExceedsITMAX() {
        given().when().get("/api/gammq/1000/1000.5").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGcfLargeA() {
        given().when().get("/api/gammq/200/300").then().statusCode(200);
    }
}