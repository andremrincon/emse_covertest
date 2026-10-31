package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = baseUrl != null ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testText2TxtTwo() {
        given().when().get("/api/text2txt/two/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/two/x/y").then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testText2TxtFor() {
        given().when().get("/api/text2txt/for/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/for/x/y").then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testText2TxtYou() {
        given().when().get("/api/text2txt/you/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/you/x/y").then().body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testText2TxtAnd() {
        given().when().get("/api/text2txt/and/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/and/x/y").then().body(equalTo("n"));
    }

    @Test(timeout = 60000)
    public void testText2TxtAre() {
        given().when().get("/api/text2txt/are/x/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/are/x/y").then().body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testText2TxtSee() {
        given().when().get("/api/text2txt/see/you/y").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/see/you/y").then().body(equalTo("cu"));
    }
}