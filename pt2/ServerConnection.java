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
		String linha = new String(dado,0,nBytes);
		int valorInt = Integer.parseInt( linha.trim() );
		short valor = (short)valorInt;
		
		if(valor != 0){
			ca.push(valor);
			skt.close();
		} 
		else {
			short value = ca.pop();
			System.out.println(value);
			if(value == (short)0)
                System.out.println(" > Area Critica vazia.");
			sendAnswer(value,buffOut);
		}
	}
	public void sendAnswer(short value, BufferedOutputStream buffOut) throws IOException {
		String toSend = Short.toString(value);
		byte[] dado = toSend.getBytes();
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
