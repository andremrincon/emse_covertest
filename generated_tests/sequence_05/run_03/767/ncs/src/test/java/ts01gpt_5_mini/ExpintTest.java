package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    @BeforeClass
    public static void setup() {
        String base = Optional.ofNullable(System.getProperty("api.base"))
                .orElse(Optional.ofNullable(System.getenv("API_BASE"))
                        .orElse("http://localhost:8080"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testExpint_ContinuedFraction_Returns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/{n}/{x}", 3, 2.5);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_SeriesBranch_Returns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/{n}/{x}", 3, 0.1);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_NEqualsZero_Returns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/{n}/{x}", 0, 1.0);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_XEqualsZero_NonZeroN_Returns200() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/{n}/{x}", 2, 0.0);
        act.then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpint_InvalidNegativeN_Returns400() {
        given().when().get("/api/triangle/{a}/{b}/{c}", 3, 4, 5).then().statusCode(lessThan(300));
        Response act = given().when().get("/api/expint/{n}/{x}", -1, 1.0);
        act.then().statusCode(400);
    }
}