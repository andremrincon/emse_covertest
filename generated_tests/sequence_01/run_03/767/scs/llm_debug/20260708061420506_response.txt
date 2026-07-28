package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    private final String baseUrl = System.getProperty("baseUrl", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        String w = "aaaaa";
        String x = "bbbbb";
        String z = "ddddd";
        String y = "ccccc";

        Response response = RestAssured
                .given()
                    .baseUri(baseUrl)
                .when()
                    .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y);

        response.then().body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        String w = "eeeee";
        String x = "ddddd";
        String z = "bbbbb";
        String y = "ccccc";

        Response response = RestAssured
                .given()
                    .baseUri(baseUrl)
                .when()
                    .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y);

        response.then().body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testUnorderedInvalidLength() {
        String w = "a";
        String x = "b";
        String z = "d";
        String y = "c";

        Response response = RestAssured
                .given()
                    .baseUri(baseUrl)
                .when()
                    .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y);

        response.then().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedValidLength() {
        String w = "aaaaa";
        String x = "bbbbb";
        String z = "ccccc";
        String y = "ddddd";

        Response response = RestAssured
                .given()
                    .baseUri(baseUrl)
                .when()
                    .get("/api/ordered4/{w}/{x}/{z}/{y}", w, x, z, y);

        response.then().body(equalTo("unordered"));
    }
}