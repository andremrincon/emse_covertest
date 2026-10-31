package ts01glm_5_2_3;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class NotyPevarTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testBranchI0TrueXPlusYEquals56() {
        given()
            .when()
                .get("/api/notypevar/28/world")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBranchI1TrueHelloYEqualsHello7() {
        given()
            .when()
                .get("/api/notypevar/7/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBranchI3FalseYNotGreaterThanX() {
        given()
            .when()
                .get("/api/notypevar/5/zzzz")
            .then()
                .statusCode(200);
    }
}