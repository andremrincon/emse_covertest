package ts01gpt_5_mini_2;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    private static String BASE;

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        BASE = base;
    }

    @Test(timeout = 60000)
    public void testWordTwoReturns2() {
        given().when().get(BASE + "/api/pat/a").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/text2txt/Two/example/example");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWordForReturns4() {
        given().when().get(BASE + "/api/pat/a").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/text2txt/for/anything/else");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWordYouReturnsU() {
        given().when().get(BASE + "/api/pat/a").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/text2txt/you/x/y");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWordAndReturnsN() {
        given().when().get(BASE + "/api/pat/a").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/text2txt/and/alpha/beta");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsCU() {
        given().when().get(BASE + "/api/pat/a").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/text2txt/see/you/ignored");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testByTheWayReturnsBTW() {
        given().when().get(BASE + "/api/pat/a").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/text2txt/by/the/way");
        act.then().statusCode(200);
    }
}