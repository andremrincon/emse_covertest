package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class ConstraintExcludesTest {

    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = System.getProperty("baseUrl", "http://localhost:8080");
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_withBothFields_shouldReturn201() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC)
                .formParam("sourceFeature", "CPU-i9-13900H")
                .formParam("excludedFeature", "Integrated-Graphics-Only")
                .when()
                .post("/products/{productName}/constraints/excludes", product)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_withOnlySourceFeature_shouldReturn201() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC)
                .formParam("sourceFeature", "RAID-Controller-Card")
                .when()
                .post("/products/{productName}/constraints/excludes", product)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_withOnlyExcludedFeature_shouldReturn201() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC)
                .formParam("excludedFeature", "Integrated-Graphics-Only")
                .when()
                .post("/products/{productName}/constraints/excludes", product)
                .then()
                .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testCreateExcludesConstraint_withVeryLongExcludedFeature_shouldReturn500() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String longFeature = new String(new char[2000]).replace('\0', 'X');
        given().contentType(ContentType.URLENC)
                .formParam("sourceFeature", "SomeFeature")
                .formParam("excludedFeature", longFeature)
                .when()
                .post("/products/{productName}/constraints/excludes", product)
                .then()
                .statusCode(500);
    }
}