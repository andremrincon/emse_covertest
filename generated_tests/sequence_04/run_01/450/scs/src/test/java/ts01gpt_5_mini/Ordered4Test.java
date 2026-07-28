package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrderProducesIncreasingResult() {
        given().when().get("/api/pat/arrange-increasing").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/aaaaa/bbbbb/ddddd/ccccc").then().body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void testDecreasingOrderProducesDecreasingResult() {
        given().when().get("/api/pat/arrange-decreasing").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/mmmmm/lllll/jjjjj/kkkkk").then().body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void testEqualStringsWithinBoundsProduceUnordered() {
        given().when().get("/api/pat/arrange-equal").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/apple/apple/apple/apple").then().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void testOutOfBoundLengthProducesUnordered() {
        given().when().get("/api/pat/arrange-length").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/abcd/bbbbb/ddddd/ccccc").then().body(equalTo("unordered"));
    }
}