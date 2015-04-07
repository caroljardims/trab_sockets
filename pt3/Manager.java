import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.*;

/**
 *
 * @author caroline
 */
public class Manager {
    
    public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException{
        Manager servidor = new Manager();
        servidor.server();
    }
    
    public void server() throws ClassNotFoundException, IOException, InterruptedException{
        
        ServerSocket call = new ServerSocket(8080);
        Socket skt = null;

        Stack criticalArea = new Stack();
        short zero = 0;
        for(int i=0;i<4;i++) criticalArea.push(zero);

        System.out.println(" > Servidor Inicializado ");

        while(true){
            if(criticalArea.isEmpty()){ 
                System.out.println(" >> Terminar Execucao. ");
                break;
            }
            try{
                skt = call.accept();
                ServerStream server = new ServerStream(skt,criticalArea);
                server.start();
            } catch (SocketException e){
                //e.printStackTrace();
            }
        }
    }
}