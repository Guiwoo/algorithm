package datastructure.myLinkedList;

public class GuiwooNode {
    public int data;
    public GuiwooNode next;

    public GuiwooNode() {
        this.next = null;
    }

    public GuiwooNode(int data){
        this.data = data;
        this.next = null;
    }

    public GuiwooNode(int data, GuiwooNode next) {
        this.data = data;
        this.next = next;
    }

    public String toString(){
        return "나의 리스트 노드 => " + data ;
    }
}
