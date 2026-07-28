package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class BessjTest {

    @BeforeClass
    public static void init() throws Exception {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        java.net.URI u = new java.net.URI(base);
        String scheme = u.getScheme() != null ? u.getScheme() : "http";
        String host = u.getHost() != null ? u.getHost() : base;
        RestAssured.baseURI = scheme + "://" + host;
        if (u.getPort() != -1) RestAssured.port = u.getPort();
        if (u.getPath() != null && !u.getPath().isEmpty() && !u.getPath().equals("/")) RestAssured.basePath = u.getPath();
    }

    @Test(timeout = 60000)
    public void testBessjInvalidNBelowTwoReturns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/1/2.5");
        resp.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testBessjWithZeroXReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/3/0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxGreaterThanNPathReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/3/5.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjAxLessOrEqualPathReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/10/1.0");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjNegativeXOddNSignHandledReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/3/-2.5");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjMalformedNParamReturns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/bessj/abc/2.5");
        resp.then().statusCode(400);
    }
}