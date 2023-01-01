package datastructure;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GuiwooArrayListTest {

    @Nested
    @DisplayName("시간 비교용")
    public class BetweenMyArrayList{
        private final int num =(int) 655361;
        @Test
        void myOne(){
            long l2 = System.currentTimeMillis();
            ArrayList<Integer> list = new ArrayList<>();
            for(int i=0;i<num;i++){
                list.add(i);
            }
            long l3 = System.currentTimeMillis();
            System.out.println(l3-l2);
            System.out.println(list.size());


            long l = System.currentTimeMillis();
            GuiwooArrayList guiwoo = new GuiwooArrayList();
            for(int i =0;i<num;i++){
                guiwoo.add(i);
            }
            long l1 = System.currentTimeMillis();
            System.out.println(l1-l);
            System.out.println(guiwoo.size());
        }
    }

    @Test
    void copyTest() throws Exception{
        System.out.println( 1e6 );
    }

    @Test
    void addTest(){
        int target = 11;
        GuiwooArrayList guiwoo = new GuiwooArrayList();
        for (int i = 0; i < target; i++) {
            guiwoo.add(i);
        }
        Assertions.assertSame(target,guiwoo.size());
    }

    @Test
    void addNumByIdx(){
        int targetNum = 100;
        int targetIdx = 5;
        GuiwooArrayList guiwoo = new GuiwooArrayList();
        for (int i = 0; i < 5; i++) {
            guiwoo.add(i);
        }
        Assertions.assertThrows(ArrayIndexOutOfBoundsException.class,
                ()->guiwoo.add(targetNum,targetIdx));
    }

    @Test
    void addNumByidx(){
        int targetNum = 100;
        int targetIdx = 2;
        GuiwooArrayList guiwoo = new GuiwooArrayList();
        for (int i = 0; i < 5; i++) {
            guiwoo.add(i);
        }
        guiwoo.add(targetNum,targetIdx);
        Assertions.assertEquals(guiwoo.get(targetIdx),targetNum);
        Assertions.assertEquals(guiwoo.size(),6);
    }

    @Test
    void removeTestByNumber() throws Exception{
        int target = 11;
        GuiwooArrayList guiwoo = new GuiwooArrayList();
        for (int i = 0; i < target; i++) {
            guiwoo.add(i);
        }
        guiwoo.removeByNum(6);
        Assertions.assertSame(guiwoo.size(),target-1);
        guiwoo.add(15);
        Assertions.assertSame(15,guiwoo.get(guiwoo.size()-1));
    }

    @Test
    void removeByIdx(){
        int target = 11;
        GuiwooArrayList guiwoo = new GuiwooArrayList();
        for (int i = 0; i < target; i++) {
            guiwoo.add(i);
        }
        guiwoo.removeByIdx(6);
        Assertions.assertSame(7,guiwoo.get(6));
    }
}