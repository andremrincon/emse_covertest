package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PatTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testPatLenLessThanOrEqualTo2() {
        given()
            .when()
                .get("/api/pat/hello/ab")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatLenGreaterThan2NeitherFound() {
        given()
            .when()
                .get("/api/pat/helloworld/abc")
            .then()
                .statusCode(200)
                .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundNoPatRev() {
        given()
            .when()
                .get("/api/pat/helloabcworld/abc")
            .then()
                .statusCode(200)
                .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testPatRevFoundNoPat() {
        given()
            .when()
                .get("/api/pat/hellocbaworld/abc")
            .then()
                .statusCode(200)
                .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testPatThenPatRevImmediately() {
        given()
            .when()
                .get("/api/pat/helloabccba/abc")
            .then()
                .statusCode(200)
                .body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testPatThenPatRevLater() {
        given()
            .when()
                .get("/api/pat/helloabcxxcba/abc")
            .then()
                .statusCode(200)
                .body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testPatRevThenPatImmediately() {
        given()
            .when()
                .get("/api/pat/hellocbaabc/abc")
            .then()
                .statusCode(200)
                .body(equalTo("5"));
    }

    @Test(timeout = 60000)
    public void testPatRevThenPatLater() {
        given()
            .when()
                .get("/api/pat/hellocbaxxabc/abc")
            .then()
                .statusCode(200)
                .body(equalTo("5"));
    }
}