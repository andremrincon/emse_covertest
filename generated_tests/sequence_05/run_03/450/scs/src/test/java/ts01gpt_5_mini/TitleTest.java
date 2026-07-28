package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class TitleTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMaleTitleReturnsOne() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "Male", "MR");
        assertEquals("1", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testFemaleTitleReturnsZero() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "FEMALE", "Miss");
        assertEquals("0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testNoneTitleReturnsTwo() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "none", "Prof");
        assertEquals("2", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testUnknownTitleReturnsMinusOne() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "other", "Captain");
        assertEquals("-1", resp.getBody().asString());
    }
}