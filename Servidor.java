import java.io.*;
import java.net.*;

class Servidor {
    int puerto = 6996;
    ServerSocket ssServidor;
    Socket sCliente;
    DataOutputStream bajada;
    DataInputStream subida;

    public static void main(String args[]) {
        new Servidor();
    }

    public Servidor() {
        try {
            ssServidor = new ServerSocket(puerto);
            System.out.println("Escuchando en puerto " + puerto);

            sCliente = ssServidor.accept();
            System.out.println("Sirviendo al cliente");

            OutputStream os = sCliente.getOutputStream();
            bajada = new DataOutputStream(os);

            InputStream is = sCliente.getInputStream();
            subida = new DataInputStream(is);

            //COMIENZAN LOS MENSAJES
            sube();
            baja("Hola soy el servidor.");
            sube();
            baja("Muy bien");
            sube();
            baja("Hasta luego");


            System.out.println("Cerrando conexion...");
            cierra();

        } catch (Exception e) {
            System.out.println("Excepcion en Servidor(): " + e);
        }
    }

    public void baja(String texto) {
        texto = "[Servidor] " + texto;
        try {
            bajada.writeUTF(texto);
            System.out.println(texto);
        } catch (IOException e) {
            System.out.println("Excepcion en sube(): " + e);
        }
    }

    public void sube() {
        try {
            System.out.println(subida.readUTF());
        } catch (IOException e) {
            System.out.println("Excepcion en baja(): " + e);
        }
    }

    public void cierra(){
        try {
            subida.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            bajada.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}