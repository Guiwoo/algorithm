package datastructure.myArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class GuiwooArrayList implements ArrayListInterface{
    private ArrayList<Integer> list = new ArrayList<>();
    private Vector<Integer> list2 = new Vector<>();
    private int[] guiwoo;
    private int total;

    public GuiwooArrayList() {
        this.guiwoo = new int[10];
        this.total = 0;
    }
    @Override
    public boolean add(int a){
        expendArray();
        guiwoo[total++] = a;
        return true;
    }

    @Override
    public boolean add(int num, int idx) {
        if(idx >= this.size()) throw new ArrayIndexOutOfBoundsException();
        expendArray();
        int[] temp = new int[this.size()-idx-1];
        System.arraycopy(guiwoo,idx,temp,0,temp.length);
        System.arraycopy(temp,0,guiwoo,idx+1,temp.length);
        guiwoo[idx] = num;
        total++;
        return true;
    }
    private void expendArray(){
        if(this.size() == guiwoo.length){
            int[] next = new int[this.size()*2];
            System.arraycopy(this.guiwoo,0,next,0,guiwoo.length);
            guiwoo = next;
        }
    }

    @Override
    public boolean removeByNum(int num){
        int idx = -1;
        for(int i=0;i<this.size();i++){
            if(num == guiwoo[i]){
                idx = i;
                break;
            }
        }
        return this.removeByIdx(idx);
    }
    @Override
    public boolean removeByIdx(int idx){
        if(idx == -1) throw new IllegalArgumentException("없어요");
        int[] temp = new int[this.size()-idx-1];
        System.arraycopy(guiwoo,idx+1, guiwoo,idx,this.size()-idx-1);
        total--;
        return true;
    }
    @Override
    public int get(int idx){
        if(idx >= this.size()){
            throw new ArrayIndexOutOfBoundsException("허용 인덱스 범위  초과");
        }
        return this.guiwoo[idx];
    }
    @Override
    public boolean isEmpty(){
        return this.size() == 0 ;
    }

    @Override
    public boolean contains(int num) {
        for(int target : guiwoo){
            if(target == num) return true;
        }
        return false;
    }

    @Override
    public int size(){
        return this.total;
    }
    public String toString(){
        return Arrays.toString(this.guiwoo);
    }

}
