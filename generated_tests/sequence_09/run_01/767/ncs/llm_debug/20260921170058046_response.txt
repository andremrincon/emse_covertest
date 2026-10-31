package ts01gpt_5_mini_3;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NcsRestTest {

    private static final String BASE = System.getProperty("ncs.base", System.getenv().getOrDefault("NCS_BASE_URL", "http://localhost:8080"));

    @Test(timeout = 60000)
    public void testBessjSuccess200() {
        given().baseUri(BASE).when().get("/api/expint/3/2.5").then().statusCode(lessThan(300));
        given().baseUri(BASE).when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().baseUri(BASE).when().get("/api/bessj/3/2.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjBadN400() {
        given().baseUri(BASE).when().get("/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        given().baseUri(BASE).when().get("/api/bessj/2/2.5").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherSuccess200() {
        given().baseUri(BASE).when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().baseUri(BASE).when().get("/api/fisher/10/5/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherBadM400() {
        given().baseUri(BASE).when().get("/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        given().baseUri(BASE).when().get("/api/fisher/1001/5/0.75").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintBadN400() {
        given().baseUri(BASE).when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        given().baseUri(BASE).when().get("/api/expint/-1/2.5").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqBadA400() {
        given().baseUri(BASE).when().get("/api/expint/3/2.5").then().statusCode(lessThan(300));
        given().baseUri(BASE).when().get("/api/gammq/-1.0/2.3").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderBad400() {
        given().baseUri(BASE).when().get("/api/fisher/10/5/0.75").then().statusCode(lessThan(300));
        given().baseUri(BASE).when().get("/api/remainder/10001/1").then().statusCode(400);
    }
}