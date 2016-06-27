<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Rede Impar - Administração Hospitalar</title>
<!-- Include one of jTable styles. -->
<link href="css/lightcolor/blue/jtable.css" rel="stylesheet" type="text/css" />
<link href="css/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />



<link href="css/bootstrap.min.css" rel="stylesheet">
<!--<link href="css/datepicker3.css" rel="stylesheet">-->
<link href="css/bootstrap-table.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">

<!--Icons-->
<script src="js/lumino.glyphs.js"></script>

<!--[if lt IE 9]>
<script src="js/html5shiv.js"></script>
<script src="js/respond.min.js"></script>
<![endif]-->



</head>

<body>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebar-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"><span>REDE</span>IMPAR</a>
				<ul class="user-menu">
					<li class="dropdown pull-right">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                                    <svg class="glyph stroked male-user"><use xlink:href="#stroked-male-user"></use></svg> 
						  <%  
                                                        if (session.getAttribute("login" ) == null)
                                                        {
                                                    %> Usuário
                                                    <% }else{
                                                          %> Bem-vindo, <%=session.getAttribute("nm_usuario")%> !
                                                    <% } %>
                                                    <span class="caret"></span></a>
						<ul class="dropdown-menu" role="menu">
                                                     <%  
                                                        if (session.getAttribute("login" ) == null)
                                                        {
                                                    %>
                                                        <li><a onclick="$(window).attr('location','login.jsp')" href="#"><svg class="glyph stroked male-user"><use xlink:href="#stroked-male-user"></use></svg> Login</a></li>
						    <%  } %>
                                                        <li style="display:none;"><a href="#"><svg class="glyph stroked gear"><use xlink:href="#stroked-gear"></use></svg> Settings</a></li>
                                                        <%  
                                                        if (session.getAttribute("login" ) != null)
                                                        {
                                                        %>
                                                        <li><a onclick="$(window).attr('location','login.jsp')" href="#"><svg class="glyph stroked cancel"><use xlink:href="#stroked-cancel"></use></svg> Logout</a></li>
                                                        <% } %>
						</ul>
					</li>
				</ul>
			</div>
							
		</div><!-- /.container-fluid -->
	</nav>
                                                
                                                
		
	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar" >
            
            
		<form role="search">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Search">
			</div>
		</form>
		<ul class="nav menu">
			<li class="active"><a href="index.html"><svg class="glyph stroked dashboard-dial"><use xlink:href="#stroked-dashboard-dial"></use></svg> Dashboard</a></li>
			<li><a href="./sistemas/devcontas/index.jsp"><svg class="glyph stroked calendar"><use xlink:href="#stroked-calendar"></use></svg> Recurso de Glosa</a></li>
			<li><a href="charts.html"><svg class="glyph stroked line-graph"><use xlink:href="#stroked-line-graph"></use></svg> Charts</a></li>
			<li><a href="tables.html"><svg class="glyph stroked table"><use xlink:href="#stroked-table"></use></svg> Tables</a></li>
			<li><a href="forms.html"><svg class="glyph stroked pencil"><use xlink:href="#stroked-pencil"></use></svg> Forms</a></li>
			<li><a href="panels.html"><svg class="glyph stroked app-window"><use xlink:href="#stroked-app-window"></use></svg> Alerts &amp; Panels</a></li>
			<li><a href="icons.html"><svg class="glyph stroked star"><use xlink:href="#stroked-star"></use></svg> Icons</a></li>
			<li class="parent ">
				<a href="#">
					<span data-toggle="collapse" href="#sub-item-1"><svg class="glyph stroked chevron-down"><use xlink:href="#stroked-chevron-down"></use></svg></span> Dropdown 
				</a>
				<ul class="children collapse" id="sub-item-1">
					<li>
						<a class="" href="#">
							<svg class="glyph stroked chevron-right"><use xlink:href="#stroked-chevron-right"></use></svg> Sub Item 1
						</a>
					</li>
					<li>
						<a class="" href="#">
							<svg class="glyph stroked chevron-right"><use xlink:href="#stroked-chevron-right"></use></svg> Sub Item 2
						</a>
					</li>
					<li>
						<a class="" href="#">
							<svg class="glyph stroked chevron-right"><use xlink:href="#stroked-chevron-right"></use></svg> Sub Item 3
						</a>
					</li>
				</ul>
			</li>
			<li role="presentation" class="divider"></li>
			<li><a href="login.html"><svg class="glyph stroked male-user"><use xlink:href="#stroked-male-user"></use></svg> Login Page</a></li>
		</ul>

	</div><!--/.sidebar-->
		
	<div class="col-sm-9 col-sm-offset-3 col-lg-12 col-lg-offset-0 main">			
		<div class="row">
			<ol class="breadcrumb">
				<li><a href="#"><svg class="glyph stroked home"><use xlink:href="#stroked-home"></use></svg></a></li>
				<li class="active">Home</li>
			</ol>
		</div><!--/.row-->
                <%  
                    if (session.getAttribute("login" ) != null)
                    {
                        ArrayList<String> Empresas = (ArrayList<String>) session.getAttribute("empresas");
                        ArrayList<String> NMEmpresas = (ArrayList<String>) session.getAttribute("nm_empresas");
                %>
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Home Principal</h1>
			</div>
		</div><!--/.row-->
		
		
		
		
                <%
                }else{
                    %>
                        <div class="col-md-3 col-md-push-5">
				<div class="panel panel-warning">
					<div class="panel-heading">
						Acesso Restrito
					</div>
					<div class="panel-body">
						<p>Sua sessão expirou, favor logar novamente!</p>
					</div>
				</div>
			</div>
                    <%
                }
    
                %>
	</div>	<!--/.main-->
        <script src="js/index.js"></script>
	<script src="js/jquery-1.11.1.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<!--<script src="js/chart.min.js"></script>
	<script src="js/chart-data.js"></script>
	<script src="js/easypiechart.js"></script>
	<script src="js/easypiechart-data.js"></script>
	<script src="js/bootstrap-datepicker.js"></script>-->
        <script src="js/bootstrap-table.js"></script>
        <!-- Include jTable script file. -->
        <script src="js/jquery-1.8.2.js" type="text/javascript"></script>
        <script src="js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
        <script src="js/jquery.jtable.js" type="text/javascript"></script>
	<script>

                !function ($) {
		    $(document).on("click","ul.nav li.parent > a > span.icon", function(){          
		        $(this).find('em:first').toggleClass("glyphicon-minus");      
		    }); 
		    $(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
		}(window.jQuery);

		$(window).on('resize', function () {
		  if ($(window).width() > 768) $('#sidebar-collapse').collapse('show');
		});
		$(window).on('resize', function () {
		  if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide');
		});
	</script>	
      
        
        <script type="text/javascript">
        var cachedMotDevOptions = null;
        var options = [];
        $.ajax({ //Not found in cache, get from server
          url: 'Controller?action=getMotivos',
          type: 'POST',
          dataType: 'json',
          async: false,
          success: function (data) {
              if (data.Result !== 'OK') {
                  alert(data.Message);
                  return;
              }
              options = data.Records;
          }
        });
        cachedMotDevOptions = options;

        
	$(document).ready(function() {
		$('#DevContasTableContainer').jtable({
			title : 'Contas Reapresentadas',
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
                                      pagingInfo: 'Showing {0}-{1} of {2} Jobs',
                                      pageSizeChangeLabel: 'Row count',
                                      gotoPageLabel: 'Go to page',
                                      canNotDeletedRecords: 'Can not deleted {0} of {1} records!',
                                      deleteProggress: 'Deleted {0} of {1} records, processing...'
                                  },
			actions : {
                            listAction : 'Controller?action=list',
				createAction : 'Controller?action=create',
				updateAction : 'Controller?action=update',
				deleteAction : 'Controller?action=delete'
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
					edit : true
				},
                                cd_tipo_conta: {
                                        title: 'Tipo Conta',
                                        width: '6%',
                                        options:  {"I":"Conta Hospitalar","A":"Conta Ambulatorial"}
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
                                        type : 'Date',
					edit : true
				},
				ds_obs : {
					title : 'Observação',
					width : '30%',
					edit : true
				}
			}/*,
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
		});
                $('#DevContasTableContainer').jtable('load',{cd_multi_empresa2: $("#empresa").val()});
	});
</script>
</body>

</html>
