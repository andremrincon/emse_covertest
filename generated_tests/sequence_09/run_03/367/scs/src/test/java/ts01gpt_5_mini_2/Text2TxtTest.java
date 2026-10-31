package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testWordOneTwoReturnsOk() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/two/alpha/beta");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWordOneForReturnsOk() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/for/x/y");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWordOneYouReturnsOk() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/you/any/thing");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWordOneAreReturnsOk() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/are/you/now");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSeeYouCombinationReturnsOk() {
        given().when().get("/api/pat/ABABCABAB").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/see/you/there");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testByTheWayReturnsOk() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/by/the/way");
        act.then().statusCode(200);
    }
}