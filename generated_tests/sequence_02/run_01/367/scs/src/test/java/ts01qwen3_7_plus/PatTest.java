package ts01qwen3_7_plus;

import org.junit.Test;
import java.io.UnsupportedEncodingException;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    private String getBaseUrl() {
        return System.getProperty("baseUrl", "http://localhost:8080");
    }

    private String enc(String s) {
        try {
            return java.net.URLEncoder.encode(s, "UTF-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(timeout = 60000)
    public void testPatLenLessThanOrEqualTo2() {
        String txt = "ab";
        String pat = "ab";

        String txtEnc = enc(txt);
        String patEnc = enc(pat);

        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/pat/{txt}/{pat}", txtEnc, patEnc)
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundNoReverse() {
        String txt = "hello world";
        String pat = "world";

        String txtEnc = enc(txt);
        String patEnc = enc(pat);

        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/pat/{txt}/{pat}", txtEnc, patEnc)
        .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseImmediatelyAfter() {
        String txt = "abccba";
        String pat = "abc";

        String txtEnc = enc(txt);
        String patEnc = enc(pat);

        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/pat/{txt}/{pat}", txtEnc, patEnc)
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseLater() {
        String txt = "abc xyz cba";
        String pat = "abc";

        String txtEnc = enc(txt);
        String patEnc = enc(pat);

        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/pat/{txt}/{pat}", txtEnc, patEnc)
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testReverseFoundNoPat() {
        String txt = "hello cba";
        String pat = "abc";

        String txtEnc = enc(txt);
        String patEnc = enc(pat);

        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/pat/{txt}/{pat}", txtEnc, patEnc)
        .then()
            .statusCode(200)
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatImmediatelyAfter() {
        String txt = "cbaabc";
        String pat = "abc";

        String txtEnc = enc(txt);
        String patEnc = enc(pat);

        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/pat/{txt}/{pat}", txtEnc, patEnc)
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatLater() {
        String txt = "cba xyz abc";
        String pat = "abc";

        String txtEnc = enc(txt);
        String patEnc = enc(pat);

        given()
            .baseUri(getBaseUrl())
        .when()
            .get("/api/pat/{txt}/{pat}", txtEnc, patEnc)
        .then()
            .statusCode(200)
            .body(equalTo("0"));
    }
}