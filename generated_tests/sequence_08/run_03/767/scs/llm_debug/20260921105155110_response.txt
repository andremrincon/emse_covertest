package ts01qwen3_7_plus_3;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @Test(timeout = 60000)
    public void testUrlBranch() {
        given()
                .when()
                .get("/api/pat/{txt}", "http://test/test")
                .then()
                .statusCode(404);
    }

    @Test(timeout = 60000)
    public void testDateBranch() {
        given()
                .when()
                .get("/api/pat/{txt}", "mon01jan")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFpeBranch() {
        given()
                .when()
                .get("/api/pat/{txt}", "0.0e+00")
                .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneBranch() {
        given()
                .when()
                .get("/api/pat/{txt}", "hello")
                .then()
                .statusCode(200);
    }
}