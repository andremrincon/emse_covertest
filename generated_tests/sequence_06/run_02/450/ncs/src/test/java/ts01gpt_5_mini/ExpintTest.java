package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

public class ExpintTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testExpintContinuedFractionReturns200() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/3/2.5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesReturnsSuccessStatusInBody() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/3/0.1");
        act.then().body("status", nullValue());
    }

    @Test(timeout = 60000)
    public void testExpintNZeroReturns200() {
        given().when().get("/api/fisher/10/5/0.75").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/0/2.5");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXZeroWithNNotOneReturns200() {
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/2/0");
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintNegativeNReturns400() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/-1/2.5");
        act.then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testExpintXZeroWithNOneReturns400() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/1/0");
        act.then().statusCode(400);
    }
}