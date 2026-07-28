package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    @Before
    public void setup() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = baseUrl != null ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testMaleTitles() {
        given().when().get("/api/title/male/mr").then().statusCode(lessThan(300));
        given().when().get("/api/title/male/dr").then().statusCode(lessThan(300));
        given().when().get("/api/title/male/sir").then().statusCode(lessThan(300));
        given().when().get("/api/title/male/rev").then().statusCode(lessThan(300));
        given().when().get("/api/title/male/rthon").then().statusCode(lessThan(300));
        given().when().get("/api/title/male/prof").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleTitles() {
        given().when().get("/api/title/female/mrs").then().statusCode(lessThan(300));
        given().when().get("/api/title/female/miss").then().statusCode(lessThan(300));
        given().when().get("/api/title/female/ms").then().statusCode(lessThan(300));
        given().when().get("/api/title/female/dr").then().statusCode(lessThan(300));
        given().when().get("/api/title/female/lady").then().statusCode(lessThan(300));
        given().when().get("/api/title/female/rev").then().statusCode(lessThan(300));
        given().when().get("/api/title/female/rthon").then().statusCode(lessThan(300));
        given().when().get("/api/title/female/prof").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneTitles() {
        given().when().get("/api/title/none/dr").then().statusCode(lessThan(300));
        given().when().get("/api/title/none/rev").then().statusCode(lessThan(300));
        given().when().get("/api/title/none/rthon").then().statusCode(lessThan(300));
        given().when().get("/api/title/none/prof").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidSex() {
        given().when().get("/api/title/neuter/mr").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidTitle() {
        given().when().get("/api/title/male/Jones").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidTitle2() {
        given().when().get("/api/title/male/abc").then().statusCode(200);
    }
}