import MyLinkedList.MyLinkedList;

public class Main {
    public static void main(String[] args) {

        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.add("addd");
        myLinkedList.add(123);
        myLinkedList.add(123.456);
        myLinkedList.add(true);
        myLinkedList.add(new Object());
        for (int i = 0; i < myLinkedList.size(); i++) {
            System.out.println(myLinkedList.get(i));
            System.out.println(myLinkedList.get(i).getClass().getSimpleName());
        }

    }
}