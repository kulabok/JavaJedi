INSERT INTO java_jedi_secured.user (about_me, account_non_expired, account_non_locked, credentials_non_expired,
                           email, enabled, password, rank, registration_date, score, username)
VALUES ('This is qqq', true, true, true, 'a', true, 'qqq', 'PADAVAN', '2017-03-29', 0, 'qqq');
INSERT INTO java_jedi_secured.user (about_me, account_non_expired, account_non_locked, credentials_non_expired,
                                                                                                                        email, enabled, password, rank, registration_date, score, username)
VALUES ('This is aaa', true, true, true, 'b', true, 'aaa', 'PADAVAN', '2017-03-29', 0, 'aaa');
INSERT INTO java_jedi_secured.user (about_me, account_non_expired, account_non_locked, credentials_non_expired,
                           email, enabled, password, rank, registration_date, score, username)
VALUES ('This is zzz', true, true, true, 'c', true, 'zzz', 'PADAVAN', '2017-03-29', 0, 'zzz');
INSERT INTO java_jedi_secured.user (about_me, account_non_expired, account_non_locked, credentials_non_expired,
                           email, enabled, password, rank, registration_date, score, username)
VALUES ('This is www', true, true, true, 'd', true, 'www', 'PADAVAN', '2017-03-29', 0, 'www');
INSERT INTO java_jedi_secured.user (about_me, account_non_expired, account_non_locked, credentials_non_expired,
                           email, enabled, password, rank, registration_date, score, username)
VALUES ('This is sss', true, true, true, 'e', true, 'sss', 'PADAVAN', '2017-03-29', 0, 'sss');

INSERT INTO roles (user_id, role) VALUES (1, 'USER');
INSERT INTO roles (user_id, role) VALUES (4, 'ADMIN');
INSERT INTO roles (user_id, role) VALUES (2, 'USER');
INSERT INTO roles (user_id, role) VALUES (3, 'USER');
INSERT INTO roles (user_id, role) VALUES (4, 'USER');
INSERT INTO roles (user_id, role) VALUES (5, 'USER');