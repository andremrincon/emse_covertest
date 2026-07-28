package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("baseUrl");
        if (env == null || env.isEmpty()) {
            env = System.getenv("BASE_URL");
        }
        if (env == null || env.isEmpty()) {
            env = "http://localhost:8080";
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void test_two_converts_to_2() {
        given().when().get("/api/text2txt/for/quick/brown").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/four/x/y").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/two/ignored/ignored");
        assertEquals("2", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_four_converts_to_4() {
        given().when().get("/api/text2txt/you/a/b").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/and/a/b").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/four/x/y");
        assertEquals("4", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_you_converts_to_u() {
        given().when().get("/api/text2txt/two/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/are/x/y").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/you/anything/else");
        assertEquals("u", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_and_converts_to_n() {
        given().when().get("/api/text2txt/by/the/way").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/see/you/now").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/and/some/thing");
        assertEquals("n", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_see_you_converts_to_cu() {
        given().when().get("/api/text2txt/are/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/unknown/a/b").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/see/you/now");
        assertEquals("cu", act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void test_by_the_way_converts_to_btw() {
        given().when().get("/api/text2txt/see/notyou/x").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/and/x/y").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/by/the/way");
        assertEquals("btw", act.getBody().asString());
    }
}