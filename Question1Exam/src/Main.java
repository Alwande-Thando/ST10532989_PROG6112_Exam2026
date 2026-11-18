//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


import java.util.Scanner;

//This part collects sales for three products over three months and displays a report.
public class Main {
    public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    String[] products = {
            "Cozy Nights Monarch",
            "Restonic Barbados",
            "Sealy Phuket"
    };

    int[][] sales = new int[3][3];

    for (int i = 0; i < products.length; i++) {
        for (int month = 0; month < 3; month++) {
            System.out.print("Enter number of sales for month " + (month + 1)
                    + " for " + products[i] + " mattress: ");
            while (!input.hasNextInt()) {
                System.out.print("Enter a whole number: ");
                input.next();
            }
            int value = input.nextInt();
            while (value < 0) {
                System.out.print("Sales cannot be negative. Enter again: ");
                value = input.nextInt();
            }
            sales[i][month] = value;
        }
    }

    Product product = new Product(products, sales);
    PrintProduct printer = new PrintProduct();
    printer.printReport(product);

    input.close();
}
}
