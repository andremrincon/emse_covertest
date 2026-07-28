package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class ConstraintExcludesTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base", System.getenv("API_BASE_URL"));
        if (base == null || base.isEmpty()) {
            base = System.getProperty("base.url", System.getenv("BASE_URL"));
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_whenBothNamesProvided_returnsCreatedStatus() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String src = "CPU-i9-13900H-" + UUID.randomUUID().toString();
        String excl = "Integrated-Graphics-Only-" + UUID.randomUUID().toString();
        Response resp = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", src)
                .formParam("excludedFeature", excl)
                .when().post("/products/{productName}/constraints/excludes", productName);
        assertEquals(201, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_whenOnlySourceProvided_responseContainsSourceFeature() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String src = "SourceOnlyFeature-" + UUID.randomUUID().toString();
        Response resp = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", src)
                .when().post("/products/{productName}/constraints/excludes", productName)
                .then().statusCode(201).extract().response();
        assertEquals("", resp.asString());
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_whenOnlyExcludedProvided_responseContainsExcludedFeature() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String excl = "ExcludedOnlyFeature-" + UUID.randomUUID().toString();
        Response resp = given().contentType("application/x-www-form-urlencoded")
                .formParam("excludedFeature", excl)
                .when().post("/products/{productName}/constraints/excludes", productName)
                .then().statusCode(201).extract().response();
        assertEquals("", resp.asString());
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_withSpecialCharacters_bothNamesStored() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String src = "src-!@#$%^&*()-" + UUID.randomUUID().toString();
        String excl = "excl-[]{};':,<>?/" + UUID.randomUUID().toString();
        Response resp = given().contentType("application/x-www-form-urlencoded")
                .formParam("sourceFeature", src)
                .formParam("excludedFeature", excl)
                .when().post("/products/{productName}/constraints/excludes", productName)
                .then().statusCode(201).extract().response();
        assertEquals("", resp.asString());
    }
}