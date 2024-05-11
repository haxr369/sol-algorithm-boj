-- 코드를 작성해주세요
# 잡은 물고기의 길이가 10cm 이하일 경우에는 LENGTH 가 NULL 

SELECT f.ID, fn.FISH_NAME, f.LENGTH FROM FISH_INFO f
JOIN FISH_NAME_INFO fn ON f.FISH_TYPE = fn.FISH_TYPE
JOIN
    (SELECT fni.FISH_NAME as 'max_name', MAX(fi.LENGTH) as 'max_length' 
        FROM FISH_INFO fi
        JOIN FISH_NAME_INFO fni
        ON fi.FISH_TYPE = fni.FISH_TYPE
        GROUP BY fni.FISH_NAME) AS mg
        ON mg.max_name = fn.FISH_NAME
WHERE mg.max_length = f.LENGTH
ORDER BY f.ID ASC