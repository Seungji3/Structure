package MyLinkedList;

public class MyLinkedList {
    Node head = null;
    Node tail = null;
    private int length = 0;

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

    //  특정 index까지만 loop
    public void insert(int index, Object data) {
        if (index != 0 && (index >= size() || index < 0)) {     //  LinkedList의 size가 0인 상태에서 0번째 순서의 데이터를 넣을 때의 분기 처리
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }

        Node curNode = this.head;
        if (index == 0) {                   //  head
            head = new Node<>(data);
            if (curNode != null) {     // insert시 size가 0인 상태에서 0번째 리스트에 넣을 경우
                head.next = curNode;
                tail = curNode;
            }
            tail = head;
        } else if (index == (size() -1)) {  //  tail
            curNode = tail;
            tail = new Node<>(data);
            tail.prev = curNode;
            curNode.next = tail;
        } else {                            //  else all
            Node preNode = null;
            int i = 0;
            while(i < index) {
                preNode = curNode;
                curNode = curNode.next;
                i++;
            }
            curNode.prev = preNode.next;
            preNode.next = new Node<>(data);
            preNode.next.next = curNode.prev;
        }
    }

    public Object get(int index) {
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }

        Node current = this.head;
        int i = 0;
        if (index == 0) {       //  head
            return head.data;
        } else if (index == size()) {   // LinkedList의 max값 같으면 tail 추출
            return tail.data;
        } else {    // 그외 index까지의 loop
            while (i < index) {
                current = current.next;
                i++;
            }
            return current.data;
        }
    }

    public Object remove(int index) {
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }

        Object result = null;
        if (index == 0) {   // head
            result = head.data;
            this.head = head.next;
        } else if (index == (size() - 1)) { // tail
            result = tail.data;
            this.tail = tail.prev;
        } else {    // else all
            int i = 0;
            Node curNode = head;
            Node preNode = null;
            while (i < index) {
                preNode = curNode;  //이전 노드
                curNode = curNode.next; //현재 노드
                i++;
            }
            preNode.next = curNode.next;
            result = curNode.data;
        }
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

