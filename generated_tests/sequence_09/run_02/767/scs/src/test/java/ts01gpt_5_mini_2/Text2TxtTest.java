package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String cfg = System.getProperty("base.url");
        if (cfg == null || cfg.isEmpty()) {
            cfg = System.getenv("BASE_URL");
        }
        if (cfg == null || cfg.isEmpty()) {
            cfg = "http://localhost:8080";
        }
        RestAssured.baseURI = cfg;
    }

    @Test(timeout = 60000)
    public void testWordTwoProducesSuccessStatus() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/two/example/words").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWordForProducesSuccessStatus() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/for/quick/brown").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSeeYouProducesSuccessStatus() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/see/you/now").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testByTheWayProducesSuccessStatus() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/by/the/way").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAreProducesSuccessStatus() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/are/some/one").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoMatchProducesSuccessStatus() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/hello/world/test").then().statusCode(200);
    }
}