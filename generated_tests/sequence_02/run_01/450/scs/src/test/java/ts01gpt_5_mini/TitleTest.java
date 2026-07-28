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
    public static void init() {
        String base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testMaleRecognizedTitleReturnsOne() {
        given().when().get("/api/pat/{txt}", "The quick brown fox").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "male", "mr");
        Assert.assertEquals("1", resp.asString());
    }

    @Test(timeout = 60000)
    public void testFemaleRecognizedTitleCaseInsensitiveReturnsZero() {
        given().when().get("/api/pat/{txt}", "HealthCheck").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "FeMaLe", "Mrs");
        Assert.assertEquals("0", resp.asString());
    }

    @Test(timeout = 60000)
    public void testNoneSexWithAcademicTitleReturnsTwo() {
        given().when().get("/api/pat/{txt}", "ping").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "none", "Dr");
        Assert.assertEquals("2", resp.asString());
    }

    @Test(timeout = 60000)
    public void testUnknownSexReturnsMinusOne() {
        given().when().get("/api/pat/{txt}", "status").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "unknown", "anything");
        Assert.assertEquals("-1", resp.asString());
    }
}