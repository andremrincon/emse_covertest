package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CalcTest {

    @BeforeClass
    public static void setUp() {
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
    public void testConstantOperatorsPiAndE() {
        given().when().get("/api/calc/pi/0/0").then().statusCode(200);
        given().when().get("/api/calc/e/0/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnaryOperatorsSqrtAndLog() {
        given().when().get("/api/calc/sqrt/16/0").then().statusCode(200);
        given().when().get("/api/calc/log/1/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTrigOperatorsSineCosineTangent() {
        given().when().get("/api/calc/sine/0/0").then().statusCode(200);
        given().when().get("/api/calc/cosine/0/0").then().statusCode(200);
        given().when().get("/api/calc/tangent/0/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBinaryOperatorsPlusAndSubtract() {
        given().when().get("/api/calc/plus/15.5/4.5").then().statusCode(200);
        given().when().get("/api/calc/subtract/10/3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBinaryOperatorsMultiplyAndDivide() {
        given().when().get("/api/calc/multiply/5/4").then().statusCode(200);
        given().when().get("/api/calc/divide/20/4").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnknownOperatorDefaultBranch() {
        given().when().get("/api/calc/unknown/1/1").then().statusCode(200);
    }
}