package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("baseUrl");
        if (env == null || env.isEmpty()) env = System.getenv("BASE_URL");
        if (env == null || env.isEmpty()) env = "http://localhost:8080";
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testGammq_Gser_WhenXZero_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/{a}/{x}", "5.5", "0.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_Gser_NormalConverge_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/{a}/{x}", "5.5", "2.3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_Gcf_LargeX_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/{a}/{x}", "5.5", "1000.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_Gcf_BorderlineXEqualsAplus1_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/{a}/{x}", "5.5", "6.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_InvalidA_Negative_Returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/{a}/{x}", "-1.0", "2.0").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammq_InvalidX_Negative_Returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/{a}/{x}", "5.5", "-0.1").then().statusCode(400);
    }
}