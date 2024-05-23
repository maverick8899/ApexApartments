 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<h1 class="text-center text-info mt-1">QUẢN TRỊ FEEDBACK</h1>
<body>
    <div class="container">
        <div class="row">
            <div class="col-md-8 offset-md-2">

                <c:if test="${feedbacks != null}">
                    <c:forEach items="${feedbacks}" var="f">
                        <div class="card mb-3">
                            <div class="card-body"> 
                                <h5 class="modal-title">Id: ${f.feedback_id} -- ${f.feedback_title} <p class="text-info">${f.customer_name}</p></h5>
                                <div class="form-group">
                                    <textarea class="form-control" rows="2" placeholder="Write a comment">${f.feedback_content}</textarea>
                                </div> 
                            </div> 
                        </div>
                    </c:forEach>
                </c:if>
            </div>
        </div>
    </div> 
</body>