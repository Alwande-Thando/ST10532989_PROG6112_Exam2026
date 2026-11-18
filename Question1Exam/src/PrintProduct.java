
// for displaying the report.
public class PrintProduct {
    public void printTotal(Product product) {
        System.out.println("TOTAL PRODUCT SALES: " + product.GetTotalProductsSold());
    }

    public void printAverage(Product product) {
        System.out.printf("AVERAGE PRODUCT SALES: %.2f%n",
                product.GetAverageProductsSold());
    }

    public void printMostPopular(Product product) {
        System.out.println("MOST POPULAR PRODUCT: " + product.GetMostPopularProduct());
    }

    public void printReport(Product product) {
        System.out.println("\nPRODUCT SALES REPORT - 2026");
        System.out.println("*******************************************");
        printTotal(product);
        printAverage(product);
        printMostPopular(product);
    }
}
