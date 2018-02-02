# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table student (
  id                            integer not null,
  name                          varchar(255),
  age                           integer,
  constraint pk_student primary key (id)
);


# --- !Downs

drop table if exists student;

