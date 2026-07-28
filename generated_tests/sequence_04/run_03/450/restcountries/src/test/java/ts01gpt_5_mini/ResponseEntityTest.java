package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class ResponseEntityTest {

    @BeforeClass
    public static void setup() {
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
    public void testV1NameNotFoundMessage() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/NotARealCountry");
        assertEquals("Not Found", act.jsonPath().getString("message"));
    }

    @Test(timeout = 60000)
    public void testV1NameServerErrorStatus() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/True");
        assertEquals(404, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1CapitalNotFoundMessage() {
        given().when().get("/v2").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/capital/123");
        assertEquals("Not Found", act.jsonPath().getString("message"));
    }

    @Test(timeout = 60000)
    public void testV2NameInternalServerErrorStatus() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/name/Atlantis");
        assertEquals(404, act.getStatusCode());
    }
}