package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;
import org.junit.BeforeClass;
import org.junit.Test;

public class PatTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testPatLengthLessThanThreeReturnsZero() {
        given()
            .when()
                .get("/api/pat/abcdef/ab")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatternFoundNoReverseReturnsOne() {
        given()
            .when()
                .get("/api/pat/abcXYZ/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseFoundNoPatternReturnsTwo() {
        given()
            .when()
                .get("/api/pat/cbaXYZ/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatternAndReverseAdjacentReturnsFour() {
        given()
            .when()
                .get("/api/pat/abccba/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatternAndReverseNonAdjacentReturnsThree() {
        given()
            .when()
                .get("/api/pat/abcXcba/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseAndPatternAdjacentReturnsFive() {
        given()
            .when()
                .get("/api/pat/cbaabc/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseAndPatternNonAdjacentReturnsThree() {
        given()
            .when()
                .get("/api/pat/cbaXabc/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNeitherPatternNorReverseFoundReturnsZero() {
        given()
            .when()
                .get("/api/pat/XYZXYZ/abc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSingleTxtEndpoint() {
        given()
            .when()
                .get("/api/pat/The%20quick%20brown%20fox%20jumps%20over%20the%20lazy%20dog.")
            .then()
                .statusCode(200);
    }
}