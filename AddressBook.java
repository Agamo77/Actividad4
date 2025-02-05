import java.io.*;
import java.util.*;

// Iniciamos creando la clase y su atributo HashMap contactos
public class AddressBook {
    private HashMap<String, String> contactos;

    // Creamos el constructor para inicializar el atributo
    public AddressBook() {
        contactos = new HashMap<>();
    }

    // Metodo load para cargar los contactos desde el csv
    public void load(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] contact = line.split(",");
                if (contact.length == 2) {
                    contactos.put(contact[0], contact[1]);
                }
            }
            System.out.println("Contactos Inicializados! ");
        } catch (IOException e) {
            System.out.println("Error al inicializar contactos: " + e.getMessage());
        }
    }

    // Metodo save para guardar los contactos en el csv
    public void save(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, String> entry : contactos.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
            System.out.println("Contactos guardados!");
        } catch (IOException e) {
            System.out.println("Error al guardar contactos: " + e.getMessage());
        }
    }

    // Metodo para listar todos los contactos del csv
    public void list() {
        if (contactos.isEmpty()) {
            System.out.println("No se encontraron contactos guardados.");
        } else {
            System.out.println("Contactos:");
            for (Map.Entry<String, String> entry : contactos.entrySet()) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
            }
        }
    }

    // Creamos metodo para construir contactos nuevos
    public void create(String phoneNumber, String name) {
        contactos.put(phoneNumber, name);
        System.out.println("Contacto nuevo creado: " + phoneNumber + " : " + name);
    }

    // Metodo para borrar contactos
    public void delete(String phoneNumber) {
        if (contactos.containsKey(phoneNumber)) {
            contactos.remove(phoneNumber);
            System.out.println("Contacto borrado: " + phoneNumber);
        } else {
            System.out.println("No existe contacto con ese numero");
        }
    }

    // Construimos un menu para poder dar instrucciones al programa
    public void menu(Scanner scanner) {
        int choice;

        while (true) {
            System.out.println("\nMenú:");
            System.out.println("1. Mostrar contactos");
            System.out.println("2. Crear contacto");
            System.out.println("3. Borrar contacto");
            System.out.println("4. Guardar contactos");
            System.out.println("5. Cargar contactos");
            System.out.println("6. Salir");
            System.out.print("Elige una opción: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (choice) {
                case 1:
                    list();
                    break;
                case 2:
                    System.out.print("Ingresa el numero de telefono: ");
                    String phone = scanner.nextLine();
                    System.out.print("Ingresa el nombre del contacto: ");
                    String name = scanner.nextLine();
                    create(phone, name);
                    break;
                case 3:
                    System.out.print("Ingresa el numero de telefono que quieres borrar: ");
                    String phoneToDelete = scanner.nextLine();
                    delete(phoneToDelete);
                    break;
                case 4:
                    System.out.print("Ingresa el nombre del archivo para guardar: ");
                    String saveFile = scanner.nextLine();
                    save(saveFile);
                    break;
                case 5:
                    System.out.print("Ingrese el nombre del archivo para cargar: ");
                    String loadFile = scanner.nextLine();
                    load(loadFile);
                    break;
                case 6:
                    System.out.println("Terminando Programa");
                    return;
                default:
                    System.out.println("Esa opcion no esta disponible. Intenta otra vez.");
            }
        }
    }

    // Metodo para correr el programa
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Inicializamos el scanner
        AddressBook addressBook = new AddressBook();
        addressBook.menu(scanner);  // Pasamos el scanner al menú
        scanner.close();  // Cerramos el scanner al final de la ejecución
    }
}
