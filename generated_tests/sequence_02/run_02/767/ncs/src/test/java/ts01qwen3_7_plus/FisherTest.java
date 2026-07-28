package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        RestAssured.baseURI = baseUrl != null ? baseUrl : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testFisherA1B1() {
        given().when().get("/api/fisher/1/1/0.5").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/fisher/1/1/0.75");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherA1B2() {
        given().when().get("/api/fisher/1/2/0.5").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/fisher/1/2/0.75");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherA2B1() {
        given().when().get("/api/fisher/10/1/0.5").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/fisher/10/1/0.75");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherA2B2() {
        given().when().get("/api/fisher/10/2/0.5").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/fisher/10/2/0.75");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherPGreaterThan1() {
        given().when().get("/api/fisher/10/5/500.0").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/fisher/10/5/1000000.0");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherPLessThan0() {
        given().when().get("/api/fisher/10/5/0.001").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/fisher/10/5/0.0000001");
        response.then().statusCode(200);
    }
}