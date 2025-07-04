import MyLinkedList.MyLinkedList;

public class Main {
    public static void main(String[] args) {

        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.insert(0,123.456);
        myLinkedList.add("addd");
        myLinkedList.add(123);
        myLinkedList.add(true);
        myLinkedList.add(new Object());
        myLinkedList.remove(2);

        for (int i = 0; i < myLinkedList.size(); i++) {
            System.out.printf("값: %s, 자료형: %s\n", myLinkedList.get(i), myLinkedList.get(i).getClass().getSimpleName());
        }
    }
}