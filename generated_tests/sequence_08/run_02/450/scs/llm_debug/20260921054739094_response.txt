package ts01qwen3_7_plus_3;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    @Test(timeout = 60000)
    public void testXLengthInvalid() {
        given()
            .when()
                .get("http://localhost:8080/api/ordered4/apple/bat/cherry/delta")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testYLengthInvalid() {
        given()
            .when()
                .get("http://localhost:8080/api/ordered4/apple/banana/cat/delta")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testZLengthInvalid() {
        given()
            .when()
                .get("http://localhost:8080/api/ordered4/apple/banana/cherry/dog")
            .then()
                .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnordered() {
        given()
            .when()
                .get("http://localhost:8080/api/ordered4/apple/apple/apple/apple")
            .then()
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testIncreasing() {
        given()
            .when()
                .get("http://localhost:8080/api/ordered4/apple/banana/delta/cherry")
            .then()
                .body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasing() {
        given()
            .when()
                .get("http://localhost:8080/api/ordered4/delta/cherry/apple/banana")
            .then()
                .body(equalTo("decreasing"));
    }
}