<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>    
<form:form id="editForm" modelAttribute="contact" method="post">
	<form:hidden path="id"/>
	<table class="tab_information" cellpadding="0" cellspacing="0" width="100%">
		<tr>
			<th><span class="red">*</span> <form:label path="name"> 姓名：</form:label></th>
			<td><form:input path="name" /> <div class="errorMsg"><form:errors path="name" cssClass="error" /></div></td>
		</tr>
		<tr>
			<th><span class="red">*</span> <form:label path="phone"> 电话：</form:label></th>
			<td><form:input path="phone" /> <div class="errorMsg"><form:errors path="phone" cssClass="error" /></div></td>
		</tr>
		<tr>
			<th><span class="red">*</span> <form:label path="email"> 邮箱：</form:label></th>
			<td><form:input path="email"/> <div class="errorMsg"><form:errors path="email" cssClass="error"/></div></td>
		</tr>
		<tr>
			<th><span class="red">*</span> <form:label path="addr"> 地址：</form:label></th>
			<td><form:input path="addr"/> <div class="errorMsg"><form:errors path="addr" cssClass="error"/></div></td>
		</tr>
	</table>
</form:form>
