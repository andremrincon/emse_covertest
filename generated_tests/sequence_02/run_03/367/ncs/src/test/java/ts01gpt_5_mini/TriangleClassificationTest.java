package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.net.URL;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @BeforeClass
    public static void init() throws Exception {
        String env = System.getProperty("base.uri");
        if (env == null) env = System.getenv("BASE_URI");
        if (env == null) env = "http://localhost:8080";
        URL url = new URL(env);
        RestAssured.baseURI = url.getProtocol() + "://" + url.getHost();
        if (url.getPort() != -1) RestAssured.port = url.getPort();
        RestAssured.basePath = "";
    }

    @Test(timeout = 60000)
    public void testInvalidNonPositiveEdgeReturns200() {
        given().when().get("/api/remainder/17/5").then().statusCode(lessThan(300));
        given().when().get("/api/triangle/0/1/1").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangleReturns200() {
        given().when().get("/api/remainder/10/3").then().statusCode(lessThan(300));
        given().when().get("/api/triangle/5/5/5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testDegenerateTriangleReturns200() {
        given().when().get("/api/remainder/9/4").then().statusCode(lessThan(300));
        given().when().get("/api/triangle/5/2/3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangleReturns200() {
        given().when().get("/api/remainder/8/3").then().statusCode(lessThan(300));
        given().when().get("/api/triangle/4/4/3").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangleReturns200() {
        given().when().get("/api/remainder/11/6").then().statusCode(lessThan(300));
        given().when().get("/api/triangle/3/4/5").then().statusCode(200);
    }
}