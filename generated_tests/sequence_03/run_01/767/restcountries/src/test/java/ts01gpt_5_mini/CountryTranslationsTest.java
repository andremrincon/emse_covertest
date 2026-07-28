package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import java.net.URI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CountryTranslationsTest {

    @BeforeClass
    public static void setup() {
        String cfg = System.getProperty("baseUrl");
        if (cfg == null) cfg = System.getenv("BASE_URL");
        if (cfg == null) cfg = "http://localhost:8080/rest";
        try {
            URI uri = new URI(cfg);
            String path = uri.getPath();
            String scheme = uri.getScheme() == null ? "http" : uri.getScheme();
            String host = uri.getHost();
            int port = uri.getPort();
            if (host != null) {
                RestAssured.baseURI = scheme + "://" + host;
                if (port != -1) RestAssured.port = port;
                RestAssured.basePath = path == null || path.isEmpty() ? "" : path;
            } else {
                RestAssured.baseURI = cfg;
            }
        } catch (Exception e) {
            RestAssured.baseURI = cfg;
        }
    }

    @Test(timeout = 60000)
    public void testSetDe_v1Alpha_US() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().body("translations.de", equalTo("Vereinigte Staaten von Amerika"));
    }

    @Test(timeout = 60000)
    public void testSetEs_v1Alpha_US() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().body("translations.es", equalTo("Estados Unidos"));
    }

    @Test(timeout = 60000)
    public void testSetFr_v1Alpha_US() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().body("translations.fr", equalTo("États-Unis"));
    }

    @Test(timeout = 60000)
    public void testSetJa_v1Alpha_US() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().body("translations.ja", equalTo("アメリカ合衆国"));
    }

    @Test(timeout = 60000)
    public void testSetIt_v1Alpha_US() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/alpha/US").then().body("translations.it", equalTo("Stati Uniti D'America"));
    }
}