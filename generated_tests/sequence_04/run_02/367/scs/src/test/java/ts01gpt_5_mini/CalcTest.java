package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class CalcTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPiOperator_returns200() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "pi", "0", "0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEOperator_returns200() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "setup-" + uid).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "e", "0", "0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSqrtOperator_returns200() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sqrt", "9", "0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSineOperator_returns200() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "sine", "0", "0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testPlusOperator_returns200() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", "arrange-" + uid).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "plus", "15.5", "4.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDivideOperator_returns200() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/{txt}", uid).then().statusCode(lessThan(300));
        given().when().get("/api/calc/{op}/{arg1}/{arg2}", "divide", "10", "2").then().statusCode(200);
    }
}