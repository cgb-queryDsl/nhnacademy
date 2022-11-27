-- 사용자 테이블
create table Users (
	id int auto_increment NOT NULL,
    username nvarchar(20) NOT NULL unique,
    password nvarchar(30) NOT NULL,
    nickname nvarchar(30) NOT NULL,
    created_at datetime,
    
    primary key(id)
);


-- 게시물 테이블
create table Posts (
	id int auto_increment NOT NULL,
    title nvarchar(100) NOT NULL,
    content nvarchar(20000) NOT NULL,
    created_at datetime,
    user_id int NOT NULL,
    deleted char(1) default 'N' check(deleted in ('Y', 'N')),
    
    primary key(id),
    foreign key(user_id) references Users(id)
);

-- 파일 테이블
create table Files (
	id int auto_increment NOT NULL,
    post_id int NOT NULL,
    file_name nvarchar(300),
		
	primary key(id),
	foreign key(post_id) references Posts(id)
);

-- UUID 테이블
create table Uuid (
	id int auto_increment NOT NULL,
    file_id int NOT NULL,
    post_id int NOT NULL,
    uuid_value nvarchar(100),
    
    primary key(id),
    foreign key(file_id) references Files(id),
    foreign key(post_id) references Posts(id)
);

-- 댓글 테이블
create table Replies (
	id int auto_increment NOT NULL,
    post_id int NOT NULL,
    user_id int NOT NULL,
	comment varchar(1000) NOT NULL,
    created_at datetime NOT NULL,
    deleted char(1) default 'N' check(deleted in ('Y', 'N')),
    
    primary key(id),
    foreign key(post_id) references Posts(id),
    foreign key(user_id) references Users(id)
);


-- 좋아요 테이블
create table Likes(
	id int auto_increment,
    post_id int NOT NULL,
    user_id int NOT NULL,
    liked char(1) default 'N' check(liked in ('Y', 'N')),
    
    primary key(id),
    foreign key(post_id) references Posts(id),
    foreign key(user_id) references Users(id)
);