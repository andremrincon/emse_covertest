package ts01qwen3_7_plus;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;

public class Text2TxtTest {

    @Test(timeout = 60000)
    public void testText2TxtAnd() {

        given().when().get("http://localhost:8080/api/text2txt/hello/world/test").then().statusCode(lessThan(300));


        Response response = given().when().get("http://localhost:8080/api/text2txt/and/a/b");


        response.then().body(equalTo("n"));
    }

    @Test(timeout = 60000)
    public void testText2TxtAre() {

        given().when().get("http://localhost:8080/api/text2txt/hello/world/test").then().statusCode(lessThan(300));


        Response response = given().when().get("http://localhost:8080/api/text2txt/are/a/b");


        response.then().body(equalTo("r"));
    }

    @Test(timeout = 60000)
    public void testText2TxtSeeYou() {

        given().when().get("http://localhost:8080/api/text2txt/hello/world/test").then().statusCode(lessThan(300));


        Response response = given().when().get("http://localhost:8080/api/text2txt/see/you/b");


        response.then().body(equalTo("cu"));
    }

    @Test(timeout = 60000)
    public void testText2TxtByTheWay() {

        given().when().get("http://localhost:8080/api/text2txt/hello/world/test").then().statusCode(lessThan(300));


        Response response = given().when().get("http://localhost:8080/api/text2txt/by/the/way");


        response.then().body(equalTo("btw"));
    }

    @Test(timeout = 60000)
    public void testText2TxtTwo() {

        given().when().get("http://localhost:8080/api/text2txt/hello/world/test").then().statusCode(lessThan(300));


        Response response = given().when().get("http://localhost:8080/api/text2txt/two/a/b");


        response.then().body(equalTo("2"));
    }

    @Test(timeout = 60000)
    public void testText2TxtFour() {

        given().when().get("http://localhost:8080/api/text2txt/hello/world/test").then().statusCode(lessThan(300));


        Response response = given().when().get("http://localhost:8080/api/text2txt/four/a/b");


        response.then().body(equalTo("4"));
    }
}