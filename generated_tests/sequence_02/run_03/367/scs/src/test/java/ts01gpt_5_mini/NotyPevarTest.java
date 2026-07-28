package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class NotyPevarTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i28_returns200() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", uid, "quick", "brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 28, "a");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i7_finalResult3_inBody() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", uid, "quick", "brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 7, "a");
        assertEquals("3", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testNotyPevar_i2_compareToBranch_returns2() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", uid, "quick", "brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/{i}/{s}", 2, "z");
        assertEquals("2", resp.getBody().asString());
    }
}