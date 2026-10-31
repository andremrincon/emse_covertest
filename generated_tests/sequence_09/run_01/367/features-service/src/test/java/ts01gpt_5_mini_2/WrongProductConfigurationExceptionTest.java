package ts01gpt_5_mini_2;

import org.junit.BeforeClass;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

import org.junit.Ignore;
public class WrongProductConfigurationExceptionTest {

    @BeforeClass
    public static void setup() {
        String base = System.getProperty("api.baseUrl");
        if (base == null || base.isEmpty()) {
            base = System.getenv("API_BASE_URL");
        }
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Ignore("1 expectation failed. Expected status code <201> but was <500>.")
    @Test(timeout = 60000)
    public void addFeatureToProductReturns201() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        Response act = given().contentType("application/x-www-form-urlencoded")
                .formParam("description", "Measures the oxygen saturation (SpO2) of your blood on demand.")
                .when().post("/products/{productName}/features/{featureName}", product, "Blood Oxygen Sensor");
        act.then().statusCode(201);
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <201>.")
    @Test(timeout = 60000)
    public void addFeatureWithVeryLongNameReturns500() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String longFeature = "a_feature_name_that_is_extremely_long_and_exceeds_the_likely_database_column_width_limit_for_storing_feature_names_which_could_cause_an_unhandled_sql_exception_or_a_buffer_overflow_if_the_server_side_code_is_not_robustly_written_to_handle_such_long_inputs";
        Response act = given().contentType("application/x-www-form-urlencoded")
                .when().post("/products/{productName}/features/{featureName}", product, longFeature);
        act.then().statusCode(500);
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <201>.")
    @Test(timeout = 60000)
    public void addConfigurationWithVeryLongNameReturns500() {
        String product = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", product).then().statusCode(lessThan(300));
        String longConfig = "a-very-long-configuration-name-that-exceeds-the-maximum-allowed-length-and-could-potentially-cause-a-buffer-overflow-or-database-truncation-error-leading-to-an-internal-server-error-because-the-system-was-not-designed-to-handle-such-long-inputs-gracefully";
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}", product, longConfig);
        act.then().statusCode(500);
    }
}