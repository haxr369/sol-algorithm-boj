-- 코드를 작성해주세요
# 각 아이템들은 오직 하나의 PARENT 아이템 ID 를 가짐
# 더 이상 업그레이드할 수 없는 아이템 -> 자녀가 없는 아이템 찾기

SELECT i.ITEM_ID, i.ITEM_NAME, i.RARITY FROM ITEM_INFO i
JOIN ITEM_TREE t ON i.ITEM_ID = t.ITEM_ID # 현재 로우의 PARENT_ITEM_ID를 찾기 위함
LEFT JOIN ITEM_TREE it ON i.ITEM_ID = it.PARENT_ITEM_ID 
WHERE it.ITEM_ID IS NULL
ORDER BY i.ITEM_ID DESC;
# 현재 로우가 PARENT_ITEM_ID인 item을 찾으려함, 만약 있다면 더 업그레이드 가능한 것!
