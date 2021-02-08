import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*
 * Class for managing the database.
 */
public class Database {
	
	// If you have the mysql server on your own computer use "localhost" as server address.
	private static String databaseServerAddress = "vm23.cs.lth.se";
	private static String databaseUser = "al4524gr";             // database login user
	private static String databasePassword = "yqn0jx40";          // database login password
	private static String database = "al4524gr";             // the database to use, i.e. default schema
	Connection conn = null;
	
	public Database() {
		try{
			Class.forName("com.mysql.jdbc.Driver");  //Necessary on Windows computers
			conn = DriverManager.getConnection("jdbc:mysql://" + databaseServerAddress + "/" + 
                    database, databaseUser, databasePassword);
			
			// Display the contents of the database in the console. 
			// This should be removed in the final version
			Statement stmt = conn.createStatement();		    
		    ResultSet rs = stmt.executeQuery("select * from Respondents"); 
		    while (rs.next( )) {
		    	String name = rs.getString("name"); 
		    	System.out.println(name);
		    	}

		    stmt.close();
			
		} catch (SQLException e) {
			printSqlError(e);
		} catch (ClassNotFoundException e) {    
            e.printStackTrace();
		}
	}
	
	public boolean addName(String name) {
		boolean resultOK = false;
		PreparedStatement ps = null;
		try{
			String sql = "insert into Respondents (name) values(?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
		    ps.executeUpdate(); 
		    resultOK = true;
		    ps.close();
		} catch (SQLException e) {
		    resultOK = false;  // one reason may be that the name is already in the database
		    if(e.getErrorCode()==1062 && e.getSQLState().equals("23000")){ 
		    	// duplicate key error
		    	System.out.println(name + " already exists in the database");
		    } else {
				printSqlError(e);
		    }
		} finally {
			if(ps != null){
				try {
					ps.close();
				} catch (SQLException e) {
					printSqlError(e);
				}
			}
		}
		return resultOK;
	}
	
	public boolean addProjectType(String name, String type) {
		boolean resultOK = false;
		PreparedStatement ps = null;
		try{
			String sql = "insert into ProjectType(name, type) values(?, " + type + ")";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
		    ps.executeUpdate(); 
		    resultOK = true;
		    ps.close();
		} catch (SQLException e) {
		    resultOK = false;  // one reason may be that the name is already in the database
		    if(e.getErrorCode()==1062 && e.getSQLState().equals("23000")){ 
		    	// duplicate key error
		    	System.out.println(name + " already exists in the database");
		    } else {
				printSqlError(e);
		    }
		} finally {
			if(ps != null){
				try {
					ps.close();
				} catch (SQLException e) {
					printSqlError(e);
				}
			}
		}
		return resultOK;
	}
	
	public boolean addAnswer(String name, List<Integer> answers/*int questionNum, int answer*/) {
		boolean resultOK = false;
		PreparedStatement ps = null;
		try{
//			String sql = "insert into Answers(name, answer"+ questionNum + ") values(?, '"+ answer +"')";
			String sql = "insert into Answers(name, answer1, answer2, answer3, answer4) values ";
            sql += "( ?, " + answers.get(0) + ", " + answers.get(1) + ", " + answers.get(2) + ", " + answers.get(3) + ")";
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
//			ps.setString(2, name);
		    ps.executeUpdate(); 
		    resultOK = true;
		    ps.close();
		} catch (SQLException e) {
		    resultOK = false;  // one reason may be that the name is already in the database
		    if(e.getErrorCode()==1062 && e.getSQLState().equals("23000")){ 
		    	// duplicate key error
		    	System.out.println(name + " already exists in the database");
		    } else {
				printSqlError(e);
		    }
		} finally {
			if(ps != null){
				try {
					ps.close();
				} catch (SQLException e) {
					printSqlError(e);
				}
			}
		}
		return resultOK;
	}
	
	public List<String> getDatabase() throws SQLException {
        List<String> resultList = new ArrayList<>();
        try (Statement stm = conn.createStatement()){
            String sql = "SELECT ProjectType.name, type, answer1, answer2, answer3, answer4\n"
                        + "FROM ProjectType\n"
                        + "INNER JOIN Answers\n"
                        + "ON ProjectType.name = Answers.name;";
            ResultSet rs = stm.executeQuery(sql);
            while (rs.next()) {
                resultList.add(
                        rs.getString("name") + ", " + 
                        rs.getString("type") + ", " + 
                        rs.getString("answer1") + ", " + 
                        rs.getString("answer2") + ", " + 
                        rs.getString("answer3") + ", " + 
                        rs.getString("answer4")
                );
            }
        } catch (SQLException e) {
            if(e.getErrorCode()==1062 && e.getSQLState().equals("23000")){ 
                // duplicate key error
                System.out.println("Something went wrong");
            } else {
                printSqlError(e);
            }
        }
        return resultList;
    }
	
	
	
	private void printSqlError(SQLException e){
	    System.out.println("SQLException: " + e.getMessage());
	    System.out.println("SQLState: " + e.getSQLState());
	    System.out.println("VendorError: " + e.getErrorCode());
		e.printStackTrace();
	}

}
