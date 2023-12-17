create table contents(
    id bigint not null auto_increment,
    name varchar(100) not null unique,
    description varchar(200),
    flag boolean,

    primary key (id)
);