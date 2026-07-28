package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class NotyPevarTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("baseUrl");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testNotyPevarSumEquals56() {
        given()
            .when()
                .get("/api/notypevar/28/hello")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevarHello7Match() {
        given()
            .when()
                .get("/api/notypevar/7/zzz")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testNotyPevarGreaterThanX() {
        given()
            .when()
                .get("/api/notypevar/10/zzz")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }
}