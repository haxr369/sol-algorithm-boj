#include<bits/stdc++.h>
#include<iostream>
#include<queue>
using namespace std;

/*
당신은 (1, 1)에서 (N, M)의 위치까지 이동
최단 경로로 이동 -> bfs -> 모든 위치까지 최단 경로로 가기 때문에 한번 방문한 위치에 다시 갈 수 없다....
시작하는 칸과 끝나는 칸도 포함해서 센다 

벽을 한 개 까지 부수고 이동 -> 모든 벽에 대해서 탐색??

*/

int N,M, mp[1004][1004], visited[1004][1004][2], ans = INT_MAX;
string S;

struct posi
{
    /* data */
    int y;
    int x;
    bool passed; // 과거에 벽을 통과한 적이 존재하는 가?
};
queue<posi> qu;


const int dy[] = {1,-1,0,0};
const int dx[] = {0,0,1,-1};

void bfs(){
    visited[0][0][0] =1;
    qu.push({0,0,false});

    bool flag = false;
    while(qu.size()){
        posi p = qu.front(); qu.pop();
        //cout << "y : "<<p.y<<" x : "<<p.x<<" passed : "<<p.passed<<" ans : "<<visited[p.y][p.x][p.passed]<<'\n';
        for(int i=0; i<4; i++){
            int ny = p.y + dy[i];
            int nx = p.x + dx[i];
            if(ny<0 || nx<0 || ny>=N || nx>=M) continue;
            

            // 백트래킹 부분
            bool temp = false;
            if(mp[ny][nx]){ // 벽을 만났다!!.
                if(p.passed) continue; // 이전에 벽을 만난 경우.
                else temp = true; // 이전에 벽을 안 만난 경우.
            }
            else{ // 벽을 안 만났다.
                if(p.passed) temp = true; // 이전에 벽을 만난 경우.
            }

            if(visited[ny][nx][temp]) continue;

            visited[ny][nx][temp] = visited[p.y][p.x][p.passed] + 1;
            qu.push({ny, nx, temp});
            if(ny==N-1 && nx==M-1){
                flag= true;
                ans = min(ans, visited[ny][nx][temp]);
                break;
            }

        }
        if(flag)break;
    }
    return ;
}

int main(){
    ios_base::sync_with_stdio(false);
    cin.tie(NULL); cout.tie(NULL);
    cin >> N>>M;

    for(int i=0; i<N; i++){
        cin >> S;
        for(int j=0; j<M; j++){
            mp[i][j] = S[j]-'0';
        }
    }

    bfs();
    if(N-1==0&&M-1==0) cout << 1 <<'\n';
    else if(ans!=INT_MAX)cout << ans <<'\n';
    else cout << -1<<'\n';

    return 0;
}