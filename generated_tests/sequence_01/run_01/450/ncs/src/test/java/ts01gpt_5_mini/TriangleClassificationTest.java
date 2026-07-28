package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class TriangleClassificationTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testNonPositiveSideTriggersClassificationPathForInvalidTriangle() {
        String arrangeId = UUID.randomUUID().toString();
        given().when().get("/api/remainder/1/1?uid=" + arrangeId).then().statusCode(lessThan(300));
        given().when().get(String.format("/api/triangle/%d/%d/%d", 0, 1, 1)).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testEquilateralTriangleTriggersEquilateralPath() {
        String arrangeId = UUID.randomUUID().toString();
        given().when().get("/api/remainder/2/2?uid=" + arrangeId).then().statusCode(lessThan(300));
        given().when().get(String.format("/api/triangle/%d/%d/%d", 2, 2, 2)).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testTriangleInequalityViolationTriggersInvalidPath() {
        String arrangeId = UUID.randomUUID().toString();
        given().when().get("/api/remainder/3/2?uid=" + arrangeId).then().statusCode(lessThan(300));
        given().when().get(String.format("/api/triangle/%d/%d/%d", 5, 2, 2)).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testIsoscelesTriangleTriggersIsoscelesPath() {
        String arrangeId = UUID.randomUUID().toString();
        given().when().get("/api/remainder/4/3?uid=" + arrangeId).then().statusCode(lessThan(300));
        given().when().get(String.format("/api/triangle/%d/%d/%d", 2, 2, 3)).then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testScaleneTriangleTriggersScalenePath() {
        String arrangeId = UUID.randomUUID().toString();
        given().when().get("/api/remainder/5/4?uid=" + arrangeId).then().statusCode(lessThan(300));
        given().when().get(String.format("/api/triangle/%d/%d/%d", 3, 4, 5)).then().statusCode(200);
    }
}