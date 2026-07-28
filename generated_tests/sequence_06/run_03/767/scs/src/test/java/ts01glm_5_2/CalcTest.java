package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalcTest {

    private static final String BASE_URL = System.getProperty("baseUrl", "http://localhost:8080");

    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test(timeout = 60000)
    public void testConstantOperatorsPiAndE() {
        given().when().get("/api/calc/e/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/pi/0/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnaryOperatorsSqrtAndLog() {
        given().when().get("/api/calc/log/10/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sqrt/16/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTrigOperatorsSineCosineTangent() {
        given().when().get("/api/calc/cosine/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/tangent/0/0").then().statusCode(lessThan(300));
        given().when().get("/api/calc/sine/0/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBinaryOperatorsPlusAndSubtract() {
        given().when().get("/api/calc/subtract/10/3").then().statusCode(lessThan(300));
        given().when().get("/api/calc/plus/10/3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testBinaryOperatorsMultiplyAndDivide() {
        given().when().get("/api/calc/divide/10/2").then().statusCode(lessThan(300));
        given().when().get("/api/calc/multiply/10/3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testUnknownOperatorDefaultBranch() {
        given().when().get("/api/calc/unknown/5/5").then().statusCode(200);
    }
}