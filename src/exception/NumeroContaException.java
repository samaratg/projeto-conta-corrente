package exception;

public class NumeroContaException extends RuntimeException{
	public NumeroContaException(String message) {
		super(message);
	}
	
	public NumeroContaException(int numero) {
		super("N�mero de conta n�o encontrada!");
	}
}