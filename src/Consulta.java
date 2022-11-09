
public class Consulta {
	private Doctor doctor;
	private Paciente paciente;
	
	public Consulta(Doctor doctor, Paciente paciente) {
		this.doctor = doctor;
		this.paciente = paciente;
	}
	public Consulta(Doctor doctor) {
		this.doctor = doctor;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	@Override
	public String toString() {
		return "Consulta [doctor=" + doctor + ", paciente=" + paciente + "]";
	}
	
}
