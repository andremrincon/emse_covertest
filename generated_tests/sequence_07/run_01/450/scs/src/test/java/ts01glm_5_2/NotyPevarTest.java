package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NotyPevarTest {

    @Before
    public void setUp() {
        String host = System.getProperty("server.host", "localhost");
        String port = System.getProperty("server.port", "8080");
        RestAssured.baseURI = "http://" + host + ":" + port;
    }

    @Test(timeout = 60000)
    public void testSubjectXPlusYEquals56AndAllTrueBranches() {
        given()
            .when()
                .get("/api/notypevar/28/world")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectHello7EqualsAndCompareToTrue() {
        given()
            .when()
                .get("/api/notypevar/7/i")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectAllFalseBranches() {
        given()
            .when()
                .get("/api/notypevar/0/a")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectInvalidIntegerInput() {
        given()
            .when()
                .get("/api/notypevar/abc/valid-string")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testSubjectMaxIntPlusOneOverflow() {
        given()
            .when()
                .get("/api/notypevar/2147483648/max-int-plus-one")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testSubjectBoundaryYNotGreaterThanX() {
        given()
            .when()
                .get("/api/notypevar/5/example-string")
            .then()
                .statusCode(200);
    }
}