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
    
    public void produz(int porta, String server) throws IOException{
        int MAX = 20;
        InetAddress ip = InetAddress.getByName(server);
        for(int i=0;i<10000;i++){
            Random gerador = new Random();
            short valor = (short)gerador.nextInt(100);
            if(valor > 0) valor = (short)i;
            String value = String.valueOf(valor);
            byte[] dado = value.getBytes();
            DatagramPacket pkg = new DatagramPacket(dado, dado.length, ip, porta);
            DatagramSocket skt = new DatagramSocket();
            skt.send(pkg);
            skt.close();
        }
    }

    public static byte[] short2byte(short value, int max) {
        byte[] bytes = new byte[max];
        ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
        buffer.putShort(value);
        return buffer.array();   
    }
    public static short byte2short(byte[] range) {
        return (short)((range[1] << 8) + (range[0] & 0xff));
    }
}
