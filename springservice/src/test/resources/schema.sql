
CREATE TABLE Family(
    id INT AUTO_INCREMENT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE Father
(
    pesel       varchar(11) NOT NULL PRIMARY KEY,
    firstName   varchar(10) NOT NULL,
    secondName  varchar(30) NOT NULL,
    birthDate   date NOT NULL,
    familyId    INT UNIQUE NOT NULL,
    FOREIGN KEY (familyId) REFERENCES Family(id)
);

CREATE TABLE Child
(
    pesel       varchar(11) NOT NULL PRIMARY KEY,
    firstName   varchar(10) NOT NULL,
    secondName  varchar(30) NOT NULL,
    sex         varchar(10) NOT NULL,
    familyId    INT NOT NULL,
    FOREIGN KEY (familyId) REFERENCES Family(id)
);
