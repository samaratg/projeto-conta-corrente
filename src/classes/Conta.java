package classes;

public class Conta {
	private int numero;
	private double saldo;
	private String tipo;
	private Pessoa pessoa;
	
	
	public Conta() {
	}

	public Conta(int numero, double saldo, String tipo, Pessoa pessoa) {
		this.numero = numero;
		this.saldo = saldo;
		this.tipo = tipo;
		this.pessoa = pessoa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	
	public void deposita (double valor) {
			this.saldo += valor;
	}
	
	public void saca (double valor) {
			this.saldo -= valor;
		}
	
	public void transfere(Conta destino, double valor) {
			destino.deposita(valor);
			this.saldo -= valor;

		}

	@Override
	public String toString() {
		return "Conta [Nome do cliente: " + pessoa.getNome() + ", Número da conta: " + numero + ", Saldo: " + saldo + "]";
	}

}
