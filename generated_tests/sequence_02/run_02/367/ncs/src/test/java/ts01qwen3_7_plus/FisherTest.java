package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class FisherTest {

    private String getBaseUrl() {
        String envUrl = System.getenv("BASE_URL");
        return (envUrl != null && !envUrl.isEmpty()) ? envUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testFisherA1B1() {
        Response response = given()
                .baseUri(getBaseUrl())
                .when()
                .get("/api/fisher/1/1/0.5");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherA1B2() {
        Response response = given()
                .baseUri(getBaseUrl())
                .when()
                .get("/api/fisher/1/2/0.5");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherPNegative() {
        Response response = given()
                .baseUri(getBaseUrl())
                .when()
                .get("/api/fisher/2/2/-0.5");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherPGreaterThanOne() {
        Response response = given()
                .baseUri(getBaseUrl())
                .when()
                .get("/api/fisher/2/2/-2.0");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidParam() {
        Response response = given()
                .baseUri(getBaseUrl())
                .when()
                .get("/api/fisher/abc/2/0.5");

        response.then().statusCode(400);
    }
}