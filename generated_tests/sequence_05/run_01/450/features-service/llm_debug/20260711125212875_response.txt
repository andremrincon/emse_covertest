package ts01gpt_5_mini;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class ConstraintRequiresTest {

    @BeforeClass
    public static void init() {
        String env = System.getProperty("BASE_URL");
        if (env == null || env.isEmpty()) {
            env = System.getenv("BASE_URL");
        }
        if (env == null || env.isEmpty()) {
            env = "http://localhost:8080";
        }
        RestAssured.baseURI = env;
    }

    @Test(timeout = 60000)
    public void createRequiresConstraint_withBothSourceAndRequired_returns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "Source-" + UUID.randomUUID().toString();
        String requiredFeature = "Required-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", productName);
        assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void createRequiresConstraint_withOnlySourceFeature_returns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "OnlySource-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .when().post("/products/{productName}/constraints/requires", productName);
        assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void createRequiresConstraint_withOnlyRequiredFeature_returns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String requiredFeature = "OnlyRequired-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().contentType(ContentType.URLENC)
                .formParam("requiredFeature", requiredFeature)
                .when().post("/products/{productName}/constraints/requires", productName);
        assertEquals(201, act.getStatusCode());
    }

    @Test(timeout = 60000)
    public void createRequiresConstraint_withExcessivelyLongRequiredFeature_returns500() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String sourceFeature = "Source-" + UUID.randomUUID().toString();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2000; i++) sb.append('x');
        String veryLongRequired = sb.toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().contentType(ContentType.URLENC)
                .formParam("sourceFeature", sourceFeature)
                .formParam("requiredFeature", veryLongRequired)
                .when().post("/products/{productName}/constraints/requires", productName);
        assertEquals(500, act.getStatusCode());
    }
}