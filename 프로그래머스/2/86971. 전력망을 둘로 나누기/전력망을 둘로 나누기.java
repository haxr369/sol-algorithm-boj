class Solution {

    private static int[][] mp;
    private static int[] visited;
    
    public int solution(int n, int[][] wires) {
        int answer = 999999;
        int wireCnt = wires.length;
        mp = new int[n+4][n+4]; // 연결행렬 초기화
        for(int i=0; i<wireCnt; i++){
            int y = wires[i][0]-1;
            int x = wires[i][1]-1;
            mp[y][x] = 1;
            mp[x][y] = 1;
        }
        
        for(int i=0; i<n; i++){
            for(int j=i; j<n; j++){
                // 연결 안된 전력망은 스킵!
                if(mp[i][j] == 0 && mp[j][i] == 0)
                    continue;
                // 한 연결 끊고 각 전력망 개수를 구하기
                visited = new int[104];
                mp[i][j] = 0;
                mp[j][i] = 0;
                visited[i] = 1;
                visited[j] = 1;
                int iN = dfs(i); // i부터 시작되는 전력망 노드 개수 구하기
                int jN = dfs(j);
                // System.out.println("i->"+i+" j->"+j);
                // System.out.println("===== iN->"+iN+" jN->"+jN);
                int diff = iN - jN;
                if(diff < 0)
                    diff *= -1;
                answer = Integer.min(answer, diff);
                mp[i][j] = 1;
                mp[j][i] = 1;
            }
        }
        
        
        return answer;
    }
    
    private int dfs(int node){
        int rslt = 1;
        
        for(int j=0; j < mp[node].length; j++){
            // 방문했거나 연결 안된 노드는 스킵
            if(visited[j] == 1 || mp[node][j] != 1){
                continue;
            }
            visited[j] = 1;
            rslt += dfs(j);
        }
        
        return rslt;
    }
}