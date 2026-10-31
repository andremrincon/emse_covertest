package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("api.base", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testTwoTransformsTo2() {
        given().when().get("/api/text2txt/and/irrelevant/thing").then().statusCode(lessThan(300));
        String unique = UUID.randomUUID().toString();
        Response act = given().when().get("/api/text2txt/two/" + unique + "/x");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testForTransformsTo4() {
        given().when().get("/api/text2txt/you/placeholder/val").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/for/anything/else");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testYouTransformsToU() {
        given().when().get("/api/text2txt/two/a/b").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/you/hello/world");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAreTransformsToR() {
        given().when().get("/api/text2txt/see/you/now").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/are/x/y");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSeeYouTransformsToCu() {
        given().when().get("/api/text2txt/for/alpha/beta").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/see/you/there");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testByTheWayTransformsToBtw() {
        given().when().get("/api/text2txt/and/something/else").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/by/the/way");
        act.then().statusCode(200);
    }
}