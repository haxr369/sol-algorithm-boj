-- 코드를 작성해주세요
# 각 아이템들은 오직 하나의 PARENT 아이템 ID 를 가짐
# 더 이상 업그레이드할 수 없는 아이템 -> 자녀가 없는 아이템 찾기

SELECT i.ITEM_ID, i.ITEM_NAME, i.RARITY FROM ITEM_INFO i
JOIN ITEM_TREE t ON i.ITEM_ID = t.ITEM_ID
LEFT JOIN 
    (SELECT it.PARENT_ITEM_ID AS 'PARENT_ITEM_ID', COUNT(ii.ITEM_ID) AS 'count' 
     FROM ITEM_INFO ii
    JOIN ITEM_TREE it
    ON ii.ITEM_ID = it.ITEM_ID
    WHERE it.PARENT_ITEM_ID IS NOT NULL
    GROUP BY it.PARENT_ITEM_ID) g
    ON g.PARENT_ITEM_ID = i.ITEM_ID
WHERE g.count IS NULL
ORDER BY i.ITEM_ID DESC