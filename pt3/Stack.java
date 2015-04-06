import java.util.*;

/**
 *
 * @author caroline
 */

public class Stack {

	private ArrayList<Short> pilha = new ArrayList<Short>();
	public synchronized void push(Short dado){
		pilha.add(dado);
	}
	public synchronized short pop(){
		short s = pilha.get(pilha.size()-1);
		pilha.remove(pilha.size()-1);
		return s;
	}
	public void print(){
		for(int i=0; i < this.pilha.size(); i++){
			System.out.println(this.pilha.get(i));
		}
	}

	public boolean isEmpty(){
		if(this.pilha.isEmpty()){
		 return true;
		}
		else {
		 return false;
		}
	}
}