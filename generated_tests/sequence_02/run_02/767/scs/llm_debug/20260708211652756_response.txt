package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTwoReturns2() {
        String u1 = UUID.randomUUID().toString();
        String u2 = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/are/" + u1 + "/" + u2).then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/for/" + UUID.randomUUID() + "/" + UUID.randomUUID()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/two/" + UUID.randomUUID() + "/" + UUID.randomUUID());
        assertEquals("2", resp.asString());
    }

    @Test(timeout = 60000)
    public void testForReturns4() {
        given().when().get("/api/text2txt/" + UUID.randomUUID() + "/" + UUID.randomUUID() + "/" + UUID.randomUUID()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/for/" + UUID.randomUUID() + "/" + UUID.randomUUID());
        assertEquals("4", resp.asString());
    }

    @Test(timeout = 60000)
    public void testYouReturnsU() {
        given().when().get("/api/text2txt/and/" + UUID.randomUUID() + "/" + UUID.randomUUID()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/you/" + UUID.randomUUID() + "/" + UUID.randomUUID());
        assertEquals("u", resp.asString());
    }

    @Test(timeout = 60000)
    public void testAndReturnsN() {
        given().when().get("/api/text2txt/two/" + UUID.randomUUID() + "/" + UUID.randomUUID()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/and/" + UUID.randomUUID() + "/" + UUID.randomUUID());
        assertEquals("n", resp.asString());
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsCu() {
        given().when().get("/api/text2txt/are/" + UUID.randomUUID() + "/" + UUID.randomUUID()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/see/you/" + UUID.randomUUID());
        assertEquals("cu", resp.asString());
    }

    @Test(timeout = 60000)
    public void testByTheWayReturnsBtw() {
        given().when().get("/api/text2txt/you/" + UUID.randomUUID() + "/" + UUID.randomUUID()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/by/the/way");
        assertEquals("btw", resp.asString());
    }
}