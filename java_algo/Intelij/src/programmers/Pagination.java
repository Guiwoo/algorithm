package programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Pagination {
    public static void main(String[] args) {
        List<List<String>> items = new ArrayList<>();
        items.add(List.of("owjevtkuyv","58584272","62930912"));
        items.add(List.of("rpaqgbjxik","9425650","96088250"));
        items.add(List.of("dfbkasyqcn","37469674","46363982"));
        items.add(List.of("vjrrisdfxe","1866489","88046739"));
        fetchItemsToDisplay(items,2,1,2,0);

    }
    private static int sortParameter;
    private static int sortOrder;

    public static List<String> fetchItemsToDisplay(
            List<List<String>> items,
            int sortParameter,
            int sortOrder,
            int itemsPerPage,
            int pageNumber) {
        sortParameter =sortParameter;
        sortOrder = sortOrder;

        SortParameter custom = new SortParameter();
        Collections.sort(items,custom);
        List<String> answer = new ArrayList<>();
        int start = pageNumber * itemsPerPage;
        for (int i = start; i < start+itemsPerPage && i < items.size(); i++) {
            answer.add(items.get(i).get(0));
        }
        System.out.println(answer);
        return answer;
    }

    static class SortParameter implements Comparator<List<String>>{
        @Override
        public int compare(List<String> o1, List<String> o2) {
            if(sortOrder == 0){
                Integer l = getInteger(o1, o2);
                if (l != null) return l;
                return o1.get(sortParameter).compareTo(o2.get(sortParameter));
            }
            Integer l = getInteger(o1, o2);
            if (l != null) return l;
            return o2.get(sortParameter).compareTo(o1.get(sortParameter));
        }

        private Integer getInteger(List<String> o1, List<String> o2) {
            if(sortParameter > 0){
                long l = Long.parseLong(o1.get(sortParameter));
                long c = Long.parseLong(o2.get(sortParameter));
                return (int) (l - c);
            }
            return null;
        }
    }

}

