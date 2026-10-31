package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMaleWithMrReturns200() {
        given().when().get("/api/pat/The%20quick%20brown%20fox%20jumps%20over%20the%20lazy%20dog.").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/male/mr");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testMaleWithProfReturns200() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/male/prof");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleWithMsReturns200() {
        given().when().get("/api/pat/banana").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/female/ms");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneWithDrReturns200() {
        given().when().get("/api/pat/ABABCABAB").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/none/dr");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnknownSexTitleReturns200() {
        given().when().get("/api/pat/text").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/title/unknown/unknown");
        act.then().statusCode(200);
    }
}