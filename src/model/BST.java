package model;

public class BST {

	private Node root; 

	public BST() { }	

	public void insert(Node node){
		if(root == null){
			root = node; 
		}
		else{
			insert(node, root); 
		}
	}

	private void insert(Node node, Node current){
		try{
			// Izquierda 
			if(node.getValue() < current.getValue()){
				if(current.getLeft() == null){
					current.setLeft(node); 
				}
				else{
					insert(node, current.getLeft()); 
				}
			}
			// Derecha 
			else if(node.getValue() > current.getValue()){
				if(current.getRight() == null){
					current.setRight(node); 
				}
				else{
					insert(node, current.getRight()); 
				}
			}
			else{
				// los nodos son iguales 
			}

		} catch(NullPointerException ne){
			System.out.println("el nodo debe ser diferente de null");
			System.err.println(ne.getMessage());
		}
	}

	public Node search(int goal){
		return search(goal, root); 
	}

	private Node search(int goal, Node current){

		if(current == null){
			return null; 
		}
		if(current.getValue() == goal){
			return current; 
		}
		if(current.getValue() < goal){
			return search(goal, current.getRight()); 
		}
		else{
			return search(goal, current.getLeft()); 
		}
	}

	public Node getMax(){
		return getMax(root);
	}

	private Node getMax(Node current){
		if(current.getRight() == null){
			return current; 
		}
		else{
			return getMax(current.getRight()); 
		}
	}

	private Node getMin(Node current){
		if(current.getLeft() == null){
			return current; 
		}
		else{
			return getMin(current.getLeft()); 
		}
	}

	public void inOrder(){
		inOrder(root); 
	}

	private void inOrder(Node current){
		if(current == null){
			return; 
		}
		inOrder(current.getLeft()); 
		System.out.print(current.getValue()+"\t");
		inOrder(current.getRight());
	}

	public String inOrderString(){
		return "[" + inOrderString(root) + "]"; 
	}

	private String inOrderString(Node current){
		if(current == null){
			return ""; 
		}

		return inOrderString(current.getLeft()) + " " + current.getValue() + " " + inOrderString(current.getRight()); 
	}

	// Trigger del método
	public Node delete(int goal){
		return delete(goal, root);
	}

	private Node delete(int goal, Node current){
		if(current == null){
			return null;
		}
		if(current.getValue() == goal){
			//1. Nodo Hoja
			if(current.getLeft() == null && current.getRight() == null){
				if(current == root){
					root = null;
				}else{

				}
				return null;
			}
			//2. Nodo solo hijo izquierdo
			else if (current.getRight() == null){
				if(current == root){
					root = current.getLeft();
				}
				return current.getLeft();
			}
			//3. Nodo solo hijo derecho
			else if(current.getLeft() == null){
				if(current == root){
					root = current.getRight();
				}
				return current.getRight();
			}
			//4. Nodo con dos hijos
			else{
				// sucesor
				Node min = getMin(current.getRight());
				//Transferencia de valores, NUNCA de conexiones
				current.setValue(min.getValue());
				//Hacer eliminación a partir de la derecha
				Node subarbolDER = delete(min.getValue(), current.getRight());
				current.setRight( subarbolDER );
				return current;
			}

		}else if(goal < current.getValue()){
			Node subArbolIzquierdo = delete(goal, current.getLeft());
			current.setLeft(subArbolIzquierdo);
			return current;
		}else{
			Node subArbolDerecho = delete(goal, current.getRight());
			current.setRight(subArbolDerecho);
			return current;
		}
	}

}

