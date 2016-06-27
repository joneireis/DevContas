<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Class.forName("oracle.jdbc.driver.OracleDriver");
    // Use the following 2 files whening running inside Oracle 8i
    // Connection conn = new oracle.jdbc.driver.OracleDriver().
    //                     defaultConnection ();
    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//192.168.96.93:1521/orcl", "dbaimpar", "dbamvimpar");
    //out.println("Conectou com sucesso");
    //Statement stmt = conn.createStatement();


%>