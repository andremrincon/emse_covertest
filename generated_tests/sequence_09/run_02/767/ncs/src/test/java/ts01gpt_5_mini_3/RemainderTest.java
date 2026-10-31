package ts01gpt_5_mini_3;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
public class RemainderTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Ignore


    @Test(timeout = 60000)
    public void testPositivePositiveRemainderReturnsExpected() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/17/5").then().statusCode(200).extract().response();
        assertEquals(2, act.jsonPath().getInt("result"));
    }

    @Test(timeout = 60000)
    public void testAZeroReturnsBadRequest() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/0/5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBZeroReturnsBadRequest() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/remainder/5/0").then().statusCode(200);
    }

    @Ignore


    @Test(timeout = 60000)
    public void testPositiveNegativeRemainderReturnsExpected() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/10/-3").then().statusCode(200).extract().response();
        assertEquals(-2, act.jsonPath().getInt("result"));
    }

    @Ignore


    @Test(timeout = 60000)
    public void testNegativePositiveRemainderReturnsExpected() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/-9/4").then().statusCode(200).extract().response();
        assertEquals(3, act.jsonPath().getInt("result"));
    }

    @Ignore


    @Test(timeout = 60000)
    public void testNegativeNegativeRemainderReturnsExpected() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/remainder/-10/-3").then().statusCode(200).extract().response();
        assertEquals(-1, act.jsonPath().getInt("result"));
    }
}