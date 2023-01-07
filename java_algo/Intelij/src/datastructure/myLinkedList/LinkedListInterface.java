package datastructure.myLinkedList;

public interface LinkedListInterface {
    void add(int index,int number);
    boolean add(int number);
    void addFirst(int number);
    void addLast(int number);

    boolean contains(int number);

    int get(int index);
    int getFirst();
    int getLast();

    int indexOf(int number);

    boolean offer(int number);
    boolean offerFirst(int number);
    boolean offerLast(int number);

    int peek();
    int peekFirst();
    int peekLast();

    int poll();
    int pollFirst();
    int pollLast();

    int pop();
    void push(int number);

    int remove();
    int remove(int index);
    int removeFirst();
    int removeLast();

    int set(int index,int number);
    int size();
}
