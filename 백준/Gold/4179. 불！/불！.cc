
/**
 * 지훈이와 불은 매 분마다 한칸씩 수평또는 수직으로(비스듬하게 이동하지 않는다) 이동한다.
 * 불은 각 지점에서 네 방향으로 확산된다. (bfs 최단거리, fireTime)
 * 
 * 지훈이는 미로의 가장자리에 접한 공간에서 탈출할 수 있다.
 * 
 *  #: 벽
    .: 지나갈 수 있는 공간
    J: 지훈이의 미로에서의 초기위치 (지나갈 수 있는 공간) J=1
    F: 불이 난 공간 0<= F <= 1,000,000

    탈출 불가하면 IMPOSSIBLE 출력.

    미로 크기 최대 1000x1000
    1 ≤ R, C ≤ 1000 이다.
    
    알고리즘
    1. 불의 최단 방문 시간 구하기
    2. 지훈을 bfs 할 때 지훈의 위치가 불 방문시간 보다 작을 때 이동 가능!!
    3. 지훈의 이동 결과 벽이 존재하면 이동 거리 +1
    3-1.                    존재 안하면 IMPOSSIBLE

*/

#include <bits/stdc++.h>
using namespace std;

const int maxL = 1004;
const int dy[] = {-1,1,0,0};
const int dx[] = {0,0,-1,1};

int R,C, visited[maxL][maxL],y,x, fireTime[maxL][maxL];
char A[maxL][maxL];
string temp;

pair<int,int> Jihun;
vector<pair<int,int>> fires;

int main(){
    cin >> R >> C;
    for(int i=0; i<R; i++){
        cin >> temp;
        for(int j=0; j<C; j++){
            A[i][j] = temp[j];
            if(A[i][j] == 'J') Jihun = {i,j};
            if(A[i][j] == 'F') fires.push_back({i,j});
        }
    }

    //불의 최단 방문 시간 구하기
    for(pair<int,int> fire : fires){
        queue<pair<int,int>> qp;
        qp.push({fire.first, fire.second});
        fireTime[fire.first][fire.second] = 1;

        //bfs
        while(qp.size()){
            tie(y,x) = qp.front(); qp.pop();
            
            for(int i=0; i<4; i++){
                int ny = y + dy[i]; 
                int nx = x + dx[i];
                if(ny <0 || nx <0 || ny >=R||nx >= C) continue;
                if(A[ny][nx] == '#') continue;
                if(!fireTime[ny][nx] || fireTime[ny][nx] > fireTime[y][x]+1){
                    fireTime[ny][nx] = fireTime[y][x]+1;
                    qp.push({ny,nx});
                }
            }
        }
    }

    //지훈이의 도망 시작 visited 쓰면 안되넹??

    queue<pair<int,int>> qp;
    qp.push({Jihun.first, Jihun.second});
    visited[Jihun.first][Jihun.second] = 1;

    //bfs
    bool flag = false;
    int domag = 1e9;
    while(qp.size()){
        tie(y,x) = qp.front(); qp.pop();
        
        if(y==0 || y == R-1 || x ==0 || x == C-1){
            flag = true;
            domag = min(domag, visited[y][x]);
        }

        for(int i=0; i<4; i++){
            int ny = y + dy[i]; 
            int nx = x + dx[i];
            if(ny <0 || nx <0 || ny >=R||nx >= C) continue;
            if(A[ny][nx] == '#') continue;
            if(visited[ny][nx]) continue;
            if(visited[y][x]+1 < fireTime[ny][nx] || !fireTime[ny][nx]){
                visited[ny][nx] = visited[y][x]+1;
                qp.push({ny,nx});
            }
        }
    }
    
    if(flag) cout << domag << '\n';
    else cout << "IMPOSSIBLE" << '\n';

    return 0;
}