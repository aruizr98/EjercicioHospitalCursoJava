
public class Doctor extends Empleado {
	private String especialidad;
	
	public Doctor(String nombre, String apellidos, String dni, String especialidad) {
		super(nombre, apellidos, dni);
		this.especialidad=especialidad;
	}
	public Doctor(String nombre, String apellidos, String especialidad) {
		super(nombre, apellidos);
		this.especialidad=especialidad;
	}
	
	/**
	 * Método que diagnostica aleatoriamente al paciente introducido como parámetro de entrada.
	 * Devuelve null si el paciente no está enfermo. En caso contrario, devuelve el enfermo.
	 * @param p
	 * @return Enfermo
	 */
	public Enfermo diagnosticar(Paciente p) {
		Enfermo e = null;
		String[] enfermedades= {"Asma", "Artritis", "Maningitis","Cáncer", "Neumonía", "Covid", "Otitis", "Apraxia", "Apendicitis"};
		if((int)(Math.random()*(10-1)+1) > 7) {
			int random=(int)(Math.random()*enfermedades.length);
			String enfermedad=enfermedades[random];
			System.out.println("El paciente "+p.getNombre()+" "+p.getApellidos()+" está enfermo de "+enfermedad);
			e = new Enfermo(p.getNombre(), p.getApellidos(), p.getDni(), enfermedad);
		}else {
			System.out.println("El paciente "+p.getNombre()+" "+p.getApellidos()+" no está enfermo.");
		}
		return e;
	}
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	@Override
	public void fichar(String accion) {
		if(accion.equalsIgnoreCase("Entrar")) {
		System.out.println("El doctor "+this.getNombre()+" ha fichado al entrar.");
		}else if(accion.equalsIgnoreCase("Salir")){
			System.out.println("El doctor "+this.getNombre()+" ha fichado al salir.");
		}
		
	}

	@Override
	public String toString() {
		return super.toString()+"Doctor [especialidad=" + especialidad + "]";
	}
	
}
