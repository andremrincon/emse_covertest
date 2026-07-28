package ts01gpt_5_mini;

import org.junit.Test;
import java.util.UUID;
import static org.junit.Assert.assertEquals;
import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    private final String base = System.getProperty("api.base", System.getenv("API_BASE")) == null ? "http://localhost:8080" : (System.getProperty("api.base", System.getenv("API_BASE")));

    @Test(timeout = 60000)
    public void testMaleRecognizedTitleReturnsOne() {
        given().when().get(base + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/title/male/Mr").then().statusCode(200).extract().response();
        assertEquals("1", act.asString().trim());
    }

    @Test(timeout = 60000)
    public void testFemaleRecognizedTitleReturnsZero() {
        given().when().get(base + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/title/female/MS").then().statusCode(200).extract().response();
        assertEquals("0", act.asString().trim());
    }

    @Test(timeout = 60000)
    public void testNoneRecognizedTitleReturnsTwo() {
        given().when().get(base + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/title/none/Prof").then().statusCode(200).extract().response();
        assertEquals("2", act.asString().trim());
    }

    @Test(timeout = 60000)
    public void testMaleUnrecognizedTitleReturnsMinusOne() {
        given().when().get(base + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        String randomTitle = "king-" + UUID.randomUUID().toString();
        Response act = given().when().get(base + "/api/title/male/" + randomTitle).then().statusCode(200).extract().response();
        assertEquals("-1", act.asString().trim());
    }

    @Test(timeout = 60000)
    public void testFemaleUnrecognizedTitleReturnsMinusOne() {
        given().when().get(base + "/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        String randomTitle = "queen-" + UUID.randomUUID().toString();
        Response act = given().when().get(base + "/api/title/female/" + randomTitle).then().statusCode(200).extract().response();
        assertEquals("-1", act.asString().trim());
    }
}