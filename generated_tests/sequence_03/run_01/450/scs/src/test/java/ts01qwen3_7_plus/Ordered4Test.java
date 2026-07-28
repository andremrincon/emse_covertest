package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class Ordered4Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"unordered\"   Actual: ...")
    @Test(timeout = 60000)
    public void testWInvalidLength() {
        String w = "app";
        String x = "banana";
        String z = "delta";
        String y = "cherry";

        Response response = given().when().get("/api/ordered4/" + w + "/" + x + "/" + z + "/" + y);

        response.then().statusCode(404).body(equalTo("unordered"));
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"unordered\"   Actual: ...")
    @Test(timeout = 60000)
    public void testXInvalidLength() {
        String w = "apple";
        String x = "ban";
        String z = "delta";
        String y = "cherry";

        Response response = given().when().get("/api/ordered4/" + w + "/" + x + "/" + z + "/" + y);

        response.then().statusCode(404).body(equalTo("unordered"));
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"unordered\"   Actual: ...")
    @Test(timeout = 60000)
    public void testYInvalidLength() {
        String w = "apple";
        String x = "banana";
        String z = "delta";
        String y = "cherryx";

        Response response = given().when().get("/api/ordered4/" + w + "/" + x + "/" + z + "/" + y);

        response.then().statusCode(404).body(equalTo("unordered"));
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"unordered\"   Actual: ...")
    @Test(timeout = 60000)
    public void testZInvalidLength() {
        String w = "apple";
        String x = "banana";
        String z = "del";
        String y = "cherry";

        Response response = given().when().get("/api/ordered4/" + w + "/" + x + "/" + z + "/" + y);

        response.then().statusCode(404).body(equalTo("unordered"));
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"increasing\"   Actual:...")
    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        String w = "apple";
        String x = "banana";
        String z = "delta";
        String y = "cherry";

        Response response = given().when().get("/api/ordered4/" + w + "/" + x + "/" + z + "/" + y);

        response.then().statusCode(404).body(equalTo("increasing"));
    }

    @Ignore("1 expectation failed. Response body doesn't match expectation. Expected: \"decreasing\"   Actual:...")
    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        String w = "zebra";
        String x = "yakxx";
        String z = "vultu";
        String y = "wolfx";

        Response response = given().when().get("/api/ordered4/" + w + "/" + x + "/" + z + "/" + y);

        response.then().statusCode(404).body(equalTo("decreasing"));
    }
}