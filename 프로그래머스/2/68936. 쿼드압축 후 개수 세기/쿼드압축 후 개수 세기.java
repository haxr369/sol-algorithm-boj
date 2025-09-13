class Solution {
    
    private int zeroCnt = 0;
    private int oneCnt = 0;
    
    public int[] solution(int[][] arr) {
        
        int N = arr[0].length;
        int[][] compressed = new int[N+4][N+4];
        
        // NxN 면적을 체크하고 쪼개기
        dfs(arr, 0, 0, N);
        
        int[] answer = {zeroCnt, oneCnt};
        return answer;
    }
    // compressArea
    // NxN 면적을 dfs 이용해서 4개 사분면으로 분리
    // 1사분면, 2사분면, 3사분면, 4사분면
    // 1: y: 0 x (N//2 - 1)
    //    x: N//2 x N-1  

    // 2: y: 0 x (N//2 - 1)
    //    x: 0 x (N//2 - 1)

    // 3: y: N//2 x N-1  
    //    x: 0 x (N//2 - 1)
    // 4: y: N//2 x N-1  
    //    x: N//2 x N-1  
    // checkArea가 true거나 N이 1일 경우 해당 면적은 완성된걸로 치고 return
    public void dfs(int[][] arr, int sy, int sx, int N){
        if(checkArea(arr, sy, sx, N) || N == 1){
            // printArea(arr, sy, sx, N);
            // System.out.println("sy->"+sy+" sx->"+sx+" N->"+N+" stdValue->"+arr[sy][sx]);
            // 압축하기
            compressArea(arr[sy][sx]);
            return;
        }
        
        // 1사분면 호출
        dfs(arr, sy, sx+N/2, N/2);
        // 2사분면
        dfs(arr, sy, sx, N/2);
        // 3사분면
        dfs(arr, sy+N/2, sx, N/2);
        // 4사분면
        dfs(arr, sy+N/2, sx+N/2, N/2);
        return ;
    }
    
    public void printArea(int[][] arr, int sy, int sx, int N){
        StringBuilder sb = new StringBuilder();
        for(int i = sy; i<sy+N; i++){
            for(int j=sx; j<sx+N; j++){
                sb.append(arr[i][j]).append(" ");
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }
    
    
    
    
    // sy,sx 기준의 NxN 크기의 사분면을 체크하기
    public boolean checkArea(int[][] arr, int sy, int sx, int N){
        
        int stdValue = arr[sy][sx];
        
        for(int i = sy; i<sy+N; i++){
            for(int j=sx; j<sx+N; j++){
                if(arr[i][j] != stdValue){
                    // System.out.println("        check fail i->"+i+" j->"+j);
                    return false;
                }
            }
        }
        // System.out.println("        check success");
        return true;
    }
    
    // 압축 가능한 면적을 stdValue로 압축하기
    // sy, sx 기준의 NxN 크기의 사분면을 압축하기
    public void compressArea(int stdValue){
        // for(int i = sy; i<sy+N; i++){
        //     for(int j=sx; j<sx+N; j++){
        //         compressed[i][j] = 1;
        //     }
        // }
        if(stdValue == 0){
            this.addZero();
        } else{
            this.addOne();
        }
    }
    
    private void addZero(){
        this.zeroCnt+=1;
    }
    
    private void addOne(){
        this.oneCnt+=1;
    }
}

