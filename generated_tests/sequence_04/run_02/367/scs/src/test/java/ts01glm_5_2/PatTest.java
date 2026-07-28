package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testPatLenLessThanOrEqual2() {
        given()
            .pathParam("txt", "hello")
            .pathParam("pat", "ab")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundNoReverse() {
        given()
            .pathParam("txt", "helloabc")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseAdjacent() {
        given()
            .pathParam("txt", "abccba")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseNonAdjacent() {
        given()
            .pathParam("txt", "abcXcba")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testReverseFoundFirstPatAdjacent() {
        given()
            .pathParam("txt", "cbaabc")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testReverseFoundFirstPatNonAdjacent() {
        given()
            .pathParam("txt", "cbaXabc")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testReverseFoundNoPat() {
        given()
            .pathParam("txt", "cbaXYZ")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testNeitherFound() {
        given()
            .pathParam("txt", "XYZXYZ")
            .pathParam("pat", "abc")
        .when()
            .get("/api/pat/{txt}/{pat}")
        .then()
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testSingleTxtEndpoint() {
        String encoded;
        try {
            encoded = URLEncoder.encode("The quick brown fox jumps over the lazy dog.", "UTF-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        given()
            .pathParam("txt", encoded)
        .when()
            .get("/api/pat/{txt}")
        .then()
            .statusCode(200);
    }
}