-- 코드를 작성해주세요
# 가장 큰 대장균 크기 - 각 대장균의 크기
# 1. 연도별 가장 큰 크기를 구하기
# 2. 연도에 따라 DEV를 구하기


SELECT YEAR(e.DIFFERENTIATION_DATE) AS YEAR,
        ((SELECT  MAX(ed.SIZE_OF_COLONY) as MAX_CSIZE
                FROM ECOLI_DATA ed
            WHERE YEAR(ed.DIFFERENTIATION_DATE) = 
          YEAR(e.DIFFERENTIATION_DATE)) - 
                e.SIZE_OF_COLONY) AS YEAR_DEV,
        e.ID
FROM ECOLI_DATA e
ORDER BY YEAR, YEAR_DEV ASC;
