package application;

import model.entities.Contract;
import model.sevices.ContractService;
import model.sevices.paypalFeeService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws ParseException {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Enter contract data");
        System.out.print("Number: ");
        int numberContract = scanner.nextInt();
        System.out.print("Date (dd/MM/yyyy): ");
        Date dateContract = sdf.parse(scanner.next());
        System.out.print("Contract value: ");
        double valueContract = scanner.nextDouble();
        System.out.print("Enter number of installments: ");
        int numberInstallmentsContract = scanner.nextInt();

        Contract contract = new Contract(numberContract, dateContract, valueContract);
        ContractService service = new ContractService(contract, numberInstallmentsContract, new paypalFeeService());
        System.out.println("Installments: ");
        service.printInstallmentValues();
        scanner.close();
    }
}
