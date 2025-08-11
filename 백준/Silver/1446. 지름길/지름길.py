"""
지름길의 개수 N과 고속도로의 길이 D
 시작 위치, 도착 위치, 지름길의 길이

길은 0부터 D까지 이어짐

i번째 지름길은 Node[i][0]에서 Node[i][1]로 이어짐
distance[i]만큼 떨어져있음

지름길은 단방향 간선을 의미

최대 12개의 간선이 존재

노드의 최대 개수는 10,000개 -> 2중배열보다는 연결리스트로 저장

[
[1]
...
[i+1], // i번째 노드는 i+1로 갈 수 있다.
[i+2, node[a][1]], // i+1이 node[a][0]일 때, node[a][0]로도 갈 수 있다!
...
[D, ]   // D-1에는 D로 갈 수 있고 // D-1이 node[b][0]일 때, node[b][0]로도 갈 수 있다!
]

bfs를 돌면서 다음 노드를 큐에 쌓기
minVisitedDistances 배열에는 최소 방문 거리 값을 저장하기

"""

from collections import deque

N, D = map(int, input().split())

# 인접리스트 초기화 1부터 D-1까지, 위치 0부터 D-1까지
adjList = [[[posi, 1]] for posi in range(1,100004)]

# 지름길 데이터 추가하기
for _ in range(N):
  node1, node2, d = map(int, input().split())
  adjList[node1].append([node2, d])

# print(adjList)

# 최소 방문 거리 값을 저장하기
minVisitedDistances = [100004]*10004

# bfs용 큐 생성
qu = deque([])

# 시작 노드 0과 그 거리 0을 넣기
qu.append([0,0])
minVisitedDistances[0] = 0

# 큐가 빌 때까지 계속 수행
while(len(qu) != 0):
  # 현재 방문 노드
  node, distance = qu.popleft()
  # print("node->",node," acc-distance->", distance)
  # 인접 노드를 찾기
  for info in adjList[node]:
    adjNode, adjDsitance = info

    # 다음 노드 예상 거리
    nxtDistacne = adjDsitance + distance

    # 인접노드가 방문 가능하면 굿
    if(minVisitedDistances[adjNode] <= nxtDistacne or nxtDistacne > D):
      # 굳이 방문 안함
      continue
    else:
      minVisitedDistances[adjNode] = nxtDistacne
      # 다음 위치와 누적 거리
      qu.append([adjNode, nxtDistacne])

  
print(minVisitedDistances[D])

