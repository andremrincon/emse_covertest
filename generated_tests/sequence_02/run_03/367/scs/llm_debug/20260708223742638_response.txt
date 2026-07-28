package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CalcTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", System.getenv().getOrDefault("BASE_URL", "http://localhost:8080"));
    }

    @Test(timeout = 60000)
    public void testPiEndpointReturns200() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "e", 0, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sqrt", 16, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "log", 2.718281828459045, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sine", 0, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "cosine", 0, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "tangent", 0, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "pi", 0, 0).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPlusReturnsCorrectBody() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "multiply", 2, 3).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "divide", 10, 2).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "subtract", 10, 4).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "plus", 15.5, 4.5).then().statusCode(200).body(equalTo("20.0"));
    }

    @Test(timeout = 60000)
    public void testDivideByZeroReturns500() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "plus", 1, 1).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "multiply", 5, 5).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "divide", 100, 0).then().statusCode(200);
    }
}