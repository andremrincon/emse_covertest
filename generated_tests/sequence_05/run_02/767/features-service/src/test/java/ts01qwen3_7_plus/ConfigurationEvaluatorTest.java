package ts01qwen3_7_plus;

import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConfigurationEvaluatorTest {

    @Test(timeout = 60000)
    public void testRequiresConstraintDerivation() {
        String productName = "ProdReq-" + UUID.randomUUID().toString();
        String confName = "ConfReq";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/FeatA").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/FeatB").then().statusCode(lessThan(300));
        given().param("sourceFeature", "FeatA").param("requiredFeature", "FeatB").when().post("/products/" + productName + "/constraints/requires").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + confName).then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + confName + "/features/FeatA").then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testExcludesConstraintValid() {
        String productName = "ProdExc-" + UUID.randomUUID().toString();
        String confName = "ConfExc";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/FeatC").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/FeatD").then().statusCode(lessThan(300));
        given().param("sourceFeature", "FeatC").param("excludedFeature", "FeatD").when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + confName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + confName + "/features/FeatC").then().statusCode(lessThan(300));

        given().when().get("/products/" + productName + "/configurations/" + confName + "/features").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void testExcludesConstraintViolated() {
        String productName = "ProdExcV-" + UUID.randomUUID().toString();
        String confName = "ConfExcV";

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/FeatE").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/features/FeatF").then().statusCode(lessThan(300));
        given().param("sourceFeature", "FeatE").param("excludedFeature", "FeatF").when().post("/products/" + productName + "/constraints/excludes").then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + confName).then().statusCode(lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + confName + "/features/FeatE").then().statusCode(lessThan(300));

        given().when().post("/products/" + productName + "/configurations/" + confName + "/features/FeatF").then().statusCode(500);
    }
}