package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            String env = System.getenv("BASE_URL");
            base = (env == null || env.isEmpty()) ? "http://localhost:8080" : env;
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTwoMapsTo2() {
        given().when().get("/api/text2txt/for/quick/brown").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/you/quick/brown").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/see/you/now").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/two/a/b").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testForMapsTo4() {
        given().when().get("/api/text2txt/two/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/you/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/for/anything/else").then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testYouMapsToU() {
        given().when().get("/api/text2txt/for/one/two").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/two/one/two").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/you/ignored/params").then().body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testSeeYouMapsToCu() {
        given().when().get("/api/text2txt/are/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/See/You/Now").then().body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testByTheWayMapsToBtw() {
        given().when().get("/api/text2txt/two/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/by/the/way").then().body(equalTo("btw"));
    }

    @Test(timeout = 60000)
    public void testAreMapsToR() {
        given().when().get("/api/text2txt/see/you/x").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/and/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/are/anything/else").then().body(equalTo("r"));
    }
}