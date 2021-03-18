package exception;

public class SaldoInsuficienteException extends RuntimeException {
	public SaldoInsuficienteException(String message) {
		super(message);
	}
	
	public SaldoInsuficienteException(double saldo) {
		super("Saldo insuficiente para sacar o valor de: " + saldo);
	}
}

