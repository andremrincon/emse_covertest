package ts01gpt_5_mini;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class WrongProductConfigurationExceptionTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.base");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void addFeatureToConfiguration_withMalformedFeatureName_returns500() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configurationName = "conf-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        String problematicFeatureName = "%7B%22feature%22%3A%22new-feature%22%7D";
        Response response = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, problematicFeatureName);
        response.then().statusCode(500);
    }

    @Test(timeout = 60000)
    public void addFeatureToProduct_withExcessiveDescription_returnsBodyContainingWrongProductConfiguration() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String featureName = "a_feature_name_that_is_extremely_long_and_exceeds_the_likely_database_column_width_limit_for_storing_feature_names_which_could_cause_an_unhandled_sql_exception_or_a_buffer_overflow_if_the_server_side_code_is_not_robustly_written_to_handle_such_long_inputs";
        String longDescription = "Using special characters that might be improperly handled by the server's backend logic, causing an unhandled exception. \u0000 A feature name that is excessively long.";
        Response response = given().formParam("description", longDescription).when().post("/products/{productName}/features/{featureName}", productName, featureName);
        response.then().statusCode(201).body(equalTo(""));
    }
}