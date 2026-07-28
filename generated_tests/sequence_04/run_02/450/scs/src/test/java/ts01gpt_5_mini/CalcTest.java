package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CalcTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            String env = System.getenv("API_BASE");
            base = (env == null || env.isEmpty()) ? "http://localhost:8080" : env;
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPiReturnsPiValue() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/calc/e/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sqrt/9/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/log/2.718281828459045/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sine/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/cosine/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/tangent/0/0").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/pi/0/0");
        act.then().body(equalTo(Double.toString(Math.PI)));
    }

    @Test(timeout = 60000)
    public void testPlusReturnsSum() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/calc/subtract/10/4").then().statusCode(lessThan(300));
        given().when().get("/api/calc/multiply/3/5").then().statusCode(lessThan(300));
        given().when().get("/api/calc/divide/9/3").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/calc/plus/7.5/2.5");
        act.then().body(equalTo(Double.toString(7.5 + 2.5)));
    }
}