drop table if exists messages;
create table messages(
    id bigserial primary key,
    message_value varchar(32),
    sha2 varchar(64)
);