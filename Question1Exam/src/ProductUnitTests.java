import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ProductUnitTests {
    private Product createSampleProduct() {
        String[] names = {"Cozy Nights Monarch", "Restonic Barbados", "Sealy Phuket"};
        int[][] sales = {
                {25, 10, 20}, // = 55
                {15, 10, 12}, // = 37
                {18, 15, 20}  // = 53
        };
        return new Product(names, sales);
    }

    // Unit Test 1
    @Test
    public void GetTotalProductSales_WithValidData_ReturnsCorrectValue() {
        Product product = createSampleProduct();
        assertEquals(145, product.GetTotalProductsSold());
    }

    // Unit Test 2
    @Test
    public void GetAverageSales_WithValidData_ReturnsCorrectValue() {
        Product product = createSampleProduct();
        assertEquals(145.0 / 9.0, product.GetAverageProductsSold(), 0.001);
    }
}
