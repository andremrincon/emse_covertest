package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URL;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class NotFoundExceptionMapperTest {

    @BeforeClass
    public static void setup() throws Exception {
        String configured = System.getProperty("baseUrl");
        if (configured == null || configured.isEmpty()) configured = System.getenv("BASE_URL");
        if (configured == null || configured.isEmpty()) configured = "http://localhost:8080/rest";
        URL u = new URL(configured);
        String host = u.getProtocol() + "://" + u.getHost();
        if (u.getPort() != -1) host += ":" + u.getPort();
        RestAssured.baseURI = host;
        RestAssured.basePath = u.getPath();
    }

    @Test(timeout = 60000)
    public void testNotFoundMapperReturns404ForUnknownAlphaCode() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/alpha/XYZ");
        Assert.assertEquals(404, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testNotFoundMapperReturns404ForInvalidName() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response resp = given().when().get("/v1/name/123");
        Assert.assertEquals(404, resp.getStatusCode());
    }
}