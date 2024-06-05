/**
1. 'O' 와 'X'의 개수가 같거나 'O'가 하나 더 많아야한다.
2. 그림이 연속 3개가 나오면 안된다.

**/

class Solution {
    public int solution(String[] board) {
        int answer = -1;
        
        int[][] mp = new int[4][4];
        int[] cnts = {0,0};
        
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[i].length(); j++){
                if(board[i].charAt(j)=='O'){
                    mp[i][j] = 1;
                    cnts[0]++;
                } else if(board[i].charAt(j)=='X'){
                    mp[i][j] = 2;
                    cnts[1]++;
                } else{
                    mp[i][j] = 0;
                }
            }
        }
        // 1. 'O' 와 'X'의 개수가 같거나 'O'가 하나 더 많아야한다.
        if(cnts[0] == cnts[1] || cnts[0] - cnts[1] == 1){
            // System.out.println("O : "+cnts[0]+" X : "+cnts[1]);
            // 연속으로 3개가 있는지 찾기 - 가로
            boolean isChanged = false;
            for(int i=0; i<3; i++){
                int ret = mp[i][0];
                isChanged = false;
                for(int j=1; j<3; j++){
                    if(ret != mp[i][j]){
                        isChanged = true;
                    }
                }
                // O가 연속 3개 나온 경우, X가 하나 없어야한다.
                if(!isChanged && ret==1 && (cnts[0]-cnts[1] != 1)){
                    return 0;
                } else if(!isChanged && ret==2 && (cnts[0]-cnts[1] != 0)){
                    // X가 연속 3개 나온 경우, 같아야한다.
                    return 0;
                }
            }

            // 연속으로 3개가 있는지 찾기 - 세로
            for(int j=0; j<3; j++){
                int ret = mp[0][j];
                isChanged = false;
                for(int i=1; i<3; i++){
                    if(ret != mp[i][j]){
                        isChanged = true;
                    }
                }
                // O가 연속 3개 나온 경우, X가 하나 없어야한다.
                if(!isChanged && ret==1 && (cnts[0]-cnts[1] != 1)){
                    return 0;
                } else if(!isChanged && ret==2 && (cnts[0]-cnts[1] != 0)){
                    // X가 연속 3개 나온 경우, 같아야한다.
                    return 0;
                }
            }
            
            // 연속으로 3개가 있은지 찾기 - 왼쪽 위 시작하는 대각
            isChanged = false;
            int ret = mp[0][0];
            for(int i=1; i<3; i++){
                if(ret != mp[i][i]){
                    isChanged = true;
                }
            }
            // O가 연속 3개 나온 경우, X가 하나 없어야한다.
            if(!isChanged && ret==1 && (cnts[0]-cnts[1] != 1)){
                return 0;
            } else if(!isChanged && ret==2 && (cnts[0]-cnts[1] != 0)){
                // X가 연속 3개 나온 경우, 같아야한다.
                return 0;
            }
            
            // 연속으로 3개가 있은지 찾기 - 오른쪽 위 시작하는 대각
            isChanged = false;
            ret = mp[0][2];
            for(int i=1; i<3; i++){
                if(ret != mp[i][2-i]){
                    isChanged = true;
                }
            }
            // O가 연속 3개 나온 경우, X가 하나 없어야한다.
            if(!isChanged && ret==1 && (cnts[0]-cnts[1] != 1)){
                return 0;
            } else if(!isChanged && ret==2 && (cnts[0]-cnts[1] != 0)){
                // X가 연속 3개 나온 경우, 같아야한다.
                return 0;
            }

            
            return 1;
        }
        
        return 0;
    }
}