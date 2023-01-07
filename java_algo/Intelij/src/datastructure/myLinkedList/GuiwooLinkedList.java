package datastructure.myLinkedList;

import java.util.LinkedList;

public class GuiwooLinkedList implements LinkedListInterface {

    private int total;
    private GuiwooNode first;
    private GuiwooNode last;

    public GuiwooLinkedList() {
        this.total = 0;
        this.first = null;
        this.last = null;
    }

    @Override
    public void add(int index, int number) {
        if(index >= this.size()) throw new IndexOutOfBoundsException();
        if(this.size() == 0 || this.size() == index+1){
            this.add(number);
            return;
        }

        if(index == 0){
            first = new GuiwooNode(number,first);
        }else{
            int count = 0;
            GuiwooNode current = this.first;
            GuiwooNode previous = this.first;
            while(count < index){
                previous = current;
                current = current.next;
                count++;
            }
            previous.next = new GuiwooNode(number,current);
        }
        total++;
    }

    @Override
    public boolean add(int number) {
        if(first == null){
            first = new GuiwooNode(number);
            first.next = first;
            last = first;
        }else{
            GuiwooNode next = new GuiwooNode(number);
            last.next = next;
            last = next;
        }
        total++;
        return true;
    }

    @Override
    public void addFirst(int number) {
        if(this.size() == 0){
            this.add(number);
        }else{
            first = new GuiwooNode(number,first);
        }
    }

    @Override
    public void addLast(int number) {
        this.add(number);
    }

    @Override
    public boolean contains(int number) {
        return this.indexOf(number) >= 0;
    }

    @Override
    public int get(int index) {
        if(index >= this.size()) throw new IndexOutOfBoundsException();
        GuiwooNode current = this.first;
        int cnt = 0;
        while(cnt < index){
            current = current.next;
            cnt++;
        }
        return current.data;
    }

    @Override
    public int getFirst() {
        if(first == null) throw new NullPointerException();
        return first.data;
    }

    @Override
    public int getLast() {
        if(first == null) throw new NullPointerException();
        return last.data;
    }

    @Override
    public int indexOf(int number) {
        GuiwooNode current = this.first;
        int cnt = 0;
        while(current != null){
            if(current.data == number) return cnt;
            cnt++;
            current = current.next;
        }
        return -1;
    }

    @Override
    public boolean offer(int number) {
        try{
            return this.add(number);
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean offerFirst(int number) {
        try{
            this.addFirst(number);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean offerLast(int number) {
        try{
            this.addLast(number);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public int peek() {
        return this.getFirst();
    }

    @Override
    public int peekFirst() {
        return this.getFirst();
    }

    @Override
    public int peekLast() {
        return this.getLast();
    }

    @Override
    public int poll() {
        if(first == null) throw new NullPointerException();
        int value = first.data;
        first = first.next;
        total--;
        return value;
    }

    @Override
    public int pollFirst() {
        return this.poll();
    }

    @Override
    public int pollLast() {
        GuiwooNode current = first;
        GuiwooNode previous = first;
        while(true){
            if(current.next == null) break;
            previous = current;
            current = current.next;
        }
        previous.next = null;
        return current.data;
    }

    @Override
    public int pop() {
        return this.pollLast();
    }

    @Override
    public void push(int number) {
        this.add(number);
    }

    @Override
    public int remove() {
        return this.pollLast();
    }

    @Override
    public int remove(int index) {
        if(index >= this.size()) throw new IndexOutOfBoundsException();
        if(index == 0) return this.poll();
        else if(index+1 == this.size()){
            return this.pollLast();
        }else{
            int cnt = 0;
            GuiwooNode current = first;
            GuiwooNode previous = first;
            while(cnt < index){
                cnt++;
                previous = current;
                current = current.next;
            }
            previous.next = current.next;
            return current.data;
        }
    }

    @Override
    public int removeFirst() {return this.pollFirst();}

    @Override
    public int removeLast() {
        return this.pollLast();
    }

    @Override
    public int set(int index, int number) {
        if(index >= this.size()) throw new IndexOutOfBoundsException();
        GuiwooNode current = first;
        int count = 0;
        while(count <index){
            if(current != null) current = current.next;
            count++;
        }
        current.data = number;
        return number;
    }

    @Override
    public int size() {
        return this.total;
    }
}
