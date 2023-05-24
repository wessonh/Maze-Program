package application;

public class SolveQueue<Any> { // Queue class that is used in Solve class

	LinkedLists <Any> list;
	
	public SolveQueue() {
		list = new LinkedLists<>();
	}
   public void enqueue(Any item) {
   	 
   	list.add(item);
   }
  
 	
 	public Any dequeue() {
 		Any temp = list.get(0);
 		list.remove(0);
 		return temp;
 	}
 	
 	public boolean isEmpty() {
 		return list.isEmpty();
 	}
}
