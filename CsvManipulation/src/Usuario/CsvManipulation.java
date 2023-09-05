/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Usuario;

/**
 *
 * @author alexc
 */
import java.io.*;
import java.util.*;

public class CsvManipulation {
    private static final String FILE_PATH = "personas.csv";
    private static final String COPIA_FILE_PATH = "CopiaPersonas.csv";

    public static void main(String[] args) {
        // Crear un archivo .csv si no existe
        crearArchivoSiNoExiste();
        // Mostrar los datos de las personas cuyo apellido empiece por 'a'
        mostrarPersonasConApellidoA();
        // Agregar nuevos datos al archivo .csv
        agregarDatosAlArchivo();
        // Crear una copia del archivo sin las personas cuyo apellido empieza por 'a'
        crearCopiaSinPersonasApellidoA();
        // Borrar el archivo cuya ruta es proporcionada por el usuario
        borrarArchivoPorRutaUsuario();
    }

    private static void crearArchivoSiNoExiste() {
        File archivo = new File(FILE_PATH);
        if (!archivo.exists()) {
            try {
                archivo.createNewFile();
                System.out.println("Se ha creado el archivo " + FILE_PATH);
            } catch (IOException e) {
                System.out.println("No se pudo crear el archivo " + FILE_PATH);
            }
        }
    }

    private static void mostrarPersonasConApellidoA() {
        try (Scanner scanner = new Scanner(new File(FILE_PATH))) {
            System.out.println("Personas con apellido que empieza por 'a':");
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] campos = linea.split(",");
                String apellido = campos[1].trim();
                if (apellido.toLowerCase().startsWith("a")) {
                    System.out.println(linea);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado: " + FILE_PATH);
        }
    }

    private static void agregarDatosAlArchivo() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH, true))) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese apellido: ");
            String apellido = scanner.nextLine();
            System.out.print("Ingrese edad: ");
            String edad = scanner.nextLine();
            System.out.print("Ingrese ciudad: ");
            String ciudad = scanner.nextLine();
            System.out.print("Ingrese teléfono: ");
            String telefono = scanner.nextLine();
            writer.println(nombre + "," + apellido + "," + edad + "," + ciudad + "," + telefono);
            System.out.println("Se han agregado los datos al archivo.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo: " + FILE_PATH);
        }
    }

    private static void crearCopiaSinPersonasApellidoA() {
        try (Scanner scanner = new Scanner(new File(FILE_PATH));
             PrintWriter writer = new PrintWriter(new FileWriter(COPIA_FILE_PATH))) {
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] campos = linea.split(",");
                String apellido = campos[1].trim();
                if (!apellido.toLowerCase().startsWith("a")) {
                    writer.println(linea);
                }
            }
            System.out.println("Se ha creado una copia del archivo sin personas cuyo apellido empieza por 'a'.");
        } catch (IOException e) {
            System.out.println("Error al escribir en el archivo de copia: " + COPIA_FILE_PATH);
        }
    }

    private static void borrarArchivoPorRutaUsuario() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese la ruta del archivo que desea borrar: ");
        String rutaArchivo = scanner.nextLine();

        File archivo = new File(rutaArchivo);
        if (archivo.exists()) {
            if (archivo.delete()) {
                System.out.println("El archivo ha sido borrado exitosamente.");
            } else {
                System.out.println("No se pudo borrar el archivo.");
            }
        } else {
            System.out.println("El archivo no existe.");
        }
    }
}
        