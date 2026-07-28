package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Text2TxtTest {

    @Before
    public void setup() {
        RestAssured.baseURI = System.getProperty("test.base.uri", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testWord1Two() {
        String word1 = "two";
        String word2 = "x";
        String word3 = "y";

        Response response = given()
            .when()
            .get("/api/text2txt/" + word1 + "/" + word2 + "/" + word3);

        response.then().statusCode(200).body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testWord1For() {
        String word1 = "for";
        String word2 = "x";
        String word3 = "y";

        Response response = given()
            .when()
            .get("/api/text2txt/" + word1 + "/" + word2 + "/" + word3);

        response.then().statusCode(200).body(equalTo("4"));
    }

    @Test(timeout = 60000)
    public void testWord1You() {
        String word1 = "you";
        String word2 = "x";
        String word3 = "y";

        Response response = given()
            .when()
            .get("/api/text2txt/" + word1 + "/" + word2 + "/" + word3);

        response.then().statusCode(200).body(equalTo("u"));
    }

    @Test(timeout = 60000)
    public void testWord1And() {
        String word1 = "and";
        String word2 = "x";
        String word3 = "y";

        Response response = given()
            .when()
            .get("/api/text2txt/" + word1 + "/" + word2 + "/" + word3);

        response.then().statusCode(200).body(equalTo("n"));
    }

    @Test(timeout = 60000)
    public void testWord1Are() {
        String word1 = "are";
        String word2 = "x";
        String word3 = "y";

        Response response = given()
            .when()
            .get("/api/text2txt/" + word1 + "/" + word2 + "/" + word3);

        response.then().statusCode(200).body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testWord1SeeWord2You() {
        String word1 = "see";
        String word2 = "you";
        String word3 = "y";

        Response response = given()
            .when()
            .get("/api/text2txt/" + word1 + "/" + word2 + "/" + word3);

        response.then().statusCode(200).body(equalTo("cu"));
    }
}