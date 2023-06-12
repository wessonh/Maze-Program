package application;

public class SolveQueue<sq> { // Queue class that is used in Solve class

    private static class Node<sq> { // Node class

        private final sq data;
        private Node<sq> next;

        public Node(sq data) {

            this.data = data; // data for this node
            this.next = null; // next node in queue
        }
    }

    private Node<sq> front; // front of queue
    private Node<sq> back; // back of queue

    public SolveQueue() {

        this.front = this.back = null; //sets front and back to null when queue is created
    }

    public void enqueue(sq item) { // method to add item to queue

        Node<sq> temp = new Node<>(item);

        if (back == null) { // if queue is empty then new node is both front and back

            front = back = temp;
            return;
        }

        back.next = temp; // otherwise add new node to back of queue. update back of queue
        back = temp;
    }

    public sq dequeue() { //  removes an item from the queue

        if (front == null) {  // if queue empty, error

            throw new IllegalStateException("Queue is Empty!");
        }

        sq item = front.data; // get item from front of queue
        front = front.next; // move front to next node

        if (front == null) { // If queue is empty, back is null

            back = null;
        }
        return item;
    }

    public boolean isEmpty() { // boolean checks if queue is empty

        return front == null;
    }
}