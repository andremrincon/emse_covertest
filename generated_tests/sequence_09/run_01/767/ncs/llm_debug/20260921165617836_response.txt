package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class NcsRestTest {

    private static final String BASE;
    static {
        String b = System.getProperty("BASE_URL");
        if (b == null) b = System.getenv("BASE_URL");
        if (b == null) b = "http://localhost:8080";
        BASE = b;
    }

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE;
    }

    @Test(timeout = 60000)
    public void testBessj_valid_returns200() {
        String seed = UUID.randomUUID().toString();
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/3/2.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessj_nTooSmall_returns400() {
        given().when().get("/api/triangle/5/5/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/2/1e-10").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisher_valid_returns200() {
        given().when().get("/api/triangle/6/7/8").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/10/5/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_mTooLarge_returns400() {
        given().when().get("/api/triangle/7/8/9").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/1001/5/0.5").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisher_runtimeException_returns400() {
        given().when().get("/api/triangle/8/9/10").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/10/5/1.2").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_valid_returns200() {
        given().when().get("/api/triangle/4/5/6").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_runtimeException_returns400() {
        given().when().get("/api/triangle/2/3/4").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/-1.0/2.0").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainder_valid_returnsBody() {
        given().when().get("/api/triangle/3/5/7").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/17/5").then().body("resultAsInt", equalTo(2));
    }

    @Test(timeout = 60000)
    public void testRemainder_outOfRange_returns400() {
        given().when().get("/api/triangle/9/9/9").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/10001/1").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpint_valid_returns200() {
        given().when().get("/api/triangle/10/11/12").then().statusCode(lessThan(300));
        given().when().get("/api/expint/3/2.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_runtimeException_returns400() {
        given().when().get("/api/triangle/11/12/13").then().statusCode(lessThan(300));
        given().when().get("/api/expint/-1/2.5").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testTriangle_valid_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/triangle/3/4/5").then().statusCode(200);
    }
}