package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    @Before
    public void setUp() {
        String host = System.getProperty("server.host", "localhost");
        String port = System.getProperty("server.port", "8080");
        RestAssured.baseURI = "http://" + host;
        RestAssured.port = Integer.parseInt(port);
    }

    @Test(timeout = 60000)
    public void testExpintN0XPositive() {
        given().when().get("/api/expint/0/1.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintNGreaterThan1XZero() {
        given().when().get("/api/expint/2/0.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXGreaterThanOne() {
        given().when().get("/api/expint/3/2.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesWithPsi() {
        given().when().get("/api/expint/3/0.1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesNm1Zero() {
        given().when().get("/api/expint/1/0.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintNegativeNError() {
        given().when().get("/api/expint/-1/1.0").then().statusCode(400);
    }
}