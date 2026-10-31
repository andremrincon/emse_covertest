package ts01qwen3_7_plus_3;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    @Test(timeout = 60000)
    public void testUnorderedInvalidLength() {
        given()
                .when()
                .get("http://localhost:8080/api/ordered4/a/b/c/d")
                .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given()
                .when()
                .get("http://localhost:8080/api/ordered4/aaaaa/bbbbb/ddddd/ccccc")
                .then()
                .statusCode(200)
                .body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given()
                .when()
                .get("http://localhost:8080/api/ordered4/eeeee/ddddd/bbbbb/ccccc")
                .then()
                .statusCode(200)
                .body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testUnorderedValidLength() {
        given()
                .when()
                .get("http://localhost:8080/api/ordered4/aaaaa/bbbbb/aaaaa/ccccc")
                .then()
                .statusCode(200)
                .body(equalTo("unordered"));
    }
}