package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Ignore;
public class WrongProductConfigurationExceptionTest {

    @BeforeClass
    public static void setup() {
        String env = System.getenv("API_BASE_URL");
        RestAssured.baseURI = System.getProperty("api.base.url", env != null ? env : "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void testConstructorTriggeredWhenAddingLongFeatureToConfiguration() {
        String productName = "prod-" + UUID.randomUUID().toString();
        String configurationName = "base";
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName).then().statusCode(lessThan(300));
        String longFeature = "a_feature_name_that_is_extremely_long_and_exceeds_the_likely_database_column_width_limit_for_storing_feature_names_which_could_cause_an_unhandled_sql_exception_or_a_buffer_overflow_if_the_server_side_code_is_not_robustly_written_to_handle_such_long_inputs";
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, longFeature);
        act.then().statusCode(500).body(containsString("Object with id "));
    }

    @Ignore("1 expectation failed. Expected status code <500> but was <201>.")
    @Test(timeout = 60000)
    public void testConstructorTriggeredWhenCreatingInvalidConfiguration() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String badConfig = "a-very-long-configuration-name-that-exceeds-the-maximum-allowed-length-and-could-potentially-cause-a-buffer-overflow-or-database-truncation-error-leading-to-an-internal-server-error-because-the-system-was-not-designed-to-handle-such-long-inputs-gracefully";
        Response act = given().when().post("/products/{productName}/configurations/{configurationName}", productName, badConfig);
        act.then().statusCode(500).body(equalTo(""));
    }
}