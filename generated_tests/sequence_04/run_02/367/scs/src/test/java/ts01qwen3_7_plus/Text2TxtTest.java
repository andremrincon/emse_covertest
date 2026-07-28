package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    static {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testText2TxtTwo() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/two/a/b").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtFor() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/for/a/b").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtFour() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/four/a/b").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtYou() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/you/a/b").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtAnd() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/and/a/b").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testText2TxtSeeYou() {
        given().when().get("/api/text2txt/The/quick/brown").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/see/you/b").then().statusCode(200);
    }
}