package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    private static final String BASE = System.getProperty("baseUrl", System.getenv().getOrDefault("BASE_URL", "http://localhost:8080"));

    @Test(timeout = 60000)
    public void testSeeYouReturnsCu() {
        given().baseUri(BASE).when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response res = given().baseUri(BASE).when().get("/api/text2txt/see/you/now");
        res.then().assertThat().body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testByTheWayReturnsBtw() {
        given().baseUri(BASE).when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response res = given().baseUri(BASE).when().get("/api/text2txt/by/the/way");
        res.then().assertThat().body(equalTo("btw"));
    }
}