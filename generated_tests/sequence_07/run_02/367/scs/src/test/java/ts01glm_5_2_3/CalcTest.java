package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalcTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testPlusOperation() {
        RestAssured.given()
            .when()
                .get("/api/calc/plus/15.5/4.5")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDivideOperation() {
        RestAssured.given()
            .when()
                .get("/api/calc/divide/100/4")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSqrtOperation() {
        RestAssured.given()
            .when()
                .get("/api/calc/sqrt/16/0")
            .then()
                .statusCode(200);
    }
}