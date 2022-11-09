
public class Enfermo extends Persona {
	private String enfermedad;
	
	public Enfermo(String nombre, String apellidos, String dni, String enfermedad) {
		super(nombre, apellidos, dni);
		this.enfermedad=enfermedad;
	}
	
	public String getSintoma() {
		return enfermedad;
	}
	public void setSintoma(String sintoma) {
		this.enfermedad = sintoma;
	}

	@Override
	public String toString() {
		return super.toString()+"Enfermo [enfermedad=" + enfermedad + "]";
	}

	
}
