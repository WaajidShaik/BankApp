package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.jdbc.driver.OracleDriver;

public class Model 
{
	String url="jdbc:oracle:thin:@//localhost:1521/XE";

	String user="SYSTEM";
	String pwd="system";
	Connection con=null;
	PreparedStatement pstmt=null;	
	ResultSet res;

	String name;
	int accno;
	String userid;
	String password;
	int balance;
	String email;
	
	int amount;
	int rvcno;
	ArrayList al1=new ArrayList();
	ArrayList al2=new ArrayList();
	
	public int getRvcno() 
	{
		return rvcno;
	}

	public void setRvcno(int rvcno) 
	{
		this.rvcno = rvcno;
	}

	public int getAmount() 
	{
		return amount;
	}

	public void setAmount(int amount) 
	{
		this.amount = amount;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public int getAccno() 
	{
		return accno;
	}

	public void setAccno(int accno) 
	{
		this.accno = accno;
	}

	public String getUserid() 
	{
		return userid;
	}


	public void setUserid(String userid) 
	{
		this.userid = userid;
	}

	public String getPassword() 
	{
		return password;
	}

	public void setPassword(String password) 
	{
		this.password = password;
	}

	public int getBalance() 
	{
		return balance;
	}

	public void setBalance(int balance) 
	{
		this.balance = balance;
	}

	public String getEmail() 
	{
		return email;
	}

	public void setEmail(String email) 
	{
		this.email = email;
	}

	public Model()
	{
		try 
		{
			DriverManager.registerDriver(new OracleDriver());
			con=DriverManager.getConnection(url,user,pwd);
		} 
		catch (SQLException e) 
		{		
			e.printStackTrace();
		}
	}

	public boolean register() throws Exception
	{
		String sql="INSERT INTO BANK VALUES(?,?,?,?,?,?)";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, name);
		pstmt.setInt(2, accno);
		pstmt.setString(3, userid);
		pstmt.setString(4, password);
		pstmt.setInt(5, balance);
		pstmt.setString(6, email);

		int row=pstmt.executeUpdate();

		if(row==0)
		{	
			return false;
		}
		else
		{
			return true;
		}

	}

	public boolean login() throws Exception
	{
		String sql="SELECT ACCNO FROM BANK WHERE USERID=? AND PASSWORD=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, userid);
		pstmt.setString(2, password);

		res=pstmt.executeQuery();

		if(res.next()==true)
		{
			accno=res.getInt("ACCNO");
			return true;
		}

		else
		{
			return false;
		}

	}

	public boolean balance() throws Exception
	{
		String sql="SELECT BALANCE FROM BANK WHERE ACCNO=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, accno);
		res=pstmt.executeQuery();
		if(res.next()==true)
		{
			balance=res.getInt("BALANCE");
			return true;
		}

		else
		{
			return false;
		}
	}
 
	public boolean changePassword(String newpassword) throws Exception 
	{
		String sql="UPDATE BANK SET PASSWORD=? WHERE ACCNO=? AND PASSWORD=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setString(1, newpassword);
		pstmt.setInt(2, accno);
		pstmt.setString(3, password);

		int row=pstmt.executeUpdate();

		if(row==0)
		{	
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public boolean transfer1() throws Exception 
	{
		String sql="UPDATE BANK SET BALANCE=BALANCE-? WHERE ACCNO=? ";
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, amount);
		pstmt.setInt(2, accno);

		int row=pstmt.executeUpdate();

		if(row==0)
		{	
			return false;
		}
		else
		{
			statement1();
			return true;
		}

	}
	
	public void statement1()
	{
		String sql="INSERT INTO STATEMENT(ACCNO,DEBIT) VALUES(?,?)";
		try 
		{
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, accno);
			pstmt.setInt(2, amount);
			pstmt.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public boolean transfer2(int rvcno1) throws Exception 
	{
		String sql="UPDATE BANK SET BALANCE=BALANCE+? WHERE ACCNO=? ";
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, amount);
		pstmt.setInt(2, rvcno1);

		int row=pstmt.executeUpdate();

		if(row==0)
		{	
			return false;
		}
		else
		{
			statement2();
			return true;
		}

	}
	
	public void statement2()
	{
		String sql="INSERT INTO STATEMENT(ACCNO,CREDIT) VALUES(?,?)";
		try 
		{
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, rvcno);
			pstmt.setInt(2, amount);
			pstmt.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		
	}
	
	public ArrayList getCredit() throws Exception
	{
		String sql="SELECT CREDIT FROM STATEMENT WHERE ACCNO=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1,accno);
		res=pstmt.executeQuery();
		while(res.next()==true)
		{
			al1.add(res.getInt("CREDIT"));
		}
		return al1;	
	}
	
	public ArrayList getDebit() throws Exception
	{
		String sql="SELECT DEBIT FROM STATEMENT WHERE ACCNO=?";
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1,accno);
		res=pstmt.executeQuery();
		while(res.next()==true)
		{
			al2.add(res.getInt("DEBIT"));
		}
		return al2;
	}
}
