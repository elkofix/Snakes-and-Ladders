package model; 

public class CircularLinkedList {
	//Esta clase es la lista de jugadores, la idea es iterarla recursivamente para generear los turnos, pues es una lista circular
	private Player head; 
	private Player tail; 

	public CircularLinkedList() { }

	public void addLast(Player node){
		if(this.head == null){
			this.tail = node; 
			this.head = node; 
			this.tail.setNext(this.head);
			this.head.setPrevious(this.tail);
		}
		else{
			this.tail.setNext(node);
			node.setPrevious(this.tail);
			this.tail = node; 
			this.tail.setNext(this.head);
			this.head.setPrevious(this.tail);
		}
	}
}
