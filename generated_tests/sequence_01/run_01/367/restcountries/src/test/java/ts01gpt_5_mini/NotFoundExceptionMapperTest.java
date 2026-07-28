package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.Optional;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class NotFoundExceptionMapperTest {

    @BeforeClass
    public static void setup() {
        String base = Optional.ofNullable(System.getProperty("base.url"))
                .orElse(Optional.ofNullable(System.getenv("BASE_URL"))
                .orElse("http://localhost:8080/rest"));
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testV1AlphaNotFoundTriggersNotFoundExceptionMapper() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/XYZ");
        assertEquals(404, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testV1NameNotFoundTriggersNotFoundExceptionMapper() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/123");
        assertEquals(404, act.getStatusCode());
    }
}