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
            System.out.println("Aguardando requisicao");
            
            int MAX = 20;
            
            byte[] dado = new byte[MAX];

            DatagramPacket rcb = new DatagramPacket(dado, dado.length);
            skt.receive(rcb);
            short conteudo = byte2short(rcb.getData());
            if(conteudo != 0){
                System.out.println(" > Recebido\n >> produtor ");
                criticalArea.push(conteudo);
            }
            else{ 
                System.out.println(" > Recebido\n >> consumidor ");
                short value = criticalArea.pop();
                byte[] toSend = short2byte(value,MAX);
                if(value == 0){
                    System.out.println(" > Area Critica vazia.");
                    ProxyC consumidor = new ProxyC(rcb.getPort(),rcb.getAddress(),toSend);
                    consumidor.run();
                    if(criticalArea.isEmpty()){ 
                        System.out.println(" >> Fim de Execucao.");
                        break;
                    }
                }
                else{
                    ProxyC consumidor = new ProxyC(rcb.getPort(),rcb.getAddress(),toSend);
                    consumidor.run();
                }
            }
        }
        skt.close();
        
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
