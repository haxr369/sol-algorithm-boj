-- 코드를 작성해주세요
/**
1분기 1~3월
2분기 4~6월
3분기 7~9월
4분기 10~12월
**/

SELECT CASE 
            WHEN MONTH(e.DIFFERENTIATION_DATE) BETWEEN 1 AND 3
            THEN "1Q"
            WHEN MONTH(e.DIFFERENTIATION_DATE) BETWEEN 4 AND 6
            THEN "2Q"
            WHEN MONTH(e.DIFFERENTIATION_DATE) BETWEEN 7 AND 9
            THEN "3Q"
            ELSE "4Q"
         END
            AS QUARTER,
      COUNT(e.ID) AS  ECOLI_COUNT
FROM ECOLI_DATA e
GROUP BY QUARTER;

