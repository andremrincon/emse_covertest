package ts01glm_5_2;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;

public class GammqTest {

    @Before
    public void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testGammqGserPathValid() {
        given().when().get("/api/gammq/5.5/2.3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGcfPathValid() {
        given().when().get("/api/gammq/0.001/1000.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqXZeroGserBranch() {
        given().when().get("/api/gammq/5.5/0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidANegative() {
        given().when().get("/api/gammq/-1.0/2.0").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidXNegative() {
        given().when().get("/api/gammq/5.5/-1.0").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidAType() {
        given().when().get("/api/gammq/abc/2.0").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqSmallASmallXGserPath() {
        given().when().get("/api/gammq/0.001/0.001").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqAZeroInvalid() {
        given().when().get("/api/gammq/0/2.0").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqLargeXGcfPath() {
        given().when().get("/api/gammq/5.5/1000.0").then().statusCode(200);
    }
}