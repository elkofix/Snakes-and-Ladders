package model;

public class LinkedList {
    private Box head;
    public Box getHead() {
        return head;
    }

    public Box getTail() {
        return tail;
    }

    private Box tail;
    private int size;
    private int rows;
    private int columns;

    public int getColumns() {
        return columns;
    }

    public void addPlayers(String player){
        head.addPlayers(player);
    }
    
    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
    //Este metodo agrega una casilla
    public void addNode(int value){
        Box node = new Box(value);
        if(head==null){
            head = node;
            tail = node;
        }else{
            tail.setNext(node);
            node.setPrevious(tail);
            tail = node;
        }
    }

    
	public Box search(int goal){
		return search(goal, this.head); 
	}

	private Box search(int goal, Box current){
		// Caso base 
		if(current == null){
			return null; 
		}

		// caso borde 
		if(goal == head.getValue() && current.equals(this.head)){
			return this.head; 
		}

		if(goal == tail.getValue() && current.equals(this.tail)){
			return this.tail; 
		}
		if(goal == current.getValue()){
			return current; 
		}

		return search(goal, current.getNext()); 
	}
    //Este metodo busca una casilla por index y no por valor
    //Esto se usa para la copia, porque los valores se van borrando
    //Entonces se busca en funcion del tamaño de la lista
    public Box searchIndex(int index){
		return search(index, this.head, 0); 
	}

	private Box search(int index, Box current, int counter){
		// Caso base 
		if(index==counter){
            return current;
        }

		return search(index, current.getNext(), ++counter); 
	}

    public void addFirst(Box node){

        if(head==null){
            head = node;
            tail = node;
        }else{
            node.setNext(head);
            head.setPrevious(node);
            head = node;
        }
        
    }
    //Activacion. Este metodo imprime el table cuando las filas son pares
    public String printBoard(){
        return printBoard(tail, "");
    }

    //Recursivo
    private String printBoard(Box current, String msj){
        if(current == null ){
            return msj;
        }
        if((current.getValue()+columns-1)%columns!=0){
            msj += current.toString();
            
        }else{
            msj += current.toString()+"\n";
            if(current.getValue()!=1){
                 msj+=printReverse(search(current.getValue()-columns), current  .getValue()-1, "");
                return printBoard(search(current.getValue()-columns-1), msj);
            }
            
        }

        return printBoard(current.getPrevious(), msj);
    }
    //Este metodo imprime en orden inverso, para dar ese recorrido en forma de S
    private String printReverse(Box current, int goal, String msj){
        if(current.getValue()==goal+1){
            return msj;
        }
        if(current.getValue()!=goal){
            msj += current.toString();
            
        }else{
            msj += current.toString()+"\n";
        }

        return printReverse(current.getNext(), goal, msj);
    }
    

    //Activacion. Este metodo imprime el table cuando las filas son impares y cambia en funcion de que si las columnas son pares o impares
    public String printBoardOdd(){
        return printBoardOdd(tail, "");
    }
    //Recursivo
    private String printBoardOdd(Box current, String msj){
        if(current == null ){
            return msj;
        }

        if(columns%2!=0) {
            if ((current.getValue() % columns == 0 && (current.getValue() % 2) != 0)) {
                if (current.getValue() != 1) {
                    msj += printReverseOdd(search(current.getValue() - columns + 1), current.getValue(), "");
                    return printBoardOdd(search(current.getValue() - columns), msj);
                }


            } else {
                if ((current.getValue() + columns - 1) % columns == 0) {
                    msj += current.toString() + "\n";
                } else {
                    msj += current.toString();

                }

            }
        }else{
            Double divisible = (double)current.getValue();
            Double x = (divisible/columns);
            Double row = Math.ceil(x);
            if ((current.getValue() % columns == 0 && (current.getValue() % 2) == 0) && row%2!=0){
                if (current.getValue() != 1) {
                    msj += printReverseOdd(search(current.getValue() - columns + 1), current.getValue(), "");
                    return printBoardOdd(search(current.getValue() - columns), msj);
                }


            } else {
                if ((current.getValue() + columns - 1) % columns == 0) {
                    msj += current.toString() + "\n";
                } else {
                    msj += current.toString();

                }

            }
        }
        return printBoardOdd(current.getPrevious(), msj);
    }
    //Imprime inverso para las filas impares
    private String printReverseOdd(Box current, int goal, String msj){
        if(current==null){
            return msj;
        }
        if(current.getValue()>goal){
            return msj;
        }
        if(current.getValue()!=goal){
            msj += current.toString();
            
        }else{
            msj += current.toString()+"\n";
        }

        return printReverseOdd(current.getNext(), goal, msj);
    }

    //Activacion. Este metodo imprime el table cuando las filas son pares mostrando serpientes y escaleras
    public String printSnakes(){
        return printSnakes(tail, "");
    }

    //Recursivo
    private String printSnakes(Box current, String msj){
        if(current == null ){
            return msj;
        }
        if((current.getValue()+columns-1)%columns!=0){
            msj += current.toString2();

        }else{
            msj += current.toString()+"\n";
            if(current.getValue()!=1){
                msj+=printReverseS(search(current.getValue()-columns), current  .getValue()-1, "");
                return printSnakes(search(current.getValue()-columns-1), msj);
            }

        }

        return printSnakes(current.getPrevious(), msj);
    }
    //Imprime en orden inverso las serpientes  y escaleras
    private String printReverseS(Box current, int goal, String msj){
        if(current.getValue()==goal+1){
            return msj;
        }
        if(current.getValue()!=goal){
            msj += current.toString2();

        }else{
            msj += current.toString2()+"\n";
        }

        return printReverseS(current.getNext(), goal, msj);
    }


    //Imprimer serpientes y escaleras cuando filas impares
    public String printSnakesOdd(){
        return printSnakesOdd(tail, "");
    }

    private String printSnakesOdd(Box current, String msj){
        if(current == null ){
            return msj;
        }
        if(columns%2!=0) {
            if((current.getValue()%columns==0 && current.getValue()%2!=0)){
                if(current.getValue()!=1){
                    msj+=printReverseOddS(search(current.getValue()-columns+1), current.getValue(), "");
                    return printSnakesOdd(search(current.getValue()-columns), msj);
                }


            }else{
                if((current.getValue()+columns-1)%columns==0){
                    msj += current.toString2()+"\n";
                }else{
                    msj += current.toString2();

                }

            }
        }else{
            Double divisible = (double)current.getValue();
            Double x = (divisible/columns);
            Double row = Math.ceil(x);
            if ((current.getValue() % columns == 0 && (current.getValue() % 2) == 0) && row%2!=0){
                if (current.getValue() != 1) {
                    msj += printReverseOddS(search(current.getValue() - columns + 1), current.getValue(), "");
                    return printSnakesOdd(search(current.getValue() - columns), msj);
                }


            } else {
                if ((current.getValue() + columns - 1) % columns == 0) {
                    msj += current.toString2() + "\n";
                } else {
                    msj += current.toString2();

                }

            }
        }

        return printSnakesOdd(current.getPrevious(), msj);
    }
    //Imprime inverso serpientes y escaleras impares
    private String printReverseOddS(Box current, int goal, String msj){
        if(current==null){
            return msj;
        }
        if(current.getValue()>goal){
            return msj;
        }
        if(current.getValue()!=goal){
            msj += current.toString2();

        }else{
            msj += current.toString2()+"\n";
        }

        return printReverseOddS(current.getNext(), goal, msj);
    }

    //Eliminacion
    public void delete(int goal){
        delete(head, goal);
    }
    private void delete(Box current, int goal){
        if(current == null){
            return;
        }
        if(current.getValue() == goal){
            if(current == head && current.getNext()==null){
                this.head=null;
                return;
            }
            if(current == head){
                Box next = head.getNext();
                next.setPrevious(null);
                head = next;
                return;
            }
            if(current == tail){
                Box prev = tail.getPrevious();
                prev.setNext(null);
                tail = prev;
                return;
            }
            //Eliminar
            Box prev = current.getPrevious();
            Box next = current.getNext();
            prev.setNext(next);
            next.setPrevious(prev);
            return;
        }
        delete(current.getNext(), goal);

    }

    public void decreaseSize(){
        this.size-=2;
    }

}