package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    private static String BASE;

    @BeforeClass
    public static void setup() {
        String env = System.getProperty("base.url");
        if (env == null || env.isEmpty()) {
            env = System.getenv("BASE_URL");
        }
        if (env == null || env.isEmpty()) {
            env = "http://localhost:8080";
        }
        BASE = env;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test(timeout = 60000)
    public void testTriangleScaleneProducesOkStatus() {
        given().when().get(BASE + "/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/triangle/3/4/5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleIsoscelesProducesOkStatus() {
        given().when().get(BASE + "/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/triangle/5/5/3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleEquilateralProducesOkStatus() {
        given().when().get(BASE + "/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/triangle/4/4/4").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleNonPositiveSideProducesOkStatus() {
        given().when().get(BASE + "/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/triangle/0/5/5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleDegenerateProducesOkStatus() {
        given().when().get(BASE + "/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/triangle/10/3/4").then().statusCode(200);
    }
}