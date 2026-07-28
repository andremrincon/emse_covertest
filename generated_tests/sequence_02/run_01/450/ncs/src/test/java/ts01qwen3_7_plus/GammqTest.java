package ts01qwen3_7_plus;

import static io.restassured.RestAssured.given;
import org.junit.Test;

public class GammqTest {

    private static final String BASE_URL = System.getProperty("test.base.url", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testGammqInvalidA() {
        given().when().get(BASE_URL + "/api/gammq/-1.0/2.0").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidX() {
        given().when().get(BASE_URL + "/api/gammq/5.5/-1.0").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqGserZeroX() {
        given().when().get(BASE_URL + "/api/gammq/5.5/0.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGserNormal() {
        given().when().get(BASE_URL + "/api/gammq/5.5/2.3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGcfNormal() {
        given().when().get(BASE_URL + "/api/gammq/0.001/1000.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGcfExtremeX() {
        given().when().get(BASE_URL + "/api/gammq/1.0/2.0E30").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidParamType() {
        given().when().get(BASE_URL + "/api/gammq/abc/2.0").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqGcfLargeA() {
        given().when().get(BASE_URL + "/api/gammq/50.0/100.0").then().statusCode(200);
    }
}