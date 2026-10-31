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
    public void testPatternFoundReverseNotFound() {
        given()
            .when()
            .get("/api/pat/ABABxyz/ABAB")
            .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatternNotFound() {
        given()
            .when()
            .get("/api/pat/BABAxyz/ABAB")
            .then()
            .statusCode(200)
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testPatternFoundReverseFoundImmediately() {
        given()
            .when()
            .get("/api/pat/ABABBABA/ABAB")
            .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatternFoundReverseFoundLater() {
        given()
            .when()
            .get("/api/pat/ABABxyzBABA/ABAB")
            .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatternFoundImmediately() {
        given()
            .when()
            .get("/api/pat/BABAABAB/ABAB")
            .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatternFoundLater() {
        given()
            .when()
            .get("/api/pat/BABAxyzABAB/ABAB")
            .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testNeitherFound() {
        given()
            .when()
            .get("/api/pat/XYZXYZ/ABAB")
            .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatternLengthLessThanThree() {
        given()
            .when()
            .get("/api/pat/ABAB/AB")
            .then()
            .statusCode(200)
            .body(equalTo("0"));
    }
}