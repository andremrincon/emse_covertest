package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_two_returns_2() {
        given().when().get("/api/pat/healthcheck").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/you/any/thing").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/two/alpha/" + UUID.randomUUID().toString());
        String body = act.then().extract().asString();
        assertEquals("2", body);
    }

    @Test(timeout = 60000)
    public void test_for_returns_4() {
        given().when().get("/api/text2txt/four/one/two").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/and/x/y").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/for/ignored/" + UUID.randomUUID().toString());
        String body = act.then().extract().asString();
        assertEquals("4", body);
    }

    @Test(timeout = 60000)
    public void test_see_you_returns_cu() {
        given().when().get("/api/text2txt/are/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/pat/TheQuickBrown").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/see/you/" + UUID.randomUUID().toString());
        String body = act.then().extract().asString();
        assertEquals("cu", body);
    }

    @Test(timeout = 60000)
    public void test_by_the_way_returns_btw() {
        given().when().get("/api/text2txt/see/you/a").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/you/x/y").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/by/the/way");
        String body = act.then().extract().asString();
        assertEquals("btw", body);
    }

    @Test(timeout = 60000)
    public void test_are_returns_r() {
        given().when().get("/api/text2txt/see/you/now").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/by/the/way").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/are/" + UUID.randomUUID().toString() + "/z");
        String body = act.then().extract().asString();
        assertEquals("r", body);
    }

    @Test(timeout = 60000)
    public void test_no_match_returns_empty_string() {
        given().when().get("/api/text2txt/you/you/you").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/and/and/and").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/text2txt/hello/world/" + UUID.randomUUID().toString());
        String body = act.then().extract().asString();
        assertEquals("", body);
    }
}