package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    private static String baseUrl;

    @BeforeClass
    public static void setup() {
        String prop = System.getProperty("base.url");
        String env = System.getenv("BASE_URL");
        if (prop != null && !prop.isEmpty()) {
            baseUrl = prop;
        } else if (env != null && !env.isEmpty()) {
            baseUrl = env;
        } else {
            baseUrl = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testTriangleInvalidWhenNonPositiveSide() {
        given().baseUri(baseUrl).when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().baseUri(baseUrl).when().get("/api/triangle/0/1/1");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleEquilateralClassificationPath() {
        given().baseUri(baseUrl).when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().baseUri(baseUrl).when().get("/api/triangle/5/5/5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleInequalityViolationPath() {
        given().baseUri(baseUrl).when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().baseUri(baseUrl).when().get("/api/triangle/10/1/1");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleIsoscelesClassificationPath() {
        given().baseUri(baseUrl).when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().baseUri(baseUrl).when().get("/api/triangle/5/5/8");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleScaleneClassificationPath() {
        given().baseUri(baseUrl).when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().baseUri(baseUrl).when().get("/api/triangle/3/4/5");
        act.then().statusCode(200);
    }
}