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
            try{
                Socket skt = new Socket(ip,port);
                String zero = "0";
                byte[] dado = zero.getBytes();
                BufferedOutputStream buffOut = new BufferedOutputStream(skt.getOutputStream());
                BufferedInputStream buffIn = new BufferedInputStream(skt.getInputStream());
                
                buffOut.write(dado, 0, dado.length);
                buffOut.flush();

                dado = new byte[MAX];
                int nBytes = buffIn.read(dado, 0, dado.length);
                String rcb = new String(dado, 0, nBytes);
                int valor = Integer.parseInt( rcb.trim() );
                System.out.println(valor);
                if(valor == 0) break;
                sum += valor;
                skt.close();
            } catch (SocketException e){
                System.out.println(" > Nao foi possivel estabelecer conexao.");        
                break;
            } 
        }
        System.out.println(" > Soma: " + sum);
        if(sum < 0) System.out.println(" >> Erro (valor da soma negativo).\n Execute novamente as requisicoes ");
    }
}
