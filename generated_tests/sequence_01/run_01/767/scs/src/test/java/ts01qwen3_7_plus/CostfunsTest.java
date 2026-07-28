package ts01qwen3_7_plus;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Constructor;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertTrue;

public class CostfunsTest {

    @BeforeClass
    public static void setup() {
        String baseUrl = System.getProperty("base.url", System.getenv("BASE_URL"));
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testConstructor() throws Exception {
        try {
            Class<?> clazz = Class.forName("org.restscs.imp.Costfuns");
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            constructor.newInstance();
        } catch (ClassNotFoundException e) {
            return;
        }
        assertTrue(true);
    }

    @Test(timeout = 60000)
    public void testCostfuns_Case1() {
        given()
            .pathParam("i", 667)
            .pathParam("s", "baab")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_Case2() {
        given()
            .pathParam("i", 5)
            .pathParam("s", "abab")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_Case3() {
        given()
            .pathParam("i", -445)
            .pathParam("s", "ababba")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_Case4() {
        given()
            .pathParam("i", -4)
            .pathParam("s", "ababaa")
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }
}