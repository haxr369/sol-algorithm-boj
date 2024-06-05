/**
뒷 큰수는 자신 보다 뒤에 있으면서 큰 첫번째 숫자

최악은 전체를 탐색하게 되는 문제가 있음

그렇다면 우선순위 큐를 이용할까?

**/
import java.util.*;
class Node{
    int data;
    int idx;
    public Node(int data, int idx){
        this.data = data;
        this.idx = idx;
    }
}

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Arrays.fill(answer, -1);
        
        Queue<Node> qu = new PriorityQueue<>(new Comparator<Node>(){
            @Override
            public int compare(Node a, Node b){
                return a.data - b.data;
            }
        });
        qu.add(new Node(numbers[0], 0));
        for(int i=1; i<numbers.length; i++){
            int top = qu.peek().data;
            if(numbers[i] <= top){
                qu.add(new Node(numbers[i], i));
            } else{ // 큐에 있는거 빼기
                do{
                    Node n = qu.poll();
                    answer[n.idx] = numbers[i];    
                } while(!qu.isEmpty() && (qu.peek().data < numbers[i]));
                qu.add(new Node(numbers[i], i));
            }
        }
        
        while(!qu.isEmpty()){
            Node n = qu.poll();
            answer[n.idx] = -1;  
        }
        
        return answer;
    }
}