create table course
(
    courseID int         not null
        primary key,
    name     varchar(20) not null,
    constraint course_pk
        unique (name)
);

create table instructor
(
    instID      int          not null
        primary key,
    first_name  varchar(20)  null,
    last_name   varchar(20)  null,
    email       varchar(255) null,
    courseID    int          null,
    course_name varchar(20)  null,
    constraint instructor_course_name_fk
        foreign key (course_name) references course (name),
    constraint instructor_ibfk_1
        foreign key (courseID) references course (courseID)
);

create index courseID
    on instructor (courseID);
    
create table assignment
(
    courseID    int          not null,
    name        varchar(20)  not null,
    due_date    date         null,
    description varchar(255) null,
    primary key (courseID, name),
    constraint assignment_ibfk_1
        foreign key (courseID) references course (courseID)
);

create table student
(
    studID int          not null
        primary key,
    f_name varchar(20)  null,
    l_name varchar(20)  null,
    email  varchar(255) null
);

create table stud_assign
(
    studID   int         not null,
    courseID int         not null,
    name     varchar(20) not null,
    grade    int         null,
    primary key (studID, courseID, name),
    constraint stud_assign_ibfk_1
        foreign key (studID) references student (studID),
    constraint stud_assign_ibfk_2
        foreign key (courseID, name) references assignment (courseID, name)
);

create index courseID
    on stud_assign (courseID, name);

create table takes
(
    studID   int not null,
    courseID int not null,

    primary key (studID, courseID),
    constraint takes_ibfk_1
        foreign key (studID) references student (studID),
    constraint takes_ibfk_2
        foreign key (courseID) references course (courseID)
);

create index courseID
    on takes (courseID);
    
create table assignment_sequence
(
    next_val bigint null
);
create table instructor_sequence
(
    next_val bigint null
);
create table student_sequence
(
    next_val bigint null
);

create table course_sequence
(
    next_val bigint null
);

DROP TABLE IF EXISTS `assignment_studentview`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `assignment_studentview`  AS   (select distinct `a`.`courseID` AS `courseID`,`a`.`name` AS `assignment_name`,`c`.`name` AS `course_name`,`a`.`due_date` AS `duedate`,`a`.`description` AS `description` from (((`assignment` `a` join `course` `c`) join `takes` `t`) join `student` `s`) where `a`.`courseID` = `c`.`courseID` and `t`.`courseID` = `c`.`courseID` and `s`.`studID` = `t`.`studID`)  ;

-- --------------------------------------------------------

--
-- Structure for view `grade_studentview`
--
DROP TABLE IF EXISTS `grade_studentview`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `grade_studentview`  AS   (select distinct `s`.`studID` AS `studID`,`s`.`courseID` AS `courseID`,`s`.`name` AS `name`,`s`.`grade` AS `grade`,`c`.`name` AS `course_name` from (`stud_assign` `s` join `course` `c` on(`s`.`courseID` = `c`.`courseID`)))  ;


CREATE TABLE `todo` (
  `item_name` varchar(255) NOT NULL,
  `todoid` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL
)