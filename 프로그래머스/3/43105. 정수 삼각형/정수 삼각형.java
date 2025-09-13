class Solution {
    public int solution(int[][] triangle) {
        int H = triangle.length; // 삼각형의 높이
        
        // for(int i=0; i<triangle.length; i++){
        //     System.out.print(triangle[i].length);
        //     for(int j=0; j<triangle[i].length; j++){
        //         System.out.print(triangle[i][j]+" ");
        //     }
        //     System.out.print('\n');
        // }
        int answer = 0;
        int[][] DP = new int[H][H];
        // 아래부터 올라가는 구조
        // DP[i][j] = i,j 위치에서 최대 숫자 합
        // 맨 아래 초기화
        for(int j=0; j<H; j++){
            DP[H-1][j] = triangle[H-1][j];
        }
        for(int i=H-2; i>=0; i--){
            for(int j=0; j<triangle[i].length; j++){
                int btom = Integer.max(DP[i+1][j], DP[i+1][j+1]);
                DP[i][j] = btom + triangle[i][j];
            }
        }
        
        
        // int answer = dfs(triangle, 0, 0, H);
        return DP[0][0];
    }
    
    // dfs H 높이의 최대 합을 구하기
    // 기준 y, x
    // 왼쪽 이동: y-1, x
    // 오른쪽 이동: y-1, x+1
    
    public int dfs(int[][] triangle, int Y, int X, int H){
        // 높이가 0이면 더 이상 더하지 않고 0을 출력한다.
        if(Y == H){
            return 0;
        }
        // printLevel(triangle, Y);
        int acc = triangle[Y][X], left = 0, right = 0;

        left = dfs(triangle, Y+1, X, H);  
        right = dfs(triangle, Y+1, X+1, H);    
        return Integer.max(left, right) + acc;   
    }
    public void printLevel(int[][] t, int Y){
        for(int i =0; i<t[Y].length; i++){
            System.out.print(t[Y][i]+" ");
        }
        System.out.print('\n');
    }
    
}