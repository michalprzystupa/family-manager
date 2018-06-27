package familyManager;

class Query {
    static final String SELECT_DISTINCT_FAMILY_ID_BY_PESEL = "SELECT DISTINCT familyId FROM Child WHERE pesel LIKE ?";
    static final String SELECT_CHILD_BY_FAMILY_ID = "SELECT * FROM Child WHERE familyId = ?";
    static final String SELECT_FATHER_BY_FAMILY_ID = "Select * FROM Father WHERE familyId = ?";
    static final String CREATE_FAMILY = "INSERT INTO Family() VALUES ()";
    static final String CREATE_FATHER = "INSERT INTO Father(firstName, secondName, pesel, birthDate, familyId) VALUES(?, ?, ?, ?, ?);";
    static final String CREATE_CHILD = "INSERT INTO Child(firstName, secondName, pesel, sex, familyId) VALUES(?, ?, ?, ?, ?);";
}
