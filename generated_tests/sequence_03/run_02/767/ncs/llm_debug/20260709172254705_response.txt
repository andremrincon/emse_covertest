package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GammqTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getenv("BASE_URL") != null ? System.getenv("BASE_URL") : "http://localhost:8080";
    }

    @Test(timeout = 60000)
    public void testGammqGserNormal() {
        String a = "5.5";
        String x = "2.3";

        given().when().get("/api/gammq/" + a + "/" + x).then().statusCode(lessThan(300));

        given().when().get("/api/gammq/" + a + "/" + x).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGserZeroX() {
        String a = "5.5";
        String x = "0.0";

        given().when().get("/api/gammq/" + a + "/" + x).then().statusCode(lessThan(300));

        given().when().get("/api/gammq/" + a + "/" + x).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGcfNormal() {
        String a = "0.001";
        String x = "1000.0";

        given().when().get("/api/gammq/" + a + "/" + x).then().statusCode(lessThan(300));

        given().when().get("/api/gammq/" + a + "/" + x).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidA() {
        String a = "-1.0";
        String x = "2.0";

        given().when().get("/api/gammq/" + "5.5" + "/" + "2.0").then().statusCode(lessThan(300));

        given().when().get("/api/gammq/" + a + "/" + x).then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidX() {
        String a = "5.5";
        String x = "-1.0";

        given().when().get("/api/gammq/" + "5.5" + "/" + "2.0").then().statusCode(lessThan(300));

        given().when().get("/api/gammq/" + a + "/" + x).then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidType() {
        String a = "abc";
        String x = "2.0";

        given().when().get("/api/gammq/" + "5.5" + "/" + "2.0").then().statusCode(lessThan(300));

        given().when().get("/api/gammq/" + a + "/" + x).then().statusCode(400);
    }
}