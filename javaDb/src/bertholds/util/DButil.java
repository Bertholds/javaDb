package bertholds.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sun.rowset.CachedRowSetImpl;

public class DButil {
    private static final String               JDBC_DRIVER       = "com.mysql.jdbc.Driver";
    private static final String               CONN_STRING       = "jdbc:mysql://localhost:3306/javaFX";

    private static Connection                 connection        = null;
    private static java.sql.PreparedStatement preparedStatement = null;
    private static ResultSet                  resultSet         = null;

    public static void dbConnect() throws ClassNotFoundException, SQLException {

        try {
            Class.forName( JDBC_DRIVER );
        } catch ( ClassNotFoundException e ) {
            System.out.println( "where is your My sql jdbc driver ?" );
            e.printStackTrace();
            throw e;
        }

        System.out.println( "Charging drivers successful" );

        try {
            connection = DriverManager.getConnection( CONN_STRING, "root", "" );
            System.out.println( " connexion database successful" );

        } catch ( SQLException e ) {
            System.out.println( "wrong to connect on database" );
            throw e;
        }
    }

    public static void dbDisconnect() throws SQLException {

        try {
            if ( connection != null && !connection.isClosed() ) {
                connection.close();

            }
        } catch ( SQLException e ) {
            e.printStackTrace();
        }
    }

    // this is the insert update and delete organisation
    public static void dbExecuteQuery( String query ) throws SQLException, ClassNotFoundException {
        try {
            dbConnect();
            preparedStatement = connection.prepareStatement( query );
            resultSet = preparedStatement.executeQuery();
        } catch ( Exception e ) {
            System.out.println( " problem occured at dbexecuteQuery operation" + e );
            throw e;
        } finally {
            if ( preparedStatement != null ) {
                preparedStatement.close();
            }
            if ( resultSet != null ) {
                resultSet.close();
            }
            dbDisconnect();
        }
    }

    public static ResultSet dbExecute( String query ) throws ClassNotFoundException, SQLException {
        CachedRowSetImpl cachedRowSetImpl = null;
        try {
            dbConnect();
            preparedStatement = connection.prepareStatement( query );
            resultSet = preparedStatement.executeQuery();
            cachedRowSetImpl = new CachedRowSetImpl();
            cachedRowSetImpl.populate( resultSet );
        } catch ( Exception e ) {
            System.out.println( " problem occured at dbexecuteQuery operation" + e );
            throw e;
        } finally {
            if ( resultSet != null ) {
                resultSet.close();
            }
            if ( preparedStatement != null ) {
                preparedStatement.close();
            }
            dbDisconnect();
        }
        return cachedRowSetImpl;
    }

}
