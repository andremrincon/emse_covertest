package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTwoReturns2() {
        given().when().get("/api/pat/" + UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/two/alpha/beta");
        resp.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testForAndFourReturn4() {
        given().when().get("/api/pat/" + UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/for/anything/thing");
        resp.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsCu() {
        given().when().get("/api/pat/" + UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/see/you/now");
        resp.then().body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testByTheWayReturnsBtw() {
        given().when().get("/api/pat/" + UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/by/the/way");
        resp.then().body(equalTo("btw"));
    }

    @Test(timeout = 60000)
    public void testArePrecedesSeeYouAndReturnsR() {
        given().when().get("/api/pat/" + UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/are/you/now");
        resp.then().body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testUnknownWordReturnsEmptyString() {
        given().when().get("/api/pat/" + UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/hello/world/foo");
        resp.then().body(equalTo(""));
    }
}