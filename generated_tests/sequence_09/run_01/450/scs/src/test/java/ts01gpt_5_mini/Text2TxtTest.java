package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTwoReturns2() {
        given().when().get("/api/text2txt/for/quick/brown").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/you/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/two/anything/whatever");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testForReturns4() {
        given().when().get("/api/text2txt/two/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/and/a/b").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/for/quick/brown");
        act.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsCu() {
        given().when().get("/api/text2txt/are/alpha/beta").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/for/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/see/you/now");
        act.then().body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testByTheWayReturnsBtw() {
        given().when().get("/api/text2txt/see/you/now").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/are/x/y").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/by/the/way");
        act.then().body(equalTo("btw"));
    }

    @Test(timeout = 60000)
    public void testAreReturnsR() {
        given().when().get("/api/text2txt/by/the/way").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/for/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/are/some/thing");
        act.then().body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testYouReturnsU() {
        given().when().get("/api/text2txt/two/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/for/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/you/anything/else");
        act.then().body(equalTo("u"));
    }
}