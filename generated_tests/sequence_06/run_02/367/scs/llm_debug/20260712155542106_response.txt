package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PatTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testPatLenLessThanOrEqualTo2() {
        given()
            .when()
            .get("/api/pat/abc/a")
            .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundNoPatrev() {
        given()
            .when()
            .get("/api/pat/abcxyz/abc")
            .then()
            .statusCode(200)
            .body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testPatAndPatrevAdjacent() {
        given()
            .when()
            .get("/api/pat/abccba/abc")
            .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatAndPatrevSeparated() {
        given()
            .when()
            .get("/api/pat/abcxyzcba/abc")
            .then()
            .statusCode(200)
            .body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatrevFoundNoPat() {
        given()
            .when()
            .get("/api/pat/cbaxyz/abc")
            .then()
            .statusCode(200)
            .body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testPatrevAndPatAdjacent() {
        given()
            .when()
            .get("/api/pat/cbaabc/abc")
            .then()
            .statusCode(200)
            .body(equalTo("0"));
    }
}