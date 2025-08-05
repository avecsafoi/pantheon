DELETE
FROM children;
DELETE
FROM sys_user;

INSERT INTO children (id, name, user_id)
VALUES (1, 'Jone', 1),
       (2, 'Jack', 1),
       (3, 'Jack2', 1),
       (4, 'Jack', 15),
       (5, 'Billie', 15);

INSERT INTO sys_user (id, name, age, email)
VALUES (1, 'Jone', 18, 'test1@koscom.co.kr'),
       (2, 'Jack', 20, 'test2@koscom.co.kr'),
       (3, 'Jack', 20, 'test3@koscom.co.kr'),
       (4, 'Jack', 20, 'test4@koscom.co.kr'),
       (5, 'Jack', 20, 'test5@koscom.co.kr'),
       (6, 'Jack', 20, 'test6@koscom.co.kr'),
       (7, 'Jack', 20, 'test7@koscom.co.kr'),
       (8, 'Jack', 20, 'test8@koscom.co.kr'),
       (9, 'Jack', 20, 'test9@koscom.co.kr'),
       (10, 'Jack', 20, 'test10@koscom.co.kr'),
       (11, 'Jack', 20, 'test11@koscom.co.kr'),
       (12, 'Jack', 20, 'test12@koscom.co.kr'),
       (13, 'Jack', 20, 'test13@koscom.co.kr'),
       (14, 'Jack', 20, 'test14@koscom.co.kr'),
       (15, 'Tom', 28, 'test15@koscom.co.kr'),
       (16, 'Sandy', 21, 'test16@koscom.co.kr'),
       (17, 'Billie', 24, 'test17@koscom.co.kr');