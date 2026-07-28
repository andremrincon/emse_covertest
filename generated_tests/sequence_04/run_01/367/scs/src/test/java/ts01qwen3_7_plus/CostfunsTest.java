package ts01qwen3_7_plus;

import org.junit.Test;
import static io.restassured.RestAssured.given;

public class CostfunsTest {

    @Test(timeout = 60000)
    public void testCostfuns_Branches_F_F_F_F_F_T_F_F_F_T() {
        int i = 0;
        String s = "a";

        given()
            .pathParam("i", i)
            .pathParam("s", s)
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_Branches_i0_T_i11_F() {
        int i = 5;
        String s = "abab";

        given()
            .pathParam("i", i)
            .pathParam("s", s)
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_Branches_i1_i2_T_T() {
        int i = -500;
        String s = "test";

        given()
            .pathParam("i", i)
            .pathParam("s", s)
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_Branches_i3_i4_T_T() {
        int i = 700;
        String s = "test";

        given()
            .pathParam("i", i)
            .pathParam("s", s)
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_Branches_i2_i6_T_T() {
        int i = -333;
        String s = "baab";

        given()
            .pathParam("i", i)
            .pathParam("s", s)
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }

    @Test(timeout = 60000)
    public void testCostfuns_Branches_i5_F_i9_i10_T_T() {
        int i = -4;
        String s = "b";

        given()
            .pathParam("i", i)
            .pathParam("s", s)
        .when()
            .get("/api/costfuns/{i}/{s}")
        .then()
            .statusCode(200);
    }
}