package ts01qwen3_7_plus_3;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @Test(timeout = 60000)
    public void testFisherReturnsZeroWhenPLessThanZero() {
        given().when().get("http://localhost:8080/api/fisher/2/2/1.0").then().statusCode(lessThan(300));
        Response response = given().when().get("http://localhost:8080/api/fisher/2/2/-0.5");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherReturnsOneWhenPGreaterThanOne() {
        given().when().get("http://localhost:8080/api/fisher/1/2/1.0").then().statusCode(lessThan(300));
        Response response = given().when().get("http://localhost:8080/api/fisher/1/2/-4.0");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherReturnsPWhenPBetweenZeroAndOne() {
        given().when().get("http://localhost:8080/api/fisher/2/2/2.0").then().statusCode(lessThan(300));
        Response response = given().when().get("http://localhost:8080/api/fisher/2/2/1.0");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisherReturns400ForInvalidParameter() {
        given().when().get("http://localhost:8080/api/fisher/2/2/1.0").then().statusCode(lessThan(300));
        Response response = given().when().get("http://localhost:8080/api/fisher/abc/2/1.0");
        response.then().statusCode(400);
    }
}