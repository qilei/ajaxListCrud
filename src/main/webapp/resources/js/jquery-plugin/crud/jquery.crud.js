(function($) {
	// the widget definition, where "custom" is the namespace,
	// "crud" the widget name
	$.widget("custom.crud", {
		// default options
		options : {
			action : null,
			url : null,
			data : null,

			// callbacks
			formCreated : function (event,data){},
			formSubmitting : function (event,data){},
			recordAdded : function (event, data) {},
			recordDeleted : function (event, data) {},
			
            //Localization
            messages: {
                title: '编辑'
            }
		},

		/************************************************************************
		 * PRIVATE FIELDS                                                        *
		 *************************************************************************/

		_$addRecordDiv : null, //Reference to the adding new record dialog div (jQuery object)
		_$showRecordDiv : null,

		/************************************************************************
		 * CONSTRUCTOR                                                           *
		 *************************************************************************/

		/* Overrides base method to do create-specific constructions.
		 *************************************************************************/
		_create : function() {
			var self = this;
			if(self.options.action == "create" || self.options.action == "edit"){
				self._createAddRecordDialogDiv();
				self.element.click(function(e){
					self._showAddRecordForm();
					e.preventDefault();
				});
			}else if(self.options.action == "delete"){
				self.element.click(function(e){
					self._deleteRecord();
					e.preventDefault();
				});
			}else if(self.options.action == "show"){
				self._createShowRecordDialogDiv();
				self.element.click(function(e){
					self._showViewRecordForm();
					e.preventDefault();
				});
			}
		},

		/* Creates and prepares add new record dialog div
		 *************************************************************************/
		_createAddRecordDialogDiv : function() {
			var self = this;

			//Check if createAction is supplied
			if (!self.options.action) {
				return;
			}

			//Create a div for dialog and add to container element
			self._$addRecordDiv = $('<div />').appendTo($("body"));

			//Prepare dialog
			self._$addRecordDiv.dialog({
				autoOpen : false,
				width : 'auto',
				minWidth : '300',
				modal : true,
				title : self.options.messages.title,
				buttons : [ { //Save button
					id : 'AddRecordDialogSaveButton',
					text : "确认",
					click : function() {
						var $addRecordForm = self._$addRecordDiv.find('form');
						$addRecordForm.submit();
					}
				},{ //Cancel button
					text : "取消",
					click : function() {
						self._$addRecordDiv.dialog('close');
					}
				}],
				close : function() {
					var $addRecordForm = self._$addRecordDiv.find('form').first();
					$addRecordForm.remove();
				}
			});
		},

		/* Creates and prepares add show record dialog div
		 *************************************************************************/
		_createShowRecordDialogDiv : function() {
			var self = this;

			//Check if createAction is supplied
			if (!self.options.action) {
				return;
			}

			//Create a div for dialog and add to container element
			self._$showRecordDiv = $('<div />').appendTo($("body"));

			//Prepare dialog
			self._$showRecordDiv.dialog({
				autoOpen : false,
				width : 'auto',
				minWidth : '300',
				modal : true,
				title : "查看",
				buttons : [ { //Save button
					id : 'ShowRecordDialogCloseButton',
					text : "关闭",
					click : function() {
						self._$showRecordDiv.dialog('close');
					}
				}],
				close : function() {
					var $showRecordForm = self._$showRecordDiv.find('form').first();
					$showRecordForm.remove();
				}
			});
		},
		
        /* Shows add new record dialog form.
        *************************************************************************/
        _showAddRecordForm: function () {
            var self = this;
			self._$addRecordDiv.dialog({
				open:function(event){
					self._$addRecordDiv.load(self.options.url,function(){
						var $addRecordForm = self._$addRecordDiv.find('form');
			            self._trigger("formCreated", null, { form: $addRecordForm});
			            self._saveAddRecordForm($addRecordForm);
					});
				}
			});
			self._$addRecordDiv.dialog("open");
        },
		
        /* Shows view record dialog form.
        *************************************************************************/
        _showViewRecordForm: function () {
            var self = this;
			self._$showRecordDiv.dialog({
				open:function(event){
					self._$showRecordDiv.load(self.options.url,function(){
						var $showRecordForm = self._$showRecordDiv.find('form');
			            self._trigger("formCreated", null, { form: $showRecordForm});
					});
				}
			});
			self._$showRecordDiv.dialog("open");
        },

        /* Saves new added record to the server and updates table.
        *************************************************************************/
        _saveAddRecordForm: function ($addRecordForm) {
            var self = this;
            
            $addRecordForm.unbind("submit");
            $addRecordForm.bind("submit",function(e) {
            	if(self._trigger("formSubmitting", null, { form: $addRecordForm}) != false){
					$.ajax({
						type : "post",
						url : $addRecordForm.attr("action"),
						data : $addRecordForm.serialize(),
						success : function(data, status, jqXHR) {
							$addRecordForm.find('.errorMsg').empty();
							$addRecordForm.find('input').removeClass('error');
							if (data.status === "SUCCESS") {
								self._$addRecordDiv.dialog("close");
								self._trigger("recordAdded");
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
            	}
				e.preventDefault();
			});
        },
        _deleteRecord : function(){
        	var self=this;
			 if(confirm("您确认要删除吗？")){
				 $.ajax({
					 type:"post",
					 url:self.options.url,
					 data:self.options.data,
					 success:function(data){
						if (data.status === "SUCCESS") {
							alert("删除成功！");
						}else {
							alert("操作失败！" + data.entity);
						}
						self._trigger("recordDeleted");
					 }
				 });
			 }
        }
	});
})(jQuery);
