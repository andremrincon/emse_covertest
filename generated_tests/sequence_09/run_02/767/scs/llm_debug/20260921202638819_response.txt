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
        String base = System.getProperty("test.server");
        if (base == null || base.isEmpty()) {
            base = System.getenv("TEST_SERVER");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNotypevar_returnsZero_forI5_andSmallS() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/5/a");
        assertEquals("0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testNotypevar_returnsTwo_forI2_andWorld() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/2/world");
        assertEquals("2", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testNotypevar_triggersMultipleBranches_forI7_andZzz_returnsThree() {
        given().when().get("/api/calc/add/15.5/4.5").then().statusCode(lessThan(300));
        String unique = UUID.randomUUID().toString();
        Response resp = given().when().get("/api/notypevar/7/zzz" + unique).then().extract().response();
        assertEquals("3", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testNotypevar_triggersI0_andI3_forI28_returnsThree() {
        given().when().get("/api/title/male/Smith").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/notypevar/28/aa");
        assertEquals("3", resp.getBody().asString());
    }
}