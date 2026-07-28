package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class Ordered4Test {

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given()
            .pathParam("w", "apple")
            .pathParam("x", "banana")
            .pathParam("z", "delta")
            .pathParam("y", "cherry")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given()
            .pathParam("w", "delta")
            .pathParam("x", "cherry")
            .pathParam("z", "apple")
            .pathParam("y", "banana")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedValidLengths() {
        given()
            .pathParam("w", "apple")
            .pathParam("x", "cherry")
            .pathParam("z", "delta")
            .pathParam("y", "banana")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLengthTooShort() {
        given()
            .pathParam("w", "app")
            .pathParam("x", "banana")
            .pathParam("z", "delta")
            .pathParam("y", "cherry")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLengthTooLong() {
        given()
            .pathParam("w", "apple")
            .pathParam("x", "banana")
            .pathParam("z", "deltass")
            .pathParam("y", "cherry")
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200);
    }
}