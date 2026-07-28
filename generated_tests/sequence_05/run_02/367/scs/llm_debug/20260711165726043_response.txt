package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("api.base", System.getenv().getOrDefault("API_BASE", "http://localhost:8080"));
    }

    @Test(timeout = 60000)
    public void testWordTwoReturns2() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/two/ignore/ignore");
        resp.then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordForReturns4() {
        given().when().get("/api/text2txt/two/x/y").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/for/anything/anything");
        resp.then().assertThat().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testWordYouReturnsU() {
        given().when().get("/api/text2txt/for/a/b").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/you/x/y");
        resp.then().assertThat().body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testWordAndReturnsN() {
        given().when().get("/api/text2txt/you/a/b").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/and/x/y");
        resp.then().assertThat().body(equalTo("n"));
    }

    @Test(timeout = 60000)
    public void testWordAreReturnsR_PreventsElseChain() {
        given().when().get("/api/text2txt/and/a/b").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/are/you/now");
        resp.then().assertThat().body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testSeeYouAndByTheWayVariantsCoveredViaArrangeButActIsSeeYou() {
        given().when().get("/api/text2txt/by/the/way").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/text2txt/see/you/now");
        resp.then().assertThat().body(equalTo("cu"));
    }
}