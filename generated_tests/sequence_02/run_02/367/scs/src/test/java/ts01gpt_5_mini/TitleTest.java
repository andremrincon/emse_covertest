package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class TitleTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void maleWithMatchingTitle_shouldReturn200() {
        given().when().get("/api/pat/health-" + UUID.randomUUID()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/male/dr");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void maleWithNonMatchingTitle_shouldReturn200() {
        given().when().get("/api/pat/setup-" + UUID.randomUUID()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/male/mrs");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void femaleWithMatchingTitle_shouldReturn200() {
        given().when().get("/api/pat/ready-" + UUID.randomUUID()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/female/ms");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void femaleWithNonMatchingTitle_shouldReturn200() {
        given().when().get("/api/pat/prime-" + UUID.randomUUID()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/female/sir");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void noneWithAllowedTitle_shouldReturn200() {
        given().when().get("/api/pat/check-" + UUID.randomUUID()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/none/prof");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void femaleWithSharedTitle_dr_shouldReturn200() {
        given().when().get("/api/pat/init-" + UUID.randomUUID()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/female/dr");
        assertEquals(200, resp.getStatusCode());
    }
}