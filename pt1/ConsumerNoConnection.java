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
        int count = 0;
        InetAddress ip = InetAddress.getByName(address);
        
        while(true){   
            String zero = "0";
            byte[] dado = zero.getBytes();
            int valor = 1;
            DatagramPacket pkg = new DatagramPacket(dado, dado.length, ip, port);
            DatagramSocket skt = new DatagramSocket();
            skt.send(pkg);
			
			skt.setSoTimeout(3000);

            try{
    			DatagramPacket rcb = new DatagramPacket(dado, dado.length);
                skt.receive(rcb);
                dado = rcb.getData();
                String linha = new String(dado);
                valor = Integer.parseInt( linha.trim() );
                if(valor <= 0) break;
                sum += (int)valor;
            } catch(SocketTimeoutException e){
                //e.printStackTrace();
                System.out.println(" >> Mensagem nao recebida ");
                count ++;
                if(count == 3) break;
            }
            skt.close();
        }
        if(count < 3){
            System.out.println(" > Soma: " + sum);
            if(sum < 0) System.out.println(" >> Erro (valor da soma negativo).\n Execute novamente as requisicoes ");
        } else System.out.println(" >> Execucao finalizada. ");
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
