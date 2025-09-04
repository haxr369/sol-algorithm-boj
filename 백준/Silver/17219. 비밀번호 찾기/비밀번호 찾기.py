"""
메모장에 사이트의 주소와 비밀번호를 저장


첫째 줄에 저장된 사이트 주소의 수 N(1 ≤ N ≤ 100,000)과  => 사이트-비번 쌍 N개
비밀번호를 찾으려는 사이트 주소의 수 M(1 ≤ M ≤ 100,000) =>  M번 탐색 필요

사이트 주소는 알파벳 소문자, 알파벳 대문자, 대시('-'), 마침표('.')로 이루어져 있고, 중복되지 않는다



"""

import sys

input = sys.stdin.readline

pairCnt, searchCnt = list(map(int, input().split()))

dic = {}

i=0
while(i < pairCnt):
  site, pw = list(input().strip().split(' '))
  # print("site->",site," pw->",pw)
  dic[site] = pw
  i+=1

# print(dic.keys())
j=0
while(j < searchCnt):
  site = input().strip()
  # print("site->",site)
  pw = dic.get(site)
  print(pw)
  j+=1