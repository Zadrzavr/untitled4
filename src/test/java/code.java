import extensions.SpoonApiTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


import java.util.Map;

import static io.restassured.RestAssured.given;


@SpoonApiTest
public class code {

    @Test
    public void code200() {
        given()
                .queryParams(Map.of(
                        "offset", 0))
                .post("/recipes/cuisine")
                .then()
                .statusCode(200);


    }
}
