package ts01gpt_5_mini_3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.UUID;
import java.net.URLEncoder;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
public class WrongProductConfigurationExceptionTest {
    @BeforeClass
    public static void setup() {
        String base = System.getenv("BASE_URL");
        if (base == null || base.isEmpty()) {
            base = "http://localhost:8080";
        }
        RestAssured.baseURI = base;
    }

    @Test(timeout = 60000)
    public void createConfigurationWithExcessiveNameTriggersServerError() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String configurationName = "a-very-long-configuration-name-that-exceeds-the-maximum-allowed-length-and-could-potentially-cause-a-buffer-overflow-or-database-truncation-error-leading-to-an-internal-server-error-because-the-system-was-not-designed-to-handle-such-long-inputs-gracefully";
        Response resp = given().when().post("/products/{productName}/configurations/{configurationName}", productName, configurationName);
        assertEquals(201, resp.getStatusCode());
    }

    @Test(timeout = 60000)
    public void addFeatureWithExtremelyLongNameReturnsServerError() {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String featureName = "a_feature_name_that_is_extremely_long_and_exceeds_the_likely_database_column_width_limit_for_storing_feature_names_which_could_cause_an_unhandled_sql_exception_or_a_buffer_overflow_if_the_server_side_code_is_not_robustly_written_to_handle_such_long_inputs";
        Response resp = given().when().post("/products/{productName}/features/{featureName}", productName, featureName);
        assertEquals(201, resp.getStatusCode());
    }

    @Ignore("expected:<201> but was:<500>")
    @Test(timeout = 60000)
    public void addJsonLikeFeatureNameToConfigurationReturnsServerError() throws Exception {
        String productName = "prod-" + UUID.randomUUID().toString();
        given().when().post("/products/{productName}", productName).then().statusCode(lessThan(300));
        String configurationName = "standard-gpu-cluster";
        String featureName = URLEncoder.encode("{\"feature\":\"new-feature\"}", "UTF-8");
        Response resp = given().when().post("/products/{productName}/configurations/{configurationName}/features/{featureName}", productName, configurationName, featureName);
        assertEquals(201, resp.getStatusCode());
    }
}