package ts01gpt_5_mini_2;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class NcsRestTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        String env = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            if (env != null && !env.isEmpty()) {
                base = env;
            } else {
                base = "http://localhost:8080";
            }
        }
        baseURI = base;
    }

    @Test(timeout = 60000)
    public void testBessjReturns200_ForValidN() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", 3, 2.5).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBessjReturns400_ForNTooSmall() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/bessj/{n}/{x}", 2, 2.5).then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherReturns200_ForValid() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/{m}/{n}/{x}", 10, 5, 0.75).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherReturns400_ForMTooLarge() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/{m}/{n}/{x}", 1001, 5, 0.5).then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisherReturns400_ForNTooLarge() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/{m}/{n}/{x}", 10, 1001, 0.5).then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderReturns200_Valid() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/{a}/{b}", 17, 5).then().body("resultAsInt", equalTo(2));
    }

    @Test(timeout = 60000)
    public void testRemainderReturns400_AOutOfRange() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/{a}/{b}", 10001, 1).then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintReturns200_ForValid() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", 3, 2.5).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqReturns200_ForValid() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/{a}/{x}", 5.5, 2.3).then().statusCode(200);
    }
}