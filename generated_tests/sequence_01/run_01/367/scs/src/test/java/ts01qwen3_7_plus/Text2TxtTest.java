package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    private String getBaseUrl() {
        String baseUrl = System.getenv("BASE_URL");
        return (baseUrl != null && !baseUrl.isEmpty()) ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testWord1IsTwo() {
        given().when().get(getBaseUrl() + "/api/text2txt/two/quick/brown").then().statusCode(lessThan(300));
        given().when().get(getBaseUrl() + "/api/text2txt/two/quick/brown").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWord1IsFor() {
        given().when().get(getBaseUrl() + "/api/text2txt/for/quick/brown").then().statusCode(lessThan(300));
        given().when().get(getBaseUrl() + "/api/text2txt/for/quick/brown").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWord1IsAnd() {
        given().when().get(getBaseUrl() + "/api/text2txt/and/quick/brown").then().statusCode(lessThan(300));
        given().when().get(getBaseUrl() + "/api/text2txt/and/quick/brown").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWord1IsAre() {
        given().when().get(getBaseUrl() + "/api/text2txt/are/quick/brown").then().statusCode(lessThan(300));
        given().when().get(getBaseUrl() + "/api/text2txt/are/quick/brown").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWord1IsSeeAndWord2IsYou() {
        given().when().get(getBaseUrl() + "/api/text2txt/see/you/brown").then().statusCode(lessThan(300));
        given().when().get(getBaseUrl() + "/api/text2txt/see/you/brown").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testWord1IsByWord2IsTheWord3IsWay() {
        given().when().get(getBaseUrl() + "/api/text2txt/by/the/way").then().statusCode(lessThan(300));
        given().when().get(getBaseUrl() + "/api/text2txt/by/the/way").then().statusCode(200);
    }
}