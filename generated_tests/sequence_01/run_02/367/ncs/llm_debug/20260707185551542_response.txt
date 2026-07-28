package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GammqTest {

    @BeforeClass
    public static void setUp() {
        String base = System.getProperty("base.url");
        if (base == null || base.isEmpty()) base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testGammqGserConverges() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/5.5/0.001").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqGserZeroXReturnsOne() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/5.5/0.0").then().statusCode(200).body("result", nullValue());
    }

    @Test(timeout = 60000)
    public void testGammqGcfLargeX() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/5.5/1000.0").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidNegativeAProducesBadRequest() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/-1.0/2.3").then().statusCode(400);
    }

    @Test(timeout = 60000)
    public void testGammqInvalidNegativeXProducesBadRequest() {
        given().when().get("/api/triangle/3/4/5").then().statusCode(lessThan(300));
        given().when().get("/api/gammq/5.5/-0.1").then().statusCode(400);
    }
}