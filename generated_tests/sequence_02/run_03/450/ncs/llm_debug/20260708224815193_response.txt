package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testEquilateral() {
        int a = 3;
        int b = 3;
        int c = 3;
        given().pathParam("a", a).pathParam("b", b).pathParam("c", c)
                .when().get("/api/triangle/{a}/{b}/{c}")
                .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidInequality() {
        int a = 1;
        int b = 2;
        int c = 3;
        given().pathParam("a", a).pathParam("b", b).pathParam("c", c)
                .when().get("/api/triangle/{a}/{b}/{c}")
                .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsosceles() {
        int a = 3;
        int b = 3;
        int c = 4;
        given().pathParam("a", a).pathParam("b", b).pathParam("c", c)
                .when().get("/api/triangle/{a}/{b}/{c}")
                .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScalene() {
        int a = 3;
        int b = 4;
        int c = 5;
        given().pathParam("a", a).pathParam("b", b).pathParam("c", c)
                .when().get("/api/triangle/{a}/{b}/{c}")
                .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidA() {
        int a = 0;
        int b = 4;
        int c = 5;
        given().pathParam("a", a).pathParam("b", b).pathParam("c", c)
                .when().get("/api/triangle/{a}/{b}/{c}")
                .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testInvalidB() {
        int a = 3;
        int b = 0;
        int c = 5;
        given().pathParam("a", a).pathParam("b", b).pathParam("c", c)
                .when().get("/api/triangle/{a}/{b}/{c}")
                .then().statusCode(200);
    }
}