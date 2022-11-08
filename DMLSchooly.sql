-- Inserting names

insert into course 
values
(1, 'Biology'), (2, 'Chemistry'), (3, 'Physics'), (4, 'Math'), (5, 'English');

insert into instructor
values
(200, 'Darwin', 'Nunezm', 'darwin@gmail.com', 1),
(201, 'Michael','Thomas','michael@gmail.com', 2),
(202, 'Einstein','Brooks','einstein@gmail.com',3),
(203, 'Leonhard','Euler','leonhard@gmail.com',4),
(204, 'William','Shakespeare','william@gmail.com',5);

insert into student
values
(300,'Claire','Ellison','claire.ellison@schooly.com'),
(301,'Dorothy','White','dorothy.white@schooly.com'),
(302,'Christian','Fraser','christian.fraser@schooly.com'),
(303,'Keith','Marshall','keith.marshall@schooly.com'),
(304,'Paul','Skinner','paul.skinner@schooly.com'),
(305,'Joshua','Taylor','joshua.taylor@schooly.com'),
(306,'Steven','Dowd','steven.dowd@schooly.com'),
(307,'Alexandra','Butler','alexandra.butler@schooly.com'),
(308,'Rachel','Jones','rachel.jones@schooly.com'),
(309,'Stewart','Carr','stewart.carr@schooly.com');


insert into takes
values
(300,1),(301,1),(302,1),(303,1),(304,1),(305,1),(306,1),(307,1),(308,1),(309,1),
(300,2),(301,2),(302,2),(303,2),(304,2),(305,2),(306,2),(307,2),(308,2),(309,2),
(300,3),(301,3),(302,3),(303,3),(304,3),(305,3),(306,3),(307,3),(308,3),(309,3),
(300,4),(301,4),(302,4),(303,4),(304,4),(305,4),(306,4),(307,4),(308,4),(309,4),
(300,5),(301,5),(302,5),(303,5),(304,5),(305,5),(306,5),(307,5),(308,5),(309,5);

insert into assignment
values
(1, 'HW1', '2022-12-12', 'Answer question 1, 2, 3 and 4'),
(2, 'HW1', '2022-11-12', 'Read chapter 2'),
(3, 'Test 1', '2022-12-03', 'Test chapter 1'),
(4, 'HW1', '2022-12-09', 'Question 3 and 4'),
(5, 'HW1', '2022-12-01', 'Try question 4');

insert into stud_assign
values
(300,1,'HW1', null),(301,1,'HW1', null),(302,1,'HW1', null),(303,1,'HW1', null),(304,1,'HW1', null),(305,1,'HW1', null),(306,1,'HW1', null),(307,1,'HW1', null),(308,1,'HW1', null),(309,1,'HW1', null),
(300,2,'HW1', null),(301,2,'HW1', null),(302,2,'HW1', null),(303,2,'HW1', null),(304,2,'HW1', null),(305,2,'HW1', null),(306,2,'HW1', null),(307,2,'HW1', null),(308,2,'HW1', null),(309,2,'HW1', null),
(300,3,'Test 1', null),(301,3,'Test 1', null),(302,3,'Test 1', null),(303,3,'Test 1', null),(304,3,'Test 1', null),(305,3,'Test 1', null),(306,3,'Test 1', null),(307,3,'Test 1', null),(308,3,'Test 1', null),(309,3,'Test 1', null),
(300,4,'HW1', null),(301,4,'HW1', null),(302,4,'HW1', null),(303,4,'HW1', null),(304,4,'HW1', null),(305,4,'HW1', null),(306,4,'HW1', null),(307,4,'HW1', null),(308,4,'HW1', null),(309,4,'HW1', null),
(300,5,'HW1', null),(301,5,'HW1', null),(302,5,'HW1', null),(303,5,'HW1', null),(304,5,'HW1', null),(305,5,'HW1', null),(306,5,'HW1', null),(307,5,'HW1', null),(308,5,'HW1', null),(309,5,'HW1', null);
