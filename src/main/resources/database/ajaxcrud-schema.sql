create table contact (
    id int not null,
    name varchar(80) null,
    phone varchar(80) null,
    email varchar(80) null,
    addr varchar(80) null,
    constraint pk_contact primary key (id)
);

CREATE TABLE sequence
(
    name               varchar(30)  not null,
    nextid             int          not null,
    constraint pk_sequence primary key (name)
);