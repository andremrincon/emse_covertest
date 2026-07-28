package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    private String getBaseUrl() {
        return System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        String w = "aaaaa";
        String x = "bbbbb";
        String z = "ddddd";
        String y = "ccccc";

        given()
            .baseUri(getBaseUrl())
            .pathParam("w", w)
            .pathParam("x", x)
            .pathParam("z", z)
            .pathParam("y", y)
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        String w = "eeeee";
        String x = "ddddd";
        String z = "bbbbb";
        String y = "ccccc";

        given()
            .baseUri(getBaseUrl())
            .pathParam("w", w)
            .pathParam("x", x)
            .pathParam("z", z)
            .pathParam("y", y)
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testUnorderedAllValidLengths() {
        String w = "aaaaa";
        String x = "bbbbb";
        String z = "ccccc";
        String y = "ddddd";

        given()
            .baseUri(getBaseUrl())
            .pathParam("w", w)
            .pathParam("x", x)
            .pathParam("z", z)
            .pathParam("y", y)
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedLengthLessThanFive() {
        String w = "aaaa";
        String x = "bbbbb";
        String z = "ddddd";
        String y = "ccccc";

        given()
            .baseUri(getBaseUrl())
            .pathParam("w", w)
            .pathParam("x", x)
            .pathParam("z", z)
            .pathParam("y", y)
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedLengthGreaterThanSix() {
        String w = "aaaaaaa";
        String x = "bbbbb";
        String z = "ddddd";
        String y = "ccccc";

        given()
            .baseUri(getBaseUrl())
            .pathParam("w", w)
            .pathParam("x", x)
            .pathParam("z", z)
            .pathParam("y", y)
        .when()
            .get("/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200)
            .body(equalTo("unordered"));
    }
}