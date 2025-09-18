/**
 당신이 모은 양의 수보다 늑대의 수가 같거나 더 많아지면 바로 모든 양을 잡아먹어 버립니다
 당신은 중간에 양이 늑대에게 잡아먹히지 않도록 하면서 
 최대한 많은 수의 양을 모아서 다시 루트 노드로 돌아오려 합니다.
 
 0은 양, 1은 늑대를 의미합니다.
 
**/
import java.util.*;
class Solution {
    int answer = 0;
    
    static class Carry{
        int sheep = 0;
        int wolf = 0;
        List<Integer> nodeList;
        public String toString(){
            return "sheep->"+sheep+" wolf->"+wolf+" nodeList->"+nodeList.toString();
        }
        public Carry(int sheep, int wolf, List<Integer> nodeList){
            this.sheep = sheep;
            this.wolf = wolf;
            this.nodeList = nodeList;
        }
    }
    public int solution(int[] info, int[][] edges) {
        List<List<Integer>> edgeList = new ArrayList<>();
        for(int i=0; i<info.length; i++){
            List<Integer> temp = new ArrayList<>();
            for(int j=0; j<edges.length; j++){
                if(edges[j][0] == i){
                    temp.add(edges[j][1]);
                }
            }
            edgeList.add(temp);
        }
        
        int ans = 0;
        // 0번째 노드는 무조건 양이기 때문에 셋팅
        List<Integer> initialList = deepCopy(edgeList.get(0));
        
        Carry status = new Carry(1,0, initialList);
        Queue<Carry> qu = new LinkedList<>();
        qu.add(status);
        // int t = 0;
        while(!qu.isEmpty()){
            Carry c = qu.poll();
            // 최대 양 개수 저장하기
            ans = Integer.max(ans, c.sheep);
            // System.out.println("기준 상태->"+c.toString());

            for(int i=0; i< c.nodeList.size(); i++){
                int idx = c.nodeList.get(i);
                
                // 이미 존재하는 노드는 스킵!
                // System.out.println("여기 근처->"+idx);
                // 탐색 가능한지 판단
                if(info[idx] == 1 && (c.sheep <= c.wolf+1)){ // 양
                    continue;
                }

                int nxtSheep = c.sheep;
                int nxtWolf = c.wolf;
                if(info[idx] == 1){
                    nxtWolf += 1;
                } else {
                    nxtSheep += 1;
                }
                List<Integer> copiedList = deepCopy(c.nodeList);
                copiedList.remove(i);
                // 현재 상태에서 탐색 가능한 노드만 추가
                for(int ndx : edgeList.get(idx)){
                    if(info[ndx] == 1 && nxtSheep <= nxtWolf + 1){
                        continue;
                    }
                    copiedList.add(ndx);
                }
                    
                Carry nxtC = new Carry(nxtSheep, nxtWolf, copiedList);
                // System.out.println("이거 추가!!!!!!->"+nxtC.toString());
                qu.add(nxtC);
            }
            
            // if(t>10){
            //     break;
            // }
        }
        
        return ans;
    }
    
    private List<Integer> deepCopy(List<Integer> list){
        List<Integer> li = new ArrayList<>();
        for(int l : list){
            li.add(l);
        }
        return li;
    }
    
    
    /*
    bfs인데 상태를 가지고 돌기
    
    1. 상태 저장 (양 개수, 늑대 개수, [상태 노드 리스트])
    1.1. 상태의 양 개수 max 값 static에 저장.
    2. 상태 노드 리스트 근처에 있는 노드 for문으로 탐색
    3. 탐색 가능한 경우 다음 상태 생성
        상태(양 개수, 늑대 개수, [기존 리스트 + 신규 노드])
    */
    
    
    
}