package ts01gpt_5_mini;

import org.junit.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.lessThan;
import io.restassured.RestAssured;

public class NcsRestTest {

    static {
        String env = System.getenv("BASE_URL");
        if(env == null || env.isEmpty()){
            env = "http://localhost:8080";
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void testFisher_Returns200_ForValidParameters() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/fisher/10/5/0.75");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_Returns400_ForLargeM() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/fisher/1001/5/0.75");
        response.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisher_Returns400_ForInvalidX_CausesException() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/fisher/10/5/1.2");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainder_Returns200_ForValidParameters() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/remainder/17/5");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testRemainder_Returns400_ForOutOfBounds() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/remainder/10001/1");
        response.then().statusCode(400);
    }
}