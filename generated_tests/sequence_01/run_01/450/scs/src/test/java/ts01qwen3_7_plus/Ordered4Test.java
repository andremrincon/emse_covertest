package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    @Test(timeout = 60000)
    public void testLengthsLessThan5() {
        given()
            .when()
                .get("http://localhost:8080/api/ordered4/a/b/c/d")
            .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testLengthsGreaterThan6() {
        given()
            .when()
                .get("http://localhost:8080/api/ordered4/aaaaaaa/bbbbbbb/ccccccc/ddddddd")
            .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testIncreasing() {
        given()
            .when()
                .get("http://localhost:8080/api/ordered4/aaaaa/bbbbb/ddddd/ccccc")
            .then()
                .statusCode(200)
                .body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasing() {
        given()
            .when()
                .get("http://localhost:8080/api/ordered4/ddddd/ccccc/aaaaa/bbbbb")
            .then()
                .statusCode(200)
                .body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testUnordered() {
        given()
            .when()
                .get("http://localhost:8080/api/ordered4/bbbbb/aaaaa/ddddd/ccccc")
            .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }
}