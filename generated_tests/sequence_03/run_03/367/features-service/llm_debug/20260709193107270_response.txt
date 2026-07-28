package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertNotNull;

public class ProductsDAOTest {

    @BeforeClass
    public static void setUp() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
        RestAssured.basePath = "/";
    }

    @Test(timeout = 60000)
    public void testInsertRequiresConstraintReturns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().contentType(ContentType.URLENC)
                .formParam("sourceFeature", "RAID-Controller-Card")
                .formParam("requiredFeature", "128GB-ECC-RAM")
                .when()
                .post("/products/{productName}/constraints/requires", productName);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testInsertExcludesConstraintReturns201() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response act = given().contentType(ContentType.URLENC)
                .formParam("sourceFeature", "CPU-i9-13900H")
                .formParam("excludedFeature", "Integrated-Graphics-Only")
                .when()
                .post("/products/{productName}/constraints/excludes", productName);
        act.then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteProductTriggersDeleteConstraintsForProductReturns204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().contentType(ContentType.URLENC)
                .formParam("sourceFeature", "RAID-Controller-Card")
                .formParam("requiredFeature", "128GB-ECC-RAM")
                .when()
                .post("/products/{productName}/constraints/requires", productName)
                .then().statusCode(lessThan(300));
        Response act = given().when().delete("/products/{productName}", productName);
        act.then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void testDeleteSpecificConstraintReturns204() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        Response created = given().contentType(ContentType.URLENC)
                .formParam("sourceFeature", "CPU-i9-13900H")
                .formParam("excludedFeature", "Integrated-Graphics-Only")
                .when()
                .post("/products/{productName}/constraints/excludes", productName);
        created.then().statusCode(lessThan(300));
        Long id = null;
        String loc = created.getHeader("Location");
        if (loc != null && !loc.isEmpty()) {
            int idx = loc.lastIndexOf('/');
            if (idx >= 0 && idx + 1 < loc.length()) {
                String tail = loc.substring(idx + 1);
                try {
                    id = Long.parseLong(tail);
                } catch (NumberFormatException e) {
                }
            }
        }
        if (id == null) {
            String ct = created.getContentType();
            if (ct != null && ct.toLowerCase().contains("json")) {
                id = created.jsonPath().getLong("id");
            }
        }
        if (id == null) {
            Response list = given().when().get("/products/{productName}/constraints/excludes", productName);
            list.then().statusCode(lessThan(300));
            List<Long> ids = list.jsonPath().getList("id", Long.class);
            if (ids != null && !ids.isEmpty()) {
                id = ids.get(0);
            }
        }
        assertNotNull(id);
        Response act = given().when().delete("/products/{productName}/constraints/{constraintId}", productName, id);
        act.then().statusCode(204);
    }
}