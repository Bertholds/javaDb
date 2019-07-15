package bertholds.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import bertholds.util.DButil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class StudentDao {
    static int result;
    static ResultSet resultSet;

    public static void insertStudent( String name, String surname, String email )
            throws ClassNotFoundException, SQLException {

        try {
            String query = "INSERT INTO student (name, surname, email) VALUES('" + name + "', '" + surname + "', '"
                    + email + "')";
            DButil.dbExecuteQuery( query );

        } catch ( Exception e ) {
            System.out.println( "Data not insert verified your query please" );
            throw e;
        }

    }

    public static int deleteStudent( int id, String name, String email ) throws ClassNotFoundException, SQLException {

        String query = "DELETE FROM student WHERE (name='" + name + "' AND id='" + id + "') OR (email= '" + email + "'  AND id='" + id + "') ";
        result = DButil.dbExecuteQuery( query );
        return result;
    }

    public static void updateStudent( int id, String name, String email ) throws ClassNotFoundException, SQLException {
        String query = "UPDATE student SET name = '" + name + "', email='" + email + "' WHERE id='" + id
                + "' ";
        DButil.dbExecuteQuery( query );
    }

    public static ObservableList<Student> getAllRecord() throws ClassNotFoundException, SQLException {
        String query = "SELECT * FROM student";
        try {
            resultSet = DButil.dbExecute( query );
            ObservableList<Student> studentList = getStudent( resultSet );
            return studentList;
        } catch ( Exception e ) {
            System.out.println( "Echec de la requete" );
            e.printStackTrace();
            throw e;
        }

    }

    private static ObservableList<Student> getStudent( ResultSet resultSet )
            throws ClassNotFoundException, SQLException {

        ObservableList<Student> studentL = FXCollections.observableArrayList();
        try {
            while ( resultSet.next() ) {
                Student student = new Student();
                student.setId( resultSet.getInt( "id" ) );
                student.setName( resultSet.getString( "name" ) );
                student.setSurname( resultSet.getString( "surname" ) );
                student.setEmail( resultSet.getString( "email" ) );
                studentL.add( student );
            }
            return studentL;
        } catch ( SQLException e ) {
            System.out.println( "Error occured while fetching the record from database" + e );
            e.printStackTrace();
            throw e;
        }

    }
    
    public static ObservableList<Student> searchStudent(int id, String name) throws Exception{
    	String query = "SELECT * FROM student WHERE id='"+id+"' OR name='"+name+"' ";
    	try {
        	resultSet = bertholds.util.DButil.dbExecute(query);
        	ObservableList<Student> studentList = getSearchStudent(resultSet);
    		return studentList;
		} catch (SQLException e) {
	      System.out.println("Erreur lors de l'execution de la requete " + e.getMessage());
	      throw e;
		}
    	
    }

	private static ObservableList<Student> getSearchStudent(ResultSet resultSet) throws Exception {
		try {
			ObservableList<Student> stuList = FXCollections.observableArrayList();
			while (resultSet.next()) {
				Student student = new Student();
				student.setId(resultSet.getInt(1));
				student.setName(resultSet.getString(2));
				student.setSurname(resultSet.getString(3));
				student.setEmail(resultSet.getString(4));
				stuList.add(student);
			}
			return stuList;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		
	}

}
