package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PatTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testReverseMethodCoverage() {
        given()
                .when()
                .get("/api/pat/ABABCABAB/ABAB")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInitCoverage() {
        given()
                .when()
                .get("/api/pat/banana/a")
                .then()
                .statusCode(200);
    }
}