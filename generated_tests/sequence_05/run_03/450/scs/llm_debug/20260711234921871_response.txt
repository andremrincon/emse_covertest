package ts01qwen3_7_plus;

import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    @Test(timeout = 60000)
    public void testWord1Two() {
        given().when().get("/api/text2txt/two/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/two/x/y").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWord1For() {
        given().when().get("/api/text2txt/for/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/for/x/y").then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testWord1You() {
        given().when().get("/api/text2txt/you/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/you/x/y").then().body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testWord1And() {
        given().when().get("/api/text2txt/and/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/and/x/y").then().body(equalTo("n"));
    }

    @Test(timeout = 60000)
    public void testWord1SeeWord2You() {
        given().when().get("/api/text2txt/see/you/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/see/you/y").then().body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testWord1ByWord2TheWord3Way() {
        given().when().get("/api/text2txt/by/the/way").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/by/the/way").then().body(equalTo("btw"));
    }
}