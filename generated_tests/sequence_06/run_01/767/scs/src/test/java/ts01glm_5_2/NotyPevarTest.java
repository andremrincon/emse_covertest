package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class NotyPevarTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testSubject_i0TrueBranch_iEquals28() {
        given()
            .when()
                .get("/api/notypevar/28/hello")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testSubject_i1TrueBranch_iEquals7() {
        given()
            .when()
                .get("/api/notypevar/7/hello")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testSubject_i2TrueBranch_sGreaterThanHello() {
        given()
            .when()
                .get("/api/notypevar/3/zzz")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testSubject_allFalseBranches_iLessThanOrEqual5_sLessThanHello() {
        given()
            .when()
                .get("/api/notypevar/3/a")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSubject_i3TrueOnly_iGreaterThan5_sEqualsHello() {
        given()
            .when()
                .get("/api/notypevar/6/hello")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testSubject_invalidIntegerInput_returns400() {
        given()
            .when()
                .get("/api/notypevar/abc/test")
            .then()
                .statusCode(400);
    }
}