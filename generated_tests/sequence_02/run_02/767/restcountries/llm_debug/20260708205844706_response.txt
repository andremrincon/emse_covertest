package ts01gpt_5_mini;

import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class ResponseEntityTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080/rest";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void test_v1_name_notFound_message() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/123").then().body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void test_v1_name_notFound_statusCode() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/123").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void test_v1_name_serverError_message() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/True").then().body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void test_v1_name_serverError_statusCode() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/name/True").then().statusCode(404);
    }

    @Test(timeout = 60000)
    public void test_v1_region_notFound_message() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/region/123").then().body("message", equalTo("Not Found"));
    }

    @Test(timeout = 60000)
    public void test_v1_region_notFound_statusCode() {
        given().when().get("/v1/all").then().statusCode(lessThan(300));
        given().when().get("/v1/region/123").then().statusCode(404);
    }
}