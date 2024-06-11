create table tb_users (
    id uuid default gen_random_uuid(),
    email text not null unique,
    password text not null,
    role varchar(30) not null,
    primary key (id)
);