package model.sevices;

import model.entities.Contract;
import model.entities.Installment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ContractService {
    private Contract contract;
    private Integer numberInstallments;
    private FeeService feeService;
    private static Calendar calendar = Calendar.getInstance();
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public ContractService(Contract contract, Integer numberInstallments, FeeService feeService) {
        this.contract = contract;
        this.numberInstallments = numberInstallments;
        this.feeService = feeService;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Integer getNumberInstallments() {
        return numberInstallments;
    }

    public void setNumberInstallments(Integer numberInstallments) {
        this.numberInstallments = numberInstallments;
    }

    public FeeService getFeeService() {
        return feeService;
    }

    public void setFeeService(FeeService feeService) {
        this.feeService = feeService;
    }

    public void calculateInstallmentValues() {
        double baseAmountPerMonth = contract.getTotalValue() / getNumberInstallments();
        for (int i = 1; i <= getNumberInstallments(); i++) {
            calendar.setTime(contract.getDate());
            calendar.add(Calendar.MONTH, 1);
            Date installmentDate = calendar.getTime();
            double valueInstallment = feeService.feeValue(baseAmountPerMonth, i);
            contract.addInstallment(new Installment(installmentDate, valueInstallment));
        }
    }

    public void printInstallmentValues() {
        calculateInstallmentValues();
        for (Installment installment : contract.getInstallments()) {
            System.out.println(sdf.format(installment.getDueDate()) + " - " + installment.getAmount());
        }
    }
}
