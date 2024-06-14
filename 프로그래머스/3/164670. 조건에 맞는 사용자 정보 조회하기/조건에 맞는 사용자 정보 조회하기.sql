-- 코드를 입력하세요
-- 중고 거래 게시물을 3건 이상 등록한 사용자

SELECT ugu.USER_ID, ugu.NICKNAME, 
    CONCAT(ugu.CITY," " ,ugu.STREET_ADDRESS1," " , ugu.STREET_ADDRESS2) AS "전체주소" ,
    CONCAT(SUBSTRING(ugu.TLNO,1,3),"-",SUBSTRING(ugu.TLNO,4,4),"-",SUBSTRING(ugu.TLNO,8,4))
    AS "전화번호"  
FROM USED_GOODS_USER ugu
JOIN USED_GOODS_BOARD ugb
    ON ugu.USER_ID = ugb.WRITER_ID
GROUP BY ugu.USER_ID
HAVING COUNT(ugb.BOARD_ID) >= 3
ORDER BY ugu.USER_ID DESC