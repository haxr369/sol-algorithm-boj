"""
1. 인접 리스트 생성
2. 팀번호는 1, 팀 구성원 수 0
3, 1번부터 n번까지 순회 i번째 사람부터 인접리스트 타기
  3-1. 빈 리스트에 i번째 사람부터 집어넣기 -> j
  3-2. 만약 팀번호가 0이 아닌 사람이 있다면?
    a. 같은 팀이라면
      리스트에서 j번째 사람이 다시 나올 때까지 빼면서 팀구성원 수 ++
    b. 다른 팀이라면
      검색 종료
4. 사람 수 - 팀 구성원 수
"""
import sys

sys.setrecursionlimit(10**6)
def adjSearch(i, wants, teamed, candi):
  
  # 3-1 i 근처 j 찾기
  j = wants[i]
  #print(i,"->",j)
  # 3-2 j가 i랑 같은 팀이라면 순환을 찾음!
  if teamed[j] == 0:
    teamed[j] = teamed[i]
    candi.append(j)
    return adjSearch(j, wants, teamed, candi)
  elif teamed[j] == teamed[i]:
    #print("순환 찾음!! ",i," -> ",j,"  ",teamed[i],"번 팀 ")
    # j 이후 사람들의 수를 찾기
    idx = candi.index(j)
    #print("idx : ",idx," 비팀원 수 : ",len(candi[:idx]))
    return len(candi[:idx])
  else:
    #print("비순환 찾음! ",j,"는 이미 다른 팀이다! 비팀원 수 : ",len(candi))
    return len(candi)

T = int(input())
while(T>0) : 
  N = int(input())
  teamNumb = 1
  answer = 0
  wants = list(map(int, input().split()))
  wants.insert(0,0)
  teamed = [0]*(N+1) # 사람이 몇번째 팀인지 

  for i in range(1, N+1) :
    # 이미 팀이 있는 사람은 스킵
    if teamed[i] !=0 :
      continue
    teamed[i] = teamNumb
    answer += adjSearch(i, wants, teamed, [i])
    teamNumb+=1
  print(answer)
  T-=1