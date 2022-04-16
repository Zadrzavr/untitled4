import com.github.javafaker.Faker;
import dto.ProductDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static util.RetrofitUtil.getCategoryService;
import static util.RetrofitUtil.getProductsService;

public class ProductTest {
    ProductDto productDto;
    int productId;

    @SneakyThrows
    @BeforeEach
    void setUp() {
        productDto = new ProductDto()
                .withCategoryTitle(Objects.requireNonNull(getCategoryService().getCategory(2).execute().body()).getTitle())
                .withTitle(new Faker().food().ingredient())
                .withPrice((int) (Math.random() * 10000));
    }

    @SneakyThrows
    @Test
    void createProductTest() {
        Response<ProductDto> productDtoResponse = getProductsService().createProducts(productDto)
                .execute();
        assertThat(productDtoResponse.isSuccessful()).isTrue();
        assertThat(productDtoResponse.body())
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(productDto);
        productId = Objects.requireNonNull(productDtoResponse.body()).getId();
    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
        assertThat(getProductsService().deleteProducts(productId).execute().isSuccessful())
                .isTrue();
    }

}
