"""
정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 탐색을 시작할 정점의 번호 V
 정점 번호는 1번부터 N번까지이
단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문


 더 이상 방문할 수 있는 점이 없는 경우 종료한
 

"""

from collections import deque

def dfs(node):
  global dfsVisited, dfsResult

  # print(node)
  dfsVisited[node] = 1
  dfsResult+=str(node)+" "

  for nxtNode in adjList[node]:
    if(dfsVisited[nxtNode] == 0):
      dfs(nxtNode)



N,M,V = map(int, input().split())

# 1~N의 연결리스트
adjList = [[] for _ in range(N+1)]

for _ in range(M):
  n1, n2 = map(int, input().split())
  adjList[n1].append(n2)
  adjList[n2].append(n1)

for n in range(N+1):
  adjList[n].sort()
# print(adjList)

dfsVisited = [0 for _ in range(N+1)]
dfsResult = ""
bfsVisited = [0 for _ in range(N+1)]
bfsResult = ""

dfs(V)

print(dfsResult)

qu = deque()

qu.append(V)
bfsVisited[V] = 1
while(len(qu) != 0):
  node = qu.popleft()
  
  bfsResult += str(node)+" "
  for nxt in adjList[node]:
    if(bfsVisited[nxt] == 0):
      bfsVisited[nxt] = 1
      qu.append(nxt)

print(bfsResult)

