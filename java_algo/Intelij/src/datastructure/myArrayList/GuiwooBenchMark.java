package datastructure.myArrayList;

import org.openjdk.jmh.annotations.*;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5)
@Fork(1)
@Measurement(iterations = 10)
public class GuiwooBenchMark {

    @Param({"10","1000000"})
    public int size;

    @Benchmark
    public void arrayList(){
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
    }
    @Benchmark
    public void arrayListCustom(){
        GuiwooArrayList list = new GuiwooArrayList();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
    }
}
