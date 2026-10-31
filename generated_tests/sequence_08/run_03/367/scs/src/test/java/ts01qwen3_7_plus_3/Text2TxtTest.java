package ts01qwen3_7_plus_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testText2TxtTwo() {
        given().when().get("/api/text2txt/two/quick/brown").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/text2txt/two/quick/brown");
        response.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testText2TxtSeeYou() {
        given().when().get("/api/text2txt/see/you/later").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/text2txt/see/you/later");
        response.then().body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testText2TxtByTheWay() {
        given().when().get("/api/text2txt/by/the/way").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/text2txt/by/the/way");
        response.then().body(equalTo("btw"));
    }
}