package org.example;

import org.example.Modelo.Contacto;
import org.example.Servicio.Agenda;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();

        // Agregar contactos
        agenda.agregarContacto("Mario", 123456789, "mariolocal@gmail.com", LocalDate.of(2034,5,2));
        agenda.agregarContacto("Link", 987654321, "linklocal@gmail.com", LocalDate.of(2034,5,2));
        agenda.agregarContacto("Zelda", 987654321, "zeldalocal@gmail.com", LocalDate.of(2034,5,2));
        agenda.agregarContacto("Peach", 987654321, "peachlocal@gmail.com", LocalDate.of(2034,5,2));
        agenda.agregarContacto("Bowser", 987654321, "bowserlocal@gmail.com", LocalDate.of(2034,5,2));
        agenda.agregarContacto("Luigi", 987654321, "luigillcal@gmail.com", LocalDate.of(2034,5,2));
        agenda.agregarContacto("Toad", 987654321, "toadlocal@gmail.com", LocalDate.of(2034,5,2));
        agenda.agregarContacto("Yoshi", 987654321, "yoshilocal@gmail.com", LocalDate.of(2034,5,2));
        agenda.agregarContacto("Donkey Kong", 987654321, "donkeylocal@gmail.com", LocalDate.of(2034,5,2));
        agenda.agregarContacto("Diddy Kong", 987654321, "diddylocal@gmail.com", LocalDate.of(2034,5,2));
        agenda.agregarContacto("Dixie Kong", 987654321, "dixielocal@gmail.com", LocalDate.of(2034,5,2));
        agenda.agregarContacto("Wario", 987654321, "wariolocal@gmail.com", LocalDate.of(2034,5,2));
        agenda.agregarContacto("Waluigi", 987654321, "waluigilocal@gmail.com", LocalDate.of(2034,5,2));
        agenda.agregarContacto("Rosalina", 987654321, "rosalinalocal@gmail.com", LocalDate.of(2034,5,2));
        agenda.agregarContacto("Toadette", 987654321, "toadettelocal@gmail.com", LocalDate.of(2034,5,2));


//        // Mostrar contactos
//        System.out.println("Contactos en la agenda:");
//        agenda.mostrarContactos();

        // Buscar un contacto por nombre, telefono o correo electrónico
        System.out.println("\nBuscando el contacto de Link:");
        Contacto contacto = agenda.buscarContacto("Link", "nombre");
        if (contacto != null) {
            System.out.println("Nombre: " + contacto.getNombre() + ", Teléfono: " + contacto.getTelefono());
        } else {
            System.out.println("Contacto no encontrado.");
        }

        // Buscar un contacto usando un objeto Contacto
        System.out.println("\nBuscando el contacto de Mario usando un objeto Contacto:");
        Contacto contactoBusqueda = new Contacto("Mario", 123456789, "mariolocal@gmail.com", LocalDate.of(2012,9,23));
        contacto = agenda.buscarContacto(contactoBusqueda);
        if (contacto != null) {
            System.out.println("Nombre: " + contacto.getNombre() + ", Teléfono: " + contacto.getTelefono());
        } else {
            System.out.println("Contacto no encontrado.");
        }

        // Guardar la agenda en un archivo
        System.out.println("\nGuardando la agenda en un archivo.");
        agenda.guardar("agenda.ser");

        // Cargar la agenda desde un archivo
        System.out.println("\nCargando la agenda desde un archivo.");
        Agenda loadedAgenda = Agenda.cargar("agenda.ser");
        if (loadedAgenda != null) {
            System.out.println("Contactos en la agenda cargada:");
            loadedAgenda.mostrarContactos();
        } else {
            System.out.println("No se pudo cargar la agenda desde el archivo.");
        }

//        // Eliminar un contacto
//        System.out.println("\nEliminando el contacto de Peach.");
//        agenda.eliminarContacto("Peach");
//
//        // Mostrar contactos después de la eliminación
//        System.out.println("Contactos en la agenda después de eliminar a Alice:");
//        agenda.mostrarContactos();
    }
}