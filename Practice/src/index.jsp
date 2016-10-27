<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@page import="java.util.*" %>
<%@page import="java.lang.*" %>
<html>
    <Body>
        <title>
            Q&amp;A System
        </title>
<%!
    int sessionID = 0;
    List<Integer> sessionIDSet = new ArrayList<Integer>();
%>
<h3>
    Welcome to the Q&amp;A system !
</h3>
<p>
    Please Login First
</p>
<form
    Username: <input type="text" name="username"/>
    Password: <input type="password" name="password"/>
/form>


    </Body>
</html>


