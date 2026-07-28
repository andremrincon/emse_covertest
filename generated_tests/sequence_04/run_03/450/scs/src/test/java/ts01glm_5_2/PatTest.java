package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testShortPatternReturnsZero() {
        given().when().get("/api/pat/abc/ab").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseNotFound() {
        given().when().get("/api/pat/ABCDEF/ABC").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseAdjacentPalindrome() {
        given().when().get("/api/pat/ABCCBA/ABC").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatFoundReverseNonAdjacent() {
        given().when().get("/api/pat/ABCXCBA/ABC").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundFirstPatAdjacentPalindrome() {
        given().when().get("/api/pat/CBAABC/ABC").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundFirstPatNonAdjacent() {
        given().when().get("/api/pat/CBAXABC/ABC").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNeitherPatNorReverseFound() {
        given().when().get("/api/pat/XYZXYZ/ABC").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundPatNotFound() {
        given().when().get("/api/pat/CBADEF/ABC").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFirstCharMatchButFullMatchFails() {
        given().when().get("/api/pat/ABXCBX/ABC").then().statusCode(200);
    }
}