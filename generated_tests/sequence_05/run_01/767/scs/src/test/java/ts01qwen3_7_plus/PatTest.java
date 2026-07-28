package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PatTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testInitAndPatLenLessThanThree() {
        given()
            .when()
            .get("/api/pat/ABC/AB")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectLine63PatAdjacentToPatrev() {
        given()
            .when()
            .get("/api/pat/ABCCBA/ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectLine84PatrevAdjacentToPat() {
        given()
            .when()
            .get("/api/pat/CBAABC/ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseWithSingleCharacterInput() {
        given()
            .when()
            .get("/api/pat/a")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectNoPatternMatch() {
        given()
            .when()
            .get("/api/pat/XYZ/ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectPatFoundWithoutPatrev() {
        given()
            .when()
            .get("/api/pat/ABCXYZ/ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectPatrevFoundWithoutPat() {
        given()
            .when()
            .get("/api/pat/CBAXYZ/ABC")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectPatAndPatrevNonAdjacent() {
        given()
            .when()
            .get("/api/pat/ABCXYCBA/ABC")
            .then()
            .statusCode(200);
    }
}