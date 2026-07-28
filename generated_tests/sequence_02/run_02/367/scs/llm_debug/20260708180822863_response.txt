package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;

public class Ordered4Test {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = System.getProperty("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testIncreasingOrder() {
        RestAssured.given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response resp = RestAssured.given().when().get("/api/ordered4/aaaaa/aaaab/aaaad/aaaac");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDecreasingOrder() {
        RestAssured.given().when().get("/api/pat/OK").then().statusCode(lessThan(300));
        Response resp = RestAssured.given().when().get("/api/ordered4/zzzzz/yyyyy/wwwww/xxxxx");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedDueToLength() {
        RestAssured.given().when().get("/api/pat/health").then().statusCode(lessThan(300));
        Response resp = RestAssured.given().when().get("/api/ordered4/abcd/aaaaa/aaaaa/aaaaa");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnorderedDueToNonMonotonic() {
        RestAssured.given().when().get("/api/pat/check").then().statusCode(lessThan(300));
        Response resp = RestAssured.given().when().get("/api/ordered4/aaaaa/aaaab/aaaac/aaaab");
        resp.then().statusCode(200);
    }
}