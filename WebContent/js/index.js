/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



function AtualizaTabela(emp)
    {  
    $("#empresa").val(emp);
    $("#titulo_pagina").html("Recurso de Glosa");
    $('#DevContasTableContainer').jtable();
    if ($('#DevContasTableContainer').html() !== ""){
        $('#DevContasTableContainer').jtable('destroy');
    }
    atualizarValores();
    
    //$('#DevContasTableContainer').jtable('reload',{cd_multi_empresa: $("#empresa").val()});
    
    
    $('#DevContasTableContainer').jtable({
			title : 'Contas Reapresentadas',
                        paging: true,
                        pageSize: 10,
                        //sorting: true,
                        toolbarsearch:true,
                        messages: {   addNewRecord: 'Novo Registro',
                                      serverCommunicationError: 'Erro de comunicação com a base de dados',
                                      loadingMessage: 'Aguarde...',
                                      noDataAvailable: 'Sem registro',
                                      editRecord: 'Editar registro',
                                      areYouSure: 'Você tem certeza?',
                                      deleteConfirmation: 'Em excluir o registro?',
                                      save: 'Salvar',
                                      saving: 'Salvando',
                                      cancel: 'Cancelar',
                                      deleteText: 'Deletar',
                                      deleting: 'Deletando',
                                      error: 'Error',
                                      close: 'Fechar',
                                      cannotLoadOptionsFor: 'Can not load options for field {0}',
                                      pagingInfo: 'Mostrando {0}-{1} de {2} Contas',
                                      gotoPageLabel: 'Ir para',
                                      pageSizeChangeLabel: 'Total Reg',
                                      canNotDeletedRecords: 'Can not deleted {0} of {1} records!',
                                      deleteProggress: 'Deleted {0} of {1} records, processing...'
                                  },
			actions : {
                            listAction : '../../Controller?action=list',
                            createAction : '../../Controller?action=create&login='+$("#login").val()+'&cd_multi_empresa='+$("#empresa").val(),
                            updateAction : '../../Controller?action=update&login='+$("#login").val()+'&cd_multi_empresa='+$("#empresa").val(),
                            deleteAction : '../../Controller?action=delete&login='+$("#login").val()+'&cd_multi_empresa='+$("#empresa").val()
			},
			fields : {
				cd_devolucao_contas : {
					title : 'ID',
					width : '10%',
					key : true,
					list : false,
					edit : false,
					create : false
				},
				cd_conta : {
					title : 'Conta',
					width : '6%',
                                        inputClass: 'validate[required]',
					edit : true,
                                        
				},
                                cd_tipo_conta: {
                                        title: 'Tipo Conta',
                                        width: '6%',
                                        options:  {"I":"Conta Hospitalar","A":"Conta Ambulatorial(PS)"}
                                },
                                 cd_motivo_dev: {
                                        title: 'Motivo Dev.',
                                        width: '12%',
                                        options:  function () {
                                            if (cachedMotDevOptions) { //Check for cache
                                                return cachedMotDevOptions;
                                            }
                                        }
                                },
				dt_devolucao : {
					title : 'Devolução',
					width : '6%',
                                        type : 'date',
                                        inputClass: 'validate[required]',
                                        displayFormat: 'dd/mm/yy',
                                        edit : true
				},
				ds_obs : {
					title : 'Observação',
					width : '30%',
                                        type  : 'textarea',
					edit : true,                                  
				},
                                 //CHILD TABLE DEFINITION THE ATENDIMENTOS
                                Atendimento: {
                                    title: '',
                                    width: '1%',
                                    sorting: false,
                                    edit: false,
                                    create: false,
                                    display: function (devContas) {
                                        //Create an image that will be used to open child table
                                        var $img = $('<img src="../../img/list_metro.png" title="Informações do Atendimento" />');
                                        //Open child table when user clicks the image
                                        $img.click(function (data) {
                                            $('#DevContasTableContainer').jtable('openChildTable',
                                                    $img.closest('tr'),
                                                    {
                                                        title: devContas.record.cd_conta + ' - Conta',
                                                         messages: {    addNewRecord: 'Novo Registro',
                                                                        serverCommunicationError: 'Erro de comunicação com a base de dados',
                                                                        loadingMessage: 'Aguarde...',
                                                                        noDataAvailable: 'Sem registro',
                                                                        editRecord: 'Editar registro',
                                                                        areYouSure: 'Você tem certeza?',
                                                                        deleteConfirmation: 'Em excluir o registro?',
                                                                        save: 'Salvar',
                                                                        saving: 'Salvando',
                                                                        cancel: 'Cancelar',
                                                                        deleteText: 'Deletar',
                                                                        deleting: 'Deletando',
                                                                        error: 'Error',
                                                                        close: 'Fechar',
                                                                        cannotLoadOptionsFor: 'Can not load options for field {0}',
                                                                        pagingInfo: 'Showing {0}-{1} of {2} Jobs',
                                                                        pageSizeChangeLabel: 'Row count',
                                                                        gotoPageLabel: 'Go to page',
                                                                        canNotDeletedRecords: 'Can not deleted {0} of {1} records!',
                                                                        deleteProggress: 'Deleted {0} of {1} records, processing...'
                                                        },
                                                        actions: {
                                                            listAction: '../../Controller?action=infoatd&cd_multi_empresa='+devContas.record.cd_multi_empresa+'&cd_conta='+devContas.record.cd_conta+'&cd_tipo_conta='+devContas.record.cd_tipo_conta,
                                                        },
                                                        fields: {
                                                            cd_devolucao_contas: {
                                                                type: 'hidden',
                                                                defaultValue: devContas.record.cd_devolucao_contas
                                                            },
                                                            cd_atendimento: {
                                                                title: 'Atendimento',
                                                                width: '10%'
                                                            },
                                                            cd_conta: {
                                                                title: ' Conta',
                                                                width: '10%'
                                                            },
                                                            nm_paciente: {
                                                                title: 'Paciente',
                                                                width: '20%'
                                                            },
                                                            nm_convenio: {
                                                                title: 'Convênio',
                                                                width: '15%'
                                                            },
                                                            cd_remessa: {
                                                                title: 'Remessa',
                                                                width: '6%'
                                                            },
                                                            dt_entrega_da_fatura : {
                                                                title : 'Entrega da Fatura',
                                                                width : '12%',
                                                                type : 'date',
                                                                displayFormat: 'dd/mm/yy'
                                                            },
                                                            vl_total :{
                                                                title : 'Valor Total',
                                                                width : '6%'
                                                            },
                                                            dt_pagamento : {
                                                                title : 'Pagamento',
                                                                width : '12%',
                                                                type : 'date',
                                                                displayFormat: 'dd/mm/yy'
                                                            },
                                                            vl_recebido :{
                                                                title : 'Recebido',
                                                                width : '6%'
                                                            }
                                                        }
                                                    }, function (data) { //opened handler
                                                        data.childTable.jtable('load');
                                                    });
                                        });
                                        //Return image to show on the person row
                                        return $img;
                                    }
                                },
			},/*,
                        toolbar: {
                                    items: [{
                                        icon: '/images/excel.png',
                                        text: 'Export to Excel',
                                        click: function () {
                                            //perform your custom job...
                                        }
                                    },{
                                        icon: '/images/pdf.png',
                                        text: 'Export to Pdf',
                                        click: function () {
                                            //perform your custom job...
                                        }
                                    }]
                                }*/
                        //Initialize validation logic when a form is created
                            formCreated: function (event, data) {
                                //$("#jtable-edit-form").validationEngine('attach');
                                data.form.validationEngine();
                                
                            },
                            //Validate form when it is being submitted
                            formSubmitting: function (event, data) {
                                return data.form.validationEngine('validate');
                            },
                            //Dispose validation logic when form is closed
                            formClosed: function (event, data) {
                                data.form.validationEngine('hide');
                                data.form.validationEngine('detach');
                                $("#Edit-vl_reapresentado").autoNumeric('init'); 
                            }
		});
                $('#DevContasTableContainer').jtable('load',{cd_multi_empresa: $("#empresa").val()});
                
	
    
}
function AtualizarTabelaFat(emp)
{
    $("#empresa").val(emp);
    $("#titulo_pagina").html("Faturamento");
    $('#DevContasTableContainer').jtable();
    if ($('#DevContasTableContainer').html() !== ""){
        $('#DevContasTableContainer').jtable('destroy');
        
    }
    
    atualizarValores();
    
    
    //$('#DevContasTableContainer').jtable('reload',{cd_multi_empresa: $("#empresa").val()});
    
    $('#DevContasTableContainer').jtable({
			title : 'Contas Devolvidas',
                        paging: true,
                        pageSize: 10,
                        //sorting: true,
                        toolbarsearch:true,
                        messages: {   addNewRecord: 'Novo Registro',
                                      serverCommunicationError: 'Erro de comunicação com a base de dados',
                                      loadingMessage: 'Aguarde...',
                                      noDataAvailable: 'Sem registro',
                                      editRecord: 'Editar registro',
                                      areYouSure: 'Você tem certeza?',
                                      deleteConfirmation: 'Em excluir o registro?',
                                      save: 'Salvar',
                                      saving: 'Salvando',
                                      cancel: 'Cancelar',
                                      deleteText: 'Deletar',
                                      deleting: 'Deletando',
                                      error: 'Error',
                                      close: 'Fechar',
                                      gotoPageLabel: 'Ir para',
                                      pageSizeChangeLabel: 'Total Reg',
                                      cannotLoadOptionsFor: 'Can not load options for field {0}',
                                      pagingInfo: 'Mostrando {0}-{1} de {2} Contas',
                                      canNotDeletedRecords: 'Can not deleted {0} of {1} records!',
                                      deleteProggress: 'Deleted {0} of {1} records, processing...'
                                  },
			actions : {
                            listAction : '../../Controller?action=list',
                            updateAction : '../../Controller?action=updatefat&login='+$("#login").val()+'&cd_multi_empresa='+$("#empresa").val()
			},
			fields : {
				cd_devolucao_contas : {
					title : 'ID',
					width : '1%',
					key : true,
					list : false,
					edit : false,
					create : false
				},
				cd_conta : {
					title : 'Conta',
					width : '5%',
					edit : false,
                                        
				},
                                cd_tipo_conta: {
                                        title: 'Tipo Conta',
                                        width: '5%',
                                        edit : false,
                                        options:  {"I":"Conta Hospitalar","A":"Conta Ambulatorial(PS)"}
                                },
                                 cd_motivo_dev: {
                                        title: 'Motivo Dev.',
                                        width: '15%',
                                        edit : false,
                                        options:  function () {
                                            if (cachedMotDevOptions) { //Check for cache
                                                return cachedMotDevOptions;
                                            }
                                        }
                                },
				dt_devolucao : {
					title : 'Devolução',
					width : '5%',
                                        type : 'date',
                                        displayFormat: 'dd/mm/yy',
                                        edit : false
				},
				ds_obs : {
					title : 'Observação Recurso de Glosa',
					width : '15%',
                                        type  : 'textarea',
					edit : false
				},
                                cd_conta_nova : {
                                        title : 'Conta Nova',
					width : '7%',
                                        listClass: 'center',
                                        edit : true 
                                },
                                dt_reapresentacao : {
                                        title : 'Reapresentação',
					width : '5%',
                                        type : 'date',
                                        listClass: 'center',
                                        inputClass: 'validate[required]',
                                        displayFormat: 'dd/mm/yy',
                                        edit : true 
                                },
                                vl_reapresentado : {
					title : 'VL Reapresentado',
					width : '10%',
                                        type: 'decimal',
                                        inputClass: 'validate[required]',
					edit : true
				},
                                ds_obs_fat : {
					title : 'Observação Faturamento',
					width : '30%',
                                        type  : 'textarea',
					edit : true
				},
                                 //CHILD TABLE DEFINITION THE ATENDIMENTOS
                                Atendimento: {
                                    title: '',
                                    width: '1%',
                                    sorting: false,
                                    edit: false,
                                    create: false,
                                    display: function (devContas) {
                                        //Create an image that will be used to open child table
                                        var $img = $('<img src="../../img/list_metro.png" title="Informações do Atendimento" />');
                                        //Open child table when user clicks the image
                                        $img.click(function (data) {
                                            $('#DevContasTableContainer').jtable('openChildTable',
                                                    $img.closest('tr'),
                                                    {
                                                        title: devContas.record.cd_conta + ' - Conta',
                                                         messages: {    addNewRecord: 'Novo Registro',
                                                                        serverCommunicationError: 'Erro de comunicação com a base de dados',
                                                                        loadingMessage: 'Aguarde...',
                                                                        noDataAvailable: 'Sem registro',
                                                                        editRecord: 'Editar registro',
                                                                        areYouSure: 'Você tem certeza?',
                                                                        deleteConfirmation: 'Em excluir o registro?',
                                                                        save: 'Salvar',
                                                                        saving: 'Salvando',
                                                                        cancel: 'Cancelar',
                                                                        deleteText: 'Deletar',
                                                                        deleting: 'Deletando',
                                                                        error: 'Error',
                                                                        close: 'Fechar',
                                                                        cannotLoadOptionsFor: 'Can not load options for field {0}',
                                                                        pagingInfo: 'Showing {0}-{1} of {2} Jobs',
                                                                        gotoPageLabel: 'Ir para',
                                                                        pageSizeChangeLabel: 'Total Reg',
                                                                        canNotDeletedRecords: 'Can not deleted {0} of {1} records!',
                                                                        deleteProggress: 'Deleted {0} of {1} records, processing...'
                                                        },
                                                        actions: {
                                                            listAction: '../../Controller?action=infoatd&cd_multi_empresa='+devContas.record.cd_multi_empresa+'&cd_conta='+devContas.record.cd_conta+'&cd_tipo_conta='+devContas.record.cd_tipo_conta,
                                                        },
                                                        fields: {
                                                            cd_devolucao_contas: {
                                                                type: 'hidden',
                                                                defaultValue: devContas.record.cd_devolucao_contas
                                                            },
                                                            cd_atendimento: {
                                                                title: 'Atendimento',
                                                                width: '10%'
                                                            },
                                                            cd_conta: {
                                                                title: ' Conta',
                                                                width: '10%'
                                                            },
                                                            nm_paciente: {
                                                                title: 'Paciente',
                                                                width: '20%'
                                                            },
                                                            nm_convenio: {
                                                                title: 'Convênio',
                                                                width: '15%'
                                                            },
                                                            cd_remessa: {
                                                                title: 'Remessa',
                                                                width: '6%'
                                                            },
                                                            dt_entrega_da_fatura : {
                                                                title : 'Entrega da Fatura',
                                                                width : '12%',
                                                                type : 'date',
                                                                displayFormat: 'dd/mm/yy'
                                                            },
                                                            vl_total :{
                                                                title : 'Valor Total',
                                                                width : '6%'
                                                            },
                                                            dt_pagamento : {
                                                                title : 'Pagamento',
                                                                width : '12%',
                                                                type : 'date',
                                                                displayFormat: 'dd/mm/yy'
                                                            },
                                                            vl_recebido :{
                                                                title : 'Recebido',
                                                                width : '6%'
                                                            }
                                                        }
                                                    }, function (data) { //opened handler
                                                        data.childTable.jtable('load');
                                                        
                                                    });
                                        });
                                        //Return image to show on the person row
                                        return $img;
                                    }
                                },
			},/*,
                        toolbar: {
                                    items: [{
                                        icon: '/images/excel.png',
                                        text: 'Export to Excel',
                                        click: function () {
                                            //perform your custom job...
                                        }
                                    },{
                                        icon: '/images/pdf.png',
                                        text: 'Export to Pdf',
                                        click: function () {
                                            //perform your custom job...
                                        }
                                    }]
                                }*/
                            //Initialize validation logic when a form is created
                            formCreated: function (event, data) {
                                //$("#jtable-edit-form").validationEngine('attach');
                                data.form.validationEngine('attach', {promptPosition : "centerRight"});
                                $("#Edit-dt_reapresentacao").blur( function() {data.form.validationEngine.loadValidation('#Edit-dt_reapresentacao');} );
                                $("#Edit-vl_reapresentado").autoNumeric('init', {aSep: '.', aDec: ','});
                                
                                
                                 
                            },
                            //Validate form when it is being submitted
                            formSubmitting: function (event, data) {
                                return data.form.validationEngine('validate');
                            },
                            //Dispose validation logic when form is closed
                            formClosed: function (event, data) {
                                data.form.validationEngine('hide');
                                data.form.validationEngine('detach');
                                $("#Edit-vl_reapresentado").autoNumeric('init'); 
                            }
		});
                $('#DevContasTableContainer').jtable('load',{cd_multi_empresa: $("#empresa").val()});
                
	
    
}
function atualizarValores()
{   
    $('#areafiltro').fadeIn(600);
    //totalizador recurso de glosa
                
            var vlrec;
                $.ajax({ //Not found in cache, get from server
                    url: '../../Controller?action=getContador&tipo=R&cd_multi_empresa='+$("#empresa").val(),
                    type: 'POST',
                    dataType: 'json',
                    async: false,
                    success: function (data) {
                        if (data.Result !== 'OK') {
                            alert(data.Message);
                            return;
                        }
                        vlrec = data.Records;
                        $("#result_rec").html(vlrec);
                    }
                  });
                  
                //totalizador recurso de fat
                var vlrec;
                $.ajax({ //Not found in cache, get from server
                    url: '../../Controller?action=getContador&tipo=F&cd_multi_empresa='+$("#empresa").val(),
                    type: 'POST',
                    dataType: 'json',
                    async: false,
                    success: function (data) {
                        if (data.Result !== 'OK') {
                            alert(data.Message);
                            return;
                        }
                        vlrec = data.Records;
                        $("#result_fat").html(vlrec);
                    }
                  });
}
function chamaIndicadores(emp){
    $('#areafiltro').hide();
    //$('#DevContasTableContainer').html("Indicadores da empresa "+emp);
    $('#DevContasTableContainer').html("<canvas class='main-chart' id='bar-chart' height='200' width='600'></canvas>");
    
    var chart2 = document.getElementById("bar-chart").getContext("2d");

    //window.myBar = new Chart(chart2).Bar(barChartData, {responsive : true});
    
    //Montar Gráfico
    
    $.ajax({ 
        url: '../../Controller?action=getGrafico&tipo=1&cd_multi_empresa='+$("#empresa").val(),
        type: 'POST',
        dataType: 'json',
        async: false,
        success: function (data) {
            if (data.Result !== 'OK') {
                alert(data.Message);
                return;
            }
            window.myBar = new Chart(chart2).Bar(data.Records, {responsive : true});
            
        }
      });
    
   
    
}
function abrirRecGlosa(emp,papel)
{
    if(papel != "180"){
        $("#alert_acesso_rec").fadeIn(600);
        var selectedEffect = 'blind';
        var options = {};
        $("#alert_acesso_rec").hide(selectedEffect, options, 400);
    }else{
        AtualizaTabela(emp);
    }
     
}
function abrirFaturamento(emp,papel)
{
    if(papel != "181"){
        $("#alert_acesso_fat").fadeIn(600);
        var selectedEffect = 'blind';
        var options = {};
        $("#alert_acesso_fat").hide(selectedEffect, options, 400);
    }else{
        AtualizarTabelaFat(emp);
    }
}

function mudarEmpresa(emp)
{
    if($('#papel_rec').val() == "180")
    {
        abrirRecGlosa(emp,$('#papel_rec').val());
    }else if($('#papel_fat').val() == "181"){
        abrirFaturamento(emp,$('#papel_fat').val());
    }
}