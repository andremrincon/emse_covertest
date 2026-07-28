package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;

public class NotFoundExceptionMapperTest {

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
    public void testAlphaCodeNotFound() {
        given().when().get("/v1/all").then().statusCode(404);
        Response act = given().when().get("/v1/alpha/XYZ");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testAlphaQueryCodesNotFound() {
        given().when().get("/v1/all").then().statusCode(404);
        String unique = UUID.randomUUID().toString();
        Response act = given().queryParam("codes", "XX").queryParam("_tid", unique).when().get("/v1/alpha");
        act.then().statusCode(404);
    }
}