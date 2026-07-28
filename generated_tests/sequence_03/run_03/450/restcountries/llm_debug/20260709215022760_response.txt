package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import io.restassured.response.Response;

public class ResponseEntityTest {

    private String getBaseUrl() {
        String envUrl = System.getenv("BASE_URL");
        if (envUrl != null && !envUrl.isEmpty()) {
            return envUrl;
        }
        String propUrl = System.getProperty("baseUrl");
        if (propUrl != null && !propUrl.isEmpty()) {
            return propUrl;
        }
        return "http://localhost:8080/rest";
    }

    @Test(timeout = 60000)
    public void testResponseEntityStatus() {
        Response response = given()
            .baseUri(getBaseUrl())
            .when()
            .get("/v1/name/123");
        assertEquals(404, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testResponseEntityMessage() {
        Response response = given()
            .baseUri(getBaseUrl())
            .when()
            .get("/v1/name/123");
        assertEquals(404, response.getStatusCode());
        assertEquals("", response.getBody().asString());
    }
}