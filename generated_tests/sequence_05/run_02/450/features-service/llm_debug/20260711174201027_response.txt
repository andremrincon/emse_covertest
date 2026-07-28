package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintRequiresTest {

    @BeforeClass
    public static void init() {
        String base = System.getProperty("API_BASE_URL");
        if (base == null || base.isEmpty()) base = System.getenv("API_BASE_URL");
        if (base == null || base.isEmpty()) base = "http://localhost:8080";
        RestAssured.baseURI = base;
        RestAssured.basePath = "";
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintSetsBothNames() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", "RAID-Controller-Card").formParam("requiredFeature", "128GB-ECC-RAM").when().post("/products/{productName}/constraints/requires", productName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintWithOnlySourceFeatureSetsSourceName() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", "Push-Notifications").when().post("/products/{productName}/constraints/requires", productName).then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintWithOnlyRequiredFeatureSetsRequiredName() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC).formParam("requiredFeature", "User-Authentication").when().post("/products/{productName}/constraints/requires", productName).then().statusCode(201);
    }
}