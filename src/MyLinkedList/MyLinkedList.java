package MyLinkedList;

public class MyLinkedList {
    SgNode<?> head = null;
    SgNode<?> tail = null;

    public void clear() {
        this.head = null;
        this.tail = null;
    }

    public boolean isEmpty() {
        return head == null && tail == null;
    }

    public <T> int indexOf(T data) {
        SgNode<?> current = this.head;
        int i = 0;
        while (i < size()) {
            if (current.data.equals(data)) return i;
            current = current.next;
            i++;
        }
        return -1;
    }

    public <T> boolean contains(T data) {
        SgNode<?> current = this.head;
        int i = 0;
        while (i < size()) {
            if (current.data.equals(data)) return true;
            current = current.next;
            i++;
        }
        return false;
    }

    public int size() {
        int size = 0;
        SgNode<?> current = this.head;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    public <T> void add(T value) {
        SgNode<T> data = new SgNode<T>(value);
        if(tail != null) {
            data.prev = tail;
            tail.next = data;
        } else head = data;
        tail = data;
    }

    public <T> void insert(int index, T value) {
        if (index != 0 && (index >= size() || index < 0)) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }

        // List is Empty 일 경우.
        if(isEmpty()) {
            add(value);
            return;
        }

        SgNode<?> current = getNode(index);
        SgNode<T> data = new SgNode<T>(value);
        data.prev = current.prev;
        data.next = current;

        data.prev.next = data;
        current.prev = data;

        if(index == 0) head = data;
    }

    private SgNode<?> getNode(int index) {
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }
        SgNode<?> current = head;
        int cnt = 0;
        while(index != cnt) {
            current = current.next;
            cnt++;
        }
        return current;
    }

    public <T> T get(int index) {
        return (T)(getNode(index).data);
    }

    public <T> T remove(int index) {
        if (index >= size() || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size());
        }

        SgNode<?> current = getNode(index);

        SgNode<?> curPrev = current.prev;
        SgNode<?> curNext = current.next;
        curPrev.next = curNext;
        curNext.prev = curPrev;

        return (T)(current.data);
    }
}