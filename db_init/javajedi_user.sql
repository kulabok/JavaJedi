INSERT INTO java_jedi_secured.users (about_me, account_non_expired, account_non_locked, credentials_non_expired,
                           email, enabled, password, rank, registration_date, score, username)
VALUES ('This is qqq', true, true, true, 'a', true, '$2a$10$QbKIO266egPSTCE940KJyu3BxZ/yWfawaufXT0aMLYGrqwvfL6LpC', 'PADAVAN', '2017-03-29', 0, 'qqq');
INSERT INTO java_jedi_secured.users (about_me, account_non_expired, account_non_locked, credentials_non_expired,                                                                                         email, enabled, password, rank, registration_date, score, username)
VALUES ('This is aaa', true, true, true, 'b', true, '$2a$10$TCshpPyoJm/SAH5q2gVmweaijH7Uyx0/863ftqCLYo/eFp4rmhl5e', 'PADAVAN', '2017-03-29', 0, 'aaa');
INSERT INTO java_jedi_secured.users (about_me, account_non_expired, account_non_locked, credentials_non_expired,
                           email, enabled, password, rank, registration_date, score, username)
VALUES ('This is zzz', true, true, true, 'c', true, '$2a$10$evrx1wPys8.ZVxMVcmms9eN7yW3QDtdbIViGIy/A8bvNSRnc7yp1.', 'PADAVAN', '2017-03-29', 0, 'zzz');
INSERT INTO java_jedi_secured.users (about_me, account_non_expired, account_non_locked, credentials_non_expired,
                           email, enabled, password, rank, registration_date, score, username)
VALUES ('This is www', true, true, true, 'd', true, '$2a$10$wtD7Z8oF3ECfld1qZ62PE..38Ch4q8IeXaj4HtRdlK/iIhEltK.AG', 'PADAVAN', '2017-03-29', 0, 'www');
INSERT INTO java_jedi_secured.users (about_me, account_non_expired, account_non_locked, credentials_non_expired,
                           email, enabled, password, rank, registration_date, score, username)
VALUES ('This is sss', true, true, true, 'e', true, '$2a$10$Aitt9U7ERTPJUQHOYVKfdOyIAT7uQLiWuCRW397d.zwP2OOYLDlFC', 'PADAVAN', '2017-03-29', 0, 'sss');

INSERT INTO roles (user_id, role) VALUES (1, 'USER');
INSERT INTO roles (user_id, role) VALUES (4, 'ADMIN');
INSERT INTO roles (user_id, role) VALUES (2, 'USER');
INSERT INTO roles (user_id, role) VALUES (3, 'USER');
INSERT INTO roles (user_id, role) VALUES (4, 'USER');
INSERT INTO roles (user_id, role) VALUES (5, 'USER');