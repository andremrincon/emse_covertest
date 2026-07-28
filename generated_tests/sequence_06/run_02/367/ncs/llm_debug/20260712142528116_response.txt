package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGammq_usesGcf_forLargeX_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/5.5/1000.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_usesGser_withXZero_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/5.5/0.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammq_negativeX_isBadRequest_returns400() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/5.5/-1.0").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammq_nonPositiveA_isBadRequest_returns400() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/-1.0/2.0").then().statusCode(400);
    }
}