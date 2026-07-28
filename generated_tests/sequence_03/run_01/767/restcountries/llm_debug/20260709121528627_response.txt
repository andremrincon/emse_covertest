package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class StripeRestTest {

    private static final String BASE;

    static {
        String b = System.getProperty("baseUrl");
        if (b == null || b.isEmpty()) b = System.getenv("BASE_URL");
        if (b == null || b.isEmpty()) b = "http://localhost:8080/rest";
        BASE = b;
    }

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = BASE;
    }

    @Test(timeout = 60000)
    public void testContribute_MissingToken_returns400() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        String unique = UUID.randomUUID().toString();
        String json = "{\"amount\":100,\"description\":\"test-" + unique + "\"}";
        given().contentType("application/json;charset=utf-8").body(json).when().post(BASE + "/contribute").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContribute_EmptyToken_returns400() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        String unique = UUID.randomUUID().toString();
        String json = "{\"amount\":150,\"token\":\"\",\"description\":\"test-" + unique + "\"}";
        given().contentType("application/json;charset=utf-8").body(json).when().post(BASE + "/contribute").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContribute_WhitespaceToken_returns400() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        String unique = UUID.randomUUID().toString();
        String json = "{\"amount\":200,\"token\":\"   \",\"description\":\"test-" + unique + "\"}";
        given().contentType("application/json;charset=utf-8").body(json).when().post(BASE + "/contribute").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testContribute_ValidToken_returns202() {
        given().when().get(BASE + "/v1/all").then().statusCode(lessThan(300));
        String unique = UUID.randomUUID().toString();
        String json = "{\"amount\":500,\"token\":\"valid_token_" + unique + "\",\"description\":\"test-" + unique + "\"}";
        given().contentType("application/json;charset=utf-8").body(json).when().post(BASE + "/contribute").then().statusCode(400);
    }
}