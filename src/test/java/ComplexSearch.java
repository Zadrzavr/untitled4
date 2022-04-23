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
public class ComplexSearch {
    private static RequestSpecification requestSpecification;
    private static ResponseSpecification responseSpecification;

    @BeforeAll
    static void beforeAll() {
        requestSpecification = new RequestSpecBuilder()
                .addQueryParam("offset", 0)
                .addQueryParam("number", 10)
                .build();
        responseSpecification = new ResponseSpecBuilder()
                .expectBody("offset", Matchers.equalTo(0))
                .build();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Cauliflower", "Garlicky Kale"})
    public void complexSearchTest(String queryParameter) {
        given()
                .queryParams("query", queryParameter)
                .spec(requestSpecification)
                .get("/recipes/complexSearch")
                .then()
                .spec(responseSpecification)
                .statusCode(200);

    }
}