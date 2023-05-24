package application;

public class Stack <Any>{
	
	LinkedLists <Any>list;
	public Stack() {
		list = new LinkedLists<>();
	}
	public void push(Any item) {
		
		list.add(item);
	}
	
	public Any pop() {
		Any temp = list.get(list.size()-1);
		list.remove(list.size()-1);
		return temp;
	}
	
	public boolean isEmpty() {
		return list.isEmpty();
	}

}
