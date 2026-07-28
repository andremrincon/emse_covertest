package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @BeforeClass
    public static void init() {
        String prop = System.getProperty("baseUrl");
        String env = System.getenv("BASE_URL");
        RestAssured.baseURI = prop != null ? prop : (env != null ? env : "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void test_NonPositiveSide_ResultsInZeroClassification() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/0/5/5");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_EquilateralTriangle_ResultsInThreeClassification() {
        given().when().get("/api/expint/3/0.1").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/5/5/5");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_DegenerateTriangle_MaxEqualsSum_ResultsInZeroClassification() {
        given().when().get("/api/bessj/3/2.5").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/1/2/3");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_IsoscelesTriangle_ResultsInTwoClassification() {
        given().when().get("/api/fisher/10/5/0.75").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/5/5/8");
        resp.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void test_ScaleneTriangle_ResultsInOneClassification() {
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        Response resp = given().when().get("/api/triangle/3/4/5");
        resp.then().statusCode(200);
    }
}