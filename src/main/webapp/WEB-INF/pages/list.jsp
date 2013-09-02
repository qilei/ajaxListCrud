<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../fragments/headTag.jsp"/>
<body>
<c:url var="datasource" value="/contact/list" />
	<datatables:table id="contactTable" url="${datasource }" cdn="true" row="contact">
		<datatables:column title="姓名" property="name"></datatables:column>
		<datatables:column title="电话" property="phone"></datatables:column>
		<datatables:column title="邮箱" property="email"></datatables:column>
		<datatables:column title="地址" property="addr"></datatables:column>
	</datatables:table>
</body>
</html>