package entidades;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Palmares<T extends Metal, S extends Participante> {

	private long id;
	private T medalla;
	private S participante;
	private Prueba prueba;
	private String observaciones;

	public Palmares(long id, T medalla, S participante, Prueba prueba, String observaciones) {
		super();
		this.id = id;
		this.medalla = medalla;
		this.participante = participante;
		this.prueba = prueba;
		this.observaciones = observaciones;
	}

	public Palmares() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public T getMedalla() {
		return medalla;
	}

	public void setMedalla(T medalla) {
		this.medalla = medalla;
	}

	public S getParticipante() {
		return participante;
	}

	public void setParticipante(S participante) {
		this.participante = participante;
	}

	public Prueba getPrueba() {
		return prueba;
	}

	public void setPrueba(Prueba prueba) {
		this.prueba = prueba;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public String mostrartoString() {
		String ret= "Palmares [id=" + id + ", medalla=" + medalla + ", + "+ prueba.getNombre()+", "+ prueba.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +", "+prueba.getLugar()
		 +"participante="+ participante.getDorsal()+participante.getCalle();


return ret;
	}

}
