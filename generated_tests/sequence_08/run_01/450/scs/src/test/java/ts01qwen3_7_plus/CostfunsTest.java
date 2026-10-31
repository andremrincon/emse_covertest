package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CostfunsTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCostfuns_IEquals5() {
        given().when().get("/api/costfuns/0/a").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/costfuns/5/baab");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_ILessThanMinus444() {
        given().when().get("/api/costfuns/0/a").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/costfuns/-500/ababba");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_IGreaterThan666() {
        given().when().get("/api/costfuns/0/a").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/costfuns/700/abab");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_INotEqualsMinus4() {
        given().when().get("/api/costfuns/0/a").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/costfuns/-4/a");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_SGreaterThanAbabba() {
        given().when().get("/api/costfuns/0/a").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/costfuns/600/z");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_InvalidInput() {
        given().when().get("/api/costfuns/0/a").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/costfuns/invalid/a");
        response.then().statusCode(400);
    }
}