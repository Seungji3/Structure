package MyLinkedList;

public class MyLinkedList<Field> {
    Node head = null;
    private int length = 0;

    private void indexOutOfException(int index) {
        if (index >= length)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + length);
    }

//    1. 안에서 제네릭 값 중 아무거나 들어올수있게 변경
//    2. Node<Field> prev 추가
//    2.1 특정 위치에 add하는 메서드 insert로 네이밍 변경
//    3. get? remove? for의 두개의 index가 이동할수있게 변경

    public void clear() {
        this.head = null;
        this.length = 0;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public int indexOf(Field data) {
        Node current = this.head;
        for (int i = 0; i < length; i++) {
            if (current.data.equals(data)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    public boolean contains(Field data) {
        for (Node current = this.head; current != null; ) {
            if (current.data.equals(data))
                return true;
        }
        return false;
    }

    public int size() {
        return length;
    }

    public void add(Field data) {
        if (head == null) {
            head = new Node<>(data);
        } else {
            Node current = this.head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node<>(data);
        }
        length++;
    }

    public void add(int index, Field data) {
        indexOutOfException(length == index ? index - 1 : index);

        Node current = this.head;
        Node endNode = null;
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
            Node addNode = new Node<>(data, endNode);
            current.next = addNode;
        } else {
            while(current.next != null) {
                current = current.next;
            }
            current.next = new Node<>(data);
        }
        length++;
    }

    public Field get(int index) {
        indexOutOfException(index);
        int size = 0;
        for (Node current = this.head; current != null; size++) {
            if (index == size)
                return current.data;
            current = current.next;
        }
        return null;    //위에서 indexOutOfException 처리하고 있음
    }

    public Field remove(int index) {
        indexOutOfException(index);

        Field result = null;
        Node current = this.head;
        Node tempNode = null;
        if (index == 0) {   // head일 경우에만 prevData가 없음
            result = (Field) head.data;
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
                result = (Field) current.data;
                current = null;
                length--;
                return result;
            } else if (currentCount == prevCount) {
                tempNode = current;
            }
            result = (Field) current.data;
            current = current.next;
        }
        current = current.next;
        tempNode.next = current;
        length--;
        return result;
    }

    private static class Node<Field> {
        Field data = null;
        Node<Field> prev = null;
        Node<Field> next = null;

        public Node(Field data) {
            this.data = data;
        }

        public Node(Field data, Node<Field> next) {
            this.data = data;
            this.next = next;
        }

        public Node(Node<Field> node){
            this.data = node.data;
            this.next = node.next;
        }
    }
}

