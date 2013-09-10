<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>ajax crud demo</title>


    <spring:url value="/webjars/bootstrap/2.3.0/css/bootstrap.min.css" var="bootstrapCss"/>
    <link href="${bootstrapCss}" rel="stylesheet"/>

    <spring:url value="/resources/css/petclinic.css" var="ajaxcrudCss"/>
    <link href="${ajaxcrudCss}" rel="stylesheet"/>

    <spring:url value="/webjars/jquery/1.9.0/jquery.js" var="jQuery"/>
    <script src="${jQuery}"></script>

    <spring:url value="/webjars/jquery-ui/1.9.2/js/jquery-ui-1.9.2.custom.js" var="jQueryUi"/>
    <script src="${jQueryUi}"></script>

    <spring:url value="/webjars/jquery-ui/1.9.2/css/smoothness/jquery-ui-1.9.2.custom.css" var="jQueryUiCss"/>
    <link href="${jQueryUiCss}" rel="stylesheet"></link>

    <spring:url value="/resources/jtable.2.3.0/themes/lightcolor/gray/jtable.css" var="jtableCss"/>
    <link href="${jtableCss}" rel="stylesheet"/>
    
	<spring:url value="/resources/jtable.2.3.0/jquery.jtable.min.js" var="jtable"/>
    <script src="${jtable}"></script>
    
	<spring:url value="/resources/crud/jquery.crudnew.js" var="crud"/>
    <script src="${crud}"></script>
    
	<spring:url value="/resources/jquery-plugin/validation/jquery.validate.js" var="jqueryValidation"/>
    <script src="${jqueryValidation}"></script>
    
	<spring:url value="/resources/jquery-plugin/validation/messages_zh.js" var="jqueryValidationMessage"/>
    <script src="${jqueryValidationMessage}"></script>
</head>


