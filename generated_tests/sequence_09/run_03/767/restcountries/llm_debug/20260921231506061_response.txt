package ts01gpt_5_mini_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class NotFoundExceptionMapperTest {

    @BeforeClass
    public static void setup() throws Exception {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080/rest";
        URI uri = new URI(base);
        String host = uri.getScheme() + "://" + uri.getHost();
        if (uri.getPort() != -1) host += ":" + uri.getPort();
        RestAssured.baseURI = host;
        RestAssured.basePath = uri.getPath();
    }

    @Test(timeout = 60000)
    public void testNotFoundMapping_v1AlphaPath_returns404() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        Response act = given().when().get("/v1/alpha/XYZ");
        assertEquals(404, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testNotFoundMapping_v2AlphaPath_returns404() {
        given().when().get("/v2/all?fields=name;capital;region;population;flag").then().statusCode(lessThan(300));
        Response act = given().when().get("/v2/alpha/ZZZ");
        assertEquals(404, act.getStatusCode());
    }
}