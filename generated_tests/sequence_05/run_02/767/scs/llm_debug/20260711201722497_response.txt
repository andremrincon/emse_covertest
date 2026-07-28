package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("base.url", "http://localhost");
        RestAssured.port = Integer.parseInt(System.getProperty("base.port", "8080"));
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given()
                .when()
                .get("/api/ordered4/aaaaa/bbbbbb/ddddd/ccccc")
                .then()
                .statusCode(lessThan(300))
                .body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given()
                .when()
                .get("/api/ordered4/ddddd/ccccc/aaaaa/bbbbb")
                .then()
                .statusCode(lessThan(300))
                .body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testUnorderedWithValidLengths() {
        given()
                .when()
                .get("/api/ordered4/aaaaa/bbbbb/ccccc/ddddd")
                .then()
                .statusCode(lessThan(300))
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedWithShortString() {
        given()
                .when()
                .get("/api/ordered4/a/bbbbb/ccccc/ddddd")
                .then()
                .statusCode(lessThan(300))
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedWithLongString() {
        given()
                .when()
                .get("/api/ordered4/aaaaaaa/bbbbb/ccccc/ddddd")
                .then()
                .statusCode(lessThan(300))
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testServerErrorWithExcessiveInput() {
        StringBuilder sb = new StringBuilder();
        int length = 2000;
        for (int i = 0; i < length; i++) {
            sb.append('y');
        }
        String longY = sb.toString();
        given()
                .when()
                .get("/api/ordered4/" + longY + "/bbbbb/ccccc/ddddd")
                .then()
                .statusCode(200);
    }
}