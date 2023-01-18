package dao;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.KadaiAccount;
import util.KadaiGenerateHashedPw;
import util.KadaiGenerateSalt;

public class KadaiAccountDAO {
	private static Connection getConnection() throws URISyntaxException, SQLException {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	    URI dbUri = new URI(System.getenv("DATABASE_URL"));

	    String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

	    return DriverManager.getConnection(dbUrl, username, password);
	}
	
	public static int registerAccount(KadaiAccount account) {
		String sql = "INSERT INTO kaaccount VALUES(default, ?, ?, ?, ?, ?, ?, current_timestamp)";
		int result = 0;
		
		String salt = KadaiGenerateSalt.getSalt(32);
		
		String hashedPw = KadaiGenerateHashedPw.getSaftyPassword(account.getPassword(), salt);
		
		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, account.getMail());
			pstmt.setString(2, hashedPw);
			pstmt.setString(3, account.getName());
			pstmt.setInt(4, account.getGrade());
			pstmt.setString(5, account.getDepartment());
			pstmt.setString(6, salt);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} finally {
			System.out.println(result + "件更新しました。");
		}
		return result;
	}
	
	public static String getSalt(String mail) {
		String sql = "SELECT salt FROM kaaccount WHERE mail = ?";
		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, mail);

			try (ResultSet rs = pstmt.executeQuery()){
				
				if(rs.next()) {
					String salt = rs.getString("salt");
					return salt;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// ログイン処理
	public static KadaiAccount login(String mail, String hashedPw) {
		String sql = "SELECT * FROM kaaccount WHERE mail = ? AND password = ?";
		try (
				Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				){
			pstmt.setString(1, mail);
			pstmt.setString(2, hashedPw);
			
			try (ResultSet rs = pstmt.executeQuery()){
				
				if(rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					int grade = rs.getInt("grade");
					String department = rs.getString("department");
					String salt = rs.getString("salt");
					String createdAt = rs.getString("created_at");
					return new KadaiAccount(id, mail, null, name, grade, department, salt, null);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

}
