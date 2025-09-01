package test;

public class Test {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            var cn = java.sql.DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/universidad?useSSL=false&serverTimezone=UTC",
                    "root", "root1234"
            );
            System.out.println("✅ OK: " + cn.getCatalog());
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
