package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class PatTest {

    private static final String BASE_URL = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testPatFoundNoReverse() {
        given()
            .when()
                .get("/api/pat/ABCDEF/ABC")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatAndReverseAdjacentPalindrome() {
        given()
            .when()
                .get("/api/pat/ABCCBA/ABC")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatAndReverseNonAdjacent() {
        given()
            .when()
                .get("/api/pat/ABCXCBA/ABC")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseAndPatAdjacentPalindrome() {
        given()
            .when()
                .get("/api/pat/CBAABC/ABC")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseAndPatNonAdjacent() {
        given()
            .when()
                .get("/api/pat/CBAXABC/ABC")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseOnlyFound() {
        given()
            .when()
                .get("/api/pat/CBADEF/ABC")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNeitherPatNorReverseFound() {
        given()
            .when()
                .get("/api/pat/XYZXYZ/ABC")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatLenLessThanThree() {
        given()
            .when()
                .get("/api/pat/AB/AB")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFirstCharMatchButNotFullMatch() {
        given()
            .when()
                .get("/api/pat/AXCDEF/ABC")
            .then()
                .statusCode(200);
    }
}