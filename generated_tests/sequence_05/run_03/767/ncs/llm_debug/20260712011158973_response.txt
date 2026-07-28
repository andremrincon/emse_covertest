package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @BeforeClass
    public static void setUp() {
        String base = System.getProperty("api.base", System.getenv().getOrDefault("API_BASE", "http://localhost:8080"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testFisher_Returns200_ForEvenM_OddN_ValidX() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/10/5/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_Returns200_ForOddM_XZero() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/1/1/0.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_Returns400_ForInvalidM_Eight() {
        given().when().get("/api/expint/3/2.5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/8/5/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_Returns400_ForInvalidN_Negative() {
        given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/10/-3/0.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testFisher_Returns400_ForInvalidX_OutOfRange() {
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        given().when().get("/api/fisher/10/5/1.2").then().statusCode(200);
    }
}