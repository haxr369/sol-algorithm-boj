-- 코드를 작성해주세요
# RARE를 가진 아이템의 자녀 아이템 구하기
# 1. RARE 희귀도 가진 아이템 찾기
# 2. 해당 아이템의 id를 부모 id로 가진 아이템 찾기

SELECT ii.ITEM_ID, ii.ITEM_NAME, ii.RARITY FROM ITEM_INFO ii
JOIN ITEM_TREE it
    ON ii.ITEM_ID = it.ITEM_ID
WHERE "RARE" = (SELECT RARITY FROM ITEM_INFO i
               WHERE i.ITEM_ID = it.PARENT_ITEM_ID)
ORDER BY ii.ITEM_ID DESC;