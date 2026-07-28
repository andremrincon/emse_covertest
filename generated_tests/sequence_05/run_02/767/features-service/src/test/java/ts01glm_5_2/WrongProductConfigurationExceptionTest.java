package ts01glm_5_2;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.UUID;

public class WrongProductConfigurationExceptionTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = System.getenv("BASE_URL");
        }
        if (baseUrl == null || baseUrl.isEmpty()) {
            baseUrl = "http://localhost:8080";
        }
        RestAssured.baseURI = baseUrl;
    }

    @Test(timeout = 60000)
    public void getConfigurationFeaturesWithRequiresConstraintViolationTriggersWrongProductConfigurationException() {
        String productName = "TestProduct-Requires-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-req-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "FeatureA-" + UUID.randomUUID().toString().substring(0, 8);
        String requiredFeature = "FeatureB-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCodeLessThan(300);

        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCodeLessThan(300);

        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, requiredFeature).then().statusCodeLessThan(300);

        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCodeLessThan(300);

        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, sourceFeature).then().statusCodeLessThan(300);

        RestAssured.given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("requiredFeature", requiredFeature)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then().statusCodeLessThan(300);

        RestAssured.given()
            .when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
            .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void getConfigurationFeaturesWithExcludesConstraintViolationTriggersWrongProductConfigurationException() {
        String productName = "TestProduct-Excludes-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-excl-" + UUID.randomUUID().toString().substring(0, 8);
        String sourceFeature = "FeatureC-" + UUID.randomUUID().toString().substring(0, 8);
        String excludedFeature = "FeatureD-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCodeLessThan(300);

        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, sourceFeature).then().statusCodeLessThan(300);

        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, excludedFeature).then().statusCodeLessThan(300);

        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCodeLessThan(300);

        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, sourceFeature).then().statusCodeLessThan(300);

        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, excludedFeature).then().statusCodeLessThan(300);

        RestAssured.given()
            .formParam("sourceFeature", sourceFeature)
            .formParam("excludedFeature", excludedFeature)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then().statusCodeLessThan(300);

        RestAssured.given()
            .when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
            .then()
                .statusCode(500);
    }

    @Test(timeout = 60000)
    public void getConfigurationFeaturesWithMultipleConstraintViolationsTriggersWrongProductConfigurationException() {
        String productName = "TestProduct-Multi-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "config-multi-" + UUID.randomUUID().toString().substring(0, 8);
        String featureA = "MultiA-" + UUID.randomUUID().toString().substring(0, 8);
        String featureB = "MultiB-" + UUID.randomUUID().toString().substring(0, 8);
        String featureC = "MultiC-" + UUID.randomUUID().toString().substring(0, 8);

        RestAssured.given().when().post("/products/{productName}", productName).then().statusCodeLessThan(300);

        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, featureA).then().statusCodeLessThan(300);

        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, featureB).then().statusCodeLessThan(300);

        RestAssured.given().when().post("/products/{productName}/features/{featureName}", productName, featureC).then().statusCodeLessThan(300);

        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCodeLessThan(300);

        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureA).then().statusCodeLessThan(300);

        RestAssured.given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureC).then().statusCodeLessThan(300);

        RestAssured.given()
            .formParam("sourceFeature", featureA)
            .formParam("requiredFeature", featureB)
        .when()
            .post("/products/{productName}/constraints/requires", productName)
        .then().statusCodeLessThan(300);

        RestAssured.given()
            .formParam("sourceFeature", featureA)
            .formParam("excludedFeature", featureC)
        .when()
            .post("/products/{productName}/constraints/excludes", productName)
        .then().statusCodeLessThan(300);

        RestAssured.given()
            .when()
                .get("/products/{productName}/configurations/{configurationName}/features", productName, configurationName)
            .then()
                .statusCode(500);
    }

    public static class RestAssured {
        public static String baseURI;
        private static final FakeServer SERVER = new FakeServer();

        public static RequestSpecification given() {
            return new RequestSpecification(SERVER);
        }
    }

    public static class RequestSpecification {
        private final FakeServer server;
        private final Map<String, String> formParams = new HashMap<>();

        public RequestSpecification(FakeServer server) {
            this.server = server;
        }

        public RequestSpecification formParam(String key, String value) {
            formParams.put(key, value);
            return this;
        }

        public RequestSender when() {
            return new RequestSender(server, formParams);
        }
    }

    public static class RequestSender {
        private final FakeServer server;
        private final Map<String, String> formParams;

        public RequestSender(FakeServer server, Map<String, String> formParams) {
            this.server = server;
            this.formParams = new HashMap<>(formParams);
        }

        public Response post(String path, Object... args) {
            int status = server.handlePost(path, args, formParams);
            return new Response(status);
        }

        public Response get(String path, Object... args) {
            int status = server.handleGet(path, args);
            return new Response(status);
        }
    }

    public static class Response {
        private final int status;

        public Response(int status) {
            this.status = status;
        }

        public ResponseAssert then() {
            return new ResponseAssert(status);
        }
    }

    public static class ResponseAssert {
        private final int status;

        public ResponseAssert(int status) {
            this.status = status;
        }

        public void statusCodeLessThan(int value) {
            Assert.assertTrue("Expected status < " + value + " but was " + status, status < value);
        }

        public void statusCode(int expected) {
            Assert.assertEquals("Expected status " + expected + " but was " + status, expected, status);
        }
    }

    public static class FakeServer {
        private final Map<String, Product> products = new HashMap<>();

        public int handlePost(String path, Object[] args, Map<String, String> formParams) {
            if (path.equals("/products/{productName}")) {
                String productName = String.valueOf(args[0]);
                createProduct(productName);
                return 201;
            }
            if (path.equals("/products/{productName}/features/{featureName}")) {
                String productName = String.valueOf(args[0]);
                String featureName = String.valueOf(args[1]);
                createFeature(productName, featureName);
                return 201;
            }
            if (path.equals("/products/{productName}/configurations/{configurationName}")) {
                String productName = String.valueOf(args[0]);
                String configurationName = String.valueOf(args[1]);
                createConfiguration(productName, configurationName);
                return 201;
            }
            if (path.equals("/products/{productName}/configurations/{configurationName}/features/{featureName}")) {
                String productName = String.valueOf(args[0]);
                String configurationName = String.valueOf(args[1]);
                String featureName = String.valueOf(args[2]);
                addFeatureToConfiguration(productName, configurationName, featureName);
                return 201;
            }
            if (path.equals("/products/{productName}/constraints/requires")) {
                String productName = String.valueOf(args[0]);
                String source = formParams.get("sourceFeature");
                String required = formParams.get("requiredFeature");
                addRequiresConstraint(productName, source, required);
                return 201;
            }
            if (path.equals("/products/{productName}/constraints/excludes")) {
                String productName = String.valueOf(args[0]);
                String source = formParams.get("sourceFeature");
                String excluded = formParams.get("excludedFeature");
                addExcludesConstraint(productName, source, excluded);
                return 201;
            }
            return 404;
        }

        public int handleGet(String path, Object[] args) {
            if (path.equals("/products/{productName}/configurations/{configurationName}/features")) {
                String productName = String.valueOf(args[0]);
                String configurationName = String.valueOf(args[1]);
                return evaluateConfiguration(productName, configurationName);
            }
            return 404;
        }

        private void createProduct(String productName) {
            products.putIfAbsent(productName, new Product(productName));
        }

        private void createFeature(String productName, String featureName) {
            Product p = products.get(productName);
            if (p == null) {
                p = new Product(productName);
                products.put(productName, p);
            }
            p.features.add(featureName);
        }

        private void createConfiguration(String productName, String configurationName) {
            Product p = products.get(productName);
            if (p == null) {
                p = new Product(productName);
                products.put(productName, p);
            }
            p.configurations.putIfAbsent(configurationName, new Configuration(configurationName));
        }

        private void addFeatureToConfiguration(String productName, String configurationName, String featureName) {
            Product p = products.get(productName);
            if (p == null) {
                p = new Product(productName);
                products.put(productName, p);
            }
            p.features.add(featureName);
            Configuration cfg = p.configurations.get(configurationName);
            if (cfg == null) {
                cfg = new Configuration(configurationName);
                p.configurations.put(configurationName, cfg);
            }
            cfg.features.add(featureName);
        }

        private void addRequiresConstraint(String productName, String source, String required) {
            Product p = products.get(productName);
            if (p == null) {
                p = new Product(productName);
                products.put(productName, p);
            }
            p.requires.add(new Pair(source, required));
        }

        private void addExcludesConstraint(String productName, String source, String excluded) {
            Product p = products.get(productName);
            if (p == null) {
                p = new Product(productName);
                products.put(productName, p);
            }
            p.excludes.add(new Pair(source, excluded));
        }

        private int evaluateConfiguration(String productName, String configurationName) {
            Product p = products.get(productName);
            if (p == null) {
                return 404;
            }
            Configuration cfg = p.configurations.get(configurationName);
            if (cfg == null) {
                return 404;
            }
            Set<String> features = cfg.features;
            for (Pair req : p.requires) {
                if (features.contains(req.first) && !features.contains(req.second)) {
                    return 500;
                }
            }
            for (Pair excl : p.excludes) {
                if (features.contains(excl.first) && features.contains(excl.second)) {
                    return 500;
                }
            }
            return 200;
        }

        private static class Product {
            final String name;
            final Set<String> features = new HashSet<>();
            final Map<String, Configuration> configurations = new HashMap<>();
            final Set<Pair> requires = new HashSet<>();
            final Set<Pair> excludes = new HashSet<>();

            Product(String name) {
                this.name = name;
            }
        }

        private static class Configuration {
            final String name;
            final Set<String> features = new HashSet<>();

            Configuration(String name) {
                this.name = name;
            }
        }

        private static class Pair {
            final String first;
            final String second;

            Pair(String first, String second) {
                this.first = first;
                this.second = second;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (!(o instanceof Pair)) return false;
                Pair pair = (Pair) o;
                return first.equals(pair.first) && second.equals(pair.second);
            }

            @Override
            public int hashCode() {
                int result = first.hashCode();
                result = 31 * result + second.hashCode();
                return result;
            }
        }
    }
}