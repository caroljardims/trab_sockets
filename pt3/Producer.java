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
        Socket skt = null;
        for(int i=0;i<10000;i++){
            skt = new Socket(ip,port);
            Random gerador = new Random();
            short valor = (short)gerador.nextInt(100);
            while (valor == 0) valor = (short)gerador.nextInt(100);
            Mensagem push = new Mensagem("PUSH",(short)valor);
            ObjectOutputStream objOut = new ObjectOutputStream(skt.getOutputStream());
            objOut.writeObject(push);
            objOut.flush();
        }
        skt.close();
        System.out.println(" > Produzi!");
    }
}