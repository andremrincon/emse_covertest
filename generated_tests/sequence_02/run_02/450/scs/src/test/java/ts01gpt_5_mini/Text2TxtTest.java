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
        String env = Optional.ofNullable(System.getProperty("API_BASE_URL"))
                .orElse(Optional.ofNullable(System.getenv("API_BASE_URL")).orElse("http://localhost:8080"));
        if (env.startsWith("http://") || env.startsWith("https://")) {
            RestAssured.baseURI = env;
        } else {
            RestAssured.baseURI = "http://" + env;
        }
    }

    @Test(timeout = 60000)
    public void testWordTwoProducesDigitTwo() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "anything", "else");
        act.then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWordForProducesDigitFour() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "for", "x", "y");
        act.then().assertThat().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testWordYouProducesU() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "ignore", "me");
        act.then().assertThat().body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testSeeYouProducesCu() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "now");
        act.then().assertThat().body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testByTheWayProducesBtw() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "by", "the", "way");
        act.then().assertThat().body(equalTo("btw"));
    }

    @Test(timeout = 60000)
    public void testAreProducesR() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "The", "quick", "brown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "you", "there");
        act.then().assertThat().body(equalTo("r"));
    }
}