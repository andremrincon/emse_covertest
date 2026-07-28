package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NcsRestTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("NCS_BASE_URL");
        if (base == null) base = System.getProperty("ncs.baseUrl");
        if (base == null) base = System.getenv("NCS_BASE_URL");
        if (base == null) base = System.getenv("ncs.baseUrl");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testBessjSuccess() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/3/2.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjBadN() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/2/2.5").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherSuccess() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/10/5/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherBadLargeM() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/1001/5/0.75").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherRuntimeExceptionPath() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/10/5/1.2").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqSuccess() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqBadA() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/-1.0/2.3").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderSuccess() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/17/5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderOutOfRange() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/20000/5").then().statusCode(400);
    }
}