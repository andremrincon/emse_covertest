package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.notNullValue;

public class CORSFilterTest {

    @BeforeClass
    public static void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080";
        }
    }

    @Test(timeout = 60000)
    public void testDoFilterWithGetRequest() {
        String productName = "AeroBook-Pro-15-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        Response response = given().when().get("/products/" + productName + "/features");

        response.then().statusCode(200);
        response.then().header("Access-Control-Allow-Origin", "*");
    }

    @Test(timeout = 60000)
    public void testDoFilterWithPostRequest() {
        String productName = "Smartwatch-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "Blood-Oxygen-Sensor-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        Response response = given()
                .formParam("description", "Measures the oxygen saturation of your blood on demand.")
                .when()
                .post("/products/" + productName + "/features/" + featureName);

        response.then().statusCode(201);
        response.then().header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    }

    @Test(timeout = 60000)
    public void testDoFilterWithPutRequest() {
        String productName = "AeroBook-Pro-15-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "backlit-keyboard-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().formParam("description", "Initial description")
                .when().post("/products/" + productName + "/features/" + featureName)
                .then().statusCode(lessThan(300));

        Response response = given()
                .formParam("description", "RGB backlit keyboard with customizable zones and per-key lighting.")
                .when()
                .put("/products/" + productName + "/features/" + featureName);

        response.then().statusCode(200);
        response.then().header("Access-Control-Allow-Headers", "x-requested-with");
    }

    @Test(timeout = 60000)
    public void testDoFilterWithDeleteRequest() {
        String productName = "SmartWatch-Pro-" + java.util.UUID.randomUUID().toString().substring(0, 8);
        String featureName = "heart-rate-monitor-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));
        given().formParam("description", "Heart rate monitor")
                .when().post("/products/" + productName + "/features/" + featureName)
                .then().statusCode(lessThan(300));

        Response response = given().when().delete("/products/" + productName + "/features/" + featureName);

        response.then().statusCode(204);
        response.then().header("Access-Control-Max-Age", "3600");
    }

    @Test(timeout = 60000)
    public void testDoFilterWithOptionsRequest() {
        String productName = "USB-C-Adapter-" + java.util.UUID.randomUUID().toString().substring(0, 8);

        given().when().post("/products/" + productName).then().statusCode(lessThan(300));

        Response response = given().when().options("/products/" + productName + "/features");

        response.then().header("Access-Control-Allow-Origin", "*");
        response.then().header("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
    }

    @Test(timeout = 60000)
    public void testDoFilterWithGetProductsList() {
        Response response = given().when().get("/products");

        response.then().statusCode(lessThan(300));
        response.then().header("Access-Control-Allow-Origin", notNullValue());
    }
}