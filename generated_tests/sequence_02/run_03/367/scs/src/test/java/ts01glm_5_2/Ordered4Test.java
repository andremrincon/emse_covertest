package ts01glm_5_2;

import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Ordered4Test {

    @Before
    public void setUp() {
        baseURI = System.getProperty("base.url", "http://localhost");
        port = Integer.parseInt(System.getProperty("server.port", "8080"));
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given()
            .when()
            .get("/api/ordered4/aaaaa/bbbbb/ddddd/ccccc")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given()
            .when()
            .get("/api/ordered4/ddddd/ccccc/aaaaa/bbbbb")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedWhenLengthsPassButNeitherCondition() {
        given()
            .when()
            .get("/api/ordered4/aaaaa/bbbbb/bbbbb/aaaaa")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedWhenStringTooShort() {
        given()
            .when()
            .get("/api/ordered4/abcd/bbbbb/ccccc/ddddd")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedWhenStringTooLong() {
        given()
            .when()
            .get("/api/ordered4/aaaaaaa/bbbbb/ccccc/ddddd")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIncreasingOrderWithSixCharStrings() {
        given()
            .when()
            .get("/api/ordered4/aaaaaa/bbbbbb/dddddd/cccccc")
            .then()
            .statusCode(200);
    }
}