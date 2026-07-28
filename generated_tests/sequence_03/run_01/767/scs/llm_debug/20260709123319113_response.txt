package ts01gpt_5_mini;

import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    private static final String BASE;
    static {
        String b = System.getProperty("api.base");
        if (b == null || b.isEmpty()) b = System.getenv("API_BASE");
        if (b == null || b.isEmpty()) b = "http://localhost:8080";
        BASE = b;
        RestAssured.baseURI = BASE;
    }

    @Test(timeout = 60000)
    public void testTwoReturns2() {
        given().when().get(BASE + "/api/text2txt/for/anything/else").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/text2txt/you/x/y").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/text2txt/and/a/b").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/text2txt/are/a/b").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/text2txt/see/you/now").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/text2txt/by/the/way").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/text2txt/two/one/two").then().assertThat().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testForReturns4() {
        given().when().get(BASE + "/api/text2txt/hello/world/test").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/text2txt/two/x/y").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/text2txt/you/x/y").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/text2txt/and/a/b").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/text2txt/for/quick/brown").then().assertThat().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testAreReturnsR() {
        given().when().get(BASE + "/api/text2txt/see/you/x").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/text2txt/by/the/way").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/text2txt/are/anything/else").then().assertThat().body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testSeeYouReturnsCu() {
        given().when().get(BASE + "/api/text2txt/are/x/y").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/text2txt/for/x/y").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/text2txt/see/you/today").then().assertThat().body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testByTheWayReturnsBtw() {
        given().when().get(BASE + "/api/text2txt/are/x/y").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/text2txt/see/you/x").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/text2txt/by/the/way").then().assertThat().body(equalTo("btw"));
    }

    @Test(timeout = 60000)
    public void testYouReturnsU() {
        given().when().get(BASE + "/api/text2txt/and/x/y").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/text2txt/hello/world/test").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/text2txt/you/anything/else").then().assertThat().body(equalTo("u"));
    }
}