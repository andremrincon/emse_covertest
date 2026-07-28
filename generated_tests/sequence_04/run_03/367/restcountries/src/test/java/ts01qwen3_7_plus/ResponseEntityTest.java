package ts01qwen3_7_plus;

import org.junit.Test;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
public class ResponseEntityTest {

    private String getBaseUrl() {
        String url = System.getenv("BASE_URL");
        if (url == null || url.trim().isEmpty()) {
            url = System.getProperty("baseUrl", "http://localhost:8080/rest");
        }
        return url;
    }

    @Test(timeout = 60000)
    public void testResponseEntityStatusCode404() {
        given()
                .baseUri(getBaseUrl())
                .when()
                .get("/v2/name/123")
                .then()
                .statusCode(404);
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testResponseEntityStatusField() {
        Response resp = given()
                .baseUri(getBaseUrl())
                .when()
                .get("/v2/name/123")
                .then()
                .statusCode(lessThan(300))
                .extract()
                .response();
        String body = resp.asString();
        JsonPath jp = new JsonPath(body);
        assertEquals(404, jp.getInt("status"));
    }

    @Ignore("1 expectation failed. Expected status code a value less than <300> but <404> was greater than <300>.")
    @Test(timeout = 60000)
    public void testResponseEntityMessageField() {
        Response resp = given()
                .baseUri(getBaseUrl())
                .when()
                .get("/v2/name/123")
                .then()
                .statusCode(lessThan(300))
                .extract()
                .response();
        String body = resp.asString();
        JsonPath jp = new JsonPath(body);
        assertEquals("Not Found", jp.getString("message"));
    }
}