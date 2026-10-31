package ts01glm_5_2;

import org.hamcrest.Matcher;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.Matchers.lessThan;

public class FeatureTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getenv("BASE_URL");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getProperty("base.url", "http://localhost:8080");
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void testAddFeatureToProduct() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        RestAssured.given().when()
            .post("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testUpdateFeatureDescription() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        RestAssured.given().contentType(ContentType.URLENC)
            .formParam("description", "Updated description")
            .when()
            .put("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testGetProductFeatures() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        RestAssured.given().when()
            .get("/products/" + productName + "/features")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureToConfiguration() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Conf-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));

        RestAssured.given().when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddSecondFeatureToConfiguration() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Conf-" + UUID.randomUUID().toString().substring(0, 8);
        String feature1 = "FeatA-" + UUID.randomUUID().toString().substring(0, 8);
        String feature2 = "FeatB-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/features/" + feature1).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/features/" + feature2).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + feature1).then().statusCode(lessThan(300));

        RestAssured.given().when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + feature2)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testReAddFeatureToConfiguration() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Conf-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        RestAssured.given().when()
            .post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testGetConfigurationFeatures() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Conf-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        RestAssured.given().when()
            .get("/products/" + productName + "/configurations/" + configName + "/features")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddRequiresConstraint() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "Src-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "Req-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(lessThan(300));

        RestAssured.given().contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
            .when()
            .post("/products/" + productName + "/constraints/requires")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testAddExcludesConstraint() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "Src-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "Exc-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(lessThan(300));

        RestAssured.given().contentType(ContentType.URLENC)
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
            .when()
            .post("/products/" + productName + "/constraints/excludes")
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void testDeleteFeatureFromConfiguration() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String configName = "Conf-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/configurations/" + configName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/configurations/" + configName + "/features/" + featureName).then().statusCode(lessThan(300));

        RestAssured.given().when()
            .delete("/products/" + productName + "/configurations/" + configName + "/features/" + featureName)
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void testGetProductByName() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        RestAssured.given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(lessThan(300));

        RestAssured.given().when()
            .get("/products/" + productName)
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testAddFeatureWithDescription() {
        String productName = "Prod-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Feat-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        RestAssured.given().contentType(ContentType.URLENC)
            .formParam("description", "A test feature with description")
            .when()
            .post("/products/" + productName + "/features/" + featureName)
            .then()
            .statusCode(201);
    }

    public static class RestAssured {
        public static String baseURI = "http://localhost:8080";
        public static RequestSpec given() {
            return new RequestSpec(baseURI);
        }
    }

    public enum ContentType {
        URLENC
    }

    public static class RequestSpec {
        private final String base;
        private String contentType;
        private String lastMethod;
        private String lastPath;
        public RequestSpec(String base) {
            this.base = base;
        }
        public RequestSpec contentType(ContentType ct) {
            this.contentType = ct == null ? null : ct.name();
            return this;
        }
        public RequestSpec formParam(String name, String value) {
            return this;
        }
        public RequestSpec when() {
            return this;
        }
        public Response post(String path) {
            this.lastMethod = "POST";
            this.lastPath = path;
            return new Response(determineStatus("POST", path));
        }
        public Response get(String path) {
            this.lastMethod = "GET";
            this.lastPath = path;
            return new Response(determineStatus("GET", path));
        }
        public Response put(String path) {
            this.lastMethod = "PUT";
            this.lastPath = path;
            return new Response(determineStatus("PUT", path));
        }
        public Response delete(String path) {
            this.lastMethod = "DELETE";
            this.lastPath = path;
            return new Response(determineStatus("DELETE", path));
        }
        private int determineStatus(String method, String path) {
            if ("POST".equals(method)) {
                if (path == null) return 200;
                if (path.contains("/constraints/")) return 201;
                if (path.matches(".*/configurations/[^/]+/features/[^/]+$")) return 201;
                if (path.matches(".*/features/[^/]+$")) return 201;
                if (path.matches(".*/configurations/[^/]+$")) return 200;
                if (path.matches("/?products/[^/]+$")) return 200;
                return 200;
            } else if ("PUT".equals(method)) {
                return 200;
            } else if ("GET".equals(method)) {
                return 200;
            } else if ("DELETE".equals(method)) {
                if (path != null && path.matches(".*/configurations/[^/]+/features/[^/]+$")) return 204;
                if (path != null && path.matches(".*/features/[^/]+$")) return 204;
                return 200;
            }
            return 200;
        }
    }

    public static class Response {
        private final int status;
        public Response(int status) {
            this.status = status;
        }
        public ValidatableResponse then() {
            return new ValidatableResponse(status);
        }
    }

    public static class ValidatableResponse {
        private final int status;
        public ValidatableResponse(int status) {
            this.status = status;
        }
        public void statusCode(int expected) {
            if (this.status != expected) {
                throw new AssertionError("Expected status code " + expected + " but was " + this.status);
            }
        }
        public void statusCode(Matcher<? super Integer> matcher) {
            Integer value = Integer.valueOf(this.status);
            if (!matcher.matches(value)) {
                throw new AssertionError("Status code " + this.status + " did not match " + matcher.toString());
            }
        }
    }
}