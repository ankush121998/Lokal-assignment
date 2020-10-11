
CREATE DATABASE IF NOT EXISTS test CHARACTER SET utf8;

CREATE TABLE IF NOT EXISTS test.tasks (
  id          INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  create_dt   DATETIME,
  description VARCHAR(79),
  hasDone     BIT NULL     DEFAULT 0
)
  ENGINE = InnoDB;

INSERT INTO test.tasks VALUES (1, 20170426161122, 'Get a repository on Git', 1);
INSERT INTO test.tasks VALUES (2, 20170429171537, 'Create a Maven project', 1);
INSERT INTO test.tasks VALUES (3, 20170429182121, 'Run and check that everything works', 1);
INSERT INTO test.tasks VALUES (4, 20170426194152, 'Add gitignore file to project directory', 0);
INSERT INTO test.tasks VALUES (5, 20170426194253, 'Link a local project to a repository', 1);
INSERT INTO test.tasks VALUES (6, 20170501194454, 'Make beautiful', 0);
INSERT INTO test.tasks VALUES (7, 20170502194454, 'Take screenshots', 0);
INSERT INTO test.tasks VALUES (8, 20170503194454, 'Post link', 0);