package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CalcTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASEURL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testSqrtReturnsFour() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "pi", "0", "0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "e", "0", "0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sine", "0", "0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "cosine", "0", "0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "tangent", "1", "0").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sqrt", "16", "0");
        act.then().assertThat().body(equalTo("4.0"));
    }

    @Test(timeout = 60000)
    public void testPlusReturnsSum() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "log", "2.718281828459045", "0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "subtract", "10", "5").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "multiply", "3", "7").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "divide", "20", "5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/{op}/{arg1}/{arg2}", "plus", "15.5", "4.5");
        act.then().assertThat().body(equalTo("20.0"));
    }

    @Test(timeout = 60000)
    public void testInvalidNumberReturns400() {
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "pi", "0", "0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "e", "0", "0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sqrt", "9", "0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "log", "10", "0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sine", "1", "0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "cosine", "1", "0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "tangent", "1", "0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "subtract", "5", "2").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "multiply", "2", "3").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "divide", "10", "2").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "plus", "10", "twenty").then().statusCode(400);
    }
}