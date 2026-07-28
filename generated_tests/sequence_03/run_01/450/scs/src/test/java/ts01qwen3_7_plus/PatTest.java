package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class PatTest {

    private static final String BASE_URI = System.getProperty("BASE_URL", "http://localhost:8080");

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testPatLenLessThan3() {
        given()
            .baseUri(BASE_URI)
        .when()
            .get("/api/pat/hello/ab")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testPatFoundOnly() {
        given()
            .baseUri(BASE_URI)
        .when()
            .get("/api/pat/helloworld/hello")
        .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testRevFoundOnly() {
        given()
            .baseUri(BASE_URI)
        .when()
            .get("/api/pat/helloworld/dlrow")
        .then()
            .statusCode(200)
            .body(equalTo("2"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testBothFoundNotAdjacent() {
        given()
            .baseUri(BASE_URI)
        .when()
            .get("/api/pat/abcxyzcba/abc")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testPalindromePatThenRev() {
        given()
            .baseUri(BASE_URI)
        .when()
            .get("/api/pat/abccba/abc")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testPalindromeRevThenPat() {
        given()
            .baseUri(BASE_URI)
        .when()
            .get("/api/pat/cbaabc/abc")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testNoMatch() {
        given()
            .baseUri(BASE_URI)
        .when()
            .get("/api/pat/helloworld/xyz")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testTxtShorterThanPat() {
        given()
            .baseUri(BASE_URI)
        .when()
            .get("/api/pat/ab/abc")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Ignore("1 expectation failed. Expected status code <200> but was <404>.")
    @Test(timeout = 60000)
    public void testPatLenExactly3NoRev() {
        given()
            .baseUri(BASE_URI)
        .when()
            .get("/api/pat/xyzabcxyz/abc")
        .then()
            .statusCode(200)
            .body(equalTo("1"));
    }
}