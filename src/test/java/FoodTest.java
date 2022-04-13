import extensions.SpoonApiTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import java.util.Map;

import static io.restassured.RestAssured.given;

@SpoonApiTest
public class FoodTest {
    private static RequestSpecification requestSpecification;
    private static ResponseSpecification responseSpecification;

    @BeforeAll
    static void beforeAll() {
        requestSpecification = new RequestSpecBuilder()
                .addQueryParam("offset", 0)
                .addQueryParam("number", 10)
                .build();
        responseSpecification = new ResponseSpecBuilder()
                .expectBody("limit", Matchers.equalTo(10))
                .expectBody("offset", Matchers.equalTo(0))
                .build();
    }

    @ParameterizedTest
    @ValueSource(strings = {"pizza", "Sushi"})
    public void foodSearchTest(String queryParameter) {
        given()
                .queryParams("query", queryParameter)
                .spec(requestSpecification)
                .get("/food/search")
                .then()
                .spec(responseSpecification)
                .statusCode(200)
                .body("query", Matchers.equalTo(queryParameter));

    }
}