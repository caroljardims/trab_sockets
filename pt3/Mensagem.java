import java.io.*;

/**
 *
 * @author caroline
 */
public class Mensagem implements Serializable {
    
    /** Tipos de mensagens POP, PUSH, RET_POP */
    private String tipo;
    private short num;
    
    public Mensagem() {
        this.tipo = "";
        this.num = (short)0;
    }    
    public Mensagem (String tipo, short numero) {
        this.tipo = tipo;
        this.num = numero;
    }

    public void setTipo(String t) { this.tipo = t; }
    public String getTipo() { return this.tipo; }
    public void setNumero(short n) { this.num = n; }
    public short getNumero() { return this.num; }
}