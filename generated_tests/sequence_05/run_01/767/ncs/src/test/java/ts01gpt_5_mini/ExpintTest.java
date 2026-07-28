package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URL;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ExpintTest {

    @BeforeClass
    public static void setup() throws Exception {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            RestAssured.baseURI = "http://localhost";
            RestAssured.port = 8080;
            RestAssured.basePath = "/";
        } else {
            URL u = new URL(base);
            String protoHost = u.getProtocol() + "://" + u.getHost();
            RestAssured.baseURI = protoHost;
            int p = u.getPort();
            RestAssured.port = p == -1 ? u.getDefaultPort() : p;
            RestAssured.basePath = u.getPath() == null || u.getPath().isEmpty() ? "/" : u.getPath();
        }
    }

    @Test(timeout = 60000)
    public void testExpintContinuedFractionLargeX() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", 3, 2.5).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintSeriesSmallXForN1() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", 1, 0.1).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintWithNZero() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", 0, 2.5).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintXZeroNonEdge() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", 2, 0.0).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExpintInvalidNegativeN() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/expint/{n}/{x}", -1, 1.0).then().statusCode(400);
    }
}