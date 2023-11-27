<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute name="name" required="true" %>
<%@ attribute name="label" required="true" %>
<%@ attribute name="order" required="true" type="SevereShop.Model.Configs.0rder.Order" %>
<tr>
    <td>${label}</td>
    <td>
        ${order[name]}
    </td>
</tr>
