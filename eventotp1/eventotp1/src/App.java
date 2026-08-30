import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int id = 1;
        boolean esGratuito = false;
        boolean continuar = true;
        //se crea la lista de estudiantes//
        System.out.println("REGISTRO DE ESTUDIANTES: ");
        List<Estudiante> estudiantes = new ArrayList<>();
        while (continuar){
            //pedimos los datos con variables locales
            System.out.println("Ingrese el legajo del estudiante");
            String legajo = scanner.nextLine();
            System.out.println("Ingrese el nombre del estudiante");
            String nombre = scanner.nextLine();
            //agregamos un Estudiante (constructor de la clase Estudiante) con add en el arraylist estudiantes con los datos pedidos
            estudiantes.add(new Estudiante(legajo, nombre));
            System.out.println("Desea cargar otro estudiante");
            String respuesta = scanner.nextLine().trim().toLowerCase();
            if(respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí")){
                continuar = true;
            } else {
                continuar = false;
            }
        }
        //Construimos Eventos
        System.out.println("\n\nREGISTRO DE EVENTOS: ");
        continuar = true;
        while (continuar){
            System.out.println("Ingrese un titulo para el evento");
            String titulo = scanner.nextLine();
            System.out.println("¿El evento es gratuito? S/N");
            String respuesta = scanner.nextLine().trim().toLowerCase();
            if (respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí")) {
                esGratuito = true;
            } else {
                esGratuito = false;
            }
            // Solo preguntamos el costo si NO es gratuito
            double costoBase = 0;
            if (!esGratuito) {
                System.out.println("Ingrese el costo base");
                costoBase = scanner.nextDouble();
                scanner.nextLine();
            }
            EventoUniversitario evento = new EventoUniversitario("EVT-" +id,titulo,costoBase,esGratuito);
            id++;
            //creamos y asignamos sala al evento
            System.out.println("Ingrese el nombre de la sala donde se realizará el evento");
            String nombreSala= scanner.nextLine();
            Sala sala = new Sala(id, nombreSala);
            evento.asignarSala(sala);
            /* Se crean las actividades del evento */
            System.out.println("\n\nREGISTRO DE ACTIVIDADES PARA EL EVENTO " + evento.getTitulo());
            int idActividad=1;
            while (continuar){
                System.out.println("Ingrese el tipo de actividad: ");
                String tipoActividad= scanner.nextLine().trim().toLowerCase();
                System.out.println("Ingrese el título de la actividad: ");
                String tituloActividad= scanner.nextLine();
                System.out.println("Ingrese el cupo máximo de estudiantes admitidos para la actividad: ");
                int cupoMaximo = scanner.nextInt();
                scanner.nextLine(); //Se consume la linea.
                evento.crearActividad(id,tituloActividad, cupoMaximo,tipoActividad); //aca llamamos para crear la actividad
                System.out.println("Desea crear otra actividad para el  evento " + evento.getTitulo() + " S/N?");
                respuesta = scanner.nextLine().trim().toLowerCase();
                continuar  = (respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí")) ? true : false;
                ++idActividad;
            }
            // Se inscriben estudiantes en actividades
            System.out.println("\n\nINSCRIPCION DE ESTUDIANTES EN ACTIVIDADES DEL  EVENTO " + evento.getTitulo());
            continuar = true;
            while (continuar){
                System.out.println("Ingrese legajo del estudiante a inscribir: ");
                String legajo = scanner.nextLine();
                System.out.println("Ingrese id de la Actividad: ");
                idActividad = scanner.nextInt();
                scanner.nextLine(); // se consume linea
                //ahora buscamos secuencialmente el legajo para asignarlo a una actividad
                for (Estudiante estudiante: estudiantes){
                    if (estudiante.getLegajo().equals(legajo)){
                        evento.getActividades().get(--idActividad).inscribir(estudiante); //el -- es porque arranca en 0 y nosotros lo inicializamos en 1
                    }
                }
                System.out.println("Desea generar otra inscripción  S/N?");
                respuesta = scanner.nextLine().trim().toLowerCase();
                continuar  = (respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí")) ? true : false;
            }
            System.out.println("\n\n DATOS DEL EVENTO");
            evento.mostrarDatos();
            System.out.println("\n\nDesea crear otro evento  S/N?");
            respuesta = scanner.nextLine().trim().toLowerCase();
            continuar  = (respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí")) ? true : false;
        }
        System.out.println("\nCantidad total de eventos creados: " + EventoUniversitario.getCantidadEventos());
    }
    }
