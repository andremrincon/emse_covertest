package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String baseUri = System.getenv("BASE_URL");
        if (baseUri == null || baseUri.isEmpty()) {
            baseUri = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUri;
    }

    @Test(timeout = 60000)
    public void testTwoAbbreviation() {
        given().when().get("/api/text2txt/two/x/y").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testFourAbbreviation() {
        given().when().get("/api/text2txt/four/x/y").then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testYouAbbreviation() {
        given().when().get("/api/text2txt/you/x/y").then().body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testAreAbbreviation() {
        given().when().get("/api/text2txt/are/x/y").then().body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testSeeYouAbbreviation() {
        given().when().get("/api/text2txt/see/you/y").then().body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testByTheWayAbbreviation() {
        given().when().get("/api/text2txt/by/the/way").then().body(equalTo("btw"));
    }
}