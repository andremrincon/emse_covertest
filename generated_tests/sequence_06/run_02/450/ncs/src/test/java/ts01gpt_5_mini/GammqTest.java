package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("baseUrl", System.getenv().getOrDefault("BASE_URL","http://localhost:8080"));
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testGammqGserBranchReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get(String.format("/api/gammq/%s/%s","5.5","0.001")).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGcfBranchReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get(String.format("/api/gammq/%s/%s","5.5","1000.0")).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidAReturns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get(String.format("/api/gammq/%s/%s","-1.0","2.0")).then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqXZeroHandledByGserReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get(String.format("/api/gammq/%s/%s","5.0","0.0")).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqNegativeXReturns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get(String.format("/api/gammq/%s/%s","5.0","-1.0")).then().statusCode(400);
    }
}