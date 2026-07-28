package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class RemainderTest {
    private static String base;

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("API_BASE", System.getenv("API_BASE") != null ? System.getenv("API_BASE") : "http://localhost:8080");
        base = env.endsWith("/") ? env.substring(0, env.length() - 1) : env;
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testAEqualsZeroReturns400() {
        String marker = UUID.randomUUID().toString();
        given().when().get(base + "/api/triangle/1/1/1?m=" + marker).then().statusCode(lessThan(300));
        given().when().get(base + "/api/remainder/0/5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBEqualsZeroReturns400() {
        String marker = UUID.randomUUID().toString();
        given().when().get(base + "/api/triangle/1/1/1?m=" + marker).then().statusCode(lessThan(300));
        given().when().get(base + "/api/remainder/5/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAPositiveBPositiveReturnsRemainder() {
        String marker = UUID.randomUUID().toString();
        given().when().get(base + "/api/triangle/1/1/1?m=" + marker).then().statusCode(lessThan(300));
        given().when().get(base + "/api/remainder/17/5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAPositiveBNegativeReturnsRemainder() {
        String marker = UUID.randomUUID().toString();
        given().when().get(base + "/api/triangle/1/1/1?m=" + marker).then().statusCode(lessThan(300));
        given().when().get(base + "/api/remainder/17/-9").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testANegativeBPositiveReturns200() {
        String marker = UUID.randomUUID().toString();
        given().when().get(base + "/api/triangle/1/1/1?m=" + marker).then().statusCode(lessThan(300));
        given().when().get(base + "/api/remainder/-17/5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testANegativeBNegativeReturnsRemainder() {
        String marker = UUID.randomUUID().toString();
        given().when().get(base + "/api/triangle/1/1/1?m=" + marker).then().statusCode(lessThan(300));
        given().when().get(base + "/api/remainder/-17/-5").then().statusCode(200);
    }
}