package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CostfunsTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testCostfunsBranchI0() {
        Response response = given()
                .pathParam("i", 5)
                .pathParam("s", "a")
                .when()
                .get("/api/costfuns/{i}/{s}");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsBranchI5() {
        Response response = given()
                .pathParam("i", -4)
                .pathParam("s", "a")
                .when()
                .get("/api/costfuns/{i}/{s}");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsBranchI1I2() {
        Response response = given()
                .pathParam("i", -500)
                .pathParam("s", "a")
                .when()
                .get("/api/costfuns/{i}/{s}");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsBranchI3I4() {
        Response response = given()
                .pathParam("i", 700)
                .pathParam("s", "a")
                .when()
                .get("/api/costfuns/{i}/{s}");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsBranchI6() {
        Response response = given()
                .pathParam("i", 0)
                .pathParam("s", "baab")
                .when()
                .get("/api/costfuns/{i}/{s}");
        response.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfunsBranchI9I10() {
        Response response = given()
                .pathParam("i", 0)
                .pathParam("s", "ababba")
                .when()
                .get("/api/costfuns/{i}/{s}");
        response.then().statusCode(200);
    }
}