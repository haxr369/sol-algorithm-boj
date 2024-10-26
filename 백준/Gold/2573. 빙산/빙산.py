from collections import deque



N, M = map(int, input().split())
mp = []
nxtMp = []

for i in range(N):
  tmpRow = list(map(int, input().split()))
  mp.append(tmpRow)

years = 0

dy = [0,0,-1,1]
dx = [1,-1,0,0]

def meltIce(Y, X):
  
  area = 0
  qu = deque()
  qu.append([Y,X])
  visited = [[0 for _ in range(M) ] for _ in range(N)]
  visited[Y][X] = 1
  nowMp = [[0 for _ in range(M) ] for _ in range(N)]
  # print("nowMp start ---- ")
  # printIce(nowMp)
  while(qu):
    y, x = qu.popleft()
    # print(y,x)
    seaCnt = 0
    
    for i in range(4):
      ny = y + dy[i]
      nx = x + dx[i]
      # print("-",ny,nx)
      # 맵 내부 인지 체크
      if(ny<0 or nx<0 or ny>=N or nx>=M):
        continue
      # print("mp : ",mp[ny][nx]," visited : ",visited[ny][nx])
      # y,x 주변 바다 개수 카운트
      if(mp[ny][nx] == 0):
        seaCnt += 1
      elif(visited[ny][nx] == 0 and mp[ny][nx] != 0): # 방문하지 않았던 위치
        # print("---- ",ny, " : ",nx)
        qu.append([ny, nx])
        visited[ny][nx] = 1
    # 녹고 난 후 빙산 높이
    nowMp[y][x] = max(0, mp[y][x] - seaCnt)
    # print("mp : ",mp[y][x], "now : ",nowMp[y][x], " sea : ",seaCnt)
    if(nowMp[y][x] != 0):
      area+=1 # 예상 높이++
  # print("nowMp end ---- ")
  # printIce(nowMp)
  return area, nowMp

def bfs(y, x):
  area = 0
  qu = deque()
  qu.append([y,x])
  visited = [[0 for _ in range(M) ] for _ in range(N)]
  visited[y][x] = 1
  while(qu):
    y, x = qu.popleft()
    area+=1
    # print(y,x)
    for i in range(4):
      ny = y + dy[i]
      nx = x + dx[i]
      if(ny<0 or nx<0 or ny>=N or nx>=M 
         or visited [ny][nx] != 0 or mp[ny][nx] == 0):
        continue
      visited[ny][nx] = 1
      qu.append([ny, nx])
      
  return area


def printIce(li):
  for i in range(N):
    for j in range(M):
      print(li[i][j], end = "")
    print()

# printIce(mp)
while(True):
  # print("year : ",years)
  expArea = -1 # 녹은 후 빙산의 예상 넓이 
  # 빙산을 녹이고, mp를 업데이트
  for i in range(N):
    for j in range(M):
      if(mp[i][j] != 0):
        expArea, mp = meltIce(i, j)
        
        break
    if(expArea != -1) : break
  # print("--------- year : ",years,"----------------")
  # printIce(mp)
  if(expArea==0):
    years = 0
    break
  realArea = 0 # 녹은 후 빙산의 실제 넓이
  for i in range(N):
    for j in range(M):
      if(mp[i][j] != 0):
        realArea = bfs(i, j)
        break
    if(realArea != 0) : break
    
  # print("realArea : ",realArea," expArea : ",expArea)
  years+=1
  if(expArea != realArea):
    break
  

print(years)

