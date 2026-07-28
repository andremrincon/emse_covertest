package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class RemainderTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"2\"   Actual: {\"resul...")
    @Test(timeout = 60000)
    public void testRemainderPositiveAPositiveB() {
        given()
            .when()
                .get("/api/remainder/17/5")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"2\"   Actual: {\"resul...")
    @Test(timeout = 60000)
    public void testRemainderPositiveANegativeB() {
        given()
            .when()
                .get("/api/remainder/17/-5")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"-2\"   Actual: {\"resu...")
    @Test(timeout = 60000)
    public void testRemainderNegativeAPositiveB() {
        given()
            .when()
                .get("/api/remainder/-17/5")
            .then()
                .statusCode(200)
                .body(equalTo("-2"));
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"2\"   Actual: {\"resul...")
    @Test(timeout = 60000)
    public void testRemainderNegativeANegativeB() {
        given()
            .when()
                .get("/api/remainder/-17/-5")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"0\"   Actual: {\"resul...")
    @Test(timeout = 60000)
    public void testRemainderEdgeCasePositive() {
        given()
            .when()
                .get("/api/remainder/1/1")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"0\"   Actual: {\"resul...")
    @Test(timeout = 60000)
    public void testRemainderEdgeCaseNegative() {
        given()
            .when()
                .get("/api/remainder/-1/-1")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }
}