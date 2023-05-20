package application;

public class SolveQueue<sq> {

    private static class Node<sq> {
        private final sq data;
        private Node<sq> next;

        public Node(sq data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<sq> front;
    private Node<sq> back;

    public SolveQueue() {

        this.front = this.back = null;
    }

    public void enqueue(sq item) {

        Node<sq> temp = new Node<>(item);

        if (back == null) {

            front = back = temp;
            return;
        }

        back.next = temp;
        back = temp;
    }

    public sq dequeue() {

        if (front == null) {

            throw new IllegalStateException("Queue is Empty!");
        }

        sq item = front.data;
        front = front.next;

        if (front == null) {

            back = null;
        }
        return item;
    }

    public boolean isEmpty() {

        return front == null;
    }
}