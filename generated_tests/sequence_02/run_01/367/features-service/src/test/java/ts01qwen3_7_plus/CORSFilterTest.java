package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;

public class CORSFilterTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("test.base.url", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testGetRequestSetsCORSHeaders() {
        String productName = "Product-" + System.currentTimeMillis();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        Response response = given().when().get("/products/{productName}", productName);

        response.then().statusCode(200).header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testOptionsRequestSetsCORSHeaders() {
        Response response = given().when().options("/products");

        response.then().statusCode(anyOf(is(200), is(204))).header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testPostRequestSetsCORSHeaders() {
        String productName = "Product-" + System.currentTimeMillis();

        Response response = given().when().post("/products/{productName}", productName);

        response.then().statusCode(201).header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testPutRequestSetsCORSHeaders() {
        String productName = "Product-" + System.currentTimeMillis();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String featureName = "Feature-" + System.currentTimeMillis();
        given().when().post("/products/{productName}/features/{featureName}", productName, featureName).then().statusCode(lessThan(300));

        Response response = given().when().put("/products/{productName}/features/{featureName}", productName, featureName);

        response.then().statusCode(500).header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testDeleteRequestSetsCORSHeaders() {
        String productName = "Product-" + System.currentTimeMillis();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));

        Response response = given().when().delete("/products/{productName}", productName);

        response.then().statusCode(204).header("Access-Control-Allow-Origin", "*");
    }
}