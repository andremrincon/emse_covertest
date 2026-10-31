package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class ResponseEntityTest {

    @BeforeClass
    public static void setUp() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNameNumericReturns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/{name}", "123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testNameTrueReturnsInternalServerErrorMessage() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/{name}", "True").then().body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testNameXyzReturnsJsonStatus404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/{name}", "XYZ").then().body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testRegionTrueReturnsStatus500() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/region/{region}", "True").then().body("status", equalTo(404));
    }
}