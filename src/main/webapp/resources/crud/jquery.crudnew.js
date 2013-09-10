(function($) {
	// the widget definition, where "custom" is the namespace,
	// "colorize" the widget name
	$.widget("custom.crud", {
		// default options
		options : {
			action : null,
			url : null,
//			dialogWidth : 800,
//			dialogHeight : 600,
			validationInfo:null,

			// callbacks
			recordAdded : function (event, data) { }
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
			var self = this;
			self._createAddRecordDialogDiv();
			self.element.click(function(e){
				self._showAddRecordForm();
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
//				width:self.options.dialogWidth,
//				height:self.options.dialogHeight,
				modal : true,
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
		
        /* Shows add new record dialog form.
        *************************************************************************/
        _showAddRecordForm: function () {
            var self = this;
			self._$addRecordDiv.dialog({
				open:function(event){
					self._$addRecordDiv.load(self.options.url,function(){
						var $addRecordForm = self._$addRecordDiv.find('form');
						if(self.options.validationInfo){
							$addRecordForm.validate(self.options.validationInfo);
						}
						self._saveAddRecordForm($addRecordForm);
					});
				}
			});
			self._$addRecordDiv.dialog("open");
        },

        /* Saves new added record to the server and updates table.
        *************************************************************************/
        _saveAddRecordForm: function ($addRecordForm) {
            var self = this;
            
            $addRecordForm.unbind("submit");
            $addRecordForm.bind("submit",function(e) {
				if ($addRecordForm.valid()) {
					$.ajax({
						type : "post",
						url : $addRecordForm.attr("action"),
						data : $addRecordForm.serialize(),
						success : function(data, status, jqXHR) {
							$addRecordForm.find('.errorMsg').empty();
							$addRecordForm.find('input').removeClass('error');
							if (data.status === "SUCCESS") {
								self._$addRecordDiv.dialog("close");
								self._trigger("recordAdded", null, {});
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
				return false;
			});
        },
	});
})(jQuery);
