package ts01gpt_5_mini;

import io.restassured.response.Response;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NotFoundExceptionMapperTest {

    private static String baseUrl() {
        String b = System.getProperty("rest.base.url");
        if (b == null || b.isEmpty()) b = System.getenv("REST_BASE_URL");
        if (b == null || b.isEmpty()) b = System.getProperty("BASE_URL");
        if (b == null || b.isEmpty()) b = System.getenv("BASE_URL");
        if (b == null || b.isEmpty()) b = "http://localhost:8080/rest";
        return b;
    }

    @Test(timeout = 60000)
    public void testV1AlphaNotFoundMapsTo404() {
        given().baseUri(baseUrl()).when().get("/v1/all").then().statusCode(lessThan(300));
        given().baseUri(baseUrl()).when().get("/v1/alpha/XYZ").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testUnknownPathReturns404ThroughMapper() {
        given().baseUri(baseUrl()).when().get("/v2").then().statusCode(lessThan(300));
        String id = UUID.randomUUID().toString();
        given().baseUri(baseUrl()).when().get("/non-existent-path-" + id).then().statusCode(404);
    }
}