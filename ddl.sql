CREATE TABLE user(
    email VARCHAR(150) NOT NULL,
    name VARCHAR(50) NOT NULL,
    photo_path VARCHAR(150),
    PRIMARY KEY(email)
);

CREATE TABLE friend(
    idx INT(5) NOT NULL AUTO_INCREMENT,
    email VARCHAR(150) NOT NULL,
    friend_email VARCHAR(150) NOT NULL,
    PRIMARY KEY(idx),
    CONSTRAINT UNIQUE_EMAIL_FRIENDEMAIL UNIQUE (email, friend_email)
);

CREATE TABLE room(
     idx INT(5) NOT NULL AUTO_INCREMENT,
     room_name VARCHAR(50) NOT NULL,
     admin VARCHAR(150) NOT NULL,
     people INT(5) NOT NULL DEFAULT 0,
     photo_path VARCHAR(150),
     PRIMARY KEY(idx)
);

CREATE TABLE user_in_room(
     idx INT(5) NOT NULL AUTO_INCREMENT,
     email VARCHAR(150) NOT NULL,
     room_name VARCHAR(50) NOT NULL,
     room_idx INT(5) NOT NULL,
     PRIMARY KEY(idx)
);