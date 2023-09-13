import java.math.BigDecimal;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        WalletService wService = new WalletService();
        Scanner sc = new Scanner(System.in);
        outer: while (true)  {
            System.out.println("\nOPTIONS:");
            System.out.println("1. Create Wallet");
            System.out.println("2. Transfer Amount");
            System.out.println("3. Account Statement");
            System.out.println("4. Overview");
            System.out.println("5. Exit");
            switch (sc.nextInt()) {
                case 1:
                    System.out.println("YOU SELECTED CREATE WALLET");
                    System.out.println("ENTER NAME");
                    String name = sc.next();
                    System.out.println("ENTER AMOUNT");
                    BigDecimal amount = sc.nextBigDecimal();
                    wService.createWallet(name, amount);
                    break;

                case 2:
                    System.out.println("YOU SELECTED TRANSFER");
                    System.out.println("ENTER SENDER ACCOUNT NUMBER");
                    int from = sc.nextInt();
                    System.out.println("ENTER RECEIVER ACCOUNT NUMBER");
                    int to = sc.nextInt();
                    System.out.println("ENTER AMOUNT");
                    BigDecimal amount1 = sc.nextBigDecimal();
                    wService.transfer(from, to, amount1);
                    break;

                case 3:
                    System.out.println("YOU SELECTED ACCOUNT STATEMENT");
                    System.out.println("ENTER ACCOUNT NUMBER");
                    wService.statement(sc.nextInt());
                    break;

                case 4:
                    System.out.println("YOU SELECTED OVERVIEW");
                    wService.overview();
                    break;

                case 5:
                    System.out.println("APPLICATION STOPPED");
                    break outer;

                default:
                    System.out.println("YOU HAVE SELECTED INVALID OPTION. PLEASE RE-ENTER");
                    break;
            }
        }
    }
}