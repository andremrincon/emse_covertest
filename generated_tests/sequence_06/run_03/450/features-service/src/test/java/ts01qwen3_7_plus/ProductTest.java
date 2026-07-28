package ts01qwen3_7_plus;

import org.junit.Before;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.baseURI;
import static org.hamcrest.Matchers.lessThan;

public class ProductTest {

    @Before
    public void setup() {
        baseURI = System.getProperty("test.base.url", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct() {
        String productName = "Prod-AddFeat-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/Feat1").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testRemoveFeatureFromProduct() {
        String productName = "Prod-RemFeat-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/Feat1").then().statusCode(lessThan(300));
        given().when().delete("/products/" + productName + "/features/Feat1").then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraintToProduct() {
        String productName = "Prod-ReqConst-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/FeatA").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/FeatB").then().statusCode(lessThan(300));
        given().formParam("sourceFeature", "FeatA").formParam("requiredFeature", "FeatB").when().post("/products/" + productName + "/constraints/requires").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraintToProduct() {
        String productName = "Prod-ExclConst-" + UUID.randomUUID().toString();
        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/FeatC").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/FeatD").then().statusCode(lessThan(300));
        given().formParam("sourceFeature", "FeatC").formParam("excludedFeature", "FeatD").when().post("/products/" + productName + "/constraints/excludes").then().statusCode(201);
    }
}