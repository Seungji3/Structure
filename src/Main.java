import MyLinkedList.MyLinkedList;

public class Main {
    public static void main(String[] args) {

        MyLinkedList myLinkedList = new MyLinkedList();
        myLinkedList.add("addd");
        myLinkedList.add(123);
        myLinkedList.add(true);
        myLinkedList.add(new Object());
        myLinkedList.insert(0,123.456);
//        myLinkedList.remove(2);
        for (int i = 0; i < myLinkedList.size(); i++) {
            System.out.println(myLinkedList.get(i));
//            System.out.println(myLinkedList.get(i).getClass().getSimpleName());
        }
//        System.out.println(myLinkedList.size());
//        System.out.println(myLinkedList.remove(3));
//        System.out.println(myLinkedList.size());
    }
}