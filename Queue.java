public class Queue<T> {
    class Node {
        T data;
        Node next;

        public Node(T data) {
            this.data = data;

        }
    }

    Node front, rear;

    public boolean isEmpty() {
        return (rear == null && front == null);
    }

    public void enqueue(T data) {
        Node newnode = new Node(data);
        if (isEmpty()) {
            front = rear = newnode;
        } else {
            rear.next = newnode;
            rear = newnode;
        }
    }

    public T dequeue() {
        if (isEmpty())
            return null;
        else {
            T val = front.data;
            front = front.next;

            if (front == null)
                rear = null;

            return val;
        }
    }

    public int size() {
        int c = 0;
        while (front != null) {
            c++;
            front = front.next;
        }
        return c;
    }
}
