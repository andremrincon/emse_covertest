package ts01gpt_5_mini;

import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    private String baseUri() {
        String b = System.getenv("BASE_URL");
        return (b == null || b.isEmpty()) ? "http://localhost:8080" : b;
    }

    @Test(timeout = 60000)
    public void testWordTwoReturns2() {
        RestAssured.baseURI = baseUri();
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/two/anything/else");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordFourReturns4() {
        RestAssured.baseURI = baseUri();
        given().when().get("/api/pat/sample").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/four/quick/brown");
        act.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testWordYouReturnsU() {
        RestAssured.baseURI = baseUri();
        given().when().get("/api/pat/hello").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/you/are/here");
        act.then().body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testWordAreReturnsR() {
        RestAssured.baseURI = baseUri();
        given().when().get("/api/pat/check").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/are/you/ok");
        act.then().body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsCu() {
        RestAssured.baseURI = baseUri();
        given().when().get("/api/pat/arrange").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/see/you/now");
        act.then().body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testByTheWayReturnsBtw() {
        RestAssured.baseURI = baseUri();
        given().when().get("/api/pat/setup").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/by/the/way");
        act.then().body(equalTo("btw"));
    }
}