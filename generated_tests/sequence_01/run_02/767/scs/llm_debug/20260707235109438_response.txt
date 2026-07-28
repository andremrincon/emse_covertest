package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testText2TxtTwo() {
        given().when().get("/api/text2txt/dummy/dummy/dummy").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/text2txt/two/dummy/dummy");
        response.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testText2TxtFor() {
        given().when().get("/api/text2txt/dummy/dummy/dummy").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/text2txt/for/dummy/dummy");
        response.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testText2TxtFour() {
        given().when().get("/api/text2txt/dummy/dummy/dummy").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/text2txt/four/dummy/dummy");
        response.then().body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testText2TxtYou() {
        given().when().get("/api/text2txt/dummy/dummy/dummy").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/text2txt/you/dummy/dummy");
        response.then().body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testText2TxtAnd() {
        given().when().get("/api/text2txt/dummy/dummy/dummy").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/text2txt/and/dummy/dummy");
        response.then().body(equalTo("n"));
    }
}