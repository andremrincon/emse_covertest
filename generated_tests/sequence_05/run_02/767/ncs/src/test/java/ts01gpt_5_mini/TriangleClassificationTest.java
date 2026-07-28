package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @BeforeClass
    public static void init() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void equilateralTriangleShouldReturn200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/triangle/3/3/3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void isoscelesTriangleShouldReturn200() {
        given().when().get("/api/remainder/19/6").then().statusCode(lessThan(300));
        given().when().get("/api/triangle/5/5/3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void scaleneTriangleShouldReturn200() {
        given().when().get("/api/remainder/11/4").then().statusCode(lessThan(300));
        given().when().get("/api/triangle/3/4/5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void nonTriangleDegenerateShouldReturn200() {
        given().when().get("/api/remainder/13/5").then().statusCode(lessThan(300));
        given().when().get("/api/triangle/1/2/3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void invalidSidesShouldReturn200() {
        given().when().get("/api/remainder/23/7").then().statusCode(lessThan(300));
        given().when().get("/api/triangle/0/5/5").then().statusCode(200);
    }
}