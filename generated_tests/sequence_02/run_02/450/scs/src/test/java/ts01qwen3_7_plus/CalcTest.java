package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CalcTest {

    private String op;
    private double arg1;
    private double arg2;

    public CalcTest(String op, double arg1, double arg2) {
        this.op = op;
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
            {"pi", 0.0, 0.0},
            {"e", 0.0, 0.0},
            {"sqrt", 4.0, 0.0},
            {"log", 10.0, 0.0},
            {"sine", 0.0, 0.0},
            {"cosine", 0.0, 0.0},
            {"tangent", 0.0, 0.0},
            {"plus", 15.5, 4.5},
            {"subtract", 15.5, 4.5},
            {"multiply", 15.5, 4.5},
            {"divide", 15.5, 4.5}
        });
    }

    @Before
    public void setup() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testCalcOperation() {
        String url = "/api/calc/" + op + "/" + arg1 + "/" + arg2;
        Response response = RestAssured.given().when().get(url);
        response.then().statusCode(200);
    }
}