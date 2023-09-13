import java.math.BigDecimal;
import java.util.Date;

public class WalletService {
    private WalletDao dao;

    public WalletService() {
        dao = new WalletDao();
    }

    public void createWallet(String name, BigDecimal amount) {
        Account account = new Account(name, amount);
        dao.getAccountMap().put(account.getAccountNumber(), account);
        System.out.println("Account created for user " + name + " with account number " + account.getAccountNumber());
    }

    public void transfer(int fromAccNum, int toAccNum, BigDecimal transferAmount) {
        if(!validate(fromAccNum, toAccNum, transferAmount)) {
            return;
        }

        Transaction tran = new Transaction(fromAccNum, toAccNum, transferAmount, new Date());
        Account fromAccount = dao.getAccountMap().get(fromAccNum);
        Account toAccount = dao.getAccountMap().get(toAccNum);
        if(fromAccount.getBalance().compareTo(transferAmount) < 0) {
            System.out.println("Insufficient Balance");
            return;
        }
        fromAccount.setBalance(fromAccount.getBalance().subtract(transferAmount));
        toAccount.setBalance(toAccount.getBalance().add(transferAmount));
        fromAccount.getTransactions().add(tran);
        toAccount.getTransactions().add(tran);
        System.out.println("Transfer Successful");
    }

    private Boolean validate(int fromAccNum, int toAccNum, BigDecimal transferAmount) {
        if(fromAccNum == toAccNum) {
            System.out.println("Sender and receiver can't be same.");
            return false;
        }
        if(transferAmount.compareTo(new BigDecimal(0.0001)) < 0) {
            System.out.println("Amount too low");
            return false;
        }
        if(!dao.getAccountMap().containsKey(fromAccNum)) {
            System.out.println("Invalid sender account number");
            return false;
        }
        if(!dao.getAccountMap().containsKey(toAccNum)) {
            System.out.println("Invalid receiver account number");
            return false;
        }
        return true;
    }

    public void statement(int accountNum) {
        Account account = dao.getAccountMap().get(accountNum);
        if(account == null) {
            System.out.println("Invalid account number");
            return;
        }
        System.out.println("Summary for account number: " + accountNum);
        System.out.println("Current Balance: " + account.getBalance());
        System.out.println("Your Transaction History");
        System.out.println(account.getTransactions());
    }

    public void overview() {
        for(int accNum : dao.getAccountMap().keySet()) {
            System.out.println("Balance for account number " + accNum + ": ");
            System.out.println(dao.getAccountMap().get(accNum).getBalance());
        }
    }
}
