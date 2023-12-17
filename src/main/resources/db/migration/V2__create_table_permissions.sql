create table permissions(
    id bigint not null auto_increment,
    name varchar(100) not null unique,
    content_id bigint not null ,
    flag boolean,

    primary key (id),
    constraint fk_permissions_contents_id foreign key(content_id) references contents(id)
);