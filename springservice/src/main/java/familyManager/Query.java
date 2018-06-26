package familyManager;

class Query {
    static final String SELECT_DISTINCT_FAMILY_ID_BY_PESEL = "SELECT DISTINCT familyId FROM Child WHERE pesel LIKE ?";
    static final String SELECT_CHILD_BY_ID = "SELECT * FROM Child WHERE id = ?";

}
