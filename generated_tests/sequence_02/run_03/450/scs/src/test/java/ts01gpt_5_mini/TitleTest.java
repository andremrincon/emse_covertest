package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("API_BASE");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMaleTitleMr_returns200() {
        given().when().get("/api/pat/The").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/male/mr");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFemaleTitleMs_uppercase_returns200() {
        given().when().get("/api/pat/hello").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/FEMALE/MS");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testNoneTitleDr_returns200() {
        given().when().get("/api/pat/sample").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/none/dr");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testMaleUnknownTitle_returns200() {
        given().when().get("/api/pat/check").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/male/unknown");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFemaleTitleDr_mixedCase_returns200() {
        given().when().get("/api/pat/abc").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/Female/Dr");
        Assert.assertEquals(200, resp.getStatusCode());
    }
}