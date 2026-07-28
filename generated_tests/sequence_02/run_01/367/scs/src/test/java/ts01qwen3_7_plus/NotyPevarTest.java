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
    public void testSubject_branchI0True() {
        given()
            .when()
                .get("/api/notypevar/28/world")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testSubject_branchI1True() {
        given()
            .when()
                .get("/api/notypevar/7/abc")
            .then()
                .statusCode(200)
                .body(equalTo("3"));
    }

    @Test(timeout = 60000)
    public void testSubject_branchI2True() {
        given()
            .when()
                .get("/api/notypevar/0/world")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testSubject_allBranchesFalse() {
        given()
            .when()
                .get("/api/notypevar/0/abc")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }
}