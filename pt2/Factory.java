import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.*;

/**
 *
 * @author caroline
 */
public class Factory {
    
    public static void main(String[] args) throws IOException {
        Factory servidor = new Factory();
        servidor.server();
    }
    
    public void server() throws SocketException, IOException{
        
        ServerSocket call = new ServerSocket(8080);
        Socket skt = null;
        Stack criticalArea = new Stack();
        short zero = 0;
        for(int i=0;i<4;i++) criticalArea.push(zero);

        System.out.println(" > Servidor Inicializado ");
        while(true){

            if(criticalArea.isEmpty()){ 
                System.out.println(" >> Fim de Execucao.");
                break;
            }
            
            skt = call.accept();
            
            ServerConnection server = new ServerConnection(skt,criticalArea);
            server.start();
        }
    }
}
