package MyLinkedList;

public class MyLinkedList {
    Node head = null;
    Node tail = null;
    private int length = 0;

    private void indexOutOfException(int index) {
        if (index >= length)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + length);
    }

//    1. 안에서 제네릭 값 중 아무거나 들어올수있게 변경
//    2. Node<Field> prev 추가
//    2.1 특정 위치에 add하는 메서드 insert로 네이밍 변경
//    3. insert? remove? for의 두개의 index가 이동할수있게 변경
//    풀서치? 생각도 해야함

    public void clear() {
        this.head = null;
        this.length = 0;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public int indexOf(Object data) {
        Node current = this.head;
        int i = 0;
        while (i < size()) {
            if (current.data.equals(data)) return i;
            current = current.next;
            i++;
        }
        return -1;
    }

    // Full search?
    public boolean contains(Object data) {
        Node current = this.head;
        int i = 0;
        while (i < size()) {
            if (current.data.equals(data)) return true;
            current = current.next;
            i++;
        }
        return false;
    }

    public int size() {
        length = 0;
        Node current = this.head;
        while (current != null) {
            length += 1;
            current = current.next;
        }
        return length;
    }

    public void add(Object data) {
        if (head == null) {
            head = new Node<>(data);
            tail = head;
        } else {
            Node preNode = tail;
            preNode.next = new Node(data);
            tail = tail.next;
            tail.prev = preNode;
        }
    }

//    public void add(int index, Object data) {

    /// /        indexOutOfException(length == index ? index - 1 : index);
//
//        Node current = this.head;
//        Node endNode = null;
//        if (index == 0) {
//            endNode = this.head;
//            this.head = new Node<>(data, endNode);
//        } else if (index != length) {
//            int count = 1;
//            while (count < index) {
//                current = current.next;
//                count++;
//            }
//            endNode = new Node<>(current.next);
//            Node addNode = new Node<>(data, endNode);
//            current.next = addNode;
//        } else {
//            while (current.next != null) {
//                current = current.next;
//            }
//            current.next = new Node<>(data);
//        }
//    }

    //특정 index까지만 loop
    public Object get(int index) {
        indexOutOfException(index);   //todo: index와 length 비교하는 조건 필요

        Node current = this.head;
        int i = 0;
        if (index == 0) {       // 바로 head 추출
            return head.data;
        } else if (index == size()) {   // LinkedList의 max값 같으면 tail 추출
            return tail.data;
        } else {    // 그외 index까지의 loop문
            while (i < index) {
                current = current.next;
                i++;
            }
            return current.data;
        }
    }

    public Object remove(int index) {
//        indexOutOfException(index);

        Object result = null;
        Node current = this.head;
        Node tempNode = null;
        if (index == 0) {   // head일 경우에만 prevData가 없음
            result = head.data;
            if (length == 1) {
                head = null;
            } else {
                tempNode = current.next;
                head = tempNode;
            }
            return result;
        }
        for (int currentCount = 0, prevCount = index - 1; currentCount <= index; currentCount++) {
            if (length - 1 == index && currentCount == index) { //tail
                result = current.data;
                current = null;
                return result;
            } else if (currentCount == prevCount) {
                tempNode = current;
            }
            result = current.data;
            current = current.next;
        }
        current = current.next;
        tempNode.next = current;
        return result;
    }

    private static class Node<T> {
        T data = null;
        Node prev = null;
        Node next = null;

        public Node(T data) {
            this.data = data;
        }
    }
}

