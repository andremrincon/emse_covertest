package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CalcTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testConstantOperators() {
        given().pathParam("op", "pi").pathParam("arg1", 0.0).pathParam("arg2", 0.0)
                .when().get("/api/calc/{op}/{arg1}/{arg2}")
                .then().statusCode(200);

        given().pathParam("op", "e").pathParam("arg1", 0.0).pathParam("arg2", 0.0)
                .when().get("/api/calc/{op}/{arg1}/{arg2}")
                .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnaryOperators1() {
        given().pathParam("op", "sqrt").pathParam("arg1", 16.0).pathParam("arg2", 0.0)
                .when().get("/api/calc/{op}/{arg1}/{arg2}")
                .then().statusCode(200);

        given().pathParam("op", "log").pathParam("arg1", 10.0).pathParam("arg2", 0.0)
                .when().get("/api/calc/{op}/{arg1}/{arg2}")
                .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTrigonometricOperators() {
        given().pathParam("op", "sine").pathParam("arg1", 0.0).pathParam("arg2", 0.0)
                .when().get("/api/calc/{op}/{arg1}/{arg2}")
                .then().statusCode(200);

        given().pathParam("op", "cosine").pathParam("arg1", 0.0).pathParam("arg2", 0.0)
                .when().get("/api/calc/{op}/{arg1}/{arg2}")
                .then().statusCode(200);

        given().pathParam("op", "tangent").pathParam("arg1", 0.0).pathParam("arg2", 0.0)
                .when().get("/api/calc/{op}/{arg1}/{arg2}")
                .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBinaryOperators1() {
        given().pathParam("op", "plus").pathParam("arg1", 5.0).pathParam("arg2", 3.0)
                .when().get("/api/calc/{op}/{arg1}/{arg2}")
                .then().statusCode(200);

        given().pathParam("op", "subtract").pathParam("arg1", 5.0).pathParam("arg2", 3.0)
                .when().get("/api/calc/{op}/{arg1}/{arg2}")
                .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBinaryOperators2() {
        given().pathParam("op", "multiply").pathParam("arg1", 5.0).pathParam("arg2", 3.0)
                .when().get("/api/calc/{op}/{arg1}/{arg2}")
                .then().statusCode(200);

        given().pathParam("op", "divide").pathParam("arg1", 6.0).pathParam("arg2", 2.0)
                .when().get("/api/calc/{op}/{arg1}/{arg2}")
                .then().statusCode(200);
    }
}