package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    @Test(timeout = 60000)
    public void testWord1For() {
        given().when().get("/api/text2txt/for/any/any").then().statusCode(200).body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testWord1SeeWord2You() {
        given().when().get("/api/text2txt/see/you/any").then().statusCode(200).body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testWord1ByWord2TheWord3Way() {
        given().when().get("/api/text2txt/by/the/way").then().statusCode(200).body(equalTo("btw"));
    }

    @Test(timeout = 60000)
    public void testWord1Two() {
        given().when().get("/api/text2txt/two/any/any").then().statusCode(200).body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWord1You() {
        given().when().get("/api/text2txt/you/any/any").then().statusCode(200).body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testWord1And() {
        given().when().get("/api/text2txt/and/any/any").then().statusCode(200).body(equalTo("n"));
    }
}