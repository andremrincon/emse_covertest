package ts01gpt_5_mini_2;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl");
        }
        if (base == null || base.isEmpty()) {
            base = System.getenv("baseUrl");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTwoReturns200() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "for", "quick", "brown").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "quick", "brown").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "four", "quick", "brown").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "alpha", "beta").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testForReturns200() {
        given().when().get("/api/pat/{txt}", "The quick brown fox jumps over the lazy dog.").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "and", "a", "b").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "for", "quick", "brown").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSeeYouReturns200() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "you", "ok").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "later").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "now").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testByTheWayReturns200() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "two", "two").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "by", "the", "way").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "by", "the", "way").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDefaultEmptyResultReturns200() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "unknownword", "one", "two").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "unknownword", "one", "two").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAndAreBranchesReturn200() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "you", "you").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "and", "you", "you").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "you", "there").then().statusCode(200);
    }
}