package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class ResponseEntityTest {

    @BeforeClass
    public static void setup() {
        String fromProp = System.getProperty("rest.base");
        String fromEnv = System.getenv("REST_BASE");
        if (fromProp != null && !fromProp.isEmpty()) {
            RestAssured.baseURI = fromProp;
        } else if (fromEnv != null && !fromEnv.isEmpty()) {
            RestAssured.baseURI = fromEnv;
        } else {
            RestAssured.baseURI = "http://localhost:8080/rest";
        }
    }

    @Test(timeout = 60000)
    public void testGetNameNotFoundMessage() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/123");
        act.then().body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testGetNameInternalServerStatus() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/True");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testCapitalNotFoundStatusField() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/capital/123");
        act.then().body("status", equalTo(404));
    }

    @Test(timeout = 60000)
    public void testRegionInternalServerMessage() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/region/True");
        act.then().body("message", equalTo("Not Found"));
    }
}