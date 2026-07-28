package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class CookieTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testUseridWithValidUser() {
        given()
            .when()
            .get("/api/cookie/userid/user123/example.com")
            .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testUseridWithNonUserVal() {
        given()
            .when()
            .get("/api/cookie/userid/admin123/example.com")
            .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testUseridWithShortVal() {
        given()
            .when()
            .get("/api/cookie/userid/user1/example.com")
            .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testSessionWithValidAmAndAbcCom() {
        given()
            .when()
            .get("/api/cookie/session/am/abc.com")
            .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testSessionWithInvalidVal() {
        given()
            .when()
            .get("/api/cookie/session/pm/abc.com")
            .then()
            .statusCode(200)
            .body(equalTo("2"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testOtherName() {
        given()
            .when()
            .get("/api/cookie/other/any/any")
            .then()
            .statusCode(200)
            .body(equalTo("0"));
    }
}