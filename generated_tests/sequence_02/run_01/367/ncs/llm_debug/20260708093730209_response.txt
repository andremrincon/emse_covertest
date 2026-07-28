package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("test.base.url", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testFisherMOddNEven() {
        given().when().get("/api/fisher/1/2/0.75").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/1/2/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherPGreaterThanOne() {
        given().when().get("/api/fisher/2/4/1e15").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/2/4/1e15").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherPBetweenZeroAndOne() {
        given().when().get("/api/fisher/10/5/0.75").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/10/5/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherInvalidParameter() {
        given().when().get("/api/fisher/1/2/0.75").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/abc/5/0.75").then().statusCode(400);
    }
}