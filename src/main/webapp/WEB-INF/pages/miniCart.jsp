<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:useBean id="cart" class="SevereShop.Model.Configs.Cart.Cart" scope="request"/>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<a href="${pageContext.servletContext.contextPath}/controller?command=CART">
    <fmt:message key="cart"/>: ${not empty cart.totalCost ? cart.totalCost : 0}$
</a>