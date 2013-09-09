(function($) {
	$.fn.crud = function(options) {
		// Merge passed options with defaults
		var opts = $.extend({}, $.fn.crud.defaults, options);
		
		return this.each(function() {
			//验证 todo
			if (opts.action && opts.action==="create") {
		    	
		    	var $addRecordDiv=null;
		    	$addRecordDiv = $('<div id="addRecordDiv" />').appendTo($("body"));
		    	$addRecordDiv.dialog({
		            autoOpen: false,
		            width: 'auto',
		            minWidth: '300',
		            modal: true,
		            buttons:
		                    [{ //Save button
		                        id: 'AddRecordDialogSaveButton',
		                        text: "确认",
		                        click: function () {
		                            //var $saveButton = $('#AddRecordDialogSaveButton');
		                            var $addRecordForm = $addRecordDiv.find('form');
		                            $addRecordForm.submit();
		                        }
		                    },{ //Cancel button
		                        text: "取消",
		                        click: function () {
		                        	$addRecordDiv.dialog('close');
		                        }
		                    }],
		            close: function () {
		                var $addRecordForm = $addRecordDiv.find('form').first();
		                $addRecordForm.remove();
		            }
		        });
		    	
				$(this).bind("click",function(){
					$addRecordDiv.dialog({
						open:function(event){
							$addRecordDiv.load(opts.url,function(){
								
								var $form = $addRecordDiv.find('form');
								
								$form.unbind("submit");
								$form.bind("submit",function(e) {
									//if ($form.valid()) {
										$.ajax({
											type : "post",
											url : $form.attr("action"),
											data : $form.serialize(),
											success : function(data, status, jqXHR) {
												$form.find('.errorMsg').empty();
												$form.find('input').removeClass('error');
												if (data.status === "SUCCESS") {
													$addRecordDiv.dialog( "close" );
													opts.rowInserted();
												} else if (data.status === "VALIDATION_FAIL") {
													for (var i = 0; i < data.entity.length; i++) {
														var item = data.entity[i];
														var $controlInput = $("input[name='"+ item.fieldName+"']");
														$controlInput.addClass('error');
														$controlInput.parent().find('.errorMsg').html(item.message);
													}
												} else {
													alert("保存失败！" + data.entity);
												}
											}
										});
									//}
									e.preventDefault();
									return false;
								});
								
							});
						}				
					});
					$addRecordDiv.dialog("open");
					return false;
				});
			}
		});
	};

	// crud plugin default options
	$.fn.crud.defaults = {
		action : "",
		url : "",
		dialogWidth : 800,
		dialogHeight : 600,
		rowInserted:null
	};
})(jQuery);
	
