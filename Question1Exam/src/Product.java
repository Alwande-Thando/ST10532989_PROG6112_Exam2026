// The Product implements IProduct and uses both 1D and 2D arrays.
public class Product implements IProducts {
    private final String[] productNames; // one-dimensional array
    private final int[][] monthlySales;   // two-dimensional for product x month

    public Product(String[] productNames, int[][] monthlySales) {
        if (productNames == null || monthlySales == null ||
                productNames.length != monthlySales.length) {
            throw new IllegalArgumentException("Product names and sales rows must match.");
        }
        for (int[] row : monthlySales) {
            if (row == null) throw new IllegalArgumentException("Sales row cannot be null.");
            for (int sale : row) {
                if (sale < 0) throw new IllegalArgumentException("Sales cannot be negative.");
            }
        }
        this.productNames = productNames.clone();
        this.monthlySales = new int[monthlySales.length][];
        for (int i = 0; i < monthlySales.length; i++) {

            this.monthlySales[i] = monthlySales[i].clone();
        }
    }

    // this adds every value in the array.
    @Override
    public int GetTotalProductsSold() {
        int total = 0;
        for (int i = 0; i < monthlySales.length; i++) {
            for (int j = 0; j < monthlySales[i].length; j++) {
                total += monthlySales[i][j];
            }
        }
        return total;
    }

    // Calculates the Average across all entered product/month sales values.
    // For 3 products x 3 months, divide the total by 9.
    @Override
    public double GetAverageProductsSold() {
        int entries = 0;
        for (int[] row : monthlySales) entries += row.length;
        if (entries == 0) return 0.0;
        return (double) GetTotalProductsSold() / entries;
    }

    // This finds the product with the highest combined sales across the months.
    @Override
    public String GetMostPopularProduct() {
        if (productNames.length == 0) return "No products";

        int highestIndex = 0;
        for (int i = 1; i < monthlySales.length; i++) {
            if (getProductTotal(i) > getProductTotal(highestIndex)) {
                highestIndex = i;
            }
        }
        return productNames[highestIndex] + " (" + getProductTotal(highestIndex) + ")";
    }

    private int getProductTotal(int productIndex) {
        int total = 0;
        for (int sale : monthlySales[productIndex]) total += sale;
        return total;
    }
}

