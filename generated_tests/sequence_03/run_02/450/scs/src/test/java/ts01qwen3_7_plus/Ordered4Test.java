package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        io.restassured.RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        String w = "apple";
        String x = "banana";
        String y = "cherry";
        String z = "delta";

        Response response = given()
            .when()
            .get("/api/ordered4/" + w + "/" + x + "/" + z + "/" + y);

        response.then().statusCode(200).body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        String w = "delta";
        String x = "cherry";
        String y = "banana";
        String z = "apple";

        Response response = given()
            .when()
            .get("/api/ordered4/" + w + "/" + x + "/" + z + "/" + y);

        response.then().statusCode(200).body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testUnorderedDueToLengthViolation() {
        String w = "app";
        String x = "banana";
        String y = "cherry";
        String z = "delta";

        Response response = given()
            .when()
            .get("/api/ordered4/" + w + "/" + x + "/" + z + "/" + y);

        response.then().statusCode(200).body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedDueToOrderingMismatch() {
        String w = "apple";
        String x = "cherry";
        String y = "delta";
        String z = "banana";

        Response response = given()
            .when()
            .get("/api/ordered4/" + w + "/" + x + "/" + z + "/" + y);

        response.then().statusCode(200).body(equalTo("unordered"));
    }
}