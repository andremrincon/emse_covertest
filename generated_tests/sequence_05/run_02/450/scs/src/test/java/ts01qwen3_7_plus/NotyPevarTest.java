package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class NotyPevarTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("baseURI", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testNotyPevarPath1() {
        given()
        .when()
            .get("/api/notypevar/28/a")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevarPath2() {
        given()
        .when()
            .get("/api/notypevar/7/world")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevarPath3() {
        given()
        .when()
            .get("/api/notypevar/2/a")
        .then()
            .statusCode(200);
    }
}