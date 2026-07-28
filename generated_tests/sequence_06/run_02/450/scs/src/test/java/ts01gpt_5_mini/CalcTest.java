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
            String env = System.getenv("API_BASE");
            if (env != null && !env.isEmpty()) {
                base = env;
            } else {
                base = "http://localhost:8080";
            }
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPiEndpointReturns200() {
        given().when().get("/api/calc/e/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sqrt/9/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/log/2.718281828/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sine/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/cosine/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/tangent/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/plus/1/2").then().statusCode(lessThan(300));
        given().when().get("/api/calc/subtract/5/3").then().statusCode(lessThan(300));
        given().when().get("/api/calc/multiply/2/3").then().statusCode(lessThan(300));
        given().when().get("/api/calc/divide/10/2").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/calc/pi/0/0");
        assertEquals(200, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testDivideEndpointReturnsExpectedBody() {
        String uid = UUID.randomUUID().toString();
        given().when().get("/api/calc/PlUs/15.5/4.5").then().statusCode(lessThan(300));
        given().when().get("/api/calc/SuBtRaCt/10/4").then().statusCode(lessThan(300));
        given().when().get("/api/calc/Multiply/3/5").then().statusCode(lessThan(300));
        given().when().get("/api/calc/Divide/20/4").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/calc/divide/20/4");
        assertEquals("5.0", resp.asString().trim());
    }

    @Test(timeout = 60000)
    public void testUnknownOpReturns200() {
        given().when().get("/api/calc/plus/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/e/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sqrt/16/0").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/calc/unknownop/1/2");
        assertEquals(200, resp.getStatusCode());
    }
}