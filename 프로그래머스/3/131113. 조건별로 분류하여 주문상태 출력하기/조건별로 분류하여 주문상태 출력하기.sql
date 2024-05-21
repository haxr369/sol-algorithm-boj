-- 코드를 입력하세요
# SELECT f.ORDERID, f.PRODUCT_ID, f.OUT_DATE, CASE(
#         WHEN f.OUT_DATE IS NULL THEN "출고미정"
#         WHEN DATEDIFF(f.OUT_DATE, "2022-05-01") > 0
#         THNE "출고예정"
#         ELSE "출고완료"
#         END) AS "출고여부"
# FROM FOOD_ORDER f
# ORDER BY f.ORDER_ID ASC;

SELECT 
    f.ORDER_ID, f.PRODUCT_ID, DATE_FORMAT(f.OUT_DATE, "%Y-%m-%d") AS OUT_DATE,
    CASE
        WHEN f.OUT_DATE IS NULL
            THEN "출고미정"
        WHEN DATEDIFF(f.OUT_DATE, "2022-05-01") > 0
            THEN "출고대기"
        ELSE "출고완료"
    END AS "출고여부"
FROM FOOD_ORDER f
ORDER BY f.ORDER_ID ASC;