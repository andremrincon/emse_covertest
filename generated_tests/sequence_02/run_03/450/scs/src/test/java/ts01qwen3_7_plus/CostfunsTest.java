package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CostfunsTest {

    private String baseUrl;

    @Before
    public void setUp() {
        baseUrl = System.getProperty("baseUrl", "http://localhost:8080");
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCostfuns_iEquals5() {
        Response response = given()
            .when()
            .get("/api/costfuns/5/a");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iLessThanMinus444() {
        Response response = given()
            .when()
            .get("/api/costfuns/-500/a");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iGreaterThan666() {
        Response response = given()
            .when()
            .get("/api/costfuns/700/a");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_iEqualsMinus4() {
        Response response = given()
            .when()
            .get("/api/costfuns/-4/a");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_sEqualsBaab() {
        Response response = given()
            .when()
            .get("/api/costfuns/0/baab");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_sCompareToAbabbaEqualsZero() {
        Response response = given()
            .when()
            .get("/api/costfuns/0/ababba");
        response.then().statusCode(200);
    }
}