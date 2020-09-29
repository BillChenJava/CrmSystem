var TableEditable = function() {
	return {
		// main function to initiate the module
		init : function() {
			function restoreRow(oTable, nRow) {
				var aData = oTable.fnGetData(nRow);
				var jqTds = $('>td', nRow);
				for ( var i = 0, iLen = jqTds.length; i < iLen; i++) {
					oTable.fnUpdate(aData[i], nRow, i, false);
				}
				oTable.fnDraw();
			}

			function editRow(oTable, nRow) {
				var aData = oTable.fnGetData(nRow);
				var jqTds = $('>td', nRow);
				jqTds[0].innerHTML = '<input type="text" class="m-wrap small" disabled value="'+ aData[0] + '">';
				jqTds[1].innerHTML = '<input type="text" class="m-wrap small" value="'+ aData[1] + '">';
				jqTds[2].innerHTML = '<input type="text" class="m-wrap small" value="'+ aData[2] + '">';
				jqTds[3].innerHTML = '<input type="text" class="m-wrap small" disabled style="width:200px !important;" value="icon-table">';
				jqTds[4].innerHTML = '<a class="edit btn purple mini" href=""><i class="icon-save"></i>Save</a>'+ '&nbsp;<a class="cancel btn grey mini" href=""><i class="icon-minus-sign"></i>Cancel</a>';
			}

			function saveRow(oTable, nRow) {
				var jqInputs = $('input', nRow);
				oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
				oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
				oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
				oTable.fnUpdate(jqInputs[3].value, nRow, 3, false);
				oTable.fnUpdate('<a class="edit btn blue mini" href=""><i class="icon-edit"></i>Edit</a> <a class="delete btn green mini" href=""><i class="icon-trash"></i>Delete</a>',nRow, 4, false);
				oTable.fnDraw();
			}

			function cancelEditRow(oTable, nRow) {
				var jqInputs = $('input', nRow);
				oTable.fnUpdate(jqInputs[0].value, nRow, 0, false);
				oTable.fnUpdate(jqInputs[1].value, nRow, 1, false);
				oTable.fnUpdate(jqInputs[2].value, nRow, 2, false);
				oTable.fnUpdate(jqInputs[3].value, nRow, 3, false);
				oTable.fnUpdate('<a class="edit btn blue mini" href=""><i class="icon-edit"></i>Edit</a>',nRow, 4, false);
				oTable.fnDraw();
			}

			var oTable = $('#sample_editable_1').dataTable({
				"aLengthMenu" : [ 
				        [ 5, 10, 15, -1 ],
						[ 5, 10, 15, "All" ] // change per page values here
				],
				// set the initial value
				"iDisplayLength" : 5,
				"sDom" : "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
				"sPaginationType" : "bootstrap",
				"oLanguage" : {
					"sLengthMenu" : "_MENU_ Number of records per page",
					"oPaginate" : {
						"sPrevious" : "Previous page",
						"sNext" : "Next page"
					}
				},
				"aoColumnDefs" : [ {
					'bSortable' : false,
					'aTargets' : [ 0 ]
				} ]
			});

			jQuery('#sample_editable_1_wrapper .dataTables_filter input').addClass("m-wrap medium"); // modify table search input
            jQuery('#sample_editable_1_wrapper .dataTables_length select').addClass("m-wrap small"); // modify table per page dropdown
            jQuery('#sample_editable_1_wrapper .dataTables_length select').select2({
                showSearchInput : false //hide search box with special css class
            }); // initialzie select2 dropdown
			var nEditing = null;
			//id  区分添加和修改 修改时 =0 添加时 !=0 
			var newid=0;

			$('#sample_editable_1_new').click(function(e) {
				var url = "test_customer_cate!getMaxId?v="+ new Date().valueOf();
				$.get(url,function(d) {
					if (d > 0) {
						e.preventDefault();
						var aiNew = oTable.fnAddData(
							['','','','','<a class="edit btn blue mini" href=""><i class="icon-edit"></i>Edit</a> <a class="mycancel btn gray mini" data-mode="new" href=""><i class="icon-minus-sign"></i>Cancel</a>' ]);
						var nRow = oTable.fnGetNodes(aiNew[0]);
						oTable.fnGetData(nRow)[0]=d;//
						newid=d;//当添加时 将ajax 生成的id 保存到newid 
						
		                editRow(oTable, nRow);
		                nEditing = nRow;
					} else {
						$("#errmsg").text("ID loading failed");
						$("#btnerr").click();
					}
				});
				// nEditing = nRow;
			});

			$('#sample_editable_1 a.delete').live('click', function (e) {
                e.preventDefault();
                if (confirm("Are you sure you want to delete this line?") == false) {
                    return;
                }
                //此id只能在页面第一次载入好 之后的数据做删除 才能拿到  ajax 新添行  删除 拿到是0
                //var id = e.currentTarget.id;
                var nRow = $(this).parents('tr')[0];
                var id=$(nRow).children(0).html(); //拿到第一个单元格的 ID  做删除
                var url = "test_customer_cate!delCate?id=" + id + "&v=" + new Date().valueOf();
				$.get(url, function(d) {
					if (d == "success") {
						//删除行
		                oTable.fnDeleteRow(nRow);
						$("#errmsg").text("successfully deleted");
						$("#btnerr").click();
					} else {
						$("#errmsg").text("failed to delete");
						$("#btnerr").click();
					}
				});
            });

			$('#sample_editable_1 a.cancel').live('click', function(e) {
				e.preventDefault();
				if ($(this).attr('data-mode') == 'new') {
					var nRow = $(this).parents('tr')[0];
					oTable.fnDeleteRow(nRow);
				} else {
					restoreRow(oTable, nEditing);
					nEditing = null;
				}
			});


			$('#sample_editable_1 a.edit').live('click', function (e) {
                e.preventDefault();

                /* Get the row as a parent of the link that was clicked on */
            	
                var nRow = $(this).parents('tr')[0];

                if (nEditing !== null && nEditing != nRow) {
                	alert("Please complete the current edit line first.");
                	return;
                    /* Currently editing - but not this row - restore the old before continuing to edit mode */
                    /*restoreRow(oTable, nEditing);
                    editRow(oTable, nRow);
                    nEditing = nRow;
                    alert("Currently editing - but not this row - restore the old before continuing to edit mode :");
                    */
                } else if (nEditing == nRow && this.innerText == "Save") {
                    /* Editing this row and want to save it */
                	//获取编辑行 对象
                	var jqInputs = $('input', nEditing);
                	//获取编辑行 文本框value组成data 
                	var data = "id=" + jqInputs[0].value + "&customerCategory=" + jqInputs[1].value + "&parentid="
					+ jqInputs[2].value + "&customerIcon=icon-table";
                	//+ jqInputs[3].value;
                	//ajax  提交路径
                	var url="";
                	var msg="";//
                	var msgerr="";//
                	if (newid==0) {//
                    	url = "test_customer_cate!updateCate?v=" + new Date().valueOf();
                    	msg="Successfully modified";
                    	msgerr="fail to edit";
					}else{		  //提交到保存路径
	                	url = "test_customer_cate!saveCate?v=" + new Date().valueOf();
	                	msg="Added successfully";
	                	msgerr="add failed";
	                	newid=0;
					}
                	$.post(url, data, function(d) {
						if (d == "success") {
							saveRow(oTable, nEditing);
							nEditing = null;
							$("#errmsg").text(msg);
							$("#btnerr").click();
						} else {
							$("#errmsg").text(msgerr);
							$("#btnerr").click();
						}
					});
                    //alert("Updated! Do not forget to do some ajax to sync with backend :)");
                } else {
                    /* No edit in progress - let's start one */
                    editRow(oTable, nRow);
                    nEditing = nRow;
                    //alert("No edit in progress - let's start one :)");
                };
            });
		}

	};

}();