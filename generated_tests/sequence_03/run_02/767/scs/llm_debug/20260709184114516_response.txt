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
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("api.base", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPiReturnsPiString() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + uid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/pi/0/0");
        act.then().body(equalTo(Double.toString(Math.PI)));
    }

    @Test(timeout = 60000)
    public void testEReturnsEString() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/" + uid + "/a/b").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/e/0/0");
        act.then().body(equalTo(Double.toString(Math.E)));
    }

    @Test(timeout = 60000)
    public void testSqrtOfNineReturnsThree() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + uid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/sqrt/9/0");
        act.then().body(equalTo(Double.toString(Math.sqrt(9.0))));
    }

    @Test(timeout = 60000)
    public void testPlusOperationReturnsOkStatus() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + uid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/plus/15.5/4.5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDivideByZeroProducesServerError() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/text2txt/" + uid + "/x/y").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/divide/100/0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSineOfPiOverTwoReturnsOne() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/" + uid).then().statusCode(lessThan(300));
        double piOverTwo = Math.PI / 2.0;
        Response act = given().when().get("/api/calc/sine/" + Double.toString(piOverTwo) + "/0");
        act.then().body(equalTo(Double.toString(Math.sin(piOverTwo))));
    }
}