package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE");
        if (env != null && !env.isEmpty()) {
            RestAssured.baseURI = env;
        } else {
            RestAssured.baseURI = System.getProperty("api.base", "http://localhost:8080");
        }
    }

    @Test(timeout = 60000)
    public void testIncreasingOrderProduces200() {
        given().when().get("/api/pat/health").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/alpha/bravo/delta/charl").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasingOrderProduces200() {
        given().when().get("/api/pat/health2").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/zzzzz/yyyyy/wwwww/xxxxx").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedByValueProduces200() {
        given().when().get("/api/pat/setup").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/apple/mango/lemon/peach").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedByLengthProduces200() {
        given().when().get("/api/pat/arrange").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/shrt/bravo/delta/charl").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBoundaryLengthSixIncreasingProduces200() {
        given().when().get("/api/pat/prime").then().statusCode(lessThan(300));
        given().when().get("/api/ordered4/abcde1/abcde2/abcde4/abcde3").then().statusCode(200);
    }
}