package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class NcsRestTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("ncs.base", System.getenv("NCS_BASE_URL"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testFisherSuccess() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/10/5/0.75");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisherMTooLarge() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/1001/5/0.75");
        assertEquals(400, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testRemainderOutOfRange() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/20000/5");
        assertEquals(400, act.getStatusCode());
    }
}