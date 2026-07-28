package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String fromProp = System.getProperty("API_BASE_URL");
        String fromEnv = System.getenv("API_BASE_URL");
        String base = Optional.ofNullable(fromProp).orElse(Optional.ofNullable(fromEnv).orElse("http://localhost:8080"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTwoProduces2() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/two/x/x");
        act.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testForProduces4() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/for/anything/else");
        act.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testSeeYouProducesCu() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/see/you/now");
        act.then().body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testByTheWayProducesBtw() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/by/the/way");
        act.then().body(equalTo("btw"));
    }

    @Test(timeout = 60000)
    public void testAreProducesR() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/are/you/now");
        act.then().body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testYouProducesU() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/you/are/here");
        act.then().body(equalTo("u"));
    }
}