
CREATE TABLE users
(
    id INTEGER PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    isAdmin boolean

);


CREATE TABLE session
(
    id text PRIMARY KEY,
    expires_at DATE,
    user_id INTEGER

);



CREATE TABLE comment
(
    id INTEGER PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    text VARCHAR,
    user_id INTEGER,
    post_id INTEGER,
    date TIMESTAMP
);


CREATE TABLE post
(
    id INTEGER PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    title VARCHAR(50),
    body VARCHAR(200),
    date TIMESTAMP,
    user_id INTEGER
);


ALTER TABLE session
    ADD CONSTRAINT fk_user_session
        FOREIGN KEY (user_id)
            REFERENCES users (id);

ALTER TABLE comment
    ADD CONSTRAINT fk_user_comment
        FOREIGN KEY (user_id)
            REFERENCES users (id);

ALTER TABLE comment
    ADD CONSTRAINT fk_post_comment
        FOREIGN KEY (post_id)
            REFERENCES post (id);

ALTER TABLE post
    ADD CONSTRAINT fk_user_post
        FOREIGN KEY (user_id)
            REFERENCES users (id);