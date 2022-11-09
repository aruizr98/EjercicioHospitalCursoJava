import java.util.ArrayList;

public class Enfermero extends Empleado {
	private String seccion;
	
	public Enfermero(String nombre, String apellidos, String dni, String seccion) {
		super(nombre, apellidos, dni);
		this.seccion=seccion;
	}
	public Enfermero(String nombre, String apellidos, String seccion) {
		super(nombre, apellidos);
		this.seccion=seccion;
	}
	/**
	 * Método que asigna una consulta a un paciente, siempre que haya disponibilidad. Devuelve true si el paciente se ha introducido y false si no.
	 * @param p
	 * @param consultas
	 * @return
	 */
	public boolean atenderPaciente(Paciente p, Consulta[] consultas) {
		boolean pacienteIntroducido=false;
		for (Consulta consulta : consultas) {
			pacienteIntroducido=false;
			if(consulta.getPaciente()== null) {//Si la consulta no tiene paciente
				consulta.setPaciente(p);//Añadir el paciente a esa consulta.
				System.out.println("El paciente "+p.getNombre()+" "+p.getApellidos()+" ha sido asignado a la consulta del doctor "+consulta.getDoctor().getNombre()+" "+consulta.getDoctor().getApellidos());
				System.out.println();
				pacienteIntroducido=true;
				break;
			}
		}
		if(!pacienteIntroducido) {//Si no se ha logrado asignar al paciente a ninguna consulta, quiere decir que están todas ocupadas.
			System.out.println("Actualmente, todas las consultas están ocupadas.");
			pacienteIntroducido=false;
		}
		return pacienteIntroducido;
	}
	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	@Override
	public void fichar(String accion) {
		if(accion.equalsIgnoreCase("Entrar")) {
		System.out.println("El enfermero "+this.getNombre()+" ha fichado al entrar.");
		}else if(accion.equalsIgnoreCase("Salir")) {
			System.out.println("El enfermero "+this.getNombre()+" ha fichado al salir.");	
		}
		
	}

	@Override
	public String toString() {
		return super.toString()+"Enfermero [seccion=" + seccion + "]";
	}
	
	
}
