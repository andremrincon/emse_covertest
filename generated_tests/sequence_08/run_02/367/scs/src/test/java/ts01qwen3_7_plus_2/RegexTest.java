package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class RegexTest {

    @Before
    public void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testUrlBranch() {
        given()
            .pathParam("txt", "http://a/b")
            .when()
            .get("/api/pat/{txt}")
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testDateBranch() {
        given()
            .pathParam("txt", "mon01jan")
            .when()
            .get("/api/pat/{txt}")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFpeBranch() {
        given()
            .pathParam("txt", "1.2e+34")
            .when()
            .get("/api/pat/{txt}")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneBranch() {
        given()
            .pathParam("txt", "xyz")
            .when()
            .get("/api/pat/{txt}")
            .then()
            .statusCode(200);
    }
}