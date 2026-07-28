package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given()
                .when()
                .get("/api/ordered4/aaaaa/bbbbb/ddddd/ccccc")
                .then()
                .statusCode(200)
                .body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given()
                .when()
                .get("/api/ordered4/ddddd/ccccc/aaaaa/bbbbb")
                .then()
                .statusCode(200)
                .body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testUnorderedWithValidLengths() {
        given()
                .when()
                .get("/api/ordered4/aaaaa/bbbbb/ccccc/ddddd")
                .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testLengthTooShortReturnsUnordered() {
        given()
                .when()
                .get("/api/ordered4/aa/bbbbb/ccccc/ddddd")
                .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testLengthTooLongReturnsUnordered() {
        given()
                .when()
                .get("/api/ordered4/aaaaaaa/bbbbb/ccccc/ddddd")
                .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testEmptyParameterReturnsServerError() {
        given()
                .when()
                .get("/api/ordered4/aaaaa/bbbbb/ccccc/%20")
                .then()
                .statusCode(200);
    }
}