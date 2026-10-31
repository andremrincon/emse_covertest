package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Ordered4Test {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl", System.getenv().getOrDefault("BASE_URL", "http://localhost:8080"));
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testIncreasing() {
        String w = "apple";
        String x = "banana";
        String z = "durian";
        String y = "cherry";

        Response response = given()
                .pathParam("w", w)
                .pathParam("x", x)
                .pathParam("z", z)
                .pathParam("y", y)
                .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}");

        response.then().body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasing() {
        String w = "zebra";
        String x = "water";
        String z = "ultra";
        String y = "vodka";

        Response response = given()
                .pathParam("w", w)
                .pathParam("x", x)
                .pathParam("z", z)
                .pathParam("y", y)
                .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}");

        response.then().body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testUnorderedInvalidLength() {
        String w = "a";
        String x = "b";
        String z = "c";
        String y = "d";

        Response response = given()
                .pathParam("w", w)
                .pathParam("x", x)
                .pathParam("z", z)
                .pathParam("y", y)
                .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}");

        response.then().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedValidLength() {
        String w = "apple";
        String x = "apple";
        String z = "apple";
        String y = "apple";

        Response response = given()
                .pathParam("w", w)
                .pathParam("x", x)
                .pathParam("z", z)
                .pathParam("y", y)
                .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}");

        response.then().body(equalTo("unordered"));
    }
}