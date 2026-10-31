package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class PatTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testSubjectPatFoundThenPatRevFoundLater() {
        String response = given()
                .when()
                .get("/api/pat/xyzabc123cba/abc")
                .then()
                .statusCode(200)
                .extract().asString();

        assertEquals("3", response);
    }

    @Test(timeout = 60000)
    public void testReverseMethodExecution() {
        String response = given()
                .when()
                .get("/api/pat/abc/abc")
                .then()
                .statusCode(200)
                .extract().asString();

        assertEquals("1", response);
    }

    @Test(timeout = 60000)
    public void testClassInitialization() {
        String response = given()
                .when()
                .get("/api/pat/a/a")
                .then()
                .statusCode(200)
                .extract().asString();

        assertEquals("0", response);
    }

    @Test(timeout = 60000)
    public void testSubjectPatRevFoundThenPatFoundLater() {
        String response = given()
                .when()
                .get("/api/pat/xyzcba123abc/abc")
                .then()
                .statusCode(200)
                .extract().asString();

        assertEquals("3", response);
    }

    @Test(timeout = 60000)
    public void testSubjectPatFoundThenPatRevFoundImmediately() {
        String response = given()
                .when()
                .get("/api/pat/xyzabccba/abc")
                .then()
                .statusCode(200)
                .extract().asString();

        assertEquals("3", response);
    }

    @Test(timeout = 60000)
    public void testSubjectPatRevFoundThenPatFoundImmediately() {
        String response = given()
                .when()
                .get("/api/pat/xyzcbaabc/abc")
                .then()
                .statusCode(200)
                .extract().asString();

        assertEquals("3", response);
    }

    @Test(timeout = 60000)
    public void testSubjectTxtShorterThanPat() {
        String response = given()
                .when()
                .get("/api/pat/a/abc")
                .then()
                .statusCode(200)
                .extract().asString();

        assertEquals("0", response);
    }
}