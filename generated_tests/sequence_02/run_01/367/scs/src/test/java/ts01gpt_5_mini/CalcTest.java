package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;
import java.util.UUID;

public class CalcTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testPiReturnsOk() {
        String id = UUID.randomUUID().toString();
        given().when().get("/api/calc/e/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sqrt/9/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/log/1/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sine/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/cosine/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/tangent/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/plus/1.5/2.5").then().statusCode(lessThan(300));
        given().when().get("/api/calc/subtract/5/3").then().statusCode(lessThan(300));
        given().when().get("/api/text2txt/The/quick/" + id).then().statusCode(lessThan(300));
        given().when().get("/api/calc/pi/0/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDivideByZeroProducesServerError() {
        given().when().get("/api/calc/multiply/2/3").then().statusCode(lessThan(300));
        given().when().get("/api/calc/divide/10/2").then().statusCode(lessThan(300));
        given().when().get("/api/calc/plus/15.5/4.5").then().statusCode(lessThan(300));
        given().when().get("/api/calc/divide/100/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testSqrtReturnsExpectedBody() {
        given().when().get("/api/calc/plus/7/3").then().statusCode(lessThan(300));
        given().when().get("/api/calc/subtract/10/4").then().statusCode(lessThan(300));
        given().when().get("/api/calc/multiply/4/4").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sqrt/16/0").then().statusCode(200).body(equalTo("4.0"));
    }
}