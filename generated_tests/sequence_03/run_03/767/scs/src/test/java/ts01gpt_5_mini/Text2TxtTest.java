package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE");
        String base = env != null ? env : System.getProperty("api.base", "http://localhost:8080");
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testTwoBranchReturns2() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "ignore", "ignore").then().statusCode(lessThan(300));
        String unique = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/{w}/{x}/{y}", unique, "a", "b").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "something", "else").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testForBranchReturns4() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "now").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "hello", "world", "test").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "for", "anything", "here").then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testYouBranchReturnsU() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "four", "a", "b").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "unique_"+UUID.randomUUID().toString(), "x", "y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "you", "any", "thing").then().body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testAndBranchReturnsN() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "two", "x", "y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "are", "b", "c").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "and", "first", "second").then().body(equalTo("n"));
    }

    @Test(timeout = 60000)
    public void testSeeYouBranchReturnsCuAndExerciseBtwInArrange() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "by", "the", "way").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "four", "a", "b").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "later").then().body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testDefaultBranchReturns200() {
        given().when().get("/api/text2txt/{w}/{x}/{y}", "see", "you", "later").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "and", "x", "y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/{w}/{x}/{y}", "nothing-matches-"+UUID.randomUUID().toString(), "else", "here").then().statusCode(200);
    }
}