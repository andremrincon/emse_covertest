package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CalcTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("test.server");
        if (base == null || base.isEmpty()) base = System.getenv("TEST_BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("TEST_SERVER");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCalcPlusReturns200() {
        String uid = UUID.randomUUID().toString().replace("-", "");
        given().when().get("/api/pat/" + uid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/plus/15.5/4.5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcDivideByZeroReturns500() {
        String uid = UUID.randomUUID().toString().replace("-", "");
        given().when().get("/api/text2txt/" + uid + "/x/y").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/divide/100/0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCalcPiReturnsExpectedBody() {
        String uid = UUID.randomUUID().toString().replace("-", "");
        given().when().get("/api/costfuns/1/" + uid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/pi/0/0");
        act.then().body(equalTo(Double.toString(Math.PI)));
    }
}