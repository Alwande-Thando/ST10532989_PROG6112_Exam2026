import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ProductCalculationsTests {

    // Unit Test 1
    @Test
    public void GetVat_WithValidData_ReturnsCorrectValue() {
        ProductCalculations calculations = new ProductCalculations(3500);
        assertEquals(525.0, calculations.GetVat(), 0.001);
    }

    // Unit Test 2
    @Test
    public void GetTotal_WithValidData_ReturnsCorrectValue() {
        ProductCalculations calculations = new ProductCalculations(3500);
        assertEquals(4200.0, calculations.GetTotal(), 0.001);
    }
}
