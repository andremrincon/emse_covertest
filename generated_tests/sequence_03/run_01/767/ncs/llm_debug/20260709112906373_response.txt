package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNonPositiveSide_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/0/5/5");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testEquilateral_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/3/3/3");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangle_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/5/2/3");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testIsosceles_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/5/5/3");
        Assert.assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testScalene_returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/4/5/6");
        Assert.assertEquals(200, resp.getStatusCode());
    }
}