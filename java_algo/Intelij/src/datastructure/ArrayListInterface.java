package datastructure;

public interface ArrayListInterface {
    // 뒤에부터 추가
    boolean add(int num);
    // 인덱스 에 의한 추가
    boolean add(int num,int idx);
    // 처음 발견한 숫자 삭제
    boolean removeByNum(int num);
    // 인덱스 에 의한 삭제
    boolean removeByIdx(int idx);
    // 인덱스 로 값 가져오기
    int get(int idx);
    boolean isEmpty();
    boolean contains(int num);
    int size();
}
