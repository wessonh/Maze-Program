package application;


public class LinkedLists <Any> {
	
	private static class Node <Any>{
		
		public Any data;
		public Node <Any> prev;
		public Node <Any> next;
		
		public Node (Any d, Node <Any> p, Node <Any> n) {
			
			data = d;
			prev = p;
			next = n;
		}
	}
	public LinkedLists() {
		
		doClear();
	}
	public void clear() {
		
	}
	
	private void doClear() {
		
		begin = new Node <Any>(null,null,null);
		end = new Node <Any>(null, begin, null);
		begin.next = end;
		
		theSize = 0;
		modCount++;
	}
	public int size() {
		
		return theSize;
	}
	public boolean isEmpty() {
		
		return size() == 0;
	}
	public boolean add(Any x) {

		add(size(),x);
		return true;
	}
	public void add(int idx, Any x) {
		
		addBefore( getNode( idx, 0, size()), x);
	}
	public Any get(int idx) {
		Node <Any> p =  getNode(idx);
		return p.data;
	}
	public Any set(int idx, Any newVal) {
		
		Node <Any> p = getNode(idx);
		Any oldVal = p.data;
		p.data = newVal;
		return oldVal;
	}
	public Any remove(int idx) {
		
		return remove(getNode(idx));
		
	}
	private void addBefore(Node <Any> p, Any x) {
		
		Node <Any> help = new Node <>(x, p.prev, p);
		help.prev.next = help;
		p.prev = help;
		theSize++;
		modCount++;
	}
	private Any remove(Node <Any> p) {
		
		p.next.prev = p.prev;
		p.prev.next = p.next;
		theSize--;
		modCount++;
		
		return p.data;
	}
	private Node <Any> getNode(int idx) {
		
		return getNode(idx, 0, size()-1);
	}
	private Node <Any> getNode(int idx, int lower, int upper) {
		Node <Any> p;
		
		if(idx < lower || idx > upper) {
			throw new IndexOutOfBoundsException();
		}
		
		if(idx < size() / 2) {
			
			p = begin.next;
			for(int i = 0; i < idx; i++) {
				p = p.next;
			}
		}
		else {
			p = end;
			for(int i = size(); i > idx; i--) {
				p = p.prev;
			}
		}
		return p;
	}
	
	
	private int theSize;
	private int modCount = 0;
	private Node <Any> begin;
	private Node <Any> end;
}
