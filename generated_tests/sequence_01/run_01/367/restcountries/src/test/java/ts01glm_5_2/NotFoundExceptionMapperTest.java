package ts01glm_5_2;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertEquals;

public class NotFoundExceptionMapperTest {

    @Before
    public void setUp() {
        String baseUrl = System.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            RestAssured.baseURI = baseUrl;
        } else {
            RestAssured.baseURI = "http://localhost:8080/rest";
        }
    }

    @Test(timeout = 60000)
    public void toResponse_returns404_whenNonExistentPathRequested() {
        String nonExistentPath = "/v1/nonexistent" + java.util.UUID.randomUUID().toString().substring(0, 8);

        Response response = given()
                .when()
                .get(nonExistentPath);

        assertEquals(404, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void toResponse_returns404_whenInvalidRootPathRequested() {
        String invalidPath = "/nonexistentroot" + java.util.UUID.randomUUID().toString().substring(0, 8);

        Response response = given()
                .when()
                .get(invalidPath);

        assertEquals(404, response.getStatusCode());
    }

    @Test(timeout = 60000)
    public void toResponse_returns404_whenDeepNonExistentPathRequested() {
        given()
                .when()
                .get("/v1/alpha/US")
                .then()
                .statusCode(lessThan(300));

        String deepNonExistentPath = "/v1/alpha/US/nonexistent" + java.util.UUID.randomUUID().toString().substring(0, 8);

        Response response = given()
                .when()
                .get(deepNonExistentPath);

        assertEquals(404, response.getStatusCode());
    }
}