package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CostfunsTest {

    private static final String BASE_URL = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";

    @Test(timeout = 60000)
    public void testCostfuns_5_baab() {
        String uuid = java.util.UUID.randomUUID().toString();
        given().when().get(BASE_URL + "/api/costfuns/0/a").then().statusCode(lessThan(300));

        Response response = given().when().get(BASE_URL + "/api/costfuns/5/baab");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_minus500_ababba() {
        String uuid = java.util.UUID.randomUUID().toString();
        given().when().get(BASE_URL + "/api/costfuns/0/a").then().statusCode(lessThan(300));

        Response response = given().when().get(BASE_URL + "/api/costfuns/-500/ababba");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_600_abab() {
        String uuid = java.util.UUID.randomUUID().toString();
        given().when().get(BASE_URL + "/api/costfuns/0/a").then().statusCode(lessThan(300));

        Response response = given().when().get(BASE_URL + "/api/costfuns/600/abab");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_700_ababbc() {
        String uuid = java.util.UUID.randomUUID().toString();
        given().when().get(BASE_URL + "/api/costfuns/0/a").then().statusCode(lessThan(300));

        Response response = given().when().get(BASE_URL + "/api/costfuns/700/ababbc");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_minus4_a() {
        String uuid = java.util.UUID.randomUUID().toString();
        given().when().get(BASE_URL + "/api/costfuns/0/a").then().statusCode(lessThan(300));

        Response response = given().when().get(BASE_URL + "/api/costfuns/-4/a");

        response.then().statusCode(200);
    }
}