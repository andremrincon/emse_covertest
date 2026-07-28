package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTwoMapsTo2() {
        given().when().get("/api/text2txt/and/alpha/beta").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/four/x/y").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/two/anything/else");
        assertEquals("2", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testForMapsTo4() {
        given().when().get("/api/text2txt/you/a/b").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/two/x/y").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/for/placeholder/val");
        assertEquals("4", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testYouMapsToU() {
        given().when().get("/api/text2txt/for/one/two").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/are/x/y").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/you/doesnt/matter");
        assertEquals("u", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testAreMapsToR() {
        given().when().get("/api/text2txt/see/you/here").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/by/the/way").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/are/ignored/ignored");
        assertEquals("r", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSeeYouMapsToCu() {
        given().when().get("/api/text2txt/are/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/for/four/five").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/see/you/now");
        assertEquals("cu", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testByTheWayMapsToBtw() {
        given().when().get("/api/text2txt/you/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/see/notyou/here").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/by/the/way");
        assertEquals("btw", act.getBody().asString());
    }
}