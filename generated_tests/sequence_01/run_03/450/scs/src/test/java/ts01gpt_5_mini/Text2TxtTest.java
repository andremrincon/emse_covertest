package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    @BeforeClass
    public static void init() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testTwoMapping() {
        given().when().get("/api/text2txt/four/any/any").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/two/A/B").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testForMappingWithRelatedArranges() {
        given().when().get("/api/text2txt/you/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/and/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/for/q/w").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSeeYouMappingTriggeredAfterAreArrange() {
        given().when().get("/api/text2txt/are/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/see/you/z").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testByTheWayMapping() {
        given().when().get("/api/text2txt/hello/world/there").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/by/the/way").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDefaultNoMatchReturnsOk() {
        given().when().get("/api/text2txt/two/placeholder/none").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/hello/world/unknown").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testYouUppercaseHandledByLowercasing() {
        given().when().get("/api/text2txt/and/sample/val").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/You/ignored/ignored").then().statusCode(200);
    }
}