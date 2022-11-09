import java.util.Arrays;
import java.util.Iterator;

public class Hospital {
	private String nombre;
	private Paciente[] salaEspera;
	private Consulta[] consultas;
	private Enfermo[] habitaciones;
	private Enfermero[] enfermeros;

	public void abrirHospital(String nombre) {
		this.nombre = nombre;
		agregarPacientesSalaEspera();
		llenarConsultas();
		tratarPacientes();
	}

	/**
	 * Método que crea 4 pacientes y los añade a la sala de espera.
	 */
	public void agregarPacientesSalaEspera() {
		Paciente paciente1 = new Paciente("Paciente 1", "García", "tos");
		Paciente paciente2 = new Paciente("Paciente 2", "Rodriguez", "Fiebre");
		Paciente paciente3 = new Paciente("Paciente 3", "Sánchez", "dolor en el brazo.");
		Paciente paciente4 = new Paciente("Paciente 4", "Martínez", "mareos");

		Paciente[] aux = { paciente1, paciente2, paciente3, paciente4 };
		this.salaEspera = aux;
	}

	/**
	 * Método que crea 2 doctores y los añade a 2 consultas.
	 */
	public void llenarConsultas() {
		Doctor doctor1 = new Doctor("Doctor 1", "García", "Cirujía");
		Doctor doctor2 = new Doctor("Doctor 2", "Álvarez", "Traumatología");
		this.consultas = new Consulta[2];
		this.consultas[0] = new Consulta(doctor1);
		this.consultas[1] = new Consulta(doctor2);
	}

	/**
	 * Método que crea 2 enfermeros. Estos enfermeros atienden a cada paciente de la
	 * lista de espera asignándoles una consulta. Posteriormente, el doctor de cada
	 * consulta diagnosticará a cada paciente, añadiendolo a una habitación si fuera
	 * necesario.
	 */
	public void tratarPacientes() {
		Enfermero enfermero1 = new Enfermero("Enfermero 1", "Ruiz", "Traumatología");
		Enfermero enfermero2 = new Enfermero("Enfermero 2", "García", "Cirujía");
		Enfermero[] aux = { enfermero1, enfermero2 };
		this.enfermeros = aux;
		ficharEmpleados("Entrar");//Los empleados fichan al comenzar su jornada laboral.
		System.out.println();
		habitaciones = new Enfermo[3];
		do {//Los enfermeros atenderán a los pacientes hasta que la sala de espera esté vacía.
			System.out.println("Los enfermeros asignan a cada paciente a una consulta:\n");
			int e=0;
			for (int j = 0; j < salaEspera.length; j++) {
				if(j%2==0) {//Los enfermeros se reparten el trabajo: El enfermero 1 atenderá a los pacientes impares y el enfermero 2 a los pares.
					e=0;
				}else {
					e=1;
				}
				if (salaEspera[j] != null) {//Si hay alguien en la sala de espera, se le atiende.
					System.out.println("El enfermero " + enfermeros[e].getNombre() + " "
							+ enfermeros[e].getApellidos() + " atiende a " + salaEspera[j].getNombre() + " "
							+ salaEspera[j].getApellidos());
					if(enfermeros[e].atenderPaciente(salaEspera[j], consultas)) {//Si el paciente ha sido asignado a una consulta,
						salaEspera[j] = null;//eliminarlo de la sala de espera, dejando hueco para un futuro paciente. El paciente abandona la sala de espera y acude a la consulta.
					}else {
						break;//Si no hay más sitio en las consultas, dejar de atender pacientes.
					}
				}
				
			}
			System.out.println("Los pacientes son atendidos por su correspondiente doctor:\n");


			for (int i = 0; i < consultas.length; i++) {//Cada paciente es atendido por su correspondiente doctor, que le ha asignado anteriormente el enfermero.
				for (int j = 0; j < habitaciones.length; j++) {
					if (habitaciones[j] == null && consultas[i].getPaciente() != null) {//Si hay alguna habitación libre y algún paciente en la consulta, el doctor le diagnosticará.
						habitaciones[j] = consultas[i].getDoctor().diagnosticar(consultas[i].getPaciente());
						/*
						 * habitaciones[j] será null si el paciente no está enfermo.
						 * En caso contrario, habitaciones[j] recibirá el enfermo con la enfermedad que tenga el paciente al ser diagnosticado.
						 */
						if(habitaciones[j]!=null) {//Si el paciente está enfermo, informar de que ha sido ingresado.
							System.out.println("El paciente "+consultas[i].getPaciente().getNombre()+" "+consultas[i].getPaciente().getApellidos()+" ha sido ingresado.");
						}else {
							System.out.println("El paciente "+consultas[i].getPaciente().getNombre()+" "+consultas[i].getPaciente().getApellidos()+" se va a su casa.");
						}
						consultas[i].setPaciente(null);//El paciente en cualquier caso abandona la consulta, dejando un hueco libre para un futuro paciente.
					}
				}
				/*
				 * Si no hay ninguna habitación libre pero sigue habiendo algún paciente, se le trata, pero si está enfermo
				 * se le deriva a otro hospital.
				 */
				if(consultas[i].getPaciente()!=null && !comprobarHabitaciones()) {
					Enfermo resultado=consultas[i].getDoctor().diagnosticar(consultas[i].getPaciente());
					if(resultado != null) {
						System.out.println("El paciente "+consultas[i].getPaciente().getNombre()+" "+consultas[i].getPaciente().getApellidos()+" no puede ser ingresado porque no hay ninguna habitación libre. Se le deriva a otro hospital.");
					}else {
						System.out.println("El paciente "+consultas[i].getPaciente().getNombre()+" "+consultas[i].getPaciente().getApellidos()+" se va a su casa.");
					}
				}
			}
			System.out.println((comprobarHabitaciones() ? "Todavía queda alguna habitación libre." : "Se han ocupado todas las habitaciones disponibles."));
			System.out.println("Sala de espera vacía: "+(comprobarSalaEsperaVacia() ? "Sí" : "No."));
		}while(!comprobarSalaEsperaVacia());//Repetir el proceso mientras la sala de espera no esté vacía.
		mostrarHabitaciones();
	}
	/**
	 * Método que llama al método fichar tanto de los enfermeros como de los doctores.
	 */
	public void ficharEmpleados(String accion) {
		for (int i = 0; i < enfermeros.length; i++) {
			enfermeros[i].fichar(accion);
		}
		for (int i = 0; i < consultas.length; i++) {
			consultas[i].getDoctor().fichar(accion);
		}
	}
	/**
	 * Método que muestra tanto las habitaciones vacías como las ocupadas.
	 */
	public void mostrarHabitaciones() {
		System.out.println("Habitaciones:");
		for (Enfermo habitacion : habitaciones) {
			if(habitacion!=null) {
				System.out.println(habitacion);
			}else {
				System.out.println("Habitación vacía.");
			}
		}
	}

	/**
	 * Método que devuelve true en caso de que la sala de espera esté vacía o false
	 * en caso contrario.
	 * 
	 * @return
	 */
	public boolean comprobarSalaEsperaVacia() {
		boolean vacia = true;
		for (int i = 0; i < salaEspera.length; i++) {
			if (salaEspera[i] != null) {
				vacia = false;
			}
		}
		return vacia;

	}
	/**
	 * Método que devuelve true si hay alguna habitación libre y false en caso contrario.
	 * @return
	 */
	public boolean comprobarHabitaciones() {
		boolean habitacionLibre=false;
		for (int i = 0; i < habitaciones.length; i++) {
			if(habitaciones[i]==null) {
				habitacionLibre=true;
			}
		}
		return habitacionLibre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Paciente[] getSalaEspera() {
		return salaEspera;
	}

	public void setSalaEspera(Paciente[] salaEspera) {
		this.salaEspera = salaEspera;
	}

	public Consulta[] getConsultas() {
		return consultas;
	}

	public void setConsultas(Consulta[] consultas) {
		this.consultas = consultas;
	}

	public Enfermo[] getHabitaciones() {
		return habitaciones;
	}

	public void setHabitaciones(Enfermo[] habitaciones) {
		this.habitaciones = habitaciones;
	}

	public Enfermero[] getEnfermeros() {
		return enfermeros;
	}

	public void setEnfermeros(Enfermero[] enfermeros) {
		this.enfermeros = enfermeros;
	}

	@Override
	public String toString() {
		return "Hospital [nombre=" + nombre + ", salaEspera=" + Arrays.toString(salaEspera) + ", consultas="
				+ Arrays.toString(consultas) + ", habitaciones=" + Arrays.toString(habitaciones) + ", enfermeros="
				+ Arrays.toString(enfermeros) + "]";
	}

	public static void main(String[] args) {
		Hospital h = new Hospital();
		h.abrirHospital("Hospital 1");
		System.out.println();
		h.ficharEmpleados("Salir");
	}

}
