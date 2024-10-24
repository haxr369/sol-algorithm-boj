
N, R, C = map(int, input().split())
#print("R : ",R," C : ",C)
def dfs(sR, eR, sC, eC, depth, acc):
  if(depth == N+1):
    return acc
  #print("depth : ",depth, " acc : ",acc)
  stdR = (sR + eR)//2
  stdC = (sC + eC)//2
  #print("stdR : ",stdR, "stdC : ",stdC)
  if(R < stdR):
    # 0
    if(C < stdC):
      #print("0사분면")
      return dfs(sR, stdR, sC, stdC, depth+1, acc)
    else: # 1
      #print("1사분면")
      return dfs(sR, stdR, stdC, eC, depth+1, acc + 2**(2*N - 2*depth))
  else:
    # 3사분면
    if(C < stdC):
      #print("2사분면")
      return dfs(stdR, eR, sC, stdC, depth+1, acc + 2*2**(2*N - 2*depth))
    else: #4분면
      #print("3사분면")
      return dfs(stdR, eR, stdC, eC, depth+1, acc + 3*2**(2*N - 2*depth))

ans = dfs(0, 2**N, 0, 2**N, 1, 0)
print(ans)