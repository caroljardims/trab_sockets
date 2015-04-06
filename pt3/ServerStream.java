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
					skt.close();
				}
				else if ((msg.getTipo()).equals("POP")) {
					short value = pilha.pop();
					if(value >= 0){
						msg.setTipo("RET_POP");
						msg.setNumero(value);
						objOut.writeObject(msg);
						objOut.flush();
					}
					if (value == 0)
						System.out.println(" > Area Critica vazia.");						
				}
				skt.close();
			}
		} catch (IOException | ClassNotFoundException e){
			e.printStackTrace();
		}
	}
}