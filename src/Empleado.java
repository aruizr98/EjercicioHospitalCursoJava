import interfaces.IFichable;

public abstract class Empleado extends Persona implements IFichable{

	public Empleado(String nombre, String apellidos, String dni) {
		super(nombre, apellidos, dni);
	}
	public Empleado(String nombre, String apellidos) {
		super(nombre, apellidos);
	}
}
