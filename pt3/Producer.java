import java.io.*;
import java.net.*;
import java.nio.*;
import java.util.*;

/**
 *
 * @author caroline
 */

public class Producer {

    public static void main(String[] args) throws ClassNotFoundException,IOException {
        Producer produtor = new Producer();
        produtor.produz(8080,"localhost");
        
    }
    
    public void produz(int port, String address) throws ClassNotFoundException,SocketException,IOException{
        InetAddress ip = InetAddress.getByName(address);
        try{
            Socket skt = null;
            for(int i=0;i<1000;i++){
                skt = new Socket(ip,port);
                Random gerador = new Random();
                short valor = (short)gerador.nextInt();
                if (valor <= 0) valor = (short)i; 
                Mensagem push = new Mensagem("PUSH",(short)valor);
                ObjectOutputStream objOut = new ObjectOutputStream(skt.getOutputStream());
                objOut.writeObject(push);
                objOut.flush();
            }
            skt.close();
        }catch(SocketException e){
            e.printStackTrace();
        }
        System.out.println(" > Produzi!");
    }
}
