<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="datatables" uri="http://github.com/dandelion/datatables" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="../fragments/headTag.jsp"/>
<body>
	<c:url var="listUrl" value="/contact/list" />
	<c:url var="addUrl" value="/contact/add" />
	<div>
	<button id="btnAdd">新增</button>
	</div>
	<div id="ContactTableContainer"></div>
	<div id="dialog" title="通讯录编辑">
	<script type="text/javascript">
    $(document).ready(function () {
        $('#ContactTableContainer').jtable({
            title: 'Table of contact',	
            actions: {
                listAction: '${listUrl}',
            },
            paging: true,
            pageSize: 5,
            fields: {
            	id: {
                    key: true,
                    list: false
                },
                name: {
                    title: '姓名',
                    width: '40%'
                },
                phone: {
                    title: '电话',
                    width: '20%'
                },
                email: {
                    title: '邮箱',
                    width: '20%'
                },
                addr: {
                    title: '地址',
                    width: '20%'
                }
            }
        });
        $('#ContactTableContainer').jtable('load');
		
		$("#btnAdd").crud({
			action:"create",
			url:"${addUrl}",
			dialogWidth:400,
			dialogHeight:300,
			recordAdded:function(){
				$('#ContactTableContainer').jtable('reload');
			},
			validationInfo:{
				rules : {
					phone : {
						required : true
					}
				},
				errorPlacement: function(error, element) {
					element.parent("td").find('.errorMsg').html(error);
				}
			}
		});
    });

</script>
</body>
</html>