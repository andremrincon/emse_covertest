package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class CalcTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testConstantsPi_returnsMathPiString() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "e", 0, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sqrt", 9, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "log", 1, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sine", 0, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "cosine", 0, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "tangent", 0, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "plus", 1, 2).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "subtract", 5, 3).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "multiply", 2, 3).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "divide", 10, 2).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "pi", 0, 0);
        resp.then().body(equalTo(String.valueOf(Math.PI)));
    }

    @Test(timeout = 60000)
    public void testConstantE_returnsStatus200() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "pi", 0, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sqrt", 4, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "log", 2.718281828459045, 0).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "e", 0, 0);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnarySqrt_returnsExpectedValue() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "plus", 10, 5).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "subtract", 10, 5).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sqrt", 16, 0);
        resp.then().body(equalTo("4.0"));
    }

    @Test(timeout = 60000)
    public void testBinaryPlus_status200ForNumericAddition() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sqrt", 25, 0).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "log", 10, 0).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "plus", 15.5, 4.5);
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBinaryMultiply_returnsProductString() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "subtract", 9, 3).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "divide", 20, 2).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "multiply", 2, 3);
        resp.then().body(equalTo("6.0"));
    }

    @Test(timeout = 60000)
    public void testBinaryDivide_status200ForValidDivision() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "multiply", 3, 3).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "plus", 0, 0).then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "divide", 100, 4);
        resp.then().statusCode(200);
    }
}