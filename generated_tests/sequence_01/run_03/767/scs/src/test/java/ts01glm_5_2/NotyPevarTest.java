package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class NotyPevarTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSubject_allConditionsFalse_returnsZero() {
        given()
            .when()
                .get("/api/notypevar/0/a")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSubject_i0True_othersOverwritten_returnsThree() {
        given()
            .when()
                .get("/api/notypevar/28/a")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testSubject_i1AndI2AndI3True_returnsThree() {
        given()
            .when()
                .get("/api/notypevar/7/zzz")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }
}