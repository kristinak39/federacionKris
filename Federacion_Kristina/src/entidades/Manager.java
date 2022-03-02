package entidades;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import utils.Datos;

public class Manager {
	private long id;
	private String telefono;
	private String direccion;

	private DatosPersona persona;

	public Manager(long id, String telefono, String direccion) {
		super();
		this.id = id;
		this.telefono = telefono;
		this.direccion = direccion;
		this.persona = Datos.buscarPersonaPorId(id);
	}

	public Manager(long id, String telefono, String direccion, DatosPersona dp) {
		super();
		this.id = id;
		this.telefono = telefono;
		this.direccion = direccion;
		this.persona = dp;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public DatosPersona getPersona() {
		return this.persona;
	}

	public static Manager nuevoManager() {
		Manager ret = null;
		Scanner in;
		long id = -1;
		DatosPersona persona;
		String telefono;
		String direccion;
		boolean valido = false;
		do {
			System.out.println("Introduzca el id del manager:");
			in = new Scanner(System.in);
			id = in.nextLong();
			if (id > 0)
				valido = true;
		} while (!valido);
		valido = false;
		do {
			System.out.println("Introduzca el telefono del manager:");
			in = new Scanner(System.in);
			telefono = in.nextLine();
			if (telefono.length() > 3)
				valido = true;
		} while (!valido);
		return ret;
	}

	@Override
	public String toString() {
		return "idManager" + persona.getId() + "documentacion" + persona.getNifnie() + " del año "
				+ persona.getFechaNac() + "Tfno:" + persona.getTelefono();
	}

	public String data() {
		return this.getPersona().getId() + "|" + this.getPersona().getNombre() + "|"
				+ this.getPersona().getNifnie().mostrar() + "|" + this.getPersona().getFechaNac() + "|"
				+ this.getPersona().getTelefono() + "|" + this.id + "|" + this.telefono + "|" + this.direccion;

	}

	private static void exportarManagers(Manager[] managers) {
		String path = "managers.txt";
		File fichero = new File(path);
		FileWriter escritor = null;
		PrintWriter buffer = null;
		try {
			try {
				escritor = new FileWriter(fichero, false);
				buffer = new PrintWriter(escritor);
				for (Manager a : managers) {
					buffer.println(a.data());
				}
			} finally {
				if (buffer != null) {
					buffer.close();
				}
				if (escritor != null) {
					escritor.close();
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Se ha producido una FileNotFoundException" + e.getMessage());
		} catch (IOException e) {
			System.out.println("Se ha producido una IOException" + e.getMessage());
		} catch (Exception e) {
			System.out.println("Se ha producido una Exception" + e.getMessage());
		}
	}

	public void importarData() throws IOException {
		File f1 = new File("manager.txt");
		FileReader fr = new FileReader(f1);
		int c;
		String dato = null;
		while ((c = fr.read()) != -1) {
			dato += (char) c;
			if (c == 10) {
				String id = null;
				int num = 0;
				for (int i = 0; i < dato.length(); i++) {
					if (dato.charAt(i) != '|') {
						id += dato.charAt(i);

					} else {
						for (Manager a : Datos.MANAGERS) {
							if (a.getId() == Long.parseLong(id)){
								DateTimeFormatter formater=DateTimeFormatter.ofPattern("dd/MM/YYYY");
								System.out.println("Señor/a" +getPersona().getNombre()+"con NIE:NIF"+a.getPersona().getNifnie()
										+ "nacido el" + a.getPersona().getFechaNac()+"representa al equipo" );
								break;
								
							}
						}
					}
				}
			}
		}
	}
}
