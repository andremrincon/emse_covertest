package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class CalcTest {

    private final String base = System.getProperty("api.base", "http://localhost:8080");

    @Test(timeout = 60000)
    public void testPiConstant() {
        String uid = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/" + uid).then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/calc/pi/0/0");
        assertEquals(String.valueOf(Math.PI), resp.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testSqrtUnary() {
        String uid = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/setup-" + uid).then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/calc/sqrt/16/0");
        assertEquals("4.0", resp.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testSineZero() {
        String uid = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/arr-" + uid).then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/calc/sine/0/0");
        assertEquals("0.0", resp.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testAdditionBinary() {
        String uid = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/setup-add-" + uid).then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/calc/plus/15.5/4.5");
        assertEquals("20.0", resp.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testDivisionBinary() {
        String uid = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/setup-div-" + uid).then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/calc/divide/10/2");
        assertEquals("5.0", resp.getBody().asString().trim());
    }

    @Test(timeout = 60000)
    public void testLogUnary() {
        String uid = UUID.randomUUID().toString();
        given().when().get(base + "/api/pat/setup-log-" + uid).then().statusCode(lessThan(300));
        Response resp = given().when().get(base + "/api/calc/log/" + Math.E + "/0");
        assertEquals("1.0", resp.getBody().asString().trim());
    }
}