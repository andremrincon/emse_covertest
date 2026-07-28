package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    @Test(timeout = 60000)
    public void testOrdered4InvalidLengths() {
        given()
            .when()
                .get("/api/ordered4/a/b/c/d")
            .then()
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testOrdered4ValidLengthsUnordered() {
        given()
            .when()
                .get("/api/ordered4/aaaaa/bbbbb/bbbbb/bbbbb")
            .then()
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testOrdered4Increasing() {
        given()
            .when()
                .get("/api/ordered4/aaaaa/bbbbb/ddddd/ccccc")
            .then()
                .body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testOrdered4Decreasing() {
        given()
            .when()
                .get("/api/ordered4/eeeee/ddddd/bbbbb/ccccc")
            .then()
                .body(equalTo("decreasing"));
    }
}