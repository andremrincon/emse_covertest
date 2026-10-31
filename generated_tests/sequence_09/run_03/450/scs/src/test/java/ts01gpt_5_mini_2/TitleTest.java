package ts01gpt_5_mini_2;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class TitleTest {

    @BeforeClass
    public static void init() {
        String base = System.getenv("TEST_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMaleTitleMatches() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/MALE/MR");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFemaleTitleMatches() {
        given().when().get("/api/pat/banana").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/Female/MISS");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNoneTitleMatches() {
        given().when().get("/api/pat/ABABCABAB/ABAB").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/none/DR");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnknownTitleReturnsNegativeOneString() {
        given().when().get("/api/pat/a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/male/unknownTitle");
        resp.then().body(equalTo("-1"));
    }
}