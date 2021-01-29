import java.util.NoSuchElementException;

public class LinkedList<E> {
    // inner node class
    private class Node<E> {
        public Node<E> next;
        public Node<E> prev;
        public E value;

        public Node(E value) {
            this.value = value;
        }
    }

    // private fields
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> node = new Node<>(element);
        if (index == 0) {
            if (size == 0) {
                head = node;
                tail = head;
            } else {
                node.next = head;
                head.prev = node;
                head = node;
            }
        } else if (index == size) {
            tail.next = node;
            node.prev = tail;
            tail = tail.next;
        } else { /* 0 < index && index < size */
            Node<E> curr = head;
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }
            // curr.prev <-> node
            curr.prev.next = node;
            node.prev = curr.prev;

            // node <-> curr
            node.next = curr;
            curr.prev = node;
        }
        size++;
    }

    public boolean add(E e) {
        if (head == null) {
            head = new Node<>(e);
            tail = head;
        } else {
            tail.next = new Node<>(e);
            tail.next.prev = tail;
            tail = tail.next;
        }
        size++;
        return true;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        Node<E> curr = head;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.value;
    }

    public E remove() {
        if (tail == null) {
            throw new NoSuchElementException();
        }
        E value = (E) tail.value;
        tail = tail.prev;
        tail.next = null;
        size--;
        return value;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        E value;
        if (index == 0) {
            if (size == 1) {
                value = head.value;
                head = null;
                tail = null;
            } else {
                value = head.value;
                head = head.next;
            }
        } else if (index == size - 1) {
            value = tail.value;
            tail = tail.prev;
            tail.next = null;
        } else { /* 0 < index && index < size-1 */
            Node<E> curr = head;
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }
            value = curr.value;

            Node<E> prev = curr.prev;
            Node<E> next = curr.next;

            prev.next = next;
            next.prev = prev;

            curr.prev = null;
            curr.next = null;
        }
        size--;
        return value;
    }

    public int size() {
        return size;
    }

    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder(2 * size + 1);
        sb.append('[');
        Node<E> curr = head;
        for (int i = 1; i < size; i++) {
            sb.append(curr.value);
            sb.append(' ');
            curr = curr.next;
        }
        sb.append(curr.value);
        sb.append(']');
        return sb.toString();
    }
}
