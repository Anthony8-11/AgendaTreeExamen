package org.example.Servicio;
import org.example.Modelo.Contacto;
import org.example.Modelo.NodoContacto;

import java.io.*;
import java.time.LocalDate;

public class Agenda {
    private NodoContacto raiz;

    public Agenda() {
        this.raiz = null;
    }

    public void agregarContacto(String nombre, long telefono, String correoElectronico, LocalDate fechaNacimiento) {
        Contacto nuevoContacto = new Contacto(nombre, telefono, correoElectronico, fechaNacimiento);
        if (this.raiz == null) {
            this.raiz = new NodoContacto(nuevoContacto);
        } else {
            this.insertar(this.raiz, nuevoContacto);
        }
    }

    private void insertar(NodoContacto padre, Contacto contacto) {
        if (contacto.getNombre().compareTo(padre.getContacto().getNombre()) < 0) {
            if (padre.getIzdo() == null) {
                padre.setIzdo(new NodoContacto(contacto));
            } else {
                insertar(padre.getIzdo(), contacto);
            }
        } else if (contacto.getNombre().compareTo(padre.getContacto().getNombre()) > 0) {
            if (padre.getDcho() == null) {
                padre.setDcho(new NodoContacto(contacto));
            } else {
                insertar(padre.getDcho(), contacto);
            }
        }
    }

    public Contacto buscarContacto(String valor, String tipoBusqueda) {
        return buscar(this.raiz, valor, tipoBusqueda);
    }

    private Contacto buscar(NodoContacto nodo, String valor, String tipoBusqueda) {
        if (nodo == null) {
            return null;
        }
        switch (tipoBusqueda) {
            case "nombre":
                if (valor.equals(nodo.getContacto().getNombre())) {
                    return nodo.getContacto();
                } else if (valor.compareTo(nodo.getContacto().getNombre()) < 0) {
                    return buscar(nodo.getIzdo(), valor, tipoBusqueda);
                } else {
                    return buscar(nodo.getDcho(), valor, tipoBusqueda);
                }
            case "telefono":
                String telefono = String.valueOf(nodo.getContacto().getTelefono());
                if (valor.equals(telefono)) {
                    return nodo.getContacto();
                } else if (valor.compareTo(telefono) < 0) {
                    return buscar(nodo.getIzdo(), valor, tipoBusqueda);
                } else {
                    return buscar(nodo.getDcho(), valor, tipoBusqueda);
                }
            case "correo":
                if (valor.equals(nodo.getContacto().getCorreoElectronico())) {
                    return nodo.getContacto();
                } else if (valor.compareTo(nodo.getContacto().getCorreoElectronico()) < 0) {
                    return buscar(nodo.getIzdo(), valor, tipoBusqueda);
                } else {
                    return buscar(nodo.getDcho(), valor, tipoBusqueda);
                }
            default:
                return null;
        }
    }

    //metodo buscar con objeto Contacto
    public Contacto buscarContacto(Contacto contacto) {
        if (contacto.getNombre() != null) {
            return buscar(this.raiz, contacto.getNombre(), "nombre");
        } else if (contacto.getTelefono() != 0) {
            return buscar(this.raiz, String.valueOf(contacto.getTelefono()), "telefono");
        } else if (contacto.getCorreoElectronico() != null) {
            return buscar(this.raiz, contacto.getCorreoElectronico(), "correo");
        } else {
            return null;
        }
    }

//    public Contacto buscarContacto(String nombre) {
//        return buscar(this.raiz, nombre);
//    }
//
//    private Contacto buscar(NodoContacto nodo, String nombre) {
//        if (nodo == null) {
//            return null;
//        }
//        if (nombre.equals(nodo.getContacto().getNombre())) {
//            return nodo.getContacto();
//        } else if (nombre.compareTo(nodo.getContacto().getNombre()) < 0) {
//            return buscar(nodo.getIzdo(), nombre);
//        } else {
//            return buscar(nodo.getDcho(), nombre);
//        }
//    }

    public void eliminarContacto(String nombre) {
        this.raiz = eliminar(this.raiz, nombre);
    }

    private NodoContacto eliminar(NodoContacto nodo, String nombre) {
        if (nodo == null) {
            return null;
        }
        if (nombre.compareTo(nodo.getContacto().getNombre()) < 0) {
            nodo.setIzdo(eliminar(nodo.getIzdo(), nombre));
        } else if (nombre.compareTo(nodo.getContacto().getNombre()) > 0) {
            nodo.setDcho(eliminar(nodo.getDcho(), nombre));
        } else {
            if (nodo.getIzdo() == null) {
                return nodo.getDcho();
            } else if (nodo.getDcho() == null) {
                return nodo.getIzdo();
            }

            NodoContacto temp = minValorNodo(nodo.getDcho());
            nodo.getContacto().setTelefono(temp.getContacto().getTelefono());
            nodo.getContacto().setNombre(temp.getContacto().getNombre());
            nodo.setDcho(eliminar(nodo.getDcho(), temp.getContacto().getNombre()));
        }
        return nodo;
    }

    private NodoContacto minValorNodo(NodoContacto nodo) {
        NodoContacto actual = nodo;
        while (actual.getIzdo() != null) {
            actual = actual.getIzdo();
        }
        return actual;
    }

    public void mostrarContactos() {
        inOrden(this.raiz);
    }

    private void inOrden(NodoContacto nodo) {
        if (nodo != null) {
            inOrden(nodo.getIzdo());
            System.out.println("Nombre: " + nodo.getContacto().getNombre() + ", Teléfono: " + nodo.getContacto().getTelefono());
            inOrden(nodo.getDcho());
        }
    }

    //Serializar y deserializar
    public void guardar(String filename) {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static Agenda cargar(String filename) {
        Agenda agenda = null;
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            agenda = (Agenda) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return null;
        } catch (ClassNotFoundException c) {
            System.out.println("Agenda class not found");
            c.printStackTrace();
            return null;
        }
        return agenda;
    }

}