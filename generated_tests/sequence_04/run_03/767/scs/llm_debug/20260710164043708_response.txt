package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CalcTest {

    private static String BASE;

    @BeforeClass
    public static void init() {
        String env = System.getenv("BASE_URL");
        if (env == null || env.isEmpty()) {
            env = System.getProperty("api.base", "http://localhost:8080");
        }
        BASE = env;
    }

    @Test(timeout = 60000)
    public void testConstantsPiReturns200() {
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "e", "0", "0").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "sine", "0", "0").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "cosine", "0", "0").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "pi", "0", "0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSqrtProducesExpectedBody() {
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "log", "10", "0").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "tangent", "0", "0").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "subtract", "5", "3").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "sqrt", "9", "0");
        act.then().body(equalTo("3.0"));
    }

    @Test(timeout = 60000)
    public void testPlusProducesSumBody() {
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "multiply", "2", "3").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "divide", "10", "2").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "plus", "1.5", "2.5");
        act.then().body(equalTo("4.0"));
    }

    @Test(timeout = 60000)
    public void testUnknownOpReturnsZeroString() {
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "e", "0", "0").then().statusCode(lessThan(300));
        given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "sine", "1.5707963267948966", "0").then().statusCode(lessThan(300));
        Response act = given().when().get(BASE + "/api/calc/{op}/{arg1}/{arg2}", "unknown", "1", "2");
        act.then().body(equalTo("0.0"));
    }
}