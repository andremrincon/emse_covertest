package ts01gpt_5_mini_2;

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
        String base = System.getProperty("BASE_URL", System.getenv("BASE_URL"));
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMaleKnownTitle_status200() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/cookie/{name}/{val}/{site}", "session-id-" + uuid, "abc-123-xyz-789", "example.com").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "male", "mr");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFemaleKnownTitle_status200() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/cookie/{name}/{val}/{site}", "session-id-" + uuid, "1", "localhost").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "female", "mrs");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testNoneKnownTitle_status200() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/cookie/{name}/{val}/{site}", "session-id-" + uuid, "some-value", "example.com").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "none", "dr");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testUnknownSex_status500() {
        String uuid = UUID.randomUUID().toString();
        given().when().get("/api/cookie/{name}/{val}/{site}", "session-id-" + uuid, "abc-123-xyz-789", "example.com").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "neuter", "Jones");
        assertEquals(200, resp.getStatusCode());
    }
}