package principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import entidades.*;
import utils.*;

public class Principal4 {

	public static void main(String[] args) {

		Datos.cerrarResultados();
		System.out.println("INICIO");

		Scanner in;
		int elecc = -1;
		Rol rol; // Examen 4 Ejercicio 3A
		boolean correcto = false;
		while (true) {
			System.out.println("Bienvenido al programa de gestiÃ³n de la FEDERACIÃ“N DEPORTIVA:");
			do {
				System.out.println("Seleccione el id del tipo de usuario o pulse 0 para SALIR:");
				int i = 1;
				for (Rol r : Rol.values()) {
					System.out.println(i + " " + r.getNombre());
					i++;
				}
				in = new Scanner(System.in);
				elecc = in.nextInt();
				if (elecc == 0) {
					System.out.println("Â¿EstÃ¡ seguro de que desea SALIR?");
					if (Utilidades.leerBoolean()) {
						System.out.println("Â¡ADIOS!");
						return;
					}
				}
				if (elecc >= 1 && elecc <= Rol.values().length)
					correcto = true;
				else
					System.out.println("Â¡Valor invalido para el ROL seleccionado!");
			} while (!correcto);
			rol = Rol.values()[elecc - 1];

			Credenciales cred; // Examen 4 Ejericicio 3B
			boolean login = false;
			switch (rol.ordinal()) {
			case 0: // Rol.DIRECTIVA;
			case 1: // Rol.MANAGER
			case 2: // Rol.ATLETA;
			case 3: // Rol.COLEGIADO;
			case 4: // Rol.ADMIN;
				do {
					System.out.println("Introduzca sus credenciales de acceso.");
					System.out.println("Introduzca su nombre de usuario:");
					String usuario, password;
					usuario = in.next();
					System.out.println("Introduzca su contraseÃ±a:");
					password = in.next();
					cred = new Credenciales(usuario, password);
					login = login(cred);
					if (!login)
						System.out.println("ERROR: Usuario o password incorrectos.");
					else
						System.out.println("Login correcto con rol " + rol.getNombre());
				} while (!login);
				break;
			case 5: // Rol.INVITADO;
				System.out.println("Ha ingresado con el rol " + rol.getNombre());
			}

			mostrarMenu(rol);
		}

	} // Final del main

	// Examen 3 Ejercicio 2 - Examen 4 Ejercicio 3C
	private static void mostrarMenu(Rol rol) {
		int elecc = -1;
		Scanner in = new Scanner(System.in);
		boolean valido = false;

		switch (rol.ordinal()) {
		case 0: // Rol.DIRECTIVA;
			do {
				mostrarMenuDirectiva();
				System.out.println("Seleccione una de las opciones anteriores.");
				in = new Scanner(System.in);
				elecc = in.nextInt();
				if (elecc >= 0 && elecc <= 2) {
					valido = true;
					mostrarSubmenuDirectiva(elecc);
				} else
					System.out.println("Â¡Valor seleccionado no vÃ¡lido!\n");
			} while (!valido || elecc != 0);
			System.out.println("Ha elegido VOLVER.");
			break;
		case 1: // Rol.MANAGER;
			do {
				mostrarMenuManager();
				System.out.println("Seleccione una de las opciones anteriores.");
				in = new Scanner(System.in);
				elecc = in.nextInt();
				if (elecc >= 0 && elecc <= 2) {
					valido = true;
					mostrarSubmenuManager(elecc);
				} else
					System.out.println("Â¡Valor seleccionado no vÃ¡lido!\n");
			} while (!valido || elecc != 0);
			System.out.println("Ha elegido VOLVER");
			break;
		case 2: // Rol.ATLETA
			do {
				mostrarMenuAtleta();
				System.out.println("Seleccione una de las opciones anteriores.");
				in = new Scanner(System.in);
				elecc = in.nextInt();
				if (elecc >= 0 && elecc <= 2) {
					valido = true;
					mostrarSubmenuAtleta(elecc);
				} else
					System.out.println("Â¡Valor seleccionado no vÃ¡lido!\n");
			} while (!valido || elecc != 0);
			System.out.println("Ha elegido VOLVER.");
			break;
		case 3: // Rol.COLEGIADO;
			do {
				mostrarMenuColegiado();
				System.out.println("Seleccione una de las opciones anteriores.");
				in = new Scanner(System.in);
				elecc = in.nextInt();
				if (elecc >= 0 && elecc <= 2) {
					valido = true;
					mostrarSubmenuColegiado(elecc);
				} else
					System.out.println("Â¡Valor seleccionado no vÃ¡lido!\n");
			} while (!valido || elecc != 0);
			System.out.println("Ha elegido VOLVER.");
			break;
		case 4: // Rol.ADMIN;
			do {
				mostrarMenuAdmin();
				System.out.println("Seleccione una de las opciones anteriores.");
				in = new Scanner(System.in);
				elecc = in.nextInt();
				if (elecc >= 0 && elecc <= 4) {
					valido = true;
					mostrarSubmenuAdmin(elecc);
				} else
					System.out.println("Â¡Valor seleccionado no vÃ¡lido!\n");
			} while (!valido || elecc != 0);
			System.out.println("Ha elegido VOLVER.");
			break;
		case 5: // Rol.INVITADO;
			do {
				mostrarMenuInvitado();
				System.out.println("Seleccione una de las opciones anteriores.");
				in = new Scanner(System.in);
				elecc = in.nextInt();
				if (elecc >= 0 && elecc <= 2) {
					valido = true;
					mostrarSubmenuInvitado(elecc);
				} else
					System.out.println("Â¡Valor seleccionado no vÃ¡lido!\n");
			} while (!valido || elecc != 0);
			System.out.println("Ha elegido VOLVER.");
			break;
		default:
		}

	}

	private static void mostrarSubmenuDirectiva(int elecc) {
		Scanner in = new Scanner(System.in);
		int subelecc = -1;
		boolean valido = false;
		System.out.println("SUBMenÃºs de la DIRECTIVA.");
		switch (elecc) {
		case 1:
			System.out.println("Ha seleccionado GESTIÃ“N de MEDALLAS.");
			do {
				mostrarMenuGestionMedallas();
				System.out.println("Seleccione una de las opciones anteriores.");
				in = new Scanner(System.in);
				subelecc = in.nextInt();
				if (subelecc >= 0 && subelecc <= 1) {
					valido = true;
					mostrarSubmenuGestionMedallas(subelecc);
				} else
					System.out.println("Â¡Valor seleccionado no vÃ¡lido!\n");
			} while (!valido || subelecc != 0);
			System.out.println("Ha elegido VOLVER.");
			break;
		case 2:
			System.out.println("Ha seleccionado GESTIÃ“N de COMPETICIONES y de PRUEBAS.");
			break;
		default:
		}

		System.out.println("Volviendo al menÃº de la DIRECTIVA...");
	}

	private static void mostrarMenuGestionMedallas() {
		System.out.println("MenÃº de GESTIÃ“N de MEDALLAS.");
		System.out.println("Seleccione una de las siguientes opciones:");
		System.out.println("1. Nueva Medalla\n" + "0. Volver");
	}

	private static void mostrarSubmenuGestionMedallas(int elecc) {
		Scanner in = new Scanner(System.in);
		int subelecc = -1;
		boolean valido = false;
		System.out.println("\nGESTIÃ“N de MEDALLAS.");
		switch (elecc) {
		case 1: // opciÃ³n 1.1.1
			do {
				System.out.println("Ha seleccionado Nueva MEDALLA.");
				System.out.println("Seleccione 1 para ORO, 2 para PLATA o 3 para BRONCE.");
				subelecc = in.nextInt();
				if (subelecc != 1 && subelecc != 2 && subelecc != 3)
					System.out.println("Â¡Valor seleccionado no vÃ¡lido!\n");
				else
					valido = true;
			} while (!valido);
			Metal nuevo;
			if (subelecc == 1) {
				System.out.println("Ha seleccionado Nuevo ORO");
				nuevo = Oro.nuevoOro();
			} else if (subelecc == 2) {
				System.out.println("Ha seleccionado Nueva PLATA");
				nuevo = (Plata) Plata.nuevoPlata();
			} else {
				System.out.println("Ha seleccionado Nuevo BRONCE");
				nuevo = (Bronce) Bronce.nuevoBronce();
			}
			System.out.println("Se ha introducido una nueva medalla correctamente.");
			System.out.println(nuevo);
			break;
//		case 2:  //opciÃ³n 1.1.2
//			System.out.println("Ha seleccionado ");
//			break;
		default:
		}
		System.out.println("Volviendo al menÃº principal de gestiÃ³n de medallas...");

	}

	private static void mostrarSubmenuManager(int elecc) {
		Scanner in = new Scanner(System.in);
		Equipo nuevo;
		int subelecc = -1;
		boolean valido = false;
		switch (elecc) {
		case 1: // opciÃ³n 2.1
			System.out.println("Ha seleccionado CONFORMAR EQUIPO.");
			break;
		case 2: // opciÃ³n 2.2
			System.out.println("Ha seleccionado INSCRIPCION de EQUIPO en PRUEBA.");
			/// Ejercicio 1
			/// Se piden los datos del Equipo que se va a inscribir
			do {
				System.out.println("Introduzca los datos del Equipo:");
				nuevo = Equipo.nuevoEquipo();
				System.out.println("¿Son correctos los datos del equipo introducido?" + nuevo);
				if (valido = Utilidades.leerBoolean()) {
					valido = true;
				}
			} while (!valido);
			/// Se muestran las pruebas por equipo importadas desde el fichero de
			/// caracteres pruebas.txt
			valido = false;
			Prueba[] equipos = new Prueba[256];
			File fichero = new File("pruebas.txt");
			FileReader lector = null;
			BufferedReader buffer = null;
			int i = 0; /// contador de pruebas por equipo
			try {
				try {
					lector = new FileReader(fichero);
					buffer = new BufferedReader(lector);
					String linea;
					while ((linea = buffer.readLine()) != null) {
						String[] campos = linea.split("\\|");
						long idPrueba = Long.valueOf(campos[0]);
						String nombrePrueba = campos[1];
						LocalDate fecha = LocalDate.parse(campos[2], DateTimeFormatter.ofPattern("dd/MM/YYYY"));
						String lugarString = campos[3];
						/// Hay que convertir el String con el lugar a su correspondiente valor de la
						/// enum Lugar
						Lugar lugar = null;
						for (Lugar l : Lugar.values()) {
							if (l.name().equalsIgnoreCase(lugarString)) {
								lugar = l;
							}
						}
						boolean individual = Boolean.valueOf(campos[4]);
						Prueba p = new Prueba(idPrueba, nombrePrueba, fecha, lugar, individual);
						/// Solo se muestran al usuario las pruebas por equipo, que se van guardando
						/// en el array equipo
						if (!p.isIndividual()) {
							System.out.println("" + p);
							equipos[i] = p;
							i++;
						}
					}
				} finally {
					if (buffer != null) {
						buffer.close();
					}
					if (lector != null) {
						lector.close();
					}
				}
			} catch (FileNotFoundException e) {
				System.out.println("Se ha producido una FileNotFoundException" + e.getMessage());
			} catch (IOException e) {
				System.out.println("Se ha producido una IOException" + e.getMessage());
			} catch (Exception e) {
				System.out.println("Se ha producido una Exception" + e.getMessage());
			}
			/// Se pide al usuario que elija una de las pruebas y se comprueba que es un
			/// valor correcto
			Prueba pruebaSelecc = null;
			do {
				System.out.println("Introduzca el id de la prueba en que desea inscribirse:");
				subelecc = in.nextInt();
				for (int j = 0; j < i; j++) {
					if (((Prueba) equipos[j]).getId() == subelecc) {
						/// El valor introducido es alguno de los idPrueba por equipo
						pruebaSelecc = equipos[j];
						valido = true;
						break;
					}
				}
				if (!valido) {
					System.out.println("El valor " + subelecc
							+ " no es valido. Se le mostraran de nuevo las pruebas por equipo:");
					for (Prueba aux : equipos) {
						if (aux != null) {
							System.out.println("" + aux);
						}
					}
				} else {
					System.out.println("Se ha elegido la prueba de id:" + subelecc + ". ¿Es correcto?");
					if (valido = Utilidades.leerBoolean()) {
						break; /// confirmacion de idPrueba seleccionado correcto
					} else {
						System.out.println("Se le mostraran de nuevo las pruebas por equipo:");
						for (Prueba aux : equipos) {
							if (aux != null) {
								System.out.println("" + aux);
							}
						}
					}
				}
			} while (!valido);
			/// Ahora se crea el fichero con la inscripcion del equipo en la prueba
			valido = false;
			String path = "inscrip_" + pruebaSelecc.getId() + "_" + nuevo.getManager().getId() + ".dat";
			try {
				FileOutputStream ficheroInscrip = new FileOutputStream(path, false);
				ObjectOutputStream escritor = new ObjectOutputStream(ficheroInscrip);
				escritor.writeObject((Equipo) nuevo);
				escritor.writeObject((Long) pruebaSelecc.getId());
				LocalDateTime ahora = LocalDateTime.now();
				escritor.writeObject((LocalDateTime) ahora);
				escritor.flush();
				valido = true;
				escritor.close();
			} catch (FileNotFoundException e) {
				System.out.println("Se ha producido una FileNotFoundException" + e.getMessage());
			} catch (IOException e) {
				System.out.println("Se ha producido una IOException" + e.getMessage());
			} catch (Exception e) {
				System.out.println("Se ha producido una Exception" + e.getMessage());
			}
			/// Si el fichero se creo exitosamente, se lee su contenido y se muestra el
			/// mensaje
			if (!valido) {
				System.out.println("ERROR: No se creo el fichero con la inscripcion.");
			} else {
				try {
					File ficheroLeido = new File(path);
					FileInputStream ficheroInscrip = new FileInputStream(ficheroLeido);
					ObjectInputStream lectorFichInsc = new ObjectInputStream(ficheroInscrip);
					Equipo equipoLeido = (Equipo) lectorFichInsc.readObject();
					Long idPruebaLeido = (Long) lectorFichInsc.readObject();
					LocalDateTime fechahoraLeida = (LocalDateTime) lectorFichInsc.readObject();
					System.out.println("Se ha creado el fichero " + path + " a "
							+ fechahoraLeida.format(DateTimeFormatter.ofPattern("dd/MM/YY hh:mm:ss"))
							+ ", en el que el equipo " + equipoLeido.getId() + " representado por  "
							+ equipoLeido.getManager().getId() + " queda" + "inscrito en la prueba "
							+ idPruebaLeido + " de nombre " + pruebaSelecc.getNombre() + " a celebrar en "
							+ pruebaSelecc.getLugar().getNombre() + " el dia "
							+ pruebaSelecc.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/YYYY")) + ".");
					valido = true;
					lectorFichInsc.close();
				} catch (FileNotFoundException e) {
					System.out.println("Se ha producido una FileNotFoundException" + e.getMessage());
				} catch (IOException e) {
					System.out.println("Se ha producido una IOException" + e.getMessage());
				} catch (Exception e) {
					System.out.println("Se ha producido una Exception" + e.getMessage());
				}
			}
			break;
		default:
		}
		System.out.println("Volviendo al menu de MANAGERS...\n\n");
	}

	private static void mostrarSubmenuAtleta(int elecc) {
		Scanner in = new Scanner(System.in);
		int subelecc = -1;
		boolean valido = false;
		switch (elecc) {
		case 1: //// opciÃ³n 3.1
			System.out.println("Ha seleccionado FEDERARSE (Nuevo ATLETA).");
			break;
		case 2: // opciÃ³n 3.2
			System.out.println("Ha seleccionado INSCRIPCIÃ“N de ATLETA en PRUEBA..");
			break;
		default:
		}
		System.out.println("Volviendo al menÃº de ATLETAS...\n\n");
	}

	private static void mostrarSubmenuColegiado(int elecc) {
		Scanner in = new Scanner(System.in);
		int subelecc = -1;
		boolean valido = false;
		switch (elecc) {
		case 1: //// opciÃ³n 4.1
			System.out.println("Ha seleccionado Nuevo COLEGIADO.");
			Colegiado nuevo = Colegiado.nuevoColegiado();
			System.out.println("Se ha creado correctamente el nuevo colegiado:" + nuevo);
			break;
		case 2: //// opciÃ³n 4.2
			System.out.println("Ha seleccionado INTRODUCIR RESULTADOS de PRUEBA..");
			break;
		default:
		}
		System.out.println("Volviendo al menÃº de COLEGIADOS...\n\n");
	}

	private static void mostrarSubmenuAdmin(int elecc) {
		Scanner in = new Scanner(System.in);
		int subelecc = -1;
		boolean valido = false;
		switch (elecc) {
		case 1:
			System.out.println("Ha seleccionado GestiÃ³n de medallas, de competiciones y de pruebas.");
			break;
		case 2:
			System.out.println("Ha seleccionado GestiÃ³n de equipos.");
			break;
		case 3:
			System.out.println("Ha seleccionado GestiÃ³n de atletas.");
			break;
		case 4:
			System.out.println("Ha seleccionado GestiÃ³n de arbitrajes y resultados.");
			break;
		default:
		}
		System.out.println("Volviendo al menÃº de ADMINISTRADORES...\n\n");
	}

	private static void mostrarSubmenuInvitado(int elecc) {
		Scanner in = new Scanner(System.in);
		int subelecc = -1;
		boolean valido = false;
		System.out.println("SUBMenÃºs para INVITADOS.");
		System.out.println("Volviendo al menÃº principal...\n\n");
	}

	private static void mostrarMenuDirectiva() {
		System.out.println("MenÃº de la DIRECTIVA.");
		System.out.println("Seleccione una de las siguientes opciones:");
		System.out.println("1. GestiÃ³n de medallas\n" + "2. GestiÃ³n de competiciones y pruebas.\n" + "0. Volver");
	}

	private static void mostrarMenuManager() {
		System.out.println("MenÃº para los MÃ�NAGERS.");
		System.out.println("Seleccione una de las siguientes opciones:");
		System.out.println("1. Conformar equipo\n" + "2. Inscripcion de equipo en prueba.\n" + "0. Volver");
	}

	private static void mostrarMenuAtleta() {
		System.out.println("MenÃº para los ATLETAS.");
		System.out.println("Seleccione una de las siguientes opciones:");
		System.out.println("1. Federarse (nuevo Atleta)\n" + "2. Inscrcipcion de atleta en prueba.\n" + "0. Volver");

	}

	private static void mostrarMenuColegiado() {
		System.out.println("MenÃº para los COLEGIADOS.");
		System.out.println("Seleccione una de las siguientes opciones:");
		System.out.println("1. Nuevo Colegiado\n" + "2. Introducir resultados de prueba.\n" + "0. Volver");
	}

	private static void mostrarMenuAdmin() {
		System.out.println("MenÃº para los ADMINISTRADORES.");
		System.out.println("Seleccione una de las siguientes opciones:");
		System.out.println("1. GestiÃ³n de medallas, de competiciones y de pruebas\n" + "2. GestiÃ³n de equipos.\n"
				+ "3. GestiÃ³n de atletas.\n" + "4. GestiÃ³n de arbitrajes y resultados.\n" + "0. Volver");
	}

	private static void mostrarMenuInvitado() {
		System.out.println("MenÃº para los INVITADOS.");
		System.out.println("Seleccione una de las siguientes opciones:");
		System.out.println("" + "0. Volver");
	}

	private static boolean login(Credenciales cred) {
		// Por el momento siempre devolverÃ¡ true
		return true;
	}



public static void exportarColegiado() throws IOException{
	File f1=new File("colegiadosjunior.dat");
	if(!f1.exists()) {
		f1.createNewFile();
		}
	File f2=new File("colegiadosjunior.dat");
	if(!f2.exists()) {
		f2.createNewFile();
		}
	File f3=new File("colegiadosjunior.dat");
	if(!f3.exists()) {
		f3.createNewFile();
		}
	FileWriter FW=new FileWriter(f1);
	PrintWriter FW1 =new PrintWriter(f2);
}
}






