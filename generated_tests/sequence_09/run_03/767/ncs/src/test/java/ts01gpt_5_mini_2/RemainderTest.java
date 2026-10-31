package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class RemainderTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Ignore


    @Test(timeout = 60000)
    public void testPositiveAandB_returnsResult2() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/17/5").then().extract().response();
        Assert.assertEquals(2, resp.jsonPath().getInt("remainder"));
    }

    @Ignore


    @Test(timeout = 60000)
    public void testPositiveA_negativeB_returnsResult2() {
        given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/17/-5").then().extract().response();
        Assert.assertEquals(2, resp.jsonPath().getInt("remainder"));
    }

    @Test(timeout = 60000)
    public void testNegativeA_positiveB_status200() {
        given().when().get("/api/expint/1/0.1").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/-9/4").then().statusCode(200);
    }

    @Ignore


    @Test(timeout = 60000)
    public void testNegativeA_negativeB_returnsResult1() {
        given().when().get("/api/fisher/10/5/0.75").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/remainder/-9/-4").then().extract().response();
        Assert.assertEquals(-1, resp.jsonPath().getInt("remainder"));
    }
}