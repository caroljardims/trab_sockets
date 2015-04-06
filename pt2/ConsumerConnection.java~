import java.io.*;
import java.net.*;
import java.nio.*;

/**
 *
 * @author caroline
 */

public class ConsumerConnection {

    public static void main(String[] args) throws IOException {
        ConsumerConnection cliente = new ConsumerConnection();
        cliente.client(8080,"localhost");
        
    }
    
    public void client(int port, String address) throws IOException{
        int MAX = 20;
        int sum = 0;
        
        InetAddress ip = InetAddress.getByName(address);

        while(true){   
            Socket skt = new Socket(ip,port);
            byte[] dado = new byte[MAX];
            BufferedOutputStream buffOut = new BufferedOutputStream(skt.getOutputStream());
            BufferedInputStream buffIn = new BufferedInputStream(skt.getInputStream());
            
            buffOut.write(dado, 0, dado.length);
            buffOut.flush();

            dado = new byte[MAX];
            int nBytes = buffIn.read(dado, 0, dado.length);
            String rcb = new String(dado, 0, nBytes);
            int valor = Integer.parseInt(rcb.trim());
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
