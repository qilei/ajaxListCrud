(function($) {
	// the widget definition, where "custom" is the namespace,
	// "colorize" the widget name
	$.widget("custom.crud", {
		// default options
		options : {
			action : null,
			url : null,
			dialogWidth : 800,
			dialogHeight : 600,

			// callbacks
			rowInserted : function (event, data) { }
		},

		/************************************************************************
		 * PRIVATE FIELDS                                                        *
		 *************************************************************************/

		_$addRecordDiv : null, //Reference to the adding new record dialog div (jQuery object)

		/************************************************************************
		 * CONSTRUCTOR                                                           *
		 *************************************************************************/

		/* Overrides base method to do create-specific constructions.
		 *************************************************************************/
		_create : function() {
			var btnAdd=this.element;
			var self = this;
			self._createAddRecordDialogDiv();
			$(btnAdd).bind("click", function() {
				self._$addRecordDiv.dialog({
					open:function(event){
						self._$addRecordDiv.load(self.options.url,function(){
							var $addRecordForm = self._$addRecordDiv.find('form');
							self._saveAddRecordForm($addRecordForm);
						});
					}
				});
				self._$addRecordDiv.dialog("open");
				
			});
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
				buttons : [ { //Cancel button
					text : "取消",
					click : function() {
						self._$addRecordDiv.dialog('close');
					}
				}, { //Save button
					id : 'AddRecordDialogSaveButton',
					text : "确认",
					click : function() {
						var $saveButton = $('#AddRecordDialogSaveButton');
						var $addRecordForm = self._$addRecordDiv.find('form');

						//                              if (self._trigger("formSubmitting", null, { form: $addRecordForm, formType: 'create' }) != false) {
						//                                  self._setEnabledOfDialogButton($saveButton, false, self.options.messages.saving);
//						self._saveAddRecordForm($addRecordForm, $saveButton);
						//                              }
						$addRecordForm.submit();
					}
				} ],
				close : function() {
					var $addRecordForm = self._$addRecordDiv.find('form')
							.first();
//					var $saveButton = $('#AddRecordDialogSaveButton');
					//                  self._trigger("formClosed", null, { form: $addRecordForm, formType: 'create' });
					//                  self._setEnabledOfDialogButton($saveButton, true, self.options.messages.save);
					$addRecordForm.remove();
				}
			});
		},

        /* Saves new added record to the server and updates table.
        *************************************************************************/
        _saveAddRecordForm: function ($addRecordForm) {
            var self = this;
            
            $addRecordForm.unbind("submit");
            $addRecordForm.bind("submit",function(e) {
				//if ($form.valid()) {
					$.ajax({
						type : "post",
						url : $addRecordForm.attr("action"),
						data : $addRecordForm.serialize(),
						success : function(data, status, jqXHR) {
							$addRecordForm.find('.errorMsg').empty();
							$addRecordForm.find('input').removeClass('error');
							if (data.status === "SUCCESS") {
								self._$addRecordDiv.dialog("close");
								self._trigger("rowInserted", null, {});
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
        },
	});
})(jQuery);
