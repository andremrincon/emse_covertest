package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testFisher_a1_b1_loop() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/1/5/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_a1_bNot1() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/1/2/0.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_aNot1_b1() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/10/1/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_aNot1_bNot1() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/10/6/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_invalidParameterBadRequest() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/abc/5/0.75").then().statusCode(400);
    }
}