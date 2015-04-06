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
        for(int i=0;i<10000;i++){
            Random gerador = new Random();
			short valor = (short)gerador.nextInt();
            if (valor <= 0) valor = (short)i; 
			System.out.println(valor);
            byte[] dado = new byte[MAX];
            if(valor > 0) dado = short2byte(valor,MAX);
            else dado = short2byte((short)20,MAX);
            
            Socket skt = new Socket(ip,port);
            BufferedOutputStream buffOut = new BufferedOutputStream(skt.getOutputStream());
            
            buffOut.write(dado, 0, dado.length);
            buffOut.flush();
            
            skt.close();
        }
        System.out.println(" >> Produzi! ");
    }

    public static byte[] short2byte(short value, int max) {
        byte[] bytes = new byte[max];
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.putShort(value);
        return buffer.array();   
    }
}
