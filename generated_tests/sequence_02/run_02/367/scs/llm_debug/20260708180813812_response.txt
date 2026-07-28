package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("baseUrl");
        if (base == null) base = System.getenv("BASE_URL");
        if (base == null) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testConvertsTwoToDigitTwo() {
        given().when().get("/api/text2txt/for/anything/anything").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/see/you/x").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/by/the/way").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/two/irrelevant/irrelevant").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testConvertsForTo4() {
        given().when().get("/api/text2txt/two/x/x").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/you/x/x").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/and/x/x").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/for/anything/anything").then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testConvertsYouToU() {
        given().when().get("/api/text2txt/for/x/x").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/two/x/x").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/see/you/x").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/you/anything/anything").then().body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testConvertsAndToN() {
        given().when().get("/api/text2txt/you/x/x").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/for/x/x").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/two/x/x").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/and/something/else").then().body(equalTo("n"));
    }

    @Test(timeout = 60000)
    public void testConvertsAreToR() {
        given().when().get("/api/text2txt/by/the/way").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/see/you/x").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/for/x/x").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/are/anything/anything").then().body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testConvertsSeeYouToCu() {
        given().when().get("/api/text2txt/are/x/x").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/two/x/x").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/for/x/x").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/see/you/nothing").then().body(equalTo("cu"));
    }
}