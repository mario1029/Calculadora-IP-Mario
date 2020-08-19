package db;

import java.sql.*;

public class DataBase {

	private static DataBase DB= new DataBase();
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private String driverDB = "org.postgresql.Driver";
	private String dbName = "ip";
	private String urlDB = "jdbc:postgresql://localhost:5432/"+this.dbName;
	private String userDB = "postgres";
	private String passDB = "apwmwg.ga";
	public DataBase() {
		try {
			Class.forName(driverDB);
			this.conn = DriverManager.getConnection(urlDB,userDB,passDB);
		}catch (ClassNotFoundException| SQLException e) {
			e.printStackTrace();  
		}
	}
	
	public static DataBase getInstances() {
		return DB;
	}
	
	public ResultSet dbStatement(String query, String lista[][]) {
		try {
			
			this.stmt = this.conn.createStatement();
			this.rs = this.stmt.executeQuery(query);
			int i=0;
				while(rs.next()) {
					String e[]= {rs.getString(1),rs.getString(2),rs.getString(3),
								rs.getString(4),rs.getString(5),rs.getString(6),	 
								rs.getString(7),rs.getString(8),rs.getString(9),
								rs.getString(10),rs.getString(11)};
					lista[i]=e;i++;
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					this.stmt.close();
					this.rs.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			return rs;
		}
	
	public void dbPrepareStatement(String query, String[] obj) {
		try {
			if(!existe(obj[0],obj[1])) {
				this.pstmt=this.conn.prepareStatement(query);
				for(int i=0;i<11;i++) {
					this.pstmt.setString(i+1, obj[i]);
				}
				this.pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(!existe(obj[0],obj[1])) {
				try {
					this.pstmt.close();
				}catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void dbClose() {
		try {
			this.conn.close();
			//System.out.println("Conexion cerrada");
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean existe(String ip,String mask) {
		try {
			this.stmt = this.conn.createStatement();
			this.rs = this.stmt.executeQuery("select * from ip");
			while(rs.next()) {
				String clave1=rs.getString("ip");
				String clave2=rs.getString("mask");
				if(clave1.equalsIgnoreCase(ip) && clave2.equalsIgnoreCase(mask)) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public int cantidad() {
		int i=0;
		try {
			this.stmt = this.conn.createStatement();
			this.rs = this.stmt.executeQuery("select * from ip");
			while(rs.next()) {
				i++;
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
}