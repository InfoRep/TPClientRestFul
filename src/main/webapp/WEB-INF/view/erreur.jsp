<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout>
	<jsp:attribute name="pageTitle">Accueil</jsp:attribute>

    <jsp:attribute name="title">Ups une erreur est survenue !</jsp:attribute>
    
    <jsp:body>
   		${erreur}
    </jsp:body>
</t:layout>
