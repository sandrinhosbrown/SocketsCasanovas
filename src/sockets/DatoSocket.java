package sockets;

import java.io.*;

/**
 * Dato para enviar por el socket. Sus atributos son simples y una Clase Atributo
 */
public class DatoSocket implements Serializable
 {
     /** Primer atributo, un int */
     public int edad;
     
     /** Segundo atributo, un String */
     public String nombre;
     private boolean acertado = false;
     public int numero;
     /** Tercer atributo, una clase Serializable */
     Atributo e = new Atributo();
     
     public DatoSocket(){
         this.edad=0;
         this.nombre="Sin especificar";
     }

    public DatoSocket(int edad, String nombre) {
        this.edad = edad;
        this.nombre = nombre;
    }
     
     
     /** M�todo para devolver un String en el que se represente el valor de
      * todos los atributos. */
     public String toString ()
     {
         String resultado;
         resultado = "Edad: " + Integer.toString(getEdad()) +
            " -- Nombre: " + getNombre() + " -- Has acertado?: " + this.acertado + " ";
         return resultado;
     }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isNumero() {
        return numero;
    }

    public void setNumero(boolean numero) {
        this.numero = numero;
    }

     

     /*******
      *
      * LOS SIGUIENTES M�TODOS SON INNECESARIOS SALVO QUE QUERAMOS QUE SE
      * ENVIEN Y RECIBAN LOS DATOS DE FORMA NO STANDARD.
      * Puedes descomentarlos y todo funcionar� exactamente igual
      *
      *******/
     /*
     private void writeObject(java.io.ObjectOutputStream out)
         throws IOException
     {
         out.writeInt (c);
         out.writeUTF (d);
         out.writeInt (e.a);
         out.writeUTF (e.b);
     }*/
     
     /*
     private void readObject(java.io.ObjectInputStream in)
     throws IOException, ClassNotFoundException
     {
         c = in.readInt();
         d = in.readUTF();
         if (e==null) e = new Atributo();
         e.a = in.readInt();
         e.b = in.readUTF();
     }*/
}