import java.lang.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.*;

/**
 *
 * @author caroline
 */

public class ProxyC extends Thread{

	public ProxyC(int port, InetAddress address, byte[] dado) throws IOException {
                DatagramPacket pkt = new DatagramPacket(dado, dado.length, address, port);
                DatagramSocket ans = new DatagramSocket();
                ans.send(pkt);
                ans.close();
                System.out.println(" >> resposta enviada ");
	}
}