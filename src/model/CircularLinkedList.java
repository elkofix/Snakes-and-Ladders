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

	public void addFirst(Player node){
		if(head == null){
			head = node;
			tail = node;
		}else{
			node.setNext(head);
			head = node;
		}
	}

	// Método de activación
	public void print(){
		print(this.head); 
	}

	private void print(Player current){

		// Casos base
		if(this.head == null && this.tail == null){
			System.out.println("La lista esta vacia");
			return; 
		}
		if(current == this.tail){
			System.out.println(current.getId());
			return; 
		}
		System.out.println(current.getId());
		print(current.getNext());

	}



	public String printList(){
		return printList(this.head, "[ "); 
	}

	private String printList(Player current, String msj){
		if(this.head == null){
			return "Empty list";		
		}
		if(current == this.tail){
			msj += tail.getId() + " ]";
			return msj; 
		}

		msj += current.getId() + " "; 
		return printList(current.getNext(), msj); 
	}

	public Player search(String goal){
		return search(goal, this.head); 
	}

	private Player search(String goal, Player current){
		// Caso base 
		if(current == null){
			return null; 
		}

		// caso borde 
		if(goal.equals(head.getId()) && current.equals(this.head)){
			return this.head; 
		}

		if(goal.equals(tail.getId()) && current.equals(this.tail)){
			return this.tail; 
		}
		if(goal.equals(current.getId())){
			return current; 
		}
		if(current == this.tail && !goal.equals(this.tail.getId())){
			return null; 
		}

		return search(goal, current.getNext()); 
	}

	// triger de la función
	public void delete(String goal){
		delete(goal, head);
	}

	private void delete(String goal, Player current){
		//Casos base
		if(current == null){
			return;
		}
		//Caso borde: eliminar la cabeza
		if(head.getId().equals(goal)){
			head = current.getNext();
			return;
		}
		// Segundo caso borde elimina la cola
		if(tail.getId().equals(goal) && tail.equals(current)){
			current.getPrevious().setNext(null); //  previous.setNext(null);
			tail = current.getPrevious(); // previous;
			return;
		}
		// Caso intermedio 
		if(current.getId().equals(goal)){
			current.getPrevious().setNext(current.getNext()); // previous.setNext(current.getNext());
			return;
		}
		//Llamado recursivo
		delete(goal, current.getNext());
		//      ^       ^           ^
		//      |       |           | 
		// objetivo  previous    current
	}


}
