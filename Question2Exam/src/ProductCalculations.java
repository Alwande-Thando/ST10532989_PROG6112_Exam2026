// Implements the calculation interface.
public class ProductCalculations implements IProductCalculations {
    private final double price;

    public ProductCalculations(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        this.price = price;
    }

    @Override
    public double GetVat() {
        return price * 0.15;
    }

    @Override
    public double GetCommission() {
        return price * 0.05;
    }

    @Override
    public double GetTotal() {
        return price + GetVat() + GetCommission();
    }
}
