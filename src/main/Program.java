package main;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

import classes.Conta;
import classes.ContaController;
import classes.Pessoa;
import exception.NumeroContaException;
import exception.SaldoInsuficienteException;

public class Program {

	public static void main(String[] args) {
		Scanner ler = new Scanner(System.in);
		ContaController controller = new ContaController();

		int op;

		do {
			System.out.println("-----------------------------SISTEMA DE CONTROLE BANC�RIO-----------------------------");
			System.out.println("[1] - Cadastrar nova conta");
			System.out.println("[2] - Listar todas as contas cadastradas");
			System.out.println("[3] - Listar todas as contas cadastradas que possuem uma pessoa jur�dica como titular da conta");
			System.out.println("[4] - Listar todas as contas cadastradas que possuem uma pessoa f�sica como titular da conta");
			System.out.println("[5] - Apresentar todas as contas que possuem um saldo negativo");
			System.out.println("[6] - Apresentar todos os clientes que se aplicam a investimentos");
			System.out.println("[7] - Buscar contas conforme c�digo da conta, nome do cliente, CPF/CNPJ do cliente");
			System.out.println("[8] - Realizar um dep�sito banc�rio");
			System.out.println("[9] - Realizar um saque");
			System.out.println("[10] - Realizar uma transfer�ncia banc�ria");
			System.out.println("[11] - Remover conta permanentemente do sistema");
			System.out.println("[12] - Sair");
			System.out.println("-----------------------------SISTEMA DE CONTROLE BANC�RIO-----------------------------");
			System.out.println("Informe a opera��o a ser realizada: ");
			op = ler.nextInt();

			switch (op) {
			case 1:
				System.out.println("Quantos contas ser�o cadastrados?");
				int qntContas = ler.nextInt();
				for (int i = 1; i <= qntContas; i++) {
					System.out.println("Informe se o titular da conta � Pessoa F�sica ou Pessoa Jur�dica: [pf/pj]");
					String tipo = ler.next();
					System.out.println("Informe o nome do titular da conta:");
					String nome = ler.next();
					System.out.println("Informe um email para contato:");
					String email = ler.next();

					String cpf_cnpj = "";
					do {
						System.out.println("Informe o seu CPF/CNPJ: ");
						cpf_cnpj = ler.next();
					} while (controller.existeCpfCnpj(cpf_cnpj));

					System.out.println("Informe o valor a ser depositado na conta, se houver:");
					double saldo = ler.nextDouble();

					int numero = 1;
					do {
						Random gerador = new Random();
						numero = gerador.nextInt(99);
					} while (controller.existeNumero(numero));
					
					Pessoa pessoa = new Pessoa(nome, email, cpf_cnpj);
					Conta addconta = new Conta(numero, saldo, tipo, pessoa);

					controller.adicionar(addconta);
					System.out.println("Cadastro realizado com sucesso!");
					System.out.println("----------------------------------------------------------------------------");
				}

				break;

			case 2:
				System.out.println("LISTA DE CONTAS:");
				controller.listarContas();
				break;

			case 3:
				List<Conta> listaPj = controller.listaContasTipo("pj");
				if (!listaPj.isEmpty()) {
					controller.imprimeLista(listaPj);
				} else {
					System.out.println("N�o h� contas de Pessoa Jur�dica cadastradas!");
				}
				break;

			case 4:
				List<Conta> listaPf = controller.listaContasTipo("pf");
				if (!listaPf.isEmpty()) {
					controller.imprimeLista(listaPf);
				} else {
					System.out.println("N�o h� contas de Pessoa F�sica cadastradas!");
				}
				break;

			case 5:
				List<Conta> listaSaldoNegativo = controller.listaSaldoNegativo();
				if (!listaSaldoNegativo.isEmpty()) {
					controller.imprimeLista(listaSaldoNegativo);
				} else {
					System.out.println("N�o h� contas negativas!");
				}
				break;

			case 6:
				List<Conta> listaInvest = controller.listaClientesInvest();
				if (!listaInvest.isEmpty()) {
					for (Conta conta : listaInvest) {
						System.out.println("Nome: " + conta.getPessoa().getNome() + ", CPF/CNPJ: "
								+ conta.getPessoa().getCpf_cnpj() + ", Informa��o de contato: " + conta.getPessoa().getEmail());
					}
				} else {
					System.out.println("N�o h� clientes que se aplicam a investimentos!");
				}
				break;

			case 7:
				System.out.println("------------MENU DE BUSCA----------------");
				System.out.println("[1] - N�mero da Conta");
				System.out.println("[2] - Nome do Cliente");
				System.out.println("[3] - CPF ou CNPJ do Cliente");
				System.out.println("[4] - C�digo da Conta/Nome do Cliente/CPF ou CNPJ do Cliente");
				System.out.println("------------MENU DE BUSCA----------------");
				System.out.println("Informe o tipo de busca a ser realizado: ");
				int op2 = ler.nextInt();
				switch (op2) {
				case 1:
					System.out.println("N�mero da Conta: ");
					int numero = ler.nextInt();
					List<Conta> listaNumero = controller.filtrarPorNumero(numero);
					if (!listaNumero.isEmpty()) {
						controller.imprimeLista(listaNumero);
					} else {
						System.out.println("N�mero da conta n�o encontrado!");
					}
					break;
				case 2:
					System.out.println("Nome do Cliente: ");
					String nome = ler.next();
					List<Conta> listaNome = controller.filtrarPorNome(nome);
					if (!listaNome.isEmpty()) {
						controller.imprimeLista(listaNome);
					} else {
						System.out.println("Nome do Cliente n�o encontrado!");
					}
					break;
				case 3:
					System.out.println("CPF/CNPJ do Cliente: ");
					String cpf_cpnj = ler.next();
					List<Conta> listaCpfCnpj = controller.filtrarPorCpfCnpj(cpf_cpnj);
					if (!listaCpfCnpj.isEmpty()) {
						controller.imprimeLista(listaCpfCnpj);
					} else {
						System.out.println("CPF/CNPJ n�o encontrado!");
					}
					break;
				case 4:
					System.out.println("N�mero da Conta: ");
					numero = ler.nextInt();
					System.out.println("Nome do Cliente: ");
					nome = ler.next();
					System.out.println("CPF/CNPJ do Cliente: ");
					String cpf_cnpj = ler.next();
					List<Conta> listaTodos = controller.filtrar(numero, nome, cpf_cnpj);
					if (!listaTodos.isEmpty()) {
						controller.imprimeLista(listaTodos);
					} else {
						System.out.println("Conta n�o encontrada!");
					}
					break;
				default:
					System.out.println("Op��o inexistente");
					break;
				}
				break;

			case 8:
				System.out.println("Informe o n�mero da conta para dep�sito: ");
				int numero = ler.nextInt();
				System.out.println("Informe o valor a ser depositado: ");
				double valor = ler.nextDouble();
				try {
					controller.deposito(numero, valor);
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				} catch (NumeroContaException g) {
					System.out.println(g.getMessage());
				}
				break;

			case 9:
				System.out.println("Informe o n�mero da conta para saque: ");
				numero = ler.nextInt();
				System.out.println("Informe o valor a ser sacado: ");
				valor = ler.nextDouble();
				try {
					controller.saque(numero, valor);
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				} catch (SaldoInsuficienteException g) {
					System.out.println(g.getMessage());
				} catch (NumeroContaException h) {
					System.out.println(h.getMessage());
				}
				break;

			case 10:
				System.out.println("Informe o n�mero da conta para realizar a transfer�ncia: ");
				numero = ler.nextInt();
				System.out.println("Informe o n�mero da conta que ir� receber a transfer�ncia: ");
				int numero2 = ler.nextInt();
				System.out.println("Informe o valor a ser transferido: ");
				valor = ler.nextDouble();
				try {
					controller.transferir(numero, numero2, valor);
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
				} catch (SaldoInsuficienteException g) {
					System.out.println(g.getMessage());
				} catch (NumeroContaException h) {
					System.out.println(h.getMessage());
				}
				break;

			case 11:
				System.out.println("Informe o n�mero da conta: ");
				numero = ler.nextInt();
				controller.remove(numero);
				break;

			case 12:
				break;

			default:
				System.out.println("Op��o inexistente");
				break;

			}
		} while (op != 12);
		ler.close();
	}
}
