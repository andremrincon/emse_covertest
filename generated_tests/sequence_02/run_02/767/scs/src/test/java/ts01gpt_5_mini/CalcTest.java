package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;
import java.util.UUID;

public class CalcTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        base = base.endsWith("/") ? base.substring(0, base.length()-1) : base;
        baseURI = base;
    }

    @Test(timeout = 60000)
    public void testBinaryPlusReturns200() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "pi", 0, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "e", 0, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sqrt", 9, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "log", 2.7182818, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sine", 0, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "cosine", 0, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "tangent", 0, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "subtract", 5, 2).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "multiply", 3, 4).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "divide", 10, 2).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "plus", 1, 1).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "plus", 15.5, 4.5).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "subtract", 0, -1).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "multiply", 2, -3).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "divide", 9, 3).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "plus", 2, 3).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSqrtReturnsExpectedBody() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "SQRT", 25, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sqrt", 16, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sqrt", 9, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sqrt", 4, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sqrt", 100, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sqrt", 25, 0).then().body(equalTo("5.0"));
    }

    @Test(timeout = 60000)
    public void testUnknownOperationHandledGracefully() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "nonexistentop", 1, 2).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "unknown", 0, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "fooBar", 7, 8).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "nope", 0, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "mystery", 3.14, 2.71).then().statusCode(200);
    }
}