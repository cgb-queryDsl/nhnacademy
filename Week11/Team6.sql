-- ========================== 데이터베이스 생성 ==========================
CREATE DATABASE Team6;

USE Team6;

-- ========================== 테이블 생성 ==========================
CREATE TABLE Person (
	PersonID int auto_increment NOT NULL,
    Name nvarchar(10) NOT NULL,
    Gender nvarchar(5) NOT NULL,
    PlaceOfOrigin nvarchar(100) NOT NULL,
    Email nvarchar(30),
    PhoneNumber nvarchar(15),
    RRN nvarchar(20) NOT NULL,
    
    CONSTRAINT pk_PersonID PRIMARY KEY(PersonID)
);

CREATE TABLE QualifierType (
	QualifierTypeID int NOT NULL,
	TypeName nvarchar(10) NOT NULL,
	
	CONSTRAINT pk_QualifierType PRIMARY KEY(QualifierTypeID)
);

CREATE TABLE Informant (
	InformantId int NOT NULL,
    PersonId int NOT NULL,
    QualifierTypeID int NOT NULL,
    
    CONSTRAINT pk_Informant PRIMARY KEY(InformantId),
    CONSTRAINT fk_InformantId_Person FOREIGN KEY(PersonId) REFERENCES Person(PersonId),
    CONSTRAINT fk_InformantId_QualifierType FOREIGN KEY(QualifierTypeID) REFERENCES QualifierType(QualifierTypeID)
);

CREATE TABLE Address (
    AddressID int NOT NULL,
    PersonID int NOT NULL,
    ReportDate date NOT NULL,
    Address nvarchar(100) NOT NULL,
    ResidenceOrNot nvarchar(10) NOT NULL,
    
    CONSTRAINT pk_AddressID PRIMARY KEY(AddressID),
    CONSTRAINT fk_PersonID FOREIGN KEY(PersonID) REFERENCES Person(PersonID)
);

CREATE TABLE BirthReport (
	BirthReportID int NOT NULL,
	PersonID int NOT NULL,
    InformantID int NOT NULL,
    ReportDate date NOT NULL,
    
    CONSTRAINT pk_BirthReport PRIMARY KEY(BirthReportID),
    CONSTRAINT fk_BirthReport_Person FOREIGN KEY(PersonID) REFERENCES Person(PersonID),
    CONSTRAINT fk_BirthReport_Informant FOREIGN KEY(InformantID) REFERENCES Informant(InformantID)
);

CREATE TABLE DeathReport (
	DeathReportID int NOT NULL,
    PersonID int NOT NULL,
    InformantID int NOT NULL,
    ReportDate date NOT NULL,
    
    CONSTRAINT pk_DeathReport PRIMARY KEY(DeathReportID),
    CONSTRAINT fk_DeathReport_Person FOREIGN KEY(PersonID) REFERENCES Person(PersonID),
    CONSTRAINT fk_DeathReport_Informant FOREIGN KEY(InformantID) REFERENCES Informant(InformantID)
);

CREATE TABLE FamilyCertification (
	FamilyCertificationID varchar(20) NOT NULL,
    PersonID int NOT NULL,
    ReportDate date NOT NULL,
    
    CONSTRAINT pk_FamilyCertification PRIMARY KEY(FamilyCertificationID),
    CONSTRAINT fk_FamilyCertification_Person FOREIGN KEY(PersonID) REFERENCES Person(PersonID)
);

CREATE TABLE ResidentRegist (
	ResidentRegistID varchar(20) NOT NULL,
    PersonID int NOT NULL,
    ReportDate date NOT NULL,
    
    CONSTRAINT pk_ResidentRegist PRIMARY KEY(ResidentRegistID),
    CONSTRAINT fk_ResidentRegist FOREIGN KEY(PersonID) REFERENCES Person(PersonID)
);

CREATE TABLE DeathType (
	DeathTypeID int NOT NULL,
	TypeName nvarchar(30) NOT NULL,
	
	CONSTRAINT pk_DeathType PRIMARY KEY(DeathTypeID)
);

CREATE TABLE Death (
	DeathId int NOT NULL,
    PersonId int NOT NULL,
    DeathDate datetime NOT NULL,
    DeathLocation nvarchar(100) NOT NULL,
    DeathTypeID int NOT NULL,
    
    CONSTRAINT pk_Death PRIMARY KEY(DeathId),
    CONSTRAINT fk_Death_Person FOREIGN KEY(PersonId) REFERENCES Person(PersonId),
    CONSTRAINT fk_Death_DeathType FOREIGN KEY(DeathTypeID) REFERENCES DeathType(DeathTypeID)
);

CREATE TABLE Birth (
	BirthId int NOT NULL,
    PersonId int NOT NULL,
    BirthDate datetime NOT NULL,
    BirthLocation nvarchar(100) NOT NULL,
    
    CONSTRAINT pk_Birth PRIMARY KEY(BirthId),
    CONSTRAINT fk_Birth_Person FOREIGN KEY(PersonId) REFERENCES Person(PersonId)
);

CREATE TABLE FamilyCode (
	FamilyCodeID int NOT NULL,
	FamilyCodeName nvarchar(10) NOT NULL,
	
	CONSTRAINT pk_FamilyCode PRIMARY KEY(FamilyCodeID)
);

CREATE TABLE AlterationType (
	AlterationTypeID int NOT NULL,
    AlterationTypeIDName nvarchar(10) NOT NULL,
    
    CONSTRAINT pk_AlterationType PRIMARY KEY(AlterationTypeID)
);

CREATE TABLE FamilyComposition(
	FamilyCompositionID int NOT NULL,
    ReportDate date NOT NULL,
    AlterationTypeID int NOT NULL,
    
    CONSTRAINT pk_FamilyComposition PRIMARY KEY(FamilyCompositionID),
    CONSTRAINT fk_FamilyComposition_AlterationType FOREIGN KEY(AlterationTypeID) REFERENCES AlterationType(AlterationTypeID)
);

CREATE TABLE Family (
	FamilyID int NOT NULL,
	FamilyCodeID int NOT NULL,
	PersonID int NOT NULL,
    FamilyCompositionID int NOT NULL,
	
	CONSTRAINT pk_Family PRIMARY KEY(FamilyID, PersonID),
	CONSTRAINT fk_Family_FamilyCode FOREIGN KEY(FamilyCodeID) REFERENCES FamilyCode(FamilyCodeID),
    CONSTRAINT fk_Family_Person FOREIGN KEY(PersonId) REFERENCES Person(PersonId),
    CONSTRAINT fk_Family_FamilyComposition FOREIGN KEY(FamilyCompositionID) REFERENCES FamilyComposition(FamilyCompositionID)
);

-- ========================== sample data insert 쿼리 ==========================
-- Person
INSERT INTO Person VALUES (1, '남길동', '남', '경기도 성남시 분당구 대왕판교로645번길', 'gildong@sql.com', '010-3244-2313', '130914-*******');
INSERT INTO Person VALUES (2, '남석환', '남', '경기도 성남시 분당구 대왕판교로645번길', 'sunghwan@sql.com', '010-3212-8854', '540514-*******');
INSERT INTO Person VALUES (3, '박한나', '여', '서울특별시 중구 세종대로 110번길', 'hanna@sql.com', '010-4725-2321', '551022-*******');
INSERT INTO Person VALUES (4, '남기준', '남', '경기도 성남시 분당구 대왕판교로645번길', 'gijun@sql.com', '010-4295-1112', '790510-*******');
INSERT INTO Person VALUES (5, '이주은', '여', '경기도 수원시 팔달구 효원로 1번길', 'leeJu@nhnad.co.kr', '010-0021-0000', '820821-*******');
INSERT INTO Person VALUES (6, '이선미', '여', '경기도 수원시 팔달구 효원로 1번길', 'leeSun@nhnad.co.kr', '010-1111-2200', '851205-******* ');
INSERT INTO Person VALUES (7, '남기석', '남', '경기도 성남시 분당구 대왕판교로645번길', 'nam@nhnad.co.kr', '010-1234-5678', '120315-*******');

-- QualifierType
INSERT INTO QualifierType VALUES(1, '부');
INSERT INTO QualifierType VALUES(2, '모');
INSERT INTO QualifierType VALUES(3, '호주');
INSERT INTO QualifierType VALUES(4, '동거친족');
INSERT INTO QualifierType VALUES(5, '비동거친족');
INSERT INTO QualifierType VALUES(6, '동거자');
INSERT INTO QualifierType VALUES(7, '기타');

-- Birth
INSERT INTO Birth VALUES (1, 1, '1913-09-14 07:22', '자택');
INSERT INTO Birth VALUES (2, 2, '1954-05-14 17:30', '병원');
INSERT INTO Birth VALUES (3, 3, '1955-10-22 11:15', '병원');
INSERT INTO Birth VALUES (4, 4, '1979-05-10 20:45', '병원');
INSERT INTO Birth VALUES (5, 5, '1982-08-21 01:28', '병원');
INSERT INTO Birth VALUES (6, 6, '1985-12-05 22:01', '병원');
INSERT INTO Birth VALUES (7, 7, '2012-03-15 14:59', '병원');

-- DeathType
INSERT INTO DeathType VALUES (1, '주택');
INSERT INTO DeathType VALUES (2, '의료기관');
INSERT INTO DeathType VALUES (3, '사회복지시설(양로원, 고아원 등)');
INSERT INTO DeathType VALUES (4, '산업장');
INSERT INTO DeathType VALUES (5, '공공시설(학교, 운동장 등)');
INSERT INTO DeathType VALUES (6, '도로');
INSERT INTO DeathType VALUES (7, '상업/서비스시설(상점, 호텔 등)');
INSERT INTO DeathType VALUES (8, '농장(논밭, 축사, 양식장 등)');
INSERT INTO DeathType VALUES (9, '병원 이송 중 사망');
INSERT INTO DeathType VALUES (10, '기타');

-- Death
INSERT INTO Death VALUES (1, 1, '2021-04-29 09:03', '강원도 고성군 금강산로 290번길', 1);

-- Address
INSERT INTO Address VALUES (1339, 4, '2013-03-05', '경기도 성남시 분당구 대왕판교로 645번길', 'Resident');
INSERT INTO Address VALUES (1032, 4, '2009-10-31', '경기도 성남시 분당구 불정로 90번길', 'Transfer');
INSERT INTO Address VALUES (903, 4, '2007-10-31', '서울시 동작구 상도로 940번', 'Transfer');

-- Informant
INSERT INTO Informant VALUES(1, 4, 1);
INSERT INTO Informant VALUES(2, 2, 5);

-- BirthReport
INSERT INTO BirthReport VALUES(1, 7, 1, '2012-03-17');

-- DeathReport
INSERT INTO DeathReport VALUES(1, 1, 2, '2020-05-02');

-- FamilyCertification
INSERT INTO FamilyCertification VALUES('12345678-91011121', 4, '2021-10-25');

-- ResidentRegist
INSERT INTO ResidentRegist VALUES('98765432-10987654', 4, '2021-10-25');

-- AlterationType
INSERT INTO AlterationType VALUES(1, '세대분리');
INSERT INTO AlterationType VALUES(2, '전입');
INSERT INTO AlterationType VALUES(3, '출생등록');

-- FamilyComposition
INSERT INTO FamilyComposition VALUES(1, '2009-10-02', 1);
INSERT INTO FamilyComposition VALUES(2, '2010-02-15', 2);
INSERT INTO FamilyComposition VALUES(3, '2012-03-17', 3);
INSERT INTO FamilyComposition VALUES(4, '2015-11-29', 2);
INSERT INTO FamilyComposition VALUES(5, '1913-09-14', 3);
INSERT INTO FamilyComposition VALUES(6, '1954-05-14', 3);
INSERT INTO FamilyComposition VALUES(7, '1978-11-29', 2);

-- FamilyCode
INSERT INTO FamilyCode VALUES(1, '본인');
INSERT INTO FamilyCode VALUES(2, '부');
INSERT INTO FamilyCode VALUES(3, '모');
INSERT INTO FamilyCode VALUES(4, '조부모');
INSERT INTO FamilyCode VALUES(6, '자녀');
INSERT INTO FamilyCode VALUES(5, '배우자');
INSERT INTO FamilyCode VALUES(7, '처제');

-- Family
INSERT INTO Family VALUES(1, 1, 4, 1);
INSERT INTO Family VALUES(2, 2, 2, 6);
INSERT INTO Family VALUES(2, 3, 3, 7);
INSERT INTO Family VALUES(2, 4, 1 ,5);
INSERT INTO Family VALUES(1, 5, 6, 2);
INSERT INTO Family VALUES(1, 6, 7, 3);
INSERT INTO Family VALUES(1, 7, 5, 4);

-- ========================== 조회 쿼리 ==========================
-- 가족관계증명서: 상단 조회 영역
SELECT FC.ReportDate AS 발급일, FC.FamilyCertificationID AS 증명서확인번호, P.PlaceOfOrigin AS '등록기준지(본적)' FROM Person AS P
	INNER JOIN FamilyCertification AS FC ON FC.PersonID = P.PersonID
WHERE P.PersonID = 4;

-- 가족관계증명서: 가족 구성원 조회 영역
SELECT FCD.FamilyCodeName AS 구분, P.Name AS 성명, DATE_FORMAT(B.BirthDate, '%Y년 %m월 %d일') AS 출생연월일, P.RRN AS 주민등록번호, P.Gender AS 성별 
FROM Person AS P 
	INNER JOIN Family AS F ON F.PersonID = P.PersonID
	INNER JOIN Birth AS B ON B.PersonID = P.PersonID
	INNER JOIN FamilyCode AS FCD ON FCD.FamilyCodeID = F.FamilyCodeID
WHERE P.PersonID = 4 OR F.FamilyCodeID IN (1, 2, 3, 5, 6);

-- 주민등록등본: 상단 조회영역 (가족 정보 관련 테이블 및 데이터 필요함 - 세대구성 사유 및 일자 -> 미완)
SELECT RR.ReportDate AS 발급일, RR.ResidentRegistID AS 증명서확인번호, P.Name AS '세대주 성명', AT.AlterationTypeIDName AS '세대구성 사유', FCP.ReportDate AS '신고일자'
FROM Person AS P
	INNER JOIN ResidentRegist AS RR ON RR.PersonID = P.PersonID
	INNER JOIN Family AS F ON P.PersonID = F.PersonID
	INNER JOIN FamilyComposition AS FCP ON F.FamilyCompositionID = FCP.FamilyCompositionID
    INNER JOIN AlterationType AS AT ON FCP.AlterationTypeID = AT.AlterationTypeID
    INNER JOIN FamilyCode AS FC ON F.FamilyCodeID = FC.FamilyCodeID
WHERE P.PersonID = 4;

-- 주민등록등본: 전입 주소 조회 영역
SELECT A.ResidenceOrNot, A.Address, A.ReportDate FROM Address AS A
ORDER BY A.ReportDate DESC;

-- 주민등록등본: 세대 구성원 조회 영역
SELECT IF(FC.FamilyCodeID = 7, '동거인', FC.FamilyCodeName) AS '세대주 관계', P.Name AS '성명', P.RRN AS '주민등록번호', FCP.ReportDate AS '신고일', AT.AlterationTypeIDName AS '변동사유' 
FROM Person AS P INNER JOIN Family AS F ON P.PersonID = F.PersonID
	  INNER JOIN FamilyComposition AS FCP ON F.FamilyCompositionID = FCP.FamilyCompositionID
      INNER JOIN AlterationType AS AT ON FCP.AlterationTypeID = AT.AlterationTypeID
      INNER JOIN FamilyCode AS FC ON F.FamilyCodeID = FC.FamilyCodeID
WHERE F.FamilyID = 1
ORDER BY FCP.ReportDate;
