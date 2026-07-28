package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class Ordered4Test {

    private static final String BASE_URL = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testIncreasing() {
        String w = "aaaaa";
        String x = "bbbbb";
        String z = "ddddd";
        String y = "ccccc";

        given()
            .pathParam("w", w)
            .pathParam("x", x)
            .pathParam("z", z)
            .pathParam("y", y)
        .when()
            .get(BASE_URL + "/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasing() {
        String w = "ddddd";
        String x = "ccccc";
        String z = "aaaaa";
        String y = "bbbbb";

        given()
            .pathParam("w", w)
            .pathParam("x", x)
            .pathParam("z", z)
            .pathParam("y", y)
        .when()
            .get(BASE_URL + "/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedInnerFalse() {
        String w = "aaaaa";
        String x = "bbbbb";
        String z = "ddddd";
        String y = "aaaaa";

        given()
            .pathParam("w", w)
            .pathParam("x", x)
            .pathParam("z", z)
            .pathParam("y", y)
        .when()
            .get(BASE_URL + "/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedWTooShort() {
        String w = "99";
        String x = "bbbbb";
        String z = "ddddd";
        String y = "ccccc";

        given()
            .pathParam("w", w)
            .pathParam("x", x)
            .pathParam("z", z)
            .pathParam("y", y)
        .when()
            .get(BASE_URL + "/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedZTooLong() {
        String w = "aaaaa";
        String x = "bbbbb";
        String z = "ddddddd";
        String y = "ccccc";

        given()
            .pathParam("w", w)
            .pathParam("x", x)
            .pathParam("z", z)
            .pathParam("y", y)
        .when()
            .get(BASE_URL + "/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedXTooShort() {
        String w = "aaaaa";
        String x = "yak";
        String z = "ddddd";
        String y = "ccccc";

        given()
            .pathParam("w", w)
            .pathParam("x", x)
            .pathParam("z", z)
            .pathParam("y", y)
        .when()
            .get(BASE_URL + "/api/ordered4/{w}/{x}/{z}/{y}")
        .then()
            .statusCode(200);
    }
}