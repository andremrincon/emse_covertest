package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GammqTest {

    private String getBaseUrl() {
        String envUrl = System.getenv("BASE_URL");
        return (envUrl != null && !envUrl.isEmpty()) ? envUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testGammqGserNormalPath() {
        double a = 5.5;
        double x = 2.3;
        Response response = given().baseUri(getBaseUrl()).when().get("/api/gammq/" + a + "/" + x);
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGserZeroX() {
        double a = 0.001;
        double x = 0.0;
        Response response = given().baseUri(getBaseUrl()).when().get("/api/gammq/" + a + "/" + x);
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGcfLargeX() {
        double a = 5.5;
        double x = 1000.0;
        Response response = given().baseUri(getBaseUrl()).when().get("/api/gammq/" + a + "/" + x);
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGcfSmallA() {
        double a = 0.5;
        double x = 5.0;
        Response response = given().baseUri(getBaseUrl()).when().get("/api/gammq/" + a + "/" + x);
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidA() {
        double a = -1.0;
        double x = 2.0;
        Response response = given().baseUri(getBaseUrl()).when().get("/api/gammq/" + a + "/" + x);
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidX() {
        double a = 2.0;
        double x = -1.0;
        Response response = given().baseUri(getBaseUrl()).when().get("/api/gammq/" + a + "/" + x);
        response.then().statusCode(400);
    }
}