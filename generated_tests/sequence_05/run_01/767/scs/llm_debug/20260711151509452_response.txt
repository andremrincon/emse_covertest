package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class TitleTest {

    private static final String BASE;
    static {
        String p = System.getProperty("baseUrl");
        if (p == null || p.isEmpty()) p = System.getenv("BASE_URL");
        if (p == null || p.isEmpty()) p = "http://localhost:8080";
        BASE = p;
    }

    @Test(timeout = 60000)
    public void testMaleMatchingTitleMr() {
        String uuid = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/pat/" + uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/title/{sex}/{title}", "male", "mr");
        assertEquals("1", resp.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testMaleNonMatchingTitleQueen() {
        String uuid = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/pat/" + uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/title/{sex}/{title}", "male", "queen");
        assertEquals("-1", resp.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testFemaleMatchingTitleMs() {
        String uuid = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/pat/" + uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/title/{sex}/{title}", "female", "ms");
        assertEquals("0", resp.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testFemaleNonMatchingTitleKing() {
        String uuid = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/pat/" + uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/title/{sex}/{title}", "female", "king");
        assertEquals("-1", resp.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testNoneMatchingTitleDr() {
        String uuid = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/pat/" + uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/title/{sex}/{title}", "none", "dr");
        assertEquals("2", resp.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testNoneNonMatchingTitleMr() {
        String uuid = UUID.randomUUID().toString();
        given().when().get(BASE + "/api/pat/" + uuid).then().statusCode(lessThan(300));
        Response resp = given().when().get(BASE + "/api/title/{sex}/{title}", "none", "mr");
        assertEquals("-1", resp.getBody().asString().trim());
    }
}