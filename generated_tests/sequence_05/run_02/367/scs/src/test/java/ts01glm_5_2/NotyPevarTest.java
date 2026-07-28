package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class NotyPevarTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testSubjectWhenXPlusYEquals56() {
        given()
            .when()
                .get("/api/notypevar/28/hello")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectWhenXsPlusYEqualsHello7() {
        given()
            .when()
                .get("/api/notypevar/7/hello")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectWhenXsCompareToSLessThanZero() {
        given()
            .when()
                .get("/api/notypevar/0/world")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectWhenAllConditionsFalse() {
        given()
            .when()
                .get("/api/notypevar/3/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectWhenXPlusYEquals56AndXsCompareToSLessThanZero() {
        given()
            .when()
                .get("/api/notypevar/28/world")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectWhenYGreaterThanXAndAllStringConditionsFalse() {
        given()
            .when()
                .get("/api/notypevar/10/a")
            .then()
                .statusCode(200);
    }
}