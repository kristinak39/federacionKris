package entidades;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import utils.Utilidades;

public class Equipo extends Participante {
	private long idEquipo;
	private int anioinscripcion;
	private Manager manager;
	private Atleta[] atletas;

	public Equipo(long id, int anioinscripcion, Manager manager, Atleta[] atletas) {
		super();
		this.idEquipo = id;
		this.anioinscripcion = anioinscripcion;
		this.manager = manager;
		this.atletas = atletas;
	}

	public Equipo(long idParticipante, Equipo e, int dorsal, char calle) {
		super(idParticipante, dorsal, calle);
		this.idEquipo = e.idEquipo;
		this.anioinscripcion = e.anioinscripcion;
		this.manager = e.manager;
		this.atletas = e.atletas;
	}

	@Override
	public long getId() {
		return idEquipo;
	}
	@Override
	public void setId(long id) {
		this.idEquipo = id;
	}
	public int getAnioinscripcion() {
		return anioinscripcion;
	}
	public void setAnioinscripcion(int anioinscripcion) {
		this.anioinscripcion = anioinscripcion;
	}
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	public Atleta[] getAtletas() {
		return atletas;
	}
	public void setAtletas(Atleta[] atletas) {
		this.atletas = atletas;
	}

	//Ejercicio 3
	@Override
	public String toString() {
		String ret = "";
		ret+= "EQ"+idEquipo + ". de " + manager.getPersona().getNombre() + " (" + manager.getDireccion()+") " + atletas.length + " componentes en el equipo:\n";
		for(Atleta a: atletas) {
			ret += a.getId()+". " + a.getPersona().getNombre() + "(" + a.getPersona().getFechaNac().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+ ") "
					+ " Datos fÃ­sicos:\t"+ a.getPeso()+ "Kgs.\t" + a.getAltura()+"m.\n";
		}
		ret += "Valido durante el " + anioinscripcion;
		return ret;
	}

	
	

	public static Equipo nuevoEquipo() {
		Equipo ret = null;
		Scanner in;
		int id = -1;
		int anioInscripcion = -1;
		Manager manager = null;
		boolean valido = false;
		do {
			System.out.println("Introduzca el id del nuevo equipo:");
			in = new Scanner(System.in);
			id = in.nextInt();
			valido = Validaciones.validarId(id);
			if (!valido)
				System.out.println("ERROR: Valor incorrecto para el identificador.");
			else
				valido = true;
		} while (!valido);
		
		valido = false;
		do {
			System.out.println("Introduzca el anio de inscripcion del equipo:");
			in = new Scanner(System.in);
			anioInscripcion = in.nextInt();
			valido = Validaciones.validarAnio(anioInscripcion);
			if (!valido)
				System.out.println("ERROR: Valor incorrecto para el año de inscripcion del equipo.");
			else
				valido = true;
		} while (!valido);
		
		//creamos al Manager
		manager = Manager.nuevoManager();
		///////////////////
		
		valido = false;
		int natletas = 0;
		do {
			System.out.println("Introduzca el numero de atletas del nuevo equipo:");
			in = new Scanner(System.in);
			natletas = in.nextInt();
			if (natletas > 0)
				valido = true;
			else
				System.out.println("Valor incorrecto para el numero de atletas.");
		} while (!valido);

		System.out.println("Introduzca ahora los datos de los " + natletas + " atletas del nuevo equipo:");
		Atleta[] atletas = new Atleta[natletas];
		for (int i = 0; i < natletas; i++) {
			System.out.println("Introduzca los datos del " + (i + 1) + "º atleta del nuevo equipo.");
			atletas[i] = Atleta.nuevaAtleta();
		}
		ret = new Equipo(id, anioInscripcion, manager, atletas);
		return ret;
	}


}

