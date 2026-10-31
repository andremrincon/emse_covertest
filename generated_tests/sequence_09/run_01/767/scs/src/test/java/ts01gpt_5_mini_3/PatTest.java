package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class PatTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("baseUrl");
        if (env == null || env.isEmpty()) {
            env = System.getenv("BASE_URL");
        }
        RestAssured.baseURI = (env == null || env.isEmpty()) ? "http://localhost:8080" : env;
    }

    @Test(timeout = 60000)
    public void testShortPatReturnsZero() {
        given().when().get("/api/pat/{txt}", "healthcheck").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "abcdef", "ab").then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatThenReverseNonAdjacentReturnsIndex() {
        given().when().get("/api/pat/{txt}", "setupX").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "xabcxxxcba", "abc").then().body(equalTo("1"));
    }

    @Test(timeout = 60000)
    public void testReverseOnlyReturnsTwo() {
        given().when().get("/api/pat/{txt}", "setupY").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "zzcbaqq", "abc").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testReverseThenPatAdjacentReturnsIndex() {
        given().when().get("/api/pat/{txt}", "setupZ").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "cbaabc", "abc").then().body(equalTo("0"));
    }

    @Test(timeout = 60000)
    public void testPatFoundWithoutReverseReturnsOne() {
        given().when().get("/api/pat/{txt}", "setupA").then().statusCode(lessThan(300));
        given().when().get("/api/pat/{txt}/{pat}", "zabczzzz", "abc").then().body(equalTo("1"));
    }
}