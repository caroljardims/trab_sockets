import java.io.*;
import java.net.*;
import java.nio.*;
import java.util.*;

/**
 *
 * @author caroline
 */

public class Producer {

    public static void main(String[] args) throws IOException {
        Producer produtor = new Producer();
        produtor.produz(8080,"localhost");
        
    }
    
    public void produz(int port, String server) throws IOException{
        int MAX = 20;
        InetAddress ip = InetAddress.getByName(server);
        Socket skt = null;
        for(int i=0;i<10000;i++){
            Random gerador = new Random();
			short valor = (short)gerador.nextInt();
            if (valor <= 0) valor = (short)i; 
            String value = String.valueOf(valor);
            byte[] dado = value.getBytes();

            try{
                skt = new Socket(ip,port);
                BufferedOutputStream buffOut = new BufferedOutputStream(skt.getOutputStream());
                buffOut.write(dado, 0, dado.length);
                buffOut.flush();

            } catch (SocketException e){
                break;
            } 
        }
        System.out.println(" >> Produzi! ");
    }
}
