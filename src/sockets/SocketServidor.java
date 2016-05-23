package sockets;


/*
 * Sandro Gamarra. 17 de Mayo de 2016.
 *
 * SocketServidor.java
 * Ejemplo de un socket servidor m�nimo en java.
 */

import java.net.*;
import java.io.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import static java.lang.Math.random;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Clase principal que instancia un socket servidor, acepta una conexion
 * de un cliente y le envia un entero y una cadena de caracteres.
 */
public class SocketServidor
{    
    public static void main (String [] args)
    {
        // Se instancia la clase principal para que haga todo lo que tiene que
        // hacer el ejemplo
        new SocketServidor();
    }
    
     /**
      * Constructor por defecto. Hace todo lo que hace el ejemplo.
      */
    public SocketServidor()
    {
        
        try
        {
            // Se crea un socket servidor atendiendo a un determinado puerto.
            // Por ejemplo, el 35557.
            ServerSocket socket = new ServerSocket (35557);
            DatoSocket message = null;
            int contador = 0;
            //Crearemos un nº aleatorio entre 0 y 10
            int random = (int) (Math.random() * 10 + 1);  
            System.out.println(random);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //Ya tenemos el "lector"

        System.out.println("Por favor ingrese su numero");//Se pide un dato al usuario
        String numero = br.readLine(); //Se lee el nombre con readLine() que retorna un String 
        
            if (message.set==numero){
                System.out.println("Has acertado");
            } else {
                System.out.println("Has fallado");
            }
            // Se acepata una conexi�n con un cliente. Esta llamada se queda
            // bloqueada hasta que se arranque el cliente.
            while(true){
            System.out.println ("Esperando cliente");
            Socket cliente = socket.accept();
               contador++;
            System.out.println ("Conectado con cliente de " + cliente.getInetAddress());
            //Agregamos los cambios que hemos visto en el Ejemplo
            
            ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());
            //convert ObjectInputStream object to String
            try{
               message  = (DatoSocket) ois.readObject(); //cambiamos de String a DatoSocket
            }
            catch(ClassCastException e){
                System.out.println("Error: la clase Datasocket no coincide");
            }
            catch(InvalidClassException i){
                System.out.println("Error: la clase Datasocket no valida");
            }
            
            System.out.println("Message Received: " + message.getNombre() + " y tienes " + message.getEdad());
            
            // Se hace que el cierre del socket sea "gracioso". Esta llamada s�lo
            // es necesaria si cerramos el socket inmediatamente despu�s de
            // enviar los datos (como en este caso).
            // setSoLinger() a true hace que el cierre del socket espere a que
            // el cliente lea los datos, hasta un m�ximo de 10 segundos de espera.
            // Si no ponemos esto, el socket se cierra inmediatamente y si el 
            // cliente no ha tenido tiempo de leerlos, los datos se pierden.
            cliente.setSoLinger (true, 10);

            // Se prepara un flujo de salida de datos simples con el socket.
            DataOutputStream buffer = new DataOutputStream (cliente.getOutputStream());
            
            // Se envia un entero y una cadena de caracteres.
            buffer.writeInt (22);
            System.out.println ("Enviado Pojo");
            
            buffer.writeUTF ("Saludos Wey!! Enviado P-N" + " eres el nº: " + contador);
            System.out.println ("Enviado P-N");
            
                
            // Se prepara un flujo de salida para objetos y un objeto para enviar*/
            DatoSocket dato = new DatoSocket();
            ObjectOutputStream bufferObjetos = 
                new ObjectOutputStream (cliente.getOutputStream());
            
            // Se env�a el objeto 
            bufferObjetos.writeObject(dato);
            System.out.println ("Enviado " + dato.toString() + " eres el nº " + contador );
//                System.out.println("Tu eres el nº " + contador);
            
            // Se cierra el socket con el cliente.
            // La llamada anterior a setSoLinger() har�
            // que estos cierres esperen a que el cliente retire los datos.
            
//            if (contador==7){
            cliente.close();
//                System.out.println("Cerrada la conexion con "+ );
//            }
            
            }
            // Se cierra el socket encargado de aceptar clientes. Ya no
            // queremos m�s.
            //socket.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
