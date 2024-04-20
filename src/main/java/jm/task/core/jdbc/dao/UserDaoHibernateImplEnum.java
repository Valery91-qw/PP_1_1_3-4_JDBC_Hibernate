package jm.task.core.jdbc.dao;

public enum UserDaoHibernateImplEnum {
    CREATE_TABLE("CREATE TABLE IF NOT EXISTS Users(id BIGINT NOT NULL AUTO_INCREMENT, name NVARCHAR(255), lastName NVARCHAR(255), age TINYINT, PRIMARY KEY (id));"),
    DROP_TABLE("DROP TABLE IF EXISTS Users;"),
    GET_ALL_FROM_TABLE("from User"),
    DELETE_ALL_FROM_TABLE("DELETE FROM Users;");
    private String query;
    UserDaoHibernateImplEnum(String arg) {
        this.query = arg;
    }
    public String getQuery() {
        return this.query;
    }
}
