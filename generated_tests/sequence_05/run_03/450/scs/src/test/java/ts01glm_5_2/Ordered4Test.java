package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null) {
            baseUrl = System.getenv("baseUrl");
        }
        if (baseUrl == null) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrderAllLength5() {
        given()
            .when()
                .get("/api/ordered4/aaaaa/bbbbb/ddddd/ccccc")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasingOrderAllLength5() {
        given()
            .when()
                .get("/api/ordered4/ddddd/ccccc/aaaaa/bbbbb")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedValidLengthEqualStrings() {
        given()
            .when()
                .get("/api/ordered4/bbbbb/bbbbb/bbbbb/bbbbb")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedOneStringTooShort() {
        given()
            .when()
                .get("/api/ordered4/aaaa/bbbbb/ccccc/ddddd")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedOneStringTooLong() {
        given()
            .when()
                .get("/api/ordered4/aaaaaaa/bbbbb/ccccc/ddddd")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIncreasingOrderBoundaryLength6() {
        given()
            .when()
                .get("/api/ordered4/aaaaaa/bbbbbb/dddddd/cccccc")
            .then()
                .statusCode(200);
    }
}