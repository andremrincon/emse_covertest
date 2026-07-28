package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TitleTest {

    @BeforeClass
    public static void setUp() {
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
    public void testMaleValidTitleReturns200() {
        given().when().get("/api/pat/health-check").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "Male", "MR");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testMaleInvalidTitleReturnsBodyMinusOne() {
        given().when().get("/api/pat/health-" + UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "male", "mrs");
        Assert.assertEquals("-1", resp.asString().trim());
    }

    @Test(timeout = 60000)
    public void testFemaleValidTitleReturns200() {
        given().when().get("/api/pat/setup-" + UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "FeMaLe", "Mrs");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testFemaleInvalidTitleReturnsBodyMinusOne() {
        given().when().get("/api/pat/ping").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "female", "mr");
        Assert.assertEquals("-1", resp.asString().trim());
    }

    @Test(timeout = 60000)
    public void testNoneValidTitleReturns200() {
        given().when().get("/api/pat/ready-" + UUID.randomUUID().toString()).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/title/{sex}/{title}", "none", "Dr");
        Assert.assertEquals(200, resp.getStatusCode());
    }
}