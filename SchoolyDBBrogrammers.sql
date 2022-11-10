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

