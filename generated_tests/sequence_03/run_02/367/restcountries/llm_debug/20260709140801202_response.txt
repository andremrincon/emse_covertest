package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URL;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.equalTo;

public class ResponseEntityTest {

    @BeforeClass
    public static void setUp() throws Exception {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        URL u = new URL(base);
        RestAssured.baseURI = u.getProtocol() + "://" + u.getHost();
        if (u.getPort() != -1) RestAssured.port = u.getPort();
        RestAssured.basePath = u.getPath();
    }

    @Test(timeout = 60000)
    public void testGetStatusFromNameNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/123");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetMessageFromNameNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/name/123");
        act.then().body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void testGetStatusFromCapitalNotFound() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/capital/123");
        act.then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void testGetMessageFromRegionServerError() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/region/True");
        act.then().body("message", equalTo("Not Found"));
    }
}