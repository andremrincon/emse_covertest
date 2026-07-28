package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testPatNoMatchReturnsZero() {
        given()
            .pathParam("txt", "xxxx")
            .pathParam("pat", "ABAB")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatShortPatternReturnsZero() {
        given()
            .pathParam("txt", "ABAB")
            .pathParam("pat", "AB")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatPalindromePatFollowedByReverse() {
        given()
            .pathParam("txt", "ABABBABA")
            .pathParam("pat", "ABAB")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseFoundLater() {
        given()
            .pathParam("txt", "ABABxxxBABA")
            .pathParam("pat", "ABAB")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatImmediatelyAfter() {
        given()
            .pathParam("txt", "BABAABAB")
            .pathParam("pat", "ABAB")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatSingleTxtEndpoint() {
        given()
            .pathParam("txt", "hello")
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(lessThan(300));
    }

    @Test(timeout = 60000)
    public void testPatFoundOnlyPatNoReverse() {
        given()
            .pathParam("txt", "zzABABzz")
            .pathParam("pat", "ABAB")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testReverseFoundOnlyNoPat() {
        given()
            .pathParam("txt", "zzBABAzz")
            .pathParam("pat", "ABAB")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200)
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testPatFoundAtNonZeroIndex() {
        given()
            .pathParam("txt", "zzABABBABA")
            .pathParam("pat", "ABAB")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200)
            .body(equalTo("2"));
    }
}