package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null) {
            baseUrl = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testNotyPevar_AllFalseBranches() {
        given()
            .when()
                .get("/api/notypevar/0/a")
            .then()
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_Condition1And3True() {
        given()
            .when()
                .get("/api/notypevar/28/world")
            .then()
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevar_Condition2True() {
        given()
            .when()
                .get("/api/notypevar/7/a")
            .then()
                .body(equalTo("3"));
    }
}