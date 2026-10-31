package ts01gpt_5_mini_2;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.given;

public class GammqTest {

    private static String baseUrl;

    @BeforeClass
    public static void init() {
        String fromProp = System.getProperty("base.url");
        String fromEnv = System.getenv("BASE_URL");
        baseUrl = fromProp != null && !fromProp.isEmpty() ? fromProp : (fromEnv != null && !fromEnv.isEmpty() ? fromEnv : "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testGammq_usesGser_path_returns200() {
        given().baseUri(baseUrl).when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().get("/api/gammq/5.5/2.3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_usesGcf_path_returns200() {
        given().baseUri(baseUrl).when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().get("/api/gammq/0.001/1000.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_negativeX_triggersGserException_returns400() {
        given().baseUri(baseUrl).when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().get("/api/gammq/5.5/-1.0").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammq_zeroX_gserSetsGamser_returns200() {
        given().baseUri(baseUrl).when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().baseUri(baseUrl).when().get("/api/gammq/0.5/0.0").then().statusCode(200);
    }
}