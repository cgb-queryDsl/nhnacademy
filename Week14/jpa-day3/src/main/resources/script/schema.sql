CREATE TABLE IF NOT EXISTS `BoardUsers` (
   `id` bigint NOT NULL,
   `username` varchar(20) NOT NULL,
   `password` varchar(30) NOT NULL,
   `nickname` varchar(30) NOT NULL,
   `created_at` datetime,

   primary key(`id`)
);

MERGE INTO `BoardUsers` KEY ( `id` ) VALUES (1, 'admin', '12345', '관리자', now() );
MERGE INTO `BoardUsers` KEY ( `id` ) VALUES (2, 'aaa', '123', 'User 1', now() );

CREATE TABLE IF NOT EXISTS `BoardPosts` (
    `id` int NOT NULL,
    `title` varchar(100) NOT NULL,
    `content` varchar(20000) NOT NULL,
    `created_at` datetime,
    `user_id` int NOT NULL,
    `deleted` char(1) default 'N' check(`deleted` in ('Y', 'N')),

    primary key(`id`),
    foreign key(`user_id`) references `BoardUsers`(`id`)
);

MERGE INTO `BoardPosts` KEY (`id`) VALUES (1, '제목', '내용', now(), 2, 'N');
MERGE INTO `BoardPosts` KEY (`id`) VALUES (2, '제목', '내용', now(), 2, 'Y');
MERGE INTO `BoardPosts` KEY (`id`) VALUES (3, '아이폰', '11', now(), 2, 'N');
MERGE INTO `BoardPosts` KEY (`id`) VALUES (4, '맥북', '프로 14인치', now(), 2, 'N');
MERGE INTO `BoardPosts` KEY (`id`) VALUES (5, '맥북에어', 'M2', now(), 2, 'N');
MERGE INTO `BoardPosts` KEY (`id`) VALUES (6, '아이패드', '5세대', now(), 2, 'N');
MERGE INTO `BoardPosts` KEY (`id`) VALUES (7, '갤럭시워치', '하하', now(), 2, 'N');
MERGE INTO `BoardPosts` KEY (`id`) VALUES (8, '갤럭시 S', 'Hi', now(), 2, 'N');
MERGE INTO `BoardPosts` KEY (`id`) VALUES (9, '갤럭시 플립', 'BYE', now(), 2, 'N');
MERGE INTO `BoardPosts` KEY (`id`) VALUES (10, '손', '발', now(), 2, 'N');
MERGE INTO `BoardPosts` KEY (`id`) VALUES (11, '소주', '맛있겠다', now(), 2, 'N');
MERGE INTO `BoardPosts` KEY (`id`) VALUES (12, '맥주', '먹고싶다', now(), 2, 'N');

CREATE TABLE IF NOT EXISTS `BoardFiles` (
    `id` int NOT NULL,
    `post_id` int NOT NULL,
    `file_name` varchar(300),

    primary key (`id`),
    foreign key (`post_id`) references `BoardPosts`
);

MERGE INTO `BoardFiles` KEY (`id`) VALUES (1, 1, 'a.txt');
MERGE INTO `BoardFiles` KEY (`id`) VALUES (2, 1, 'b.txt');

CREATE TABLE IF NOT EXISTS `BoardUuids` (
    `id` int NOT NULL,
    `file_id` int NOT NULL,
    `post_id` int NOT NULL,
    `uuid_value` varchar(100),

    primary key (`id`),
    foreign key (`file_id`) references `BoardFiles`,
    foreign key (`post_id`) references `BoardPosts`
);

MERGE INTO `BoardUuids` KEY (`id`) VALUES (1, 1, 1, 'aszc0-29d.txt' );
MERGE INTO `BoardUuids` KEY (`id`) VALUES (2, 2, 1, 'aasd-123d.txt' );
