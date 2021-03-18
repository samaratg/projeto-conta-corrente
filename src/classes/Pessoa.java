package classes;

public class Pessoa {
	private String nome;
	private String email;
	private String cpf_cnpj;
	
	public Pessoa() {
	}
	
	public Pessoa(String nome, String email, String cpf_cnpj) {
		this.nome = nome;
		this.email = email;
		this.cpf_cnpj = cpf_cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpf_cnpj() {
		return cpf_cnpj;
	}

	public void setCpf_cnpj(String cpf_cnpj) {
			this.cpf_cnpj = cpf_cnpj;
	}
}

	
	
	
