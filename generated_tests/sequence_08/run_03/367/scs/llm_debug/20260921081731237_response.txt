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
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testUnorderedByLength() {
        Response response = given()
                .pathParam("w", "a")
                .pathParam("x", "b")
                .pathParam("z", "c")
                .pathParam("y", "d")
                .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}");

        response.then().statusCode(200);
        response.then().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedByLengthTooLong() {
        Response response = given()
                .pathParam("w", "appleee")
                .pathParam("x", "banana")
                .pathParam("z", "xrayz")
                .pathParam("y", "cherry")
                .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}");

        response.then().statusCode(200);
        response.then().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testIncreasing() {
        Response response = given()
                .pathParam("w", "apple")
                .pathParam("x", "banana")
                .pathParam("z", "xrayz")
                .pathParam("y", "cherry")
                .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}");

        response.then().statusCode(200);
        response.then().body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasing() {
        Response response = given()
                .pathParam("w", "zzzzz")
                .pathParam("x", "yyyyy")
                .pathParam("z", "wwwww")
                .pathParam("y", "xxxxx")
                .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}");

        response.then().statusCode(200);
        response.then().body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testUnorderedByValue() {
        Response response = given()
                .pathParam("w", "apple")
                .pathParam("x", "banana")
                .pathParam("z", "aaaaa")
                .pathParam("y", "cherry")
                .when()
                .get("/api/ordered4/{w}/{x}/{z}/{y}");

        response.then().statusCode(200);
        response.then().body(equalTo("unordered"));
    }
}