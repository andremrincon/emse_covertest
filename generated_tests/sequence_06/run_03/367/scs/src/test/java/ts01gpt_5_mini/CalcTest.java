package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CalcTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPiReturnsPiValueInBody() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/arrange-" + uid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/pi/0/0");
        assertEquals(Double.toString(Math.PI), act.getBody().asString());
    }

    @Test(timeout = 60000)
    public void testSqrtOperationReturns200Status() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/setup-" + uid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/sqrt/16/0");
        assertEquals(200, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testUnknownOperationReturnsZeroString() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/pat/init-" + uid).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/unknown/0/0");
        assertEquals("0.0", act.getBody().asString());
    }
}