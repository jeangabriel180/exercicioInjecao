package model.sevices;

public class paypalFeeService implements FeeService {
    @Override
    public double feeValue(double amount, int numberInstallments) {
        double simpleInterest = (amount + (amount * 0.01 * numberInstallments));
        return simpleInterest + (simpleInterest * 0.02);
    }
}
