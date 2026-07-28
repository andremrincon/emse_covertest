package ts01gpt_5_mini;

import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    private String baseUrl() {
        String prop = System.getProperty("api.base");
        if (prop != null && !prop.isEmpty()) {
            return prop;
        }
        String env = System.getenv("API_BASE");
        if (env != null && !env.isEmpty()) {
            return env;
        }
        return "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangleReturns200() {
        String base = baseUrl();
        given().when().get(base + "/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/triangle/5/5/5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangleReturns200() {
        String base = baseUrl();
        given().when().get(base + "/api/remainder/11/4").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/triangle/5/5/3");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangleReturns200() {
        String base = baseUrl();
        given().when().get(base + "/api/remainder/23/7").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/triangle/3/4/5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testZeroSideInvalidTriangleReturns200() {
        String base = baseUrl();
        given().when().get(base + "/api/remainder/19/6").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/triangle/0/5/5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleInequalityReturns200() {
        String base = baseUrl();
        given().when().get(base + "/api/remainder/13/5").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/triangle/1/2/3");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testNegativeSideReturns200() {
        String base = baseUrl();
        given().when().get(base + "/api/remainder/29/8").then().statusCode(lessThan(300));
        Response act = given().when().get(base + "/api/triangle/-1/2/3");
        act.then().statusCode(200);
    }
}