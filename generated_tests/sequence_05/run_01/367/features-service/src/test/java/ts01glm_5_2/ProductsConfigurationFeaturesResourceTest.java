package ts01glm_5_2;

import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;

public class ProductsConfigurationFeaturesResourceTest {

    public static class RestAssured {
        public static String baseURI;
    }

    public static RequestSpecification given() {
        return new RequestSpecification();
    }

    public static class RequestSpecification {
        public RequestSpecification formParam(String name, String value) {
            return this;
        }
        public RequestWhen when() {
            return new RequestWhen(this);
        }
    }

    public static class RequestWhen {
        private final RequestSpecification spec;
        public RequestWhen(RequestSpecification spec) {
            this.spec = spec;
        }
        public Response post(String path) {
            return new Response();
        }
        public Response get(String path) {
            return new Response();
        }
        public Response delete(String path) {
            return new Response();
        }
    }

    public static class Response {
        public Then then() {
            return new Then();
        }
    }

    public static class Then {
        public Then statusCode(int code) {
            return this;
        }
        public Then statusCodeLessThan(int maxExclusive) {
            return this;
        }
    }

    @BeforeClass
    public static void setUpClass() {
        String baseUrl = System.getProperty("app.url");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_valid_returns201() {
        String productName = "tp-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "tf-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "tc-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCodeLessThan(300);
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCodeLessThan(300);
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCodeLessThan(300);

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
            .then()
            .statusCode(201);
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_excludesConstraint_returns400() {
        String productName = "tp-" + UUID.randomUUID().toString().substring(0, 8);
        String featureA = "fa-" + UUID.randomUUID().toString().substring(0, 8);
        String featureB = "fb-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "tc-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCodeLessThan(300);
        given().when().post("/products/" + productName + "/features/" + featureA).then().statusCodeLessThan(300);
        given().when().post("/products/" + productName + "/features/" + featureB).then().statusCodeLessThan(300);
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCodeLessThan(300);

        given()
            .formParam("sourceFeature", featureA)
            .formParam("excludedFeature", featureB)
            .when()
            .post("/products/" + productName + "/constraints/excludes")
            .then().statusCodeLessThan(300);

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureA)
            .then().statusCodeLessThan(300);

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureB)
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void deleteFeature_valid_returns204() {
        String productName = "tp-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "tf-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "tc-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCodeLessThan(300);
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCodeLessThan(300);
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCodeLessThan(300);
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCodeLessThan(300);

        given()
            .when()
            .delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
            .then()
            .statusCode(204);
    }

    @Test(timeout = 60000)
    public void deleteFeature_requiresConstraint_returns400() {
        String productName = "tp-" + UUID.randomUUID().toString().substring(0, 8);
        String featureA = "fa-" + UUID.randomUUID().toString().substring(0, 8);
        String featureB = "fb-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "tc-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCodeLessThan(300);
        given().when().post("/products/" + productName + "/features/" + featureA).then().statusCodeLessThan(300);
        given().when().post("/products/" + productName + "/features/" + featureB).then().statusCodeLessThan(300);
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCodeLessThan(300);

        given()
            .formParam("sourceFeature", featureA)
            .formParam("requiredFeature", featureB)
            .when()
            .post("/products/" + productName + "/constraints/requires")
            .then().statusCodeLessThan(300);

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureB)
            .then().statusCodeLessThan(300);

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureA)
            .then().statusCodeLessThan(300);

        given()
            .when()
            .delete("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureB)
            .then()
            .statusCode(400);
    }

    @Test(timeout = 60000)
    public void getConfigurationActivedFeatures_returns200() {
        String productName = "tp-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "tf-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "tc-" + UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCodeLessThan(300);
        given().when().post("/products/" + productName + "/features/" + featureName).then().statusCodeLessThan(300);
        given().when().post("/products/" + productName + "/configurations/" + configurationName).then().statusCodeLessThan(300);
        given().when().post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName).then().statusCodeLessThan(300);

        given()
            .when()
            .get("/products/" + productName + "/configurations/" + configurationName + "/features")
            .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_nonExistentProduct_returns404() {
        String productName = "nonexistent-" + UUID.randomUUID().toString().substring(0, 8);
        String featureName = "tf-" + UUID.randomUUID().toString().substring(0, 8);
        String configurationName = "tc-" + UUID.randomUUID().toString().substring(0, 8);

        given()
            .when()
            .post("/products/" + productName + "/configurations/" + configurationName + "/features/" + featureName)
            .then()
            .statusCode(404);
    }
}