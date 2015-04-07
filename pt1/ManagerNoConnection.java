import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.*;

/**
 *
 * @author caroline
 */
public class ManagerNoConnection {
    
    public static void main(String[] args) throws IOException {
        ManagerNoConnection servidor = new ManagerNoConnection();
        servidor.server();
    }
    
    public void server() throws SocketException, IOException{
        
        DatagramSocket skt = new DatagramSocket(8080);
        Stack criticalArea = new Stack();
        short zero = 0;
        for(int i=0;i<4;i++) criticalArea.push(zero);
        
        while(true){
            if(criticalArea.isEmpty()){ 
                System.out.println(" >> Fim de Execucao.");
                break;
            }
            System.out.println("Aguardando requisicao");
            
            int MAX = 20;
            
            byte[] dado = new byte[MAX];

            DatagramPacket rcb = new DatagramPacket(dado, dado.length);
            skt.receive(rcb);
            String content = new String(rcb.getData());
            System.out.println(content);
            int valorInt = Integer.parseInt(content.trim());
            short conteudo = (short)valorInt;
            if(conteudo != 0){
                System.out.println(" > Recebido\n >> produtor ");
                criticalArea.push(conteudo);
            }
            else{ 
                System.out.println(" > Recebido\n >> consumidor ");
                short value = criticalArea.pop();
                String envia = Short.toString(value);
                byte[] toSend = envia.getBytes();
                if(value == 0)
                    System.out.println(" > Area Critica vazia.");
                ProxyC consumidor = new ProxyC(rcb.getPort(),rcb.getAddress(),toSend);
                consumidor.start();
            }
        }
        skt.close();
        
    }
}
