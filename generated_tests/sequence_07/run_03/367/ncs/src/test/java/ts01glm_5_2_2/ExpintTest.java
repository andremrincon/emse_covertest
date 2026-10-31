package ts01glm_5_2_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class ExpintTest {
    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testExpintErrorNegativeN() {
        given().when().get("/api/expint/-1/2.5").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintNZero() {
        given().when().get("/api/expint/0/2.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXZeroNGreaterThanOne() {
        given().when().get("/api/expint/3/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintContinuedFraction() {
        given().when().get("/api/expint/3/2.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesNOne() {
        given().when().get("/api/expint/1/0.1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesNGreaterThanOne() {
        given().when().get("/api/expint/2/0.1").then().statusCode(200);
    }
}