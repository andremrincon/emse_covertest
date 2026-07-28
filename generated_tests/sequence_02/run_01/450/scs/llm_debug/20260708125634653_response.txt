package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PatTest {

    @Test(timeout = 60000)
    public void testPatLenLessThanOrEqualTo2() {
        given()
            .when()
                .get("http://localhost:8080/api/pat/abc/ab")
            .then()
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundNoReverse() {
        given()
            .when()
                .get("http://localhost:8080/api/pat/abcde/abc")
            .then()
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseImmediatelyAfter() {
        given()
            .when()
                .get("http://localhost:8080/api/pat/abccba/abc")
            .then()
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseLater() {
        given()
            .when()
                .get("http://localhost:8080/api/pat/abcxxcba/abc")
            .then()
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testReverseFoundNoPat() {
        given()
            .when()
                .get("http://localhost:8080/api/pat/cbadef/abc")
            .then()
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatImmediatelyAfter() {
        given()
            .when()
                .get("http://localhost:8080/api/pat/cbaabc/abc")
            .then()
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatLater() {
        given()
            .when()
                .get("http://localhost:8080/api/pat/cbaxxabc/abc")
            .then()
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testNeitherFound() {
        given()
            .when()
                .get("http://localhost:8080/api/pat/xyz/abc")
            .then()
                .body(equalTo("0"));
    }
}