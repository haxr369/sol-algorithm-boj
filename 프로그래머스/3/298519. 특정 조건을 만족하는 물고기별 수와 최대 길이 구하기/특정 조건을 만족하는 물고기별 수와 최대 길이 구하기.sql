-- 코드를 작성해주세요
--  평균 길이가 33cm 이상인 물고기들을 종류별로 분류

SELECT COUNT(f.ID) AS "FISH_COUNT", 
        MAX(f.LENGTH) AS "MAX_LENGTH",
        f.FISH_TYPE
FROM FISH_INFO f
GROUP BY f.FISH_TYPE
HAVING AVG(
            CASE
               WHEN f.LENGTH <= 10
                THEN 10
                ELSE f.LENGTH
            END
        ) >= 33
ORDER BY f.FISH_TYPE ASC;