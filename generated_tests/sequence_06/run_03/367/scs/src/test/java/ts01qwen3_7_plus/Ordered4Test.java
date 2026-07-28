package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    @Before
    public void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testInvalidLengths() {
        String w = "a";
        String x = "bb";
        String z = "dddd";
        String y = "ccc";

        RestAssured.given()
                .when()
                .get("/api/ordered4/" + w + "/" + x + "/" + z + "/" + y)
                .then()
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testValidLengthsUnordered() {
        String w = "apple";
        String x = "apple";
        String z = "apple";
        String y = "apple";

        RestAssured.given()
                .when()
                .get("/api/ordered4/" + w + "/" + x + "/" + z + "/" + y)
                .then()
                .body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testValidLengthsIncreasing() {
        String w = "apple";
        String x = "berry";
        String z = "dates";
        String y = "cherry";

        RestAssured.given()
                .when()
                .get("/api/ordered4/" + w + "/" + x + "/" + z + "/" + y)
                .then()
                .body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testValidLengthsDecreasing() {
        String w = "zebra";
        String x = "yacht";
        String z = "wheat";
        String y = "xerox";

        RestAssured.given()
                .when()
                .get("/api/ordered4/" + w + "/" + x + "/" + z + "/" + y)
                .then()
                .body(equalTo("decreasing"));
    }
}