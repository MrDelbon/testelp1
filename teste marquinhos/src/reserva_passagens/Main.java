package reserva_passagens;

import javax.swing.JOptionPane;
import java.util.*;

public class Main {
	static ArrayList<Aviao> avioes = new ArrayList<Aviao>();
	static ArrayList<Voo> voos = new ArrayList<Voo>();
	static ArrayList<Passageiro> passageiros = new ArrayList<Passageiro>();
	
	public static void main(String[] args) {
		menu();
	}

	public static void menu() {
		try {
			int opc = 0;
			Integer [] options = {1, 2, 3};
			
			do {
				opc = JOptionPane.showOptionDialog(null, "1 - Parâmetros do Sistema\n2 - Reservas de Passagens\n3 - Sair", "Menu Principal", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
				
				switch(opc) {
					case 0:
						parametrosSistemaMenu();
						break;
						
					case 1:
						reservaPassagensMenu();;
						break;
						
					case 2:
					default:
						System.exit(0);
						break;
				}
				
			}while(opc != 2);
			
		} catch (Exception error) {
			JOptionPane.showMessageDialog(null, error.toString());
			menu();
		}
	}
	
	public static void parametrosSistemaMenu() {
		try {
			int opc = 0;
			Integer [] options = {1, 2, 3};
			
			do {
				opc = JOptionPane.showOptionDialog(null, "1 - Cadastrar Aeronave\n2 - Cadastrar Voo\n3 - Voltar", "Parâmetros do Sistema", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
				
				switch(opc) {
					case 0:
						cadastrarAeronave();;
						break;
						
					case 1:
						cadastrarVoo();
						break;
						
					case 2:
					default:
						menu();
						break;
				}
				
			}while(opc != 2);
			
		} catch (Exception error) {
			JOptionPane.showMessageDialog(null, error.toString());
			menu();
		}
	}
	
	public static void reservaPassagensMenu() {
		try {
			int opc = 0;
			Integer [] options = {1, 2, 3, 4};
			
			do {
				opc = JOptionPane.showOptionDialog(null, "1 - Fazer Reserva\n2 - Consultar lugares vazios\n3 - Consultar reservas realizadas\n4 - Voltar", "Reserva de Passagens", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, null);
				
				switch(opc) {
					case 0:
						fazerReserva();;
						break;
						
					case 1:
						consultarLugaresVazios();
						break;
						
					case 2:
						consultarReservas();
						break;
						
					case 3:
					default:
						menu();
						break;
				}
				
			}while(opc != 3);
			
		} catch (Exception error) {
			JOptionPane.showMessageDialog(null, error.toString());
			menu();
		}
	}
	
	public static void cadastrarAeronave() {
		try {
			String modelo = JOptionPane.showInputDialog("Digite o modelo da aeronave:");
			int fileiras = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de fileiras da aeronave:"));
			int assentos = Integer.parseInt(JOptionPane.showInputDialog("Digite a quantidade de assentos por fileira da aeronave:"));;
			Aviao aviao = new Aviao(modelo, fileiras, assentos);
			Main.avioes.add(aviao);
		}catch (Exception error) {
			throw error;
		}
	}
	
	public static void cadastrarVoo() {
		try {
			Aviao aviaoVoo;
			Voo voo;
			String data;
			String hora;
			
			int aviaoOpc = 0;
			int i = 0;
			String message = "";
			
			if(Main.avioes.isEmpty()) {
				JOptionPane.showConfirmDialog(null, "É necessário cadastrar uma aeronave!", "", JOptionPane.DEFAULT_OPTION);
				cadastrarAeronave();
			}
			
			for(Aviao aviao : Main.avioes) {
				message += "Nº " + i + " " + aviao.toString()+"\n";
				i++;
			}
			
			aviaoOpc = Integer.parseInt(JOptionPane.showInputDialog("Digite o Nº da aeronave:\n" + message));
			
			aviaoVoo = (Aviao) Main.avioes.get(aviaoOpc);
			
			data = JOptionPane.showInputDialog("Digite a data do voo:");
			hora = JOptionPane.showInputDialog("Digite a hora do voo:");
			
			voo = new Voo(aviaoVoo, i, data, hora);
			
			Main.voos.add(voo);
		}catch (Exception error) {
			throw error;
		}
	}
	
	public static void fazerReserva() {
		try {
			Passageiro passageiro;
			String nome;
			String cpf;
			String message = "";
			int i = 0;
			int numeroVoo;
			Aviao aviaoVoo;
			int qntLugaresVazios = 0;
			int fileira;
			int assento;
			
			if(Main.voos.isEmpty()) {
				JOptionPane.showConfirmDialog(null, "É necessário cadastrar um voo!", "", JOptionPane.DEFAULT_OPTION);
				reservaPassagensMenu();
			}
			
			nome = JOptionPane.showInputDialog("Digite o nome do passageiro:");
			cpf = JOptionPane.showInputDialog("Digite o CPF do passageiro:");
			
			passageiro = new Passageiro(nome, cpf);
			
			for(Aviao aviao : Main.avioes) {
				message += "Nº " + i + " " + aviao.toString()+"\n";
				i++;
			}
			
			numeroVoo = Integer.parseInt(JOptionPane.showInputDialog("Digite o Nº do Voo:\n" + message));
			
			aviaoVoo = Main.avioes.get(numeroVoo);
			
			for (int j = 0; j < aviaoVoo.getFileira(); j++) {
				for (int k = 0; k < aviaoVoo.getAssentos(); k++) {
					if(!aviaoVoo.verificaLugarOcupado(j, k)) qntLugaresVazios++;
				}
			}
			
			if (qntLugaresVazios == 0) {
				JOptionPane.showConfirmDialog(null, "Não há lugares nesse Voo!", "", JOptionPane.DEFAULT_OPTION);
				reservaPassagensMenu();
			}
			
			fileira = Integer.parseInt(JOptionPane.showInputDialog("Digite o Nº da fileira (até " + aviaoVoo.getFileira() + "):"));
			assento = Integer.parseInt(JOptionPane.showInputDialog("Digite o Nº do assento (até " + aviaoVoo.getAssentos() + "):"));
			
			if(fileira > aviaoVoo.getFileira() || assento > aviaoVoo.getAssentos()) {
				JOptionPane.showConfirmDialog(null, "Fileira ou assento inválido!", "", JOptionPane.DEFAULT_OPTION);
			} else if (aviaoVoo.verificaLugarOcupado(fileira, assento)) {
				JOptionPane.showConfirmDialog(null, "Lugar ocupado!", "", JOptionPane.DEFAULT_OPTION);
			} else {
				passageiro = new Passageiro(nome, cpf);
				aviaoVoo.setPassageiro(fileira, assento, passageiro);
				Main.passageiros.add(passageiro);
				JOptionPane.showConfirmDialog(null, nome + " assento reservado com sucesso!", "", JOptionPane.DEFAULT_OPTION);
			}	
		}catch (Exception error) {
			throw error;
		}
	}
	
	public static void consultarLugaresVazios() {
		try {
			String message = "";
			int numeroVoo;
			Aviao aviaoVoo;
			int qntLugaresVazios = 0;
			int i = 0;
			
			if(Main.voos.isEmpty()) {
				JOptionPane.showConfirmDialog(null, "É necessário cadastrar um voo!", "", JOptionPane.DEFAULT_OPTION);
				reservaPassagensMenu();
			}
			
			for(Aviao aviao : Main.avioes) {
				message += "Nº " + i + " " + aviao.toString()+"\n";
				i++;
			}
			
			numeroVoo = Integer.parseInt(JOptionPane.showInputDialog("Digite o Nº do Voo:\n" + message));
			
			aviaoVoo = Main.avioes.get(numeroVoo);
			
			for (int j = 0; j < aviaoVoo.getFileira(); j++) {
				for (int k = 0; k < aviaoVoo.getAssentos(); k++) {
					if(!aviaoVoo.verificaLugarOcupado(j, k)) qntLugaresVazios++;
				}
			}
			
			JOptionPane.showConfirmDialog(null, "Há " + qntLugaresVazios + " lugares vazios nesse Voo!", "", JOptionPane.DEFAULT_OPTION);
		}catch (Exception error) {
			throw error;
		}
	}
	
	public static void consultarReservas() {
		try {
			String message = "";
			String table = "";
			int numeroVoo;
			Aviao aviaoVoo;
			int i = 0;
			
			if(Main.voos.isEmpty()) {
				JOptionPane.showConfirmDialog(null, "É necessário cadastrar um voo!", "", JOptionPane.DEFAULT_OPTION);
				reservaPassagensMenu();
			}
			
			for(Aviao aviao : Main.avioes) {
				message += "Nº " + i + " " + aviao.toString()+"\n";
				i++;
			}
			
			numeroVoo = Integer.parseInt(JOptionPane.showInputDialog("Digite o Nº do Voo:\n" + message));
			
			aviaoVoo = Main.avioes.get(numeroVoo);
			
			for(int j = 0; j < aviaoVoo.getFileira(); j++) {
				table += "Fileira " + j + " ";
				for(int k = 0; k < aviaoVoo.getAssentos(); k++) {
					if(aviaoVoo.verificaLugarOcupado(j, k)) {
						table += "[X] ";
					}else {
						table += "[_] ";
					}
				}
				table += "\n";
			}
			
			JOptionPane.showConfirmDialog(null, "Tabela de assentos:\n" + table, "", JOptionPane.DEFAULT_OPTION);
		}catch (Exception error) {
			throw error;
		}
	}
}