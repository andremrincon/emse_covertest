package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.when;

public class PatTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testPatLengthTwoOrLess() {
        when()
            .get("/api/pat/abc/ab")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoMatchFound() {
        when()
            .get("/api/pat/XYZXYZ/ABC")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundInnerLoopNotEntered() {
        when()
            .get("/api/pat/ABCAB/ABC")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundInnerLoopFalseMatchesOnly() {
        when()
            .get("/api/pat/ABCCBCDEF/ABC")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundPatRevFoundImmediatelyPalindrome() {
        when()
            .get("/api/pat/ABCCBA/ABC")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundPatRevFoundLater() {
        when()
            .get("/api/pat/ABCXYZCBA/ABC")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevFoundInnerLoopNotEntered() {
        when()
            .get("/api/pat/CBAAB/ABC")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevFoundPatFoundImmediatelyPalindrome() {
        when()
            .get("/api/pat/CBAABC/ABC")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatRevFoundPatFoundLaterWithFalseMatch() {
        when()
            .get("/api/pat/CBAAAXABC/ABC")
        .then()
            .statusCode(200);
    }
}