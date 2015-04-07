import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.*;

/**
 *
 * @author pilharoline
 */

public class ServerStream extends Thread{

	public ServerStream(Socket skt, Stack pilha) throws ClassNotFoundException, SocketException, IOException, InterruptedException {
		try{
	        Mensagem msg = null;

	        ObjectOutputStream objOut = new ObjectOutputStream(skt.getOutputStream());
			ObjectInputStream objIn = new ObjectInputStream(skt.getInputStream());


			if(objIn.available()==0){ 
				msg = (Mensagem)objIn.readObject();

				if((msg.getTipo()).equals("PUSH")){
					pilha.push(msg.getNumero());
					//skt.close();
				}
				else if ((msg.getTipo()).equals("POP")) {
					short value = pilha.pop();
					if(value >= 0){
						msg.setTipo("RET_POP");
						msg.setNumero(value);
						objOut.writeObject(msg);
						objOut.flush();
						//System.out.println(" > Enviando pacote " + value + " ao consumidor.");
					}
					if (value == 0)
						System.out.println(" > Finaliza transimssao.");						
				}
				skt.close();
			}
		} catch(SocketException e){
            e.printStackTrace();
        } catch(IOException e1){
        	e1.printStackTrace();
        } finally {
                try {
                    if (skt != null) {
                        skt.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
        	}
	}
}