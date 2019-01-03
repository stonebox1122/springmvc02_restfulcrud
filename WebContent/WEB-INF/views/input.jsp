<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<br><br>
	<form action="testConversionServiceConverter" method="post">
		<!-- 将字符串转换为Employee对象：gg-gg@stone.com-0-105  -->
		Employee:<input type="text" name="employee">
		<br>
		<input type="submit" value="Submit">
	</form>
	
	<!-- 
		1。为什么使用form标签？可以更快速的开发出表单页面，而且可以更方便的进行表单值的回显
		2。注意：可以通过ModelAttribute属性指定绑定的模型属性，
		        若没有指定该属性，则默认从request域对象中读取command的表单bean
		        如果该属性值信息也不存在，则会发生错误。
		  java.lang.IllegalStateException: Neither BindingResult nor plain target object for bean name 'command' available as request attribute
	 -->
	 <!-- ${pageContext.request.contextPath }/emp表示使用绝对路径，否则在修改的时候会出现问题 -->
	 <form:form action="${pageContext.request.contextPath }/emp" method="POST" modelAttribute="employee">
	 	
	 	<!-- 显示该表单所有错误 -->
	 	<form:errors path="*"></form:errors>
	 	<br><br>
	 	
	 	<c:if test="${employee.id == null }">
	 		<!-- path属性对应html表单标签的name属性值 -->
	 		LastName:<form:input path="lastName"/>
	 		<form:errors path="lastName"></form:errors>
	 	</c:if>
	 	<c:if test="${employee.id != null }">
	 		<form:hidden path="id"/>
	 		<!-- 对应_method，不能使用form:hidden标签，因为modelAttribute对应的bean中没有_method这个属性 -->
	 		<input type="hidden" name="_method" value="PUT"/>
	 	</c:if>
	 	<br>
	 	Email:<form:input path="email"/>
	 	<form:errors path="email"></form:errors>
	 	<br>
	 	<%
	 		Map<String, String> genders = new HashMap();
	 		genders.put("1", "Male");
	 		genders.put("0", "Female");
	 		request.setAttribute("genders", genders);
	 	%>
	 	Gender:
	 	<br>
	 	<form:radiobuttons path="gender" items="${genders }" delimiter="<br>"/>
	 	<br>
	 	Department:<form:select path="department.id" items="${departments }" itemLabel="departmentName" itemValue="id"></form:select>
	 	<br>
	 	<!-- 
	 		1.数据类型转换
	 		2.数据类型格式化
	 		3.数据校验
	 		1).如何校验？注解？
	 		（1）。使用JSR 303验证标准
	 		（2）。加入hibernate validator验证框架的Jar包
	 		（3）。在SpringMVC配置文件中添加<mvc:annotation-driven/>
	 		（4）。需要在bean的属性上添加对应的注解
	 		（5）。在目标方法bean类型的前面添加@Valid注解
	 		2）。验证出错转向到哪一个页面
	 		3）。错误消息？如何显示，如何把错误消息进行国际化
	 	 -->
	 	Birth:<form:input path="birth"/>
	 	<form:errors path="birth"></form:errors>
	 	<br>
	 	Salary:<form:input path="salary"/>
	 	<br>
	 	<input type="submit" value="Submit">
	 </form:form>
	
</body>
</html>