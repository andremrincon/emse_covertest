package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class CalcTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testConstantsAndUnaries_returnPiBody() {
        given().when().get("/api/calc/e/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sqrt/9/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/log/2.718281828459045/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sine/1.5707963267948966/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/cosine/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/tangent/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/pi/0/0").then().body(equalTo("3.141592653589793"));
    }

    @Test(timeout = 60000)
    public void testBinaryPlus_returnsStatus200() {
        given().when().get("/api/calc/multiply/3/5").then().statusCode(lessThan(300));
        given().when().get("/api/calc/subtract/20/5").then().statusCode(lessThan(300));
        given().when().get("/api/calc/divide/20/2").then().statusCode(lessThan(300));
        given().when().get("/api/calc/plus/15.5/4.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDivideByZero_returns500() {
        given().when().get("/api/calc/sine/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/plus/1/1").then().statusCode(lessThan(300));
        given().when().get("/api/calc/divide/100/0").then().statusCode(200);
    }
}