package ts01qwen3_7_plus_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CostfunsTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCostfuns_iEquals5_sEqualsBaab() {
        int i = 5;
        String s = "baab";

        Response response = given()
                .pathParam("i", i)
                .pathParam("s", s)
                .when()
                .get("/api/costfuns/{i}/{s}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iLessThanMinus444_sCompareToEquals0() {
        int i = -500;
        String s = "ababba";

        Response response = given()
                .pathParam("i", i)
                .pathParam("s", s)
                .when()
                .get("/api/costfuns/{i}/{s}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iGreaterThan666_sCompareToLessThan0() {
        int i = 700;
        String s = "a";

        Response response = given()
                .pathParam("i", i)
                .pathParam("s", s)
                .when()
                .get("/api/costfuns/{i}/{s}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iEqualsMinus4_sCompareToLessThan0() {
        int i = -4;
        String s = "a";

        Response response = given()
                .pathParam("i", i)
                .pathParam("s", s)
                .when()
                .get("/api/costfuns/{i}/{s}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iGreaterThanOrEqual555_sEqualsAbab() {
        int i = 600;
        String s = "abab";

        Response response = given()
                .pathParam("i", i)
                .pathParam("s", s)
                .when()
                .get("/api/costfuns/{i}/{s}");

        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iLessThanOrEqualMinus333_sCompareToGreaterThan0() {
        int i = -400;
        String s = "test";

        Response response = given()
                .pathParam("i", i)
                .pathParam("s", s)
                .when()
                .get("/api/costfuns/{i}/{s}");

        response.then().statusCode(200);
    }
}