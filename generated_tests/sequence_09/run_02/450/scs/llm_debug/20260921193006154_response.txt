package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTwoReturns2() {
        given().when().get("/api/text2txt/for/quick/brown").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/four/one/two").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/hello/there/now").then().statusCode(lessThan(300));
        String path = "/api/text2txt/two/alpha/beta";
        given().when().get(path).then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testForReturns4() {
        given().when().get("/api/text2txt/two/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/you/x/y").then().statusCode(lessThan(300));
        String path = "/api/text2txt/for/quick/brown";
        given().when().get(path).then().assertThat().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testYouReturnsU() {
        given().when().get("/api/text2txt/see/you/now").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/by/the/way").then().statusCode(lessThan(300));
        String path = "/api/text2txt/you/is/here";
        given().when().get(path).then().assertThat().body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testAndReturnsN() {
        given().when().get("/api/text2txt/are/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/see/notyou/now").then().statusCode(lessThan(300));
        String path = "/api/text2txt/and/foo/bar";
        given().when().get(path).then().assertThat().body(equalTo("n"));
    }

    @Test(timeout = 60000)
    public void testAreReturnsR() {
        given().when().get("/api/text2txt/see/you/any").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/by/the/way").then().statusCode(lessThan(300));
        String path = "/api/text2txt/are/you/there";
        given().when().get(path).then().assertThat().body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsCu() {
        given().when().get("/api/text2txt/two/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/for/x/y").then().statusCode(lessThan(300));
        String path = "/api/text2txt/see/you/now";
        given().when().get(path).then().assertThat().body(equalTo("cu"));
    }

}