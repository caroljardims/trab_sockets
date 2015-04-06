import java.io.*;
import java.net.*;
import java.nio.*;
import java.util.*;

/**
 *
 * @author caroline
 */

public class ConsumerStream {

    public static void main(String[] args) throws ClassNotFoundException,IOException {
        ConsumerStream produtor = new ConsumerStream();
        produtor.produz(8080,"localhost");
        
    }
    
    public void produz(int port, String address) throws ClassNotFoundException,SocketException,IOException{
        try{
            short valor = 0;
            int sum = 0;
            InetAddress ip = InetAddress.getByName(address);

            while(true) {

                Socket skt = new Socket(ip,port);

                ObjectOutputStream objOut = new ObjectOutputStream(skt.getOutputStream());
                ObjectInputStream objIn = new ObjectInputStream(skt.getInputStream());
                
                Mensagem msg = new Mensagem("POP", (short)0);
                objOut.writeObject(msg);
                objOut.flush();

                if(objIn.available()==0){
                    msg = (Mensagem)objIn.readObject();
                    if ((msg.getTipo()).equals("RET_POP")) {
                        valor = msg.getNumero();
                    } 
                    sum += valor;
                    if(valor == 0){
                        System.out.println(" > Soma: " + sum);
                        break;
                    }
                    
                    //skt.close();
                }
            }
        } catch (Exception e){
            e.printStackTrace();;
        }
    }
}