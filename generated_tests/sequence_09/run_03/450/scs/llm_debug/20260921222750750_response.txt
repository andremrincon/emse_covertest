package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String base = Optional.ofNullable(System.getProperty("base.url"))
                .orElse(Optional.ofNullable(System.getenv("BASE_URL")).orElse("http://localhost:8080"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testWordTwoProducesSuccess() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/two/quick/brown");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWordForProducesSuccess() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/for/quick/brown");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWordYouProducesSuccess() {
        given().when().get("/api/pat/banana").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/you/quick/brown");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWordAreProducesSuccess() {
        given().when().get("/api/pat/The%20quick").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/are/quick/brown");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSeeYouProducesSuccess() {
        given().when().get("/api/pat/ABABCABAB").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/see/you/now");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testByTheWayProducesSuccess() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/by/the/way");
        act.then().statusCode(200);
    }
}