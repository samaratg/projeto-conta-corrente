package classes;

import java.util.ArrayList;
import java.util.List;

import exception.NumeroContaException;
import exception.SaldoInsuficienteException;

public class ContaController {
	public List<Conta> lista = new ArrayList<>();
	
//	private void preencherLista() {
//		Pessoa p1 = new Pessoa("Faris","f@mail.com","065");
//		Pessoa p2 = new Pessoa("Faradh","fh@mail.com","015");
//		Pessoa p3 = new Pessoa("Samara","s@mail.com","035");
//		Pessoa p4 = new Pessoa("Empresa","emp@mail.com","015");
//		Conta c1 = new Conta(90,-12.0,"pf", p1);
//		Conta c4 = new Conta(93,1000.0,"pf",p2);
//		Conta c2 = new Conta(91,120.0,"pf",p3);
//		Conta c3 = new Conta(92,3000.0,"pj",p4);
//		
//		adicionar(c1);
//		adicionar(c2);
//		adicionar(c3);
//		adicionar(c4);
//	}
	
			
//	public ContaController() {
//		//TODO Remover em caso de sistema em produção
//		this.preencherLista();
//	}

	public void adicionar(Conta conta) {
		this.lista.add(conta);
	}

	public boolean existeNumero(int numero) {
		for (Conta conta : lista) {
			if (numero == conta.getNumero()) {
				return true;
			}
		}
		return false;
	}

	public void remove(int numero) {
		if (existeNumero(numero)) {
			for (Conta conta : lista) {
				System.out.println("CONTA REMOVIDA: ");
				lista.remove(conta);
				System.out.println(conta);
				break;
			}
		} else {
			System.out.println("Número de conta não encontrada");
		}
	}

	public void listarContas() {
		for (Conta conta : lista) {
			System.out.println(conta);
		}
	}

	public List<Conta> listaContasTipo(String tipo) {
		List<Conta> listaTipos = new ArrayList<>();
		for (Conta conta : lista) {
			if (conta.getTipo().equals(tipo.toLowerCase()) || conta.getTipo().equals(tipo.toUpperCase())) {
				listaTipos.add(conta);
			}
		}
		return listaTipos;
	}

	public List<Conta> listaSaldoNegativo() {
		List<Conta> listaNeg = new ArrayList<>();
		for (Conta conta : lista) {
			if (conta.getSaldo() < 0.0) {
				listaNeg.add(conta);
			}
		}
		return listaNeg;
	}

	public List<Conta> listaClientesInvest() {
		List<Conta> listaInvest = new ArrayList<>();
		for (Conta conta : lista) {
			if (conta.getSaldo() > 2000.00) {
				listaInvest.add(conta);
			}
		}
		return listaInvest;
	}

	public List<Conta> filtrar(int codigo, String nome, String cpf_cnpj) {
		List<Conta> listAux = new ArrayList<Conta>();
		for (Conta conta : this.lista) {
			if (codigo != 0 && nome != null && cpf_cnpj != null) {
				if (codigo == conta.getNumero() 
						&& nome.equals(conta.getPessoa().getNome()) 
						&& cpf_cnpj.equals(conta.getPessoa().getCpf_cnpj())) {//contains
					listAux.add(conta);
				}
			}else {
				if ((codigo == conta.getNumero())
						|| (nome != null && nome.equals(conta.getPessoa().getNome()))
						|| (cpf_cnpj != null && cpf_cnpj.equals(conta.getPessoa().getCpf_cnpj()))) {
					listAux.add(conta);
				}
				
			}
		}
		return listAux;
	}
	
	public List<Conta> filtrarPorNumero(int numero){		
		return filtrar(numero, null, null);
	}
	
	public List<Conta> filtrarPorNome(String nome){		
		return filtrar(0, nome, null);
	}
	
	public List<Conta> filtrarPorCpfCnpj(String cpfCnpj){		
		return filtrar(0, null, cpfCnpj);
	}

	public void deposito(int numero, double valor) {
		if (valor < 0) {
			throw new IllegalArgumentException("Não posso depositar um valor negativo!");
		}
		if (!existeNumero(numero)) {
			throw new NumeroContaException("Número de conta não encontrada!");
		}
		for (Conta conta : lista) {
			if (conta.getNumero() == numero) {
				conta.setSaldo(conta.getSaldo() + valor);
				System.out.println("Depósito realizado com sucesso!");
			}
		}
	}

	public void saque(int numero, double valor) {
		if (valor < 0) {
			throw new IllegalArgumentException("Não posso sacar um valor negativo!");
		}
		if (!existeNumero(numero)) {
			throw new NumeroContaException("Número de conta não encontrada!");
		}
		Conta conta2 = filtrarPorNumero(numero).get(0);
		if (valor < conta2.getSaldo()) {
			for (Conta conta : lista) {
				if (conta.getNumero() == numero) {
					conta.setSaldo(conta.getSaldo() - valor);
				}
			}
		} else {
			throw new SaldoInsuficienteException("Saldo Insuficiente, tente um valor menor que " + conta2.getSaldo());
		}
	}

	public void transferir(int origem, int destino, double valor) {
		if (valor < 0) {
			throw new IllegalArgumentException("Não posso sacar um valor negativo!");
		}
		if (!existeNumero(origem)) {
			throw new NumeroContaException("Número de conta não encontrada!");
		}
		if (!existeNumero(destino)) {
			throw new NumeroContaException("Número de conta não encontrada!");
		}
		Conta contaOrigem = filtrarPorNumero(origem).get(0);
		if (valor < contaOrigem.getSaldo()) {
			for (Conta conta : lista) {
				if (conta.getNumero() == origem) {
					conta.setSaldo(conta.getSaldo() - valor);
				}
				if (conta.getNumero() == destino) {
					conta.setSaldo(conta.getSaldo() + valor);
				}

			}

		} else {
			throw new SaldoInsuficienteException(
					"Saldo Insuficiente, tente um valor menor que " + contaOrigem.getSaldo());
		}
	}

	public boolean existeCpfCnpj(String cpf_cnpj) {
		for (Conta c : lista) {
			if (cpf_cnpj.equals(c.getPessoa().getCpf_cnpj())) {
				System.out.println("CPF/CNPJ já cadastrado!");
				return true;
			}
		}
		return false;
	}

	public void imprimeLista(List<Conta> contas) {
		for (Conta conta : contas) {
			System.out.println(conta);
		}
	}

}
