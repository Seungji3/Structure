package MyLinkedList;

public class SgNode<T> {
    public T data = null;
    public SgNode<?> prev = null;
    public SgNode<?> next = null;
    public SgNode(T data) {
        this.data = data;
    }
}
