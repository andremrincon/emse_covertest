package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            String env = System.getenv("API_BASE_URL");
            if (env == null || env.isEmpty()) {
                RestAssured.baseURI = "http://localhost:8080";
            } else {
                RestAssured.baseURI = env;
            }
        } else {
            RestAssured.baseURI = base;
        }
    }

    @Test(timeout = 60000)
    public void nonPositiveSideProducesResponse() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/0/1/1");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void equilateralTriangleProducesResponse() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        String id = UUID.randomUUID().toString();
        Response resp = given().when().get("/api/triangle/5/5/5");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void degenerateTriangleProducesResponse() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/5/2/2");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void isoscelesTriangleProducesResponse() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/5/5/3");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void scaleneTriangleProducesResponse() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/3/4/5");
        resp.then().statusCode(200);
    }
}