import java.sql.*;


public class Main {

    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement ps;

    public static void main(String[] args) {

        try {
            connect();

            connection.setAutoCommit(false);

            System.out.println("Создаём таблицу");
            createTable();
            System.out.println();

            System.out.println("Добавляем 3 записи в таблицу");
            psSetRecord();
            stmtSetRecord();
            stmtSetRecord();
            System.out.println();

            System.out.println("Получаем все записи из таблицы:");
            getAllRecords();
            System.out.println();

            System.out.println("Получаем запись из таблицы с именем 'Michael':");
            getRecord();
            System.out.println();

            System.out.println("Удаляем одну запись из таблицы и получаем оставшиеся записи из таблицы:");
            deleteRecord();
            getAllRecords();
            System.out.println();

            System.out.println("Удаляем таблицу");
            deleteTable();

            connection.commit();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        } finally {
            disconnect();
        }
    }


    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:main.db");
            stmt = connection.createStatement();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

//    public static void getRecord() {
//        try {
//
//            ResultSet rs = stmt.executeQuery("SELECT * FROM test WHERE name = 'Roman'");
//            while (rs.next()) {
//                System.out.println(rs.getString("name") + " " + rs.getString("surname") + " " + rs.getString("age"));
//            }
//        } catch (SQLException throwable) {
//            throwable.printStackTrace();
//        }
//    }

    public static void getRecord() {
        try {
            ps = connection.prepareStatement("SELECT * FROM test WHERE name = ?");
            ps.setString(1, "Michael");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("name") + " " + rs.getString("surname") + " " + rs.getString("age"));
            }
            rs.close();
            ps.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public static void getAllRecords() {
        try {

            ResultSet rs = stmt.executeQuery("SELECT * FROM test");
            while (rs.next()) {
                System.out.println(rs.getString("name") + " " + rs.getString("surname") + " " + rs.getString("age"));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }


    public static void stmtSetRecord() {
        try {
            stmt.executeUpdate("INSERT INTO test (name, surname, age) VALUES ('Roman', 'Ivanov', 35)");
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public static void psSetRecord() {
        try {
            ps = connection.prepareStatement("INSERT INTO test (name, surname, age) VALUES (?, ?, ?)");
            ps.setString(1, "Michael");
            ps.setString(2, "Rostov");
            ps.setInt(3, 30);
            ps.addBatch();
            ps.executeBatch();
            ps.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

//    public static void deleteRecord() {
//        try {
//            stmt.executeUpdate("DELETE FROM test WHERE name = 'Michael'");
//        } catch (SQLException throwable) {
//            throwable.printStackTrace();
//        }
//    }

    public static void deleteRecord() {
        try {
            ps = connection.prepareStatement("DELETE FROM test WHERE name = ?");
            ps.setString(1, "Michael");
            ps.addBatch();
            ps.executeBatch();
            ps.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public static void createTable() {
        try {
            stmt.executeUpdate("CREATE TABLE test (" +
                    " name TEXT NOT NULL," +
                    " surname TEXT NOT NULL," +
                    " age INTEGER NOT NULL)");
            stmt.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }


    public static void deleteTable() {
        try {
            stmt.executeUpdate("DROP TABLE test");
            stmt.close();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

}
