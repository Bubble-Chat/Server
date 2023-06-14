

CREATE TABLE user (
    id  VARCHAR(100)    NOT NULL,
    email VARCHAR(100)  NOT NULL,
    name VARCHAR(100)  NOT NULL,
    photo_path VARCHAR(100),
    background_path VARCHAR(100),
    PRIMARY KEY (id)
);