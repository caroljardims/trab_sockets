import java.io.*;
import java.net.*;
import java.nio.*;

/**
 *
 * @author caroline
 */

public class ConsumerNoConnection {

    public static void main(String[] args) throws IOException {
        ConsumerNoConnection cliente = new ConsumerNoConnection();
        cliente.client(8080,"localhost");
        
    }
    
    public void client(int port, String address) throws IOException{
        int MAX = 20;
        int sum = 0;
        
        InetAddress ip = InetAddress.getByName(address);
        
        while(true){   
            byte[] dado = new byte[MAX];
            DatagramPacket pkg = new DatagramPacket(dado, dado.length, ip, port);
            DatagramSocket skt = new DatagramSocket();
            skt.send(pkg);
            DatagramPacket rcb = new DatagramPacket(dado, dado.length);
            skt.receive(rcb);
            dado = rcb.getData();
            short valor = byte2short(dado);
            if(valor <= 0) break;
            sum += (int)valor;
            skt.close();
        }
        System.out.println(" > Soma: " + sum);
        if(sum < 0) System.out.println(" >> Erro (valor da soma negativo).\n Execute novamente as requisicoes ");
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