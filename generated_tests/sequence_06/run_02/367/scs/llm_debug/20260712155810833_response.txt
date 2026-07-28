package ts01qwen3_7_plus;

import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        io.restassured.RestAssured.baseURI = System.getenv().getOrDefault("BASE_URL", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given()
            .when()
                .get("/api/ordered4/aaaaa/bbbbb/ccccc/ddddd")
            .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given()
            .when()
                .get("/api/ordered4/ddddd/ccccc/bbbbb/aaaaa")
            .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedValidLengths() {
        given()
            .when()
                .get("/api/ordered4/aaaaa/ccccc/bbbbb/ddddd")
            .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedShortLength() {
        given()
            .when()
                .get("/api/ordered4/aaaa/bbbbb/ccccc/ddddd")
            .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedLongLength() {
        given()
            .when()
                .get("/api/ordered4/aaaaaaa/bbbbb/ccccc/ddddd")
            .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }
}