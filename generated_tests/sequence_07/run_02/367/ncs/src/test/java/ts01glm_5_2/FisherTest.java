package ts01glm_5_2;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class FisherTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = System.getProperty("baseUrl", "http://localhost:8080");
    }

    @Test(timeout = 60000)
    public void fisherEvenMOddN_SwaggerExample() {
        given().when().get("/api/fisher/10/5/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void fisherOddMOddN_BothBranchesAndLoops() {
        given().when().get("/api/fisher/3/3/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void fisherOddMEvenN_ELSEBranchLines22_23() {
        given().when().get("/api/fisher/3/4/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void fisherEvenMEvenN_ELSE_ELSEBranch() {
        given().when().get("/api/fisher/4/4/0.75").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void fisherNegativeX_PClampedToZero() {
        given().when().get("/api/fisher/2/2/-0.5").then().statusCode(200);
    }

    @Test(timeout = 60000)
    public void fisherNegativeX_PClampedToOne() {
        given().when().get("/api/fisher/2/2/-2.0").then().statusCode(200);
    }
}