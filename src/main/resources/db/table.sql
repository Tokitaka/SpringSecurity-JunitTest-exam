create table user_tb (
    id int auto_increment primary key, 
    username varchar not null unique,
    password varchar not null,
    email varchar not null unique, 
    created_at timestamp not null, 
    enabled int
);

commit;