package MyLinkedList;

public class MyLinkedList<T> {
    Node<T> head = null;
    private int length = 0;

    private void indexOutOfException(int index) {
        if (index >= length)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + length);
    }

    public void clear() {
        for (Node<T> current = this.head; current != null; ) {
            Node<T> tempNode = current.next;
            current.data = null;
            current.next = null;
            current = tempNode;
        }
        this.head = null;
        this.length = 0;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public int indexOf(T data) {
        Node<T> current = this.head;
        for (int i = 0; i < length; i++) {
            if (current.data.equals(data)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    public boolean contains(T data) {
        for (Node<T> current = this.head; current != null; ) {
            if (current.data.equals(data))
                return true;
        }
        return false;
    }

    public int size() {
        return length;
    }

    public void add(T data) {
        if (head == null) {
            head = new Node<T>(data);
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node<T>(data);
        }
        length++;
    }

    public void add(int index, T data) {
        indexOutOfException(length == index ? index - 1 : index);

        Node<T> current = this.head;
        Node<T> endNode = null;
        if (index == 0) {
            endNode = this.head;
            this.head = new Node<>(data, endNode);
        } else if (index != length) {
            int count = 1;
            while (count < index) {
                current = current.next;
                count++;
            }
            endNode = new Node<>(current.next);
            Node<T> addNode = new Node<>(data, endNode);
            current.next = addNode;
        } else {
            while(current.next != null) {
                current = current.next;
            }
            current.next = new Node<T>(data);
        }
        length++;
    }

    public T get(int index) {
        indexOutOfException(index);
        int size = 0;
        for (Node<T> current = this.head; current != null; size++) {
            if (index == size)
                return current.data;
            current = current.next;
        }
        return null;    //위에서 indexOutOfException 처리하고 있음
    }

    public T remove(int index) {
        indexOutOfException(index);

        T result = null;
        Node<T> current = head;
        Node<T> tempNode = null;
        if (index == 0) {   // head일 경우에만 prevData가 없음
            result = head.data;
            if (length == 1) {
                head = null;
            } else {
                tempNode = current.next;
                head = tempNode;
            }
            length--;
            return result;
        }
        for (int currentCount = 0, prevCount = index - 1; currentCount <= index; currentCount++) {
            if (length - 1 == index && currentCount == index) { //tail
                result = current.data;
                current = null;
                length--;
                return result;
            } else if (currentCount == prevCount) {
                tempNode = current;
            }
            result = current.data;
            current = current.next;
        }
        current = current.next;
        tempNode.next = current;
        length--;
        return result;
    }

    private static class Node<T> {
        T data = null;
        Node<T> next = null;

        public Node(T data) {
            this.data = data;
        }

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        public Node(Node<T> node){
            this.data = node.data;
            this.next = node.next;
        }
    }
}

