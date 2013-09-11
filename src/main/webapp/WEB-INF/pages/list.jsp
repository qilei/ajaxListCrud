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
	<c:url var="editUrl" value="/contact/edit" />
	<c:url var="delUrl" value="/contact/delete" />
	<div>
	<button id="btnAdd">新增</button>
	<button id="btnEdit" disabled="disabled">修改</button>
	<button id="btnDel" disabled="disabled">删除</button>
	</div>
	<div id="ContactTableContainer"></div>
	<div id="dialog" title="通讯录编辑">
	<script type="text/javascript">
	var validationInfo={
		rules : {
			phone : {
				required : true
			}
		},
		errorPlacement: function(error, element) {
			element.parent("td").find('.errorMsg').html(error);
		}
	};
    $(document).ready(function () {
        $('#ContactTableContainer').jtable({
            title: '通讯录',	
            actions: {
                listAction: '${listUrl}',
            },
            paging: true,
            pageSize: 5,
            selecting: true,
            selectingCheckboxes: true,
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
            },
            selectionChanged: function () {
                var $selectedRows = $('#ContactTableContainer').jtable('selectedRows');
 
                if ($selectedRows.length > 0) {
                	initBtnEdit();
                	initBtnDel();
                	$("#btnEdit,#btnDel").attr("disabled",false);
                }else{
                	$("#btnEdit,#btnDel").attr("disabled",true);
                }
            }
        });
        $('#ContactTableContainer').jtable('load');
		
		$("#btnAdd").crud({
			action:"create",
			url:"${addUrl}",
			messages:{title:"新增联系人"},
			formCreated:function(event,data){
				data.form.validate(validationInfo);
			},
			formSubmitting:function(event,data){
				if(!data.form.valid()){
					event.preventDefault();
					return false;
				}
			},
			recordAdded:function(){
				$('#ContactTableContainer').jtable('reload');
			}
		});
    });
    
    function initBtnEdit(){
		$("#btnEdit").crud({
			action:"edit",
			url:"${editUrl}"+"?id="+$('#ContactTableContainer').jtable('selectedRows').first().data("record").id,
			formCreated:function(event,data){
				data.form.validate(validationInfo);
			},
			formSubmitting:function(event,data){
				if(!data.form.valid()){
					event.preventDefault();
					return false;
				}
			},
			recordAdded:function(){
				$('#ContactTableContainer').jtable('reload');
			}
		});
    }
    
    function initBtnDel(){
		$("#btnDel").crud({
			action:"delete",
			url:"${delUrl}",
			data:{id:$('#ContactTableContainer').jtable('selectedRows').first().data("record").id},
			recordDeleted:function(){
				$('#ContactTableContainer').jtable('reload');
			}
		});
    }

</script>
</body>
</html>