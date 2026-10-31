package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class NotyPevarTest {

    @Before
    public void setUp() {
        String host = System.getenv().getOrDefault("APP_HOST", "localhost");
        String port = System.getenv().getOrDefault("APP_PORT", "8080");
        RestAssured.baseURI = "http://" + host + ":" + port;
    }

    @Test(timeout = 60000)
    public void testSubject_i0TrueBranch() {
        given()
            .when()
                .get("/api/notypevar/28/a")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testSubject_i1TrueAndI2TrueBranches() {
        given()
            .when()
                .get("/api/notypevar/7/world")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testSubject_i3FalseBranch() {
        given()
            .when()
                .get("/api/notypevar/3/a")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSubject_i2TrueI3FalseBranches() {
        given()
            .when()
                .get("/api/notypevar/3/world")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testInvalidIntegerParameter() {
        given()
            .when()
                .get("/api/notypevar/abc/test")
            .then()
                .statusCode(400);
    }

    @Test(timeout = 60000)
    public void testIntegerOverflowParameter() {
        given()
            .when()
                .get("/api/notypevar/2147483648/test")
            .then()
                .statusCode(400);
    }
}