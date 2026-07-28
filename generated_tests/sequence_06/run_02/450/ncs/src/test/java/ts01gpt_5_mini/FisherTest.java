package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @Test(timeout = 60000)
    public void testFisher_bothOdd_returns200() {
        String base = System.getenv("BASE_URL");
        if (base == null) base = System.getProperty("api.base", "http://localhost:8080");
        given().baseUri(base).when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().baseUri(base).when().get("/api/fisher/{m}/{n}/{x}", 1, 1, 0.75);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_mOdd_nEven_returns200() {
        String base = System.getenv("BASE_URL");
        if (base == null) base = System.getProperty("api.base", "http://localhost:8080");
        given().baseUri(base).when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().baseUri(base).when().get("/api/fisher/{m}/{n}/{x}", 5, 4, 0.75);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_mEven_nOdd_returns200() {
        String base = System.getenv("BASE_URL");
        if (base == null) base = System.getProperty("api.base", "http://localhost:8080");
        given().baseUri(base).when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().baseUri(base).when().get("/api/fisher/{m}/{n}/{x}", 4, 3, 0.75);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_bothEven_returns200() {
        String base = System.getenv("BASE_URL");
        if (base == null) base = System.getProperty("api.base", "http://localhost:8080");
        given().baseUri(base).when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().baseUri(base).when().get("/api/fisher/{m}/{n}/{x}", 4, 4, 0.75);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_largeX_m1_n3_triggersUpperClamp_returns200() {
        String base = System.getenv("BASE_URL");
        if (base == null) base = System.getProperty("api.base", "http://localhost:8080");
        given().baseUri(base).when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().baseUri(base).when().get("/api/fisher/{m}/{n}/{x}", 1, 3, 1e6);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_invalidM_returns400() {
        String base = System.getenv("BASE_URL");
        if (base == null) base = System.getProperty("api.base", "http://localhost:8080");
        given().baseUri(base).when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response resp = given().baseUri(base).when().get("/api/fisher/{m}/{n}/{x}", "abc", 5, 0.75);
        resp.then().statusCode(400);
    }
}