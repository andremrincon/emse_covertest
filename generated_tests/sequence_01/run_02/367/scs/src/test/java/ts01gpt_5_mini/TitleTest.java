package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;

public class TitleTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMaleWithMrReturnsOne() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "male", "mr");
        Assert.assertEquals("1", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testFemaleWithMrsReturnsZero() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "female", "mrs");
        Assert.assertEquals("0", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testNoneWithDrReturnsTwo() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "none", "dr");
        Assert.assertEquals("2", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testMaleWithUnknownTitleReturnsMinusOne() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "male", "unknown");
        Assert.assertEquals("-1", resp.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testUnknownSexWithMrReturnsMinusOne() {
        given().when().get("/api/pat/{txt}", "a").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "unknown", "mr");
        Assert.assertEquals("-1", resp.getBody().asString());
    }
}