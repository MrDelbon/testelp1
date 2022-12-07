package reserva_passagens;

public class Aviao extends Aeronave{
	public Passageiro[][] lugares;
	int fileira;
	int assentos;

	public Aviao(String modelo, int fileira, int assentos) {
		super(modelo);
		this.fileira = fileira;
		this.assentos = assentos;
		this.lugares = new Passageiro[fileira][assentos];
	}
	
	public Passageiro getPassageiro(int fileira, int assento) {
		return lugares[fileira][assento];
	}
	
	public boolean verificaLugarOcupado(int fileira, int assento) {
		if (lugares[fileira][assento] != null) {
			return true;
		} else {
			return false;
		}
	}

	public void setPassageiro(int fileira, int assento, Passageiro passageiro) {
		this.lugares[fileira][assento] = passageiro;
	}

	public int getFileira() {
		return fileira;
	}

	public void setFileira(int fileira) {
		this.fileira = fileira;
	}

	public int getAssentos() {
		return assentos;
	}

	public void setAssentos(int assentos) {
		this.assentos = assentos;
	}

	@Override
	public String toString() {
		return "Aviao [fileira=" + fileira + ", assentos=" + assentos
				+ ", modelo=" + modelo + "]";
	}
	
}