package datastructure.myLinkedList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GuiwooLinkedListTest {
    private List<Integer> list = new LinkedList<>();
    private GuiwooLinkedList gll;
    private int listSize = 10;
    @BeforeEach
    void init(){
        gll = new GuiwooLinkedList();
        for(int i=1;i<=listSize;i++){
            gll.add(i);
        }
    }

    @Test
    void addBasic(){
        int i = gll.indexOf(10);
        assertEquals(9,i);
    }

    @Test
    void contains(){
        boolean contains = gll.contains(100);
        assertFalse(contains);
    }

    @Test
    void sizeCheck() throws Exception{
        int size = gll.size();
        assertEquals(listSize,size );
    }

    @Test
    void addNumberByIndex() throws Exception{
        gll.add(0,100);
        int i = gll.indexOf(100);
        // 맨 앞에 추가할때
        assertEquals(0,i);
        // 가운데 추가할때
        gll.add(5,500);
        int i1 = gll.indexOf(500);
        assertEquals(5,i1);

        assertEquals(listSize+2,gll.size());
    }

    @Test
    void addFirst(){
        gll.addFirst(10000);
        int i = gll.indexOf(10000);
        assertEquals(i,0);
    }

    @Test
    void addLast() throws Exception{
        gll.add(20000);
        int i = gll.indexOf(20000);
        assertTrue(gll.contains(20000));
        assertEquals(listSize,i);
        assertEquals(listSize+1,gll.size());
    }

    @Test
    void getByIndex() throws Exception{
        int i = gll.get(0);
        assertEquals(1,i);
        int i1 = gll.get(9);
        assertEquals(10,i1);
    }

    @Test
    void getFirst() throws Exception{
        int first = gll.getFirst();
        assertEquals(1,first);
    }

    @Test
    void getLast() throws Exception{
        int last = gll.getLast();
        assertEquals(10,last);
    }

    @Test
    void peek() throws Exception{
        int peek = gll.peek();
        assertEquals(1,peek);
        int i = gll.peekFirst();
        assertEquals(1,peek);
        int i1 = gll.peekLast();
        assertEquals(10,i1);
    }

    @Test
    void poll() throws Exception{
        for(int i=1;i<=listSize;i++){
            int poll = gll.poll();
            assertEquals(poll,i);
        }
    }

    @Test
    void pollLast () throws Exception{
        int i = gll.pollLast();
        assertEquals(10,i);
    }

    @Test
    void removeByIndex () throws Exception{
        gll.remove(5);
        int i = gll.indexOf(6);
        int i1 = gll.get(5);
        assertEquals(i,-1);
        assertEquals(i1,7);
    }

    @Test
    void set() throws Exception{
        gll.set(9,1000);
        int i = gll.indexOf(1000);
        assertEquals(9,i);
    }

}
