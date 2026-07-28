package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testUnorderedWhenLengthTooShort() {
        given().when().get("/api/ordered4/99/88/77/66").then().statusCode(lessThan(300)).body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        given().when().get("/api/ordered4/apple/berry/delta/candy").then().statusCode(lessThan(300)).body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        given().when().get("/api/ordered4/zebra/yacht/vapor/wagon").then().statusCode(lessThan(300)).body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testUnorderedWhenLengthPassesButNotOrdered() {
        given().when().get("/api/ordered4/apple/apple/apple/apple").then().statusCode(lessThan(300)).body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testUnorderedWhenOneStringTooLong() {
        given().when().get("/api/ordered4/zebra/yak/x-ray/wolf").then().statusCode(lessThan(300)).body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testServerErrorWithSingleCharInput() {
        given().when().get("/api/ordered4/a/b/c/d").then().statusCode(equalTo(200));
    }
}