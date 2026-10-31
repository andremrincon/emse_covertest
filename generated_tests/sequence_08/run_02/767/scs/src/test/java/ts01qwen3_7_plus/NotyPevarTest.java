package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NotyPevarTest {

    @Test(timeout = 60000)
    public void testSubjectBranchI0True() {
        given().when().get("/api/notypevar/1/a").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/notypevar/28/a");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectBranchI1True() {
        given().when().get("/api/notypevar/1/a").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/notypevar/7/a");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectBranchI2True() {
        given().when().get("/api/notypevar/1/a").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/notypevar/1/world");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectBranchI3True() {
        given().when().get("/api/notypevar/1/a").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/notypevar/10/a");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSubjectBranchI3False() {
        given().when().get("/api/notypevar/1/a").then().statusCode(lessThan(300));
        Response response = given().when().get("/api/notypevar/1/a");
        response.then().statusCode(200);
    }
}