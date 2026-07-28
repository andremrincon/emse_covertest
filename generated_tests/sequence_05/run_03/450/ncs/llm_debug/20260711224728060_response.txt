package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class FisherTest {
    static {
        String base = System.getProperty("BASE_URL", System.getenv("BASE_URL"));
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testFisher_whenA1AndB1_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/3/3/0.75");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisher_whenA1AndBNot1_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/3/4/0.75");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisher_whenANot1AndB1_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/fisher/4/3/0.5");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFisher_whenNeitherAOrBIs1_returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        String uniqueX = String.valueOf((UUID.randomUUID().hashCode() & 0x7fffffff) % 1000 / 1000.0);
        Response act = given().when().get("/api/fisher/4/2/" + uniqueX);
        assertEquals(200, act.getStatusCode());
    }
}