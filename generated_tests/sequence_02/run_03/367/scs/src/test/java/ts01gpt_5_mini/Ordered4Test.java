package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrderProduces200() {
        given().when().get("/api/pat/TheQuickBrown").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "aaaaa", "bbbbb", "ddddd", "ccccc").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasingOrderProduces200() {
        given().when().get("/api/pat/healthcheck").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "zzzzz", "yyyyy", "wwwww", "xxxxx").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLengthsValidButUnorderedProduces200() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", "brown").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "apple", "apple", "apple", "apple").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testLengthConstraintFailsProduces200() {
        given().when().get("/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/{w}/{x}/{z}/{y}", "four", "bbbbb", "ccccc", "ddddd").then().statusCode(200);
    }
}