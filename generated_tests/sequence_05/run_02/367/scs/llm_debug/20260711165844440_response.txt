package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CalcTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("BASE_URL");
        if (env != null && !env.isEmpty()) {
            RestAssured.baseURI = env;
        } else {
            RestAssured.baseURI = System.getProperty("api.base", "http://localhost:8080");
        }
    }

    @Test(timeout = 60000)
    public void testPiReturnsMathPiString() {
        String uid = UUID.randomUUID().toString().replace("-", "");
        given().when().get("/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "pi", "0", "0")
               .then().assertThat().body(equalTo(Double.toString(Math.PI)));
    }

    @Test(timeout = 60000)
    public void testDivideByZeroReturns500() {
        String uid = UUID.randomUUID().toString().replace("-", "");
        given().when().get("/api/text2txt/{w}/{x}/{y}", uid, "setup", "run").then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "divide", "100", "0")
               .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPlusAddsNumbersCorrectly() {
        String uid = UUID.randomUUID().toString().replace("-", "");
        given().when().get("/api/notypevar/{i}/{s}", "0", uid).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "plus", "15.5", "4.5")
               .then().assertThat().body(equalTo(Double.toString(15.5 + 4.5)));
    }
}