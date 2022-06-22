package stackQueueLearn;

import java.util.*;

public class MyQueue {
    private List<Integer> data;
    private int p_start;

    MyQueue() {
        this.data = new ArrayList<Integer>();
        p_start = 0;
    }

    public boolean enQueue(int val) {
        this.data.add(val);
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        p_start++;
        return true;
    }

    public int Front() {
        return data.get(p_start);
    }

    public boolean isEmpty() {
        return p_start >= data.size();
    }
}
