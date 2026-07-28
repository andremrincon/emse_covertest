package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class NotyPevarTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testNotyPevarBranch1() {
        Response response = given()
                .when()
                .get("/api/notypevar/28/a");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevarBranch2() {
        Response response = given()
                .when()
                .get("/api/notypevar/7/a");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNotyPevarBranch3() {
        Response response = given()
                .when()
                .get("/api/notypevar/1/z");
        response.then().statusCode(200);
    }
}