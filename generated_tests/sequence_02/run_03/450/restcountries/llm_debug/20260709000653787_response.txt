package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.lessThan;
import static io.restassured.RestAssured.given;

public class ResponseEntityTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testV1NameNotFoundMessage() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/NonExistentCountryXYZ");
        Assert.assertEquals("Not Found", act.jsonPath().getString("message"));
    }

    @Test(timeout = 60000)
    public void testV1CapitalNotFoundStatus() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/capital/123");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testContributeBadRequestMessage() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().contentType(ContentType.JSON).body("{}").when().post("/contribute");
        Assert.assertEquals("Bad Request", act.jsonPath().getString("message"));
    }

    @Test(timeout = 60000)
    public void testV2CapitalNotFoundStatusField() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/capital/12345");
        Integer status = act.jsonPath().getInt("status");
        Assert.assertEquals(Integer.valueOf(404), status);
    }
}