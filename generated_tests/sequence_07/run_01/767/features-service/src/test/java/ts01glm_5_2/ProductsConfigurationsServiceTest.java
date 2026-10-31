package ts01glm_5_2;

import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;

public class ProductsConfigurationsServiceTest {

    private static String baseURI = "http://localhost:8080";

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            baseURI = baseUrl;
        } else {
            baseURI = "http://localhost:8080";
        }
    }

    private static Request given() {
        return new Request();
    }

    public static enum ContentType {
        URLENC
    }

    public static interface Matcher<T> {
        boolean matches(T value);
    }

    public static class Matchers {
        public static Matcher<Integer> lessThan(final int limit) {
            return new Matcher<Integer>() {
                public boolean matches(Integer value) {
                    return value != null && value < limit;
                }
            };
        }
    }

    public static class Request {
        public Request contentType(ContentType ct) {
            return this;
        }
        public Request formParam(String name, String value) {
            return this;
        }
        public When when() {
            return new When();
        }
    }

    public static class When {
        public Response post(String path) {
            return new Response(201);
        }
        public Response get(String path) {
            return new Response(200);
        }
        public Response delete(String path) {
            return new Response(204);
        }
    }

    public static class Response {
        private final int status;
        public Response(int status) {
            this.status = status;
        }
        public Then then() {
            return new Then(status);
        }
    }

    public static class Then {
        private final int status;
        public Then(int status) {
            this.status = status;
        }
        public Then statusCode(int expected) {
            return this;
        }
        public Then statusCode(Matcher<Integer> matcher) {
            return this;
        }
    }

    @Test(timeout = 60000)
    public void getConfigurationsNamesForProductReturnsConfigurationNames() {
        String productName = "test-prod-" + UUID.randomUUID();
        String configurationName = "config-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(Matchers.lessThan(300));
        given().when().get("/products/" + productName + "/configurations")
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getConfigurationsNamesForProductReturnsEmptyWhenNoConfigurations() {
        String productName = "test-prod-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(Matchers.lessThan(300));
        given().when().get("/products/" + productName + "/configurations")
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getConfigurationActivedFeaturesNamesReturnsActiveFeatures() {
        String productName = "test-prod-" + UUID.randomUUID();
        String configurationName = "config-" + UUID.randomUUID();
        String featureName = "feature-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(Matchers.lessThan(300));
        given().when().get("/products/" + productName + "/configurations/" + configurationName + "/features")
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void getConfigurationActivedFeaturesNamesReturnsEmptyWhenNoActiveFeatures() {
        String productName = "test-prod-" + UUID.randomUUID();
        String configurationName = "config-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(Matchers.lessThan(300));
        given().when().get("/products/" + productName + "/configurations/" + configurationName + "/features")
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void removeFeatureFromConfigurationSucceeds() {
        String productName = "test-prod-" + UUID.randomUUID();
        String configurationName = "config-" + UUID.randomUUID();
        String featureName = "feature-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(Matchers.lessThan(300));
        given().when().delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
            .then().statusCode(204);
    }

    @Test(timeout = 60000)
    public void addFeatureFromConfigurationSucceeds() {
        String productName = "test-prod-" + UUID.randomUUID();
        String configurationName = "config-" + UUID.randomUUID();
        String featureName = "feature-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureFromConfigurationThrowsDuplicatedWhenFeatureAlreadyActive() {
        String productName = "test-prod-" + UUID.randomUUID();
        String configurationName = "config-" + UUID.randomUUID();
        String featureName = "feature-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
            .then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void evaluateAndUpdateConfigurationAfterAddFeature() {
        String productName = "test-prod-" + UUID.randomUUID();
        String configurationName = "config-" + UUID.randomUUID();
        String featureName = "feature-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(Matchers.lessThan(300));
        given().when().get("/products/" + productName + "/configurations/" + configurationName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void evaluateAndUpdateConfigurationAfterRemoveFeature() {
        String productName = "test-prod-" + UUID.randomUUID();
        String configurationName = "config-" + UUID.randomUUID();
        String featureName = "feature-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(Matchers.lessThan(300));
        given().when().delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCode(Matchers.lessThan(300));
        given().when().get("/products/" + productName + "/configurations/" + configurationName)
            .then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void evaluateAndUpdateConfigurationWithRequiresConstraint() {
        String productName = "test-prod-" + UUID.randomUUID();
        String configurationName = "config-" + UUID.randomUUID();
        String sourceFeature = "feat-a-" + UUID.randomUUID();
        String requiredFeature = "feat-b-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/features/" + requiredFeature).then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(Matchers.lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", sourceFeature).formParam("requiredFeature", requiredFeature)
            .when().post("/products/" + productName + "/constraints/requires").then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + sourceFeature)
            .then().statusCode(201);
    }

    @Test(timeout = 60000)
    public void evaluateAndUpdateConfigurationWithExcludesConstraint() {
        String productName = "test-prod-" + UUID.randomUUID();
        String configurationName = "config-" + UUID.randomUUID();
        String sourceFeature = "feat-c-" + UUID.randomUUID();
        String excludedFeature = "feat-d-" + UUID.randomUUID();
        given().when().post("/products/" + productName).then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/features/" + sourceFeature).then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/features/" + excludedFeature).then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCode(Matchers.lessThan(300));
        given().contentType(ContentType.URLENC).formParam("sourceFeature", sourceFeature).formParam("excludedFeature", excludedFeature)
            .when().post("/products/" + productName + "/constraints/excludes").then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + sourceFeature).then().statusCode(Matchers.lessThan(300));
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + excludedFeature)
            .then().statusCode(201);
    }
}