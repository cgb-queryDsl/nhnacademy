1. 
SELECT ReleASeYear, Title, RunningTime, Plot 
FROM movie 
WHERE 
	koreanTitle = '퍼스트 맨';

2. 
SELECT KoreanTitle, Title 
FROM movie 
WHERE 
	ReleASeYear = 2003;

3. 
SELECT p.*
FROM movie AS m INNER JOIN appear AS a ON m.movieid = a.movieid
	INNER JOIN person AS p ON a.personid = p.personid
    INNER JOIN role AS r ON a.roleid = r.roleid
WHERE
	r.rolename = 'composer'
    AND
	KoreanTitle = '블랙 팬서';

4.
SELECT COUNT(r.roleId)
FROM movie AS m INNER JOIN appear AS a ON m.movieid = a.movieid
    INNER JOIN role AS r ON a.roleid = r.roleid
WHERE
	m.KoreanTitle = '토이스토리 3'
    AND
    r.roleName = 'director';

5.
SELECT m.title, COUNT(r.rolename)
FROM movie AS m INNER JOIN appear AS a ON m.movieid = a.movieid
	INNER JOIN person AS p ON a.personid = p.personid
    INNER JOIN role AS r ON a.roleid = r.roleid
WHERE
	r.roleName = 'Director'
GROUP BY m.title, r.rolename
HAVING COUNT(r.roleName) > 1;

6. 
SELECT DISTINCT *
FROM movie AS m INNER JOIN appear AS a ON m.MovieId = a.MovieId
	INNER JOIN Person AS p ON p.PersonId = a.PersonId
	INNER JOIN AwardInvolve AS aw ON aw.appearId = a.appearId
    INNER JOIN winning AS w ON w.winningId = aw.winningId
WHERE
	p.Name = 'Hans Zimmer'
    and
    w.winOrNot = 'Winner';

7.
SELECT m.koreanTitle, r.roleName, p.koreanName, p2.koreanName
FROM movie AS m INNER JOIN appear AS a ON m.movieId = a.movieId
	INNER JOIN role AS r ON a.roleId = r.roleId
    INNER JOIN person AS p ON a.personId = p.personId
    INNER JOIN person AS p2 ON a.personId = p.personId
WHERE
	r.roleName = 'director'
    AND
    p.koreanName = '제임스 카메론'
GROUP BY
	m.koreanTitle, r.roleName, p.koreanName, p2.koreanName
HAVING
	p2.koreanName = '샘 워딩턴';

8.
SELECT m.Title
FROM movie AS m INNER JOIN appear AS a ON m.movieId = a.movieId
	INNER JOIN role AS r ON a.roleId = r.roleId
    INNER JOIN person AS p ON p.personId = a.personId
WHERE
	m.RunningTime >= 100
    AND
    r.roleName = 'Actor'
    AND
    p.KoreanName = '레오나르도 디카프리오';

9.
SELECT m.*
FROM movie AS m INNER JOIN appear AS a ON m.movieId = a.movieId
	INNER JOIN grade AS g ON g.gradeId = m.gradeId
WHERE
	g.gradeName = 'R'
ORDER BY m.BoxOfficeWWGross + m.BoxOfficeUSGross DESC
LIMIT 1;


10. 
SELECT AVG(BoxOfficeWWGross + BoxOfficeUSGross) AS 수익평균 
FROM movie 
WHERE ReleASeYear < 1999;

11. 
SELECT KoreanTitle, Title, Budget 
FROM Movie 
WHERE 
	Budget = (SELECT MAX(Budget) FROM Movie);


12. 
SELECT p.*, SUM(m.budget)
FROM movie AS m INNER JOIN appear AS a ON m.movieId = a.movieId
	INNER JOIN role AS r ON r.roleId = a.roleId
	INNER JOIN person AS p ON p.personId = a.personId
WHERE
	r.roleName = 'Director'
GROUP BY
	p.personId, m.budget
ORDER BY SUM(m.budget) DESC
LIMIT 1;

13.
SELECT p.*, m.BoxOfficeWWGross + m.BoxOfficeUSGross AS 수입
FROM movie AS m INNER JOIN appear AS a ON m.movieId = a.movieId
	INNER JOIN role AS r ON r.roleId = a.roleId
    INNER JOIN person AS p ON p.personId = a.personId
WHERE 
	r.roleName IN ('Actor', 'Actress')
ORDER BY m.BoxOfficeWWGross + m.BoxOfficeUSGross DESC
LIMIT 1;

14.
SELECT Title, BoxOfficeWWGross, BoxOfficeUSGross 
FROM Movie 
WHERE
	Budget = (SELECT MIN(Budget) FROM Movie);

15. 
SELECT AVG(BoxOfficeWWGross + BoxOfficeUSGross) AS 평균수익 
FROM Movie 
WHERE 
	Budget <= 50000000;

16. 
SELECT AVG(m.BoxOfficeWWGross + BoxOfficeUSGross)
FROM movie AS m INNER JOIN movieGenre AS mg ON m.movieId = mg.movieId
	INNER JOIN Genre AS g ON g.genreId = mg.genreId
WHERE
	genreName = 'Action';


17.
SELECT DISTINCT m.*
FROM movie AS m INNER JOIN appear AS a ON m.movieId = a.movieId
	INNER JOIN MovieGenre AS mg ON mg.movieId = m.movieId
    INNER JOIN Genre AS g ON g.genreId = mg.genreId
WHERE
	g.genreName = 'Drama'
    OR
    g.genreName = 'War';


18.
SELECT m.*
FROM movie AS m INNER JOIN appear AS a ON m.movieId = a.movieId
	INNER JOIN role AS r ON r.roleId = a.roleId
	INNER JOIN person AS p ON p.personId = a.personId
WHERE
	r.roleName = 'Actor'
    AND
    p.koreanName = '톰 행크스'
ORDER BY
	m.RunningTime DESC
LIMIT 1;


19.
SELECT p.*, COUNT(s.sectorName)
FROM person AS p INNER JOIN appear AS a ON p.personId = a.personId
	INNER JOIN awardInvolve AS ai ON a.appearId = ai.appearId
    INNER JOIN sector AS s ON s.sectorId = ai.sectorId
    INNER JOIN winning AS w ON w.winningId = ai.winningId
WHERE
    s.sectorId = 2
GROUP BY
	p.personId, s.sectorName
ORDER BY
	COUNT(s.sectorName) DESC
LIMIT 1;


20.
SELECT p.*, COUNT(p.personId)
FROM person AS p INNER JOIN appear AS a on p.personId = a.personId
	INNER JOIN role AS r on r.roleId = a.roleId
    INNER JOIN awardInvolve AS ai on ai.appearId = a.appearId
WHERE
	r.rolename IN ('Actor', 'Actress')
    AND
    ai.winningId = 2
GROUP BY p.personId
ORDER BY COUNT(p.personId) DESC
LIMIT 1;

21.
SELECT p.*, COUNT(s.sectorName)
FROM person AS p INNER JOIN appear AS a ON p.personId = a.personId
	INNER JOIN awardInvolve AS ai ON a.appearId = ai.appearId
    INNER JOIN sector AS s ON s.sectorId = ai.sectorId
    INNER JOIN winning AS w ON w.winningId = ai.winningId
WHERE
    s.sectorId = 2
GROUP BY
	p.personId, s.sectorName
HAVING
	COUNT(s.sectorName) >= 2;

23.
SELECT p.*, COUNT(p.personId)
FROM person AS p INNER JOIN appear AS a ON p.personId = a.personId
	INNER JOIN role AS r ON r.roleId = a.roleId
    INNER JOIN awardInvolve AS ai ON ai.appearId = a.appearId
    INNER JOIN awardYear AS ay ON ay.awardYearId = ai.awardYearId
	INNER JOIN award AS aw ON aw.awardId = ay.awardId
WHERE
    ai.winningId = 2
GROUP BY p.personId
ORDER BY COUNT(p.personId) DESC
LIMIT 1;

24.
SELECT m.*, COUNT(w.winOrNot)
FROM movie AS m INNER JOIN appear AS a ON m.movieId = a.movieId
	INNER JOIN awardInvolve AS ai ON a.appearId = ai.appearId
    INNER JOIN sector AS s ON s.sectorId = ai.sectorId
    INNER JOIN winning AS w ON w.winningId = ai.winningId
WHERE
	w.winOrNot = 'nominated'
GROUP BY
	m.movieId, w.winOrNot
ORDER BY
	COUNT(w.winOrNot) DESC
LIMIT 1;

25.
SELECT p.*, count(p.personId)
FROM movie AS m INNER JOIN appear AS a ON m.movieId = a.movieId
	INNER JOIN role AS r ON r.roleId = a.roleId
    INNER JOIN person AS p ON p.personId = a.personId
WHERE    
	r.roleName = 'Actress'
GROUP BY p.personId
ORDER BY count(p.personId) DESC
LIMIT 1;

26.
SELECT *
FROM movie 
ORDER BY BoxOfficeWWGross + BoxOfficeUSGross DESC
LIMIT 10;

27. 
SELECT title
FROM Movie
WHERE
	BoxOfficeWWGross + BoxOfficeUSGross >= 1000000000
	AND 
    budget <= 100000000;

28.
SELECT p.*, COUNT(g.genreName)
FROM movie AS m INNER JOIN appear AS a ON m.movieId = a.movieId
	INNER JOIN role AS r ON r.roleId = a.roleId
	INNER JOIN person AS p ON p.personId = a.personId
    INNER JOIN movieGenre AS mg ON m.movieId = mg.movieId
	INNER JOIN Genre AS g ON g.genreId = mg.genreId
WHERE
	r.roleName = 'director'
    AND
    g.genreName = 'war'
GROUP BY
	p.personId, g.genreName
ORDER BY
	COUNT(g.genreName) DESC
LIMIT 1;

29.
SELECT p.*, COUNT(g.genreName)
FROM movie AS m INNER JOIN appear AS a ON m.movieId = a.movieId
	INNER JOIN role AS r ON r.roleId = a.roleId
	INNER JOIN person AS p ON p.personId = a.personId
    INNER JOIN movieGenre AS mg ON m.movieId = mg.movieId
	INNER JOIN Genre AS g ON g.genreId = mg.genreId
WHERE
	g.genreName = 'drama'
    AND
    r.roleName IN ('Actor', 'Actress')
GROUP BY
	p.personId
ORDER BY
	COUNT(g.genreName) DESC
LIMIT 1;

30.
SELECT DISTINCT p.*
FROM movie AS m INNER JOIN appear AS a ON m.movieId = a.movieId
	INNER JOIN role AS r ON r.roleId = a.roleId
	INNER JOIN person AS p ON p.personId = a.personId
    INNER JOIN movieGenre AS mg ON m.movieId = mg.movieId
    INNER JOIN movieGenre AS mg2 ON m.movieId = mg2.movieId
	INNER JOIN Genre AS g ON g.genreId = mg.genreId
    INNER JOIN Genre AS g2 on g2.genreId = mg2.genreId
WHERE
	g.genreName = 'drama' 
	AND 
	r.roleName IN ('Actor', 'Actress')
	AND 
	g2.genreName != 'horror';

31.
SELECT Location
FROM awardYear
GROUP BY Location
LIMIT 1;

33.
SELECT TIMESTAMPDIFF(YEAR, (SELECT MIN(Date) FROM awardYear), '2022-11-10');