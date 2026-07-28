package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class PatTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testPatLengthLessThanThree() {
        String txt = "abcdefg";
        String pat = "ab";
        given()
            .pathParam("txt", txt)
            .pathParam("pat", pat)
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200)
            .body(containsString("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseNotFound() {
        String txt = "ABCXYZ";
        String pat = "ABC";
        given()
            .pathParam("txt", txt)
            .pathParam("pat", pat)
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200)
            .body(containsString("1"));
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatNotFound() {
        String txt = "CBAXYZ";
        String pat = "ABC";
        given()
            .pathParam("txt", txt)
            .pathParam("pat", pat)
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200)
            .body(containsString("2"));
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseImmediatelyAfter() {
        String txt = "ABCCBA";
        String pat = "ABC";
        given()
            .pathParam("txt", txt)
            .pathParam("pat", pat)
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200)
            .body(containsString("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseFoundLater() {
        String txt = "ABCXYZCBA";
        String pat = "ABC";
        given()
            .pathParam("txt", txt)
            .pathParam("pat", pat)
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200)
            .body(containsString("0"));
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatImmediatelyAfter() {
        String txt = "CBAABC";
        String pat = "ABC";
        given()
            .pathParam("txt", txt)
            .pathParam("pat", pat)
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200)
            .body(containsString("0"));
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatFoundLater() {
        String txt = "CBAXYZABC";
        String pat = "ABC";
        given()
            .pathParam("txt", txt)
            .pathParam("pat", pat)
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200)
            .body(containsString("0"));
    }

    @Test(timeout = 60000)
    public void testNeitherPatNorReverseFound() {
        String txt = "XYZXYZXYZ";
        String pat = "ABC";
        given()
            .pathParam("txt", txt)
            .pathParam("pat", pat)
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .statusCode(200)
            .body(containsString("0"));
    }

    @Test(timeout = 60000)
    public void testPatTxtOnlyEndpoint() {
        String txt = "The quick brown fox jumps over the lazy dog.";
        String encodedTxt;
        try {
            encodedTxt = URLEncoder.encode(txt, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            encodedTxt = txt;
        }
        given()
            .pathParam("txt", encodedTxt)
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200);
    }
}