package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given()
            .when()
                .get("/api/ordered4/apple/banana/zebra/mango")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given()
            .when()
                .get("/api/ordered4/zebra/mango/apple/banana")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedWithValidLengthsNeitherCondition() {
        given()
            .when()
                .get("/api/ordered4/apple/apple/apple/apple")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedWithShortStrings() {
        given()
            .when()
                .get("/api/ordered4/abcd/bcde/cdef/defg")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedWithLongStrings() {
        given()
            .when()
                .get("/api/ordered4/aaaaaaa/bbbbbbb/ccccccc/ddddddd")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedWithMixedLengths() {
        given()
            .when()
                .get("/api/ordered4/zebra/yak/x-ray/wolf")
            .then()
                .statusCode(200);
    }
}