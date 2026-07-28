package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class NotyPevarTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testSubject_i0_true_branch() {
        given()
            .pathParam("i", 28)
            .pathParam("s", "hello")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testSubject_i1_true_branch() {
        given()
            .pathParam("i", 7)
            .pathParam("s", "hello")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testSubject_i2_true_branch() {
        given()
            .pathParam("i", 0)
            .pathParam("s", "world")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testSubject_all_false_branches() {
        given()
            .pathParam("i", 0)
            .pathParam("s", "abc")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSubject_i3_true_branch() {
        given()
            .pathParam("i", 10)
            .pathParam("s", "world")
        .when()
            .get("/api/notypevar/{i}/{s}")
        .then()
            .statusCode(200)
            .body(equalTo("3"));
    }
}