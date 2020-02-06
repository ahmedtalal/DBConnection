package conndb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author javcoder
 * Here i used singleton design pattern
 * 
 */
public class DatabaseConnection {

    private static DatabaseConnection connection;
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/ college ";
    private static final String UNICODE = "?useUnicode=yes&characterEncoding=UTF-8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private Connection conn = null;
    private PreparedStatement ps = null;
    private Statement st = null;
    private ResultSet rs = null;
    private ResultSetMetaData rsm = null;
    private int count = 0;

    private DatabaseConnection() {
    }

    public static DatabaseConnection getInstance() {
        if (connection == null) {
            connection = new DatabaseConnection();
        }
        return connection;
    }

    // this method is used to create connection with data base
    private static final Connection getconnection() {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            try {
                conn = DriverManager.getConnection(DB_URL + UNICODE, USERNAME, PASSWORD);
            } catch (SQLException ex) {
                Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    public int insertIntoDB(String query, ArrayList<Object> dataList) {
        int count = 0;
        try {
            conn = DatabaseConnection.getconnection();
            ps = conn.prepareStatement(query);
            int i = 1;
            for (Object dataList1 : dataList) {
                ps.setObject(i, dataList1);
                i++;
            }
            count = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return count;
    }

    /*
    this method is used to select any data from databse.
    it contains one parameter 
    */
    public ArrayList<Object> RetrieveDataFromDB(String query) {
        ArrayList<Object> list = new ArrayList<>();
        try {
            conn = DatabaseConnection.getconnection();
            st = conn.createStatement();
            rs = st.executeQuery(query);
            rsm = rs.getMetaData();
            int cot = rsm.getColumnCount();
            if (rs.first()) {
                do {
                    ArrayList<Object> liss = new ArrayList<>(cot);
                    for (int j = 1; j <= cot; j++) {
                        liss.add(rs.getObject(j));
                    }
                    list.add(liss);
                } while (rs.next());
            }

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (st != null) {
                    st.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception e) {
            }
        }
        return list;
    }

    public int UpdateDataIntoDB(String query, ArrayList<Object> updataList) {

        try {
            conn = DatabaseConnection.getconnection();
            ps = conn.prepareStatement(query);
            int i = 1;
            for (Object dataList1 : updataList) {
                ps.setObject(i, dataList1);
                i++;
            }
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
            }
        }
        return count;
    }

    public int deleteDataFromDB(String query) {

        try {
            conn = DatabaseConnection.getconnection();
            ps = conn.prepareStatement(query);
            count = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (ps != null) {
                    ps.close();
                }
            } catch (Exception e) {
            }
        }
        return count ;
    }
}
