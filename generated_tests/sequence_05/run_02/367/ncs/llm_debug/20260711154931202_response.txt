package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NcsRestTest {

    @BeforeClass
    public static void setup() {
        String url = System.getProperty("baseUrl");
        if (url == null || url.isEmpty()) {
            url = System.getenv("BASE_URL");
        }
        if (url == null || url.isEmpty()) {
            url = "http://localhost:8080";
        }
        RestAssured.baseURI = url;
    }

    @Test(timeout = 60000)
    public void testFisherSuccessReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/10/5/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherWithLargeMReturns400() {
        given().when().get("/api/triangle/1/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/1001/5/0.75").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testRemainderSuccessReturns200() {
        given().when().get("/api/triangle/2/3/4").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/17/5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainderOutOfBoundsReturns400() {
        given().when().get("/api/triangle/1/1/2").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/10001/1").then().statusCode(400);
    }
}