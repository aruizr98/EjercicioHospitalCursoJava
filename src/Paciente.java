
public class Paciente extends Persona {
	private String sintoma;
	
	public Paciente(String nombre, String apellidos, String dni, String sintoma) {
		super(nombre, apellidos, dni);
		this.sintoma=sintoma;
	}
	
	public Paciente(String nombre, String apellidos, String sintoma) {
		super(nombre, apellidos);
		this.sintoma=sintoma;
	}
	
	public String getSintoma() {
		return sintoma;
	}
	public void setSintoma(String sintoma) {
		this.sintoma = sintoma;
	}

	@Override
	public String toString() {
		return super.toString()+"Paciente [sintoma=" + sintoma + "]";
	}
	
	
}
