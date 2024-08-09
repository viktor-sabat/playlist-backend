drop table if exists playlist_songs;
drop table if exists song;
drop table if exists playlist;
drop table if exists app_user;

create table song (
  id bigint primary key auto_increment,
  title varchar(200),
  artist varchar(100),
  album varchar(100),
  length int,
  genre varchar(50)
);

create table app_user (
  id bigint primary key auto_increment,
  first_name varchar(50),
  last_name varchar(50),
  email varchar(100) not null unique
);

create table playlist (
  id bigint primary key auto_increment,
  name varchar(100),
  max_songs int,
  user_id bigint,
  foreign key (user_id) references app_user(id)
);

create table playlist_song (
   playlist_id bigint references playlist(id),
   song_id bigint references song(id)

);