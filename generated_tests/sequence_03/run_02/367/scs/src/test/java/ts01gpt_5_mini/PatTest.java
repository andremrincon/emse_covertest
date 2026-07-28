package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class PatTest {
    private static String BASE;

    @BeforeClass
    public static void init() {
        String b = System.getProperty("base.url");
        if (b == null || b.isEmpty()) {
            b = System.getenv("BASE_URL");
        }
        if (b == null || b.isEmpty()) {
            b = "http://localhost:8080";
        }
        BASE = b;
    }

    @Test(timeout = 60000)
    public void testShortPatternReturns0() {
        given().when().get(BASE + "/api/pat/healthcheck").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/pat/abcdef/ab").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOnlyPatFoundReturns200() {
        given().when().get(BASE + "/api/pat/healthcheck").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/pat/xxxABCyyy/ABC").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testOnlyReverseFoundReturns200() {
        given().when().get(BASE + "/api/pat/healthcheck").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/pat/xxxCBAyyy/ABC").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPalindromicAdjacentReturns200() {
        given().when().get(BASE + "/api/pat/healthcheck").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/pat/ABAABA/ABA").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPatThenReverseNonAdjacentReturns200() {
        given().when().get(BASE + "/api/pat/healthcheck").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/pat/ABCxxxCBA/ABC").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testReverseThenPatAdjacentReturns200() {
        given().when().get(BASE + "/api/pat/healthcheck").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/pat/CBAABC/ABC").then().statusCode(200);
    }
}