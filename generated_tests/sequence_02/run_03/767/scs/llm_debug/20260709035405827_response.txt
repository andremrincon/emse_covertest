package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTwoMapsTo2() {
        given().when().get("/api/text2txt/and/foo/bar").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/two/x/y").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testForMapsTo4() {
        given().when().get("/api/text2txt/the/quick/brown").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/for/anything/anything").then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testYouMapsToU() {
        given().when().get("/api/text2txt/the/quick/brown").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/you/hello/world").then().body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testAreMapsToR() {
        given().when().get("/api/text2txt/see/you/now").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/are/x/y").then().body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testSeeYouMapsToCu() {
        given().when().get("/api/text2txt/and/foo/bar").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/see/you/now").then().body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testByTheWayMapsToBtw() {
        given().when().get("/api/text2txt/you/a/b").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/by/the/way").then().body(equalTo("btw"));
    }
}