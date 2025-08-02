# 영어 소문자만을 기록할 수 있는 편집기로, 최대 600,000글자입력

'''
맨 앞(첫 번째 문자의 왼쪽), 
문장의 맨 뒤(마지막 문자의 오른쪽), 
또는 문장 중간 임의의 곳(모든 연속된 두 문자 사이)에 위치할 수 있다. 
즉 길이가 L인 문자열이 현재 편집기에 입력되어 있으면, 커서가 위치할 수 있는 곳은 L+1가지 경우가 있다.


L	커서를 왼쪽으로 한 칸 옮김 (커서가 문장의 맨 앞이면 무시됨)
D	커서를 오른쪽으로 한 칸 옮김 (커서가 문장의 맨 뒤이면 무시됨)
B	커서 왼쪽에 있는 문자를 삭제함 (커서가 문장의 맨 앞이면 무시됨)
  삭제로 인해 커서는 한 칸 왼쪽으로 이동한 것처럼 나타나지만, 
  실제로 커서의 오른쪽에 있던 문자는 그대로임
P $	$라는 문자를 커서 왼쪽에 추가함


단, 명령어가 수행되기 전에 커서는 문장의 맨 뒤에 위치


L 다음 D가 나오면 똑같음
D 다음 L이 나오면 똑같음

L 다음 B는 R.pop

L 다음 

'''

import sys

input = sys.stdin.readline

# 문자열 입력 받기
line = input().rstrip()

# 명령 수 받기
numb = int(input().rstrip())

# 문자열 길이 저장하기 = len(string)

LLINE = []
RLINE = []

# 한문자씩 왼쪽 스택에 쌓기
LLINE = list(line)

# 반복하기
for _ in range(numb):
  
  # 명령어 수행하기
  command = input().rstrip()
  # print(numb," -> ","    c:  ",comand,"  Left: ",LLINE, "  Rright: ",RLINE)
  
  # print(comand)
  # L: cursor = max(cursor-1, 0)
  #   왼쪽 스택 값을 빼서 오른쪽 스택으로 넣기
  if(command[0] == 'L'):
    if(len(LLINE) > 0 ):
      tmp= LLINE.pop()
      RLINE.append(tmp)
      
  # D: cursor = min(len(string), cursor+1)
  #   오른쪽 스택 값을 빼고 왼쪽으로 옮김
  if(command[0] == 'D'):
    if(len(RLINE) > 0 ):
      tmp= RLINE.pop()
      LLINE.append(tmp)

  # B: cursor!=0
  #   왼쪽 스택 값을 제거
  if(command[0] == 'B'):
   if(len(LLINE) > 0 ):
      LLINE.pop()
  
  # P: string[0:cursor], string[cursor:len(string)] 으로 짜르로
  #   오른쪽 스택에 값 추가
  if(command[0] == 'P'):
    _, val = command.split()
    LLINE.append(val)

sys.stdout.write(''.join(LLINE + list(reversed(RLINE)))) 


    

