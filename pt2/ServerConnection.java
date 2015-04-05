import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.*;

/**
 *
 * @author caroline
 */

public class ServerConnection extends Thread{

	public ServerConnection(Socket skt, Stack ca) throws IOException {
		int MAX = 20;
        
        BufferedOutputStream buffOut = new BufferedOutputStream(skt.getOutputStream());
		BufferedInputStream buffIn = new BufferedInputStream(skt.getInputStream());

		byte[] dado = new byte[MAX];
		int nBytes = buffIn.read(dado, 0, dado.length);
		short valor = byte2short(dado);

		if(valor != 0){
			ca.push(valor);
			skt.close();
		} 
		else {
			short value = ca.pop();
			if(value == 0){
                    System.out.println(" > Area Critica vazia.");
                    sendAnswer(value,dado,buffOut);
                    if(ca.isEmpty()){ 
                        System.out.println(" >> Fim de Execucao.");
                    }
                }
			sendAnswer(value,dado,buffOut);
		}
	}
	public void sendAnswer(short value, byte[] dado, BufferedOutputStream buffOut) throws IOException {
		String toSend = Short.toString(value);
		dado = toSend.getBytes();
		buffOut.write(dado, 0, dado.length);
		buffOut.flush();
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