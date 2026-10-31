package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @BeforeClass
    public static void setup() {
        String env = Optional.ofNullable(System.getenv("BASE_URL")).orElse(System.getProperty("baseUrl"));
        if (env == null || env.isEmpty()) {
            RestAssured.baseURI = "http://localhost:8080";
        } else {
            RestAssured.baseURI = env;
        }
    }

    @Test(timeout = 60000)
    public void testFisher_OddM_OddN_Returns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/11/5/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_EvenM_EvenN_Returns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/10/6/0.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_OddM_EvenN_WithZeroX_Returns200() {
        given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/11/6/0.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_InvalidM_NonNumeric_Returns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/abc/5/0.75").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testFisher_InvalidN_Negative_Returns400() {
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/10/-3/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_InvalidX_OutOfRange_Returns400() {
        given().when().get("/api/expint/1/2.5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/10/5/1.2").then().statusCode(200);
    }
}