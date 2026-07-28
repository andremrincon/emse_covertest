package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void increasingOrderReturnsIncreasing() {
        given().when().get("/api/pat/ok").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/aaaaa/bbbbb/ddddd/ccccc").then().body(equalTo("increasing"));
    }

    @Test(timeout = 60000)
    public void decreasingOrderReturnsDecreasing() {
        given().when().get("/api/pat/ok").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/zzzzz/yyyyy/wwwww/xxxxx").then().body(equalTo("decreasing"));
    }

    @Test(timeout = 60000)
    public void lengthOutOfRangeReturnsUnordered() {
        given().when().get("/api/pat/ok").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/four/bbbbb/ddddd/ccccc").then().body(equalTo("unordered"));
    }

    @Test(timeout = 60000)
    public void validLengthsButNoOrderReturnsUnordered() {
        given().when().get("/api/pat/ok").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/aaaaa/ccccc/ddddd/bbbbb").then().body(equalTo("unordered"));
    }
}