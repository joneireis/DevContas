<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Rede Impar - Administração Hospitalar</title>
<!-- Include one of jTable styles. -->
<link href="../../css/lightcolor/blue/jtable.css" rel="stylesheet" type="text/css" />
<link href="../../css/jquery-ui-1.10.3.custom.css" rel="stylesheet" type="text/css" />
<link href="../../css/validationEngine.jquery.css" rel="stylesheet" type="text/css"/>

<% /* ####Papeis de Acessos####
    181	BIWEB - DevContas - Faturamento
    180	BIWEB - DevContas - Recurso de Glosa
    44	Super Usuário
    */
%>
<link href="../../css/bootstrap.min.css" rel="stylesheet">
<link href="../../css/datepicker3.css" rel="stylesheet">
<link href="../../css/bootstrap-table.css" rel="stylesheet">
<link href="../../css/styles.css" rel="stylesheet">

<!--Icons-->
<script src="../../js/lumino.glyphs.js"></script>
<script src="../../js/jquery.mobile-1.4.5.min.js"></script>
<!--[if lt IE 9]>
<script src="../../js/html5shiv.js"></script>
<script src="../../js/respond.min.js"></script>
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
				<a class="navbar-brand" href="#"><span>DEV</span>CONTAS</a>
				<ul class="user-menu">
					<li class="dropdown pull-right">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown"><svg class="glyph stroked male-user"><use xlink:href="#stroked-male-user"></use></svg> 
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
		
	<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar" style="display:none" >
		
                <ul class="nav menu">
			
			<li class="parent ">
				<a href="#">
					<span data-toggle="collapse" href="#sub-item-1"><svg class="glyph landed browser-app"><use xlink:href="#stroked-chevron-down"></use></svg></span> Serviços 
				</a>
				<ul class="children collapse in" id="sub-item-1">
					<li><a href="index.jsp?tp_sistema=recglosa"><svg class="glyph stroked calendar"><use xlink:href="#stroked-calendar"></use></svg> Recurso de Glosa</a></li>
                                        <li><a href="index.jsp?tp_sistema=faturamento"><svg class="glyph stroked table"><use xlink:href="#stroked-table"></use></svg> Faturamento</a></li>
				</ul>
                            
			</li>
                        
			<li role="presentation" class="divider"></li>
			<li><a href="index.jsp"><svg class="glyph stroked male-user"><use xlink:href="#stroked-male-user"></use></svg> Login</a></li>
		</ul>

	</div><!--/.sidebar-->
		
	<div class="col-sm-9 col-sm-offset-3 col-lg-12 col-lg-offset-0 main">			
		<div class="row">
			<ol class="breadcrumb">
                            <li><a href="#"><svg id="homemenu" class="glyph stroked home"><use xlink:href="#stroked-home"></use></svg></a></li>
				<li class="active">Home</li>
			</ol>
		</div><!--/.row-->
                <%  
                    if (session.getAttribute("login" ) != null)
                    {
                        ArrayList<String> Empresas = (ArrayList<String>) session.getAttribute("empresas");
                        ArrayList<String> NMEmpresas = (ArrayList<String>) session.getAttribute("nm_empresas");
                        ArrayList<String> Papeis = (ArrayList<String>) session.getAttribute("papel");
                 %>      
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">Devolução de Contas / Representação</h1>
			</div>
		</div><!--/.row-->
		
		<div class="row">
                    <div id="divfaturamento" class="col-xs-12 col-md-6 col-lg-3">
                            <div class="panel panel-blue panel-widget " style="cursor:pointer;" onclick="abrirRecGlosa($('#empresa').val(),$('#papel_rec').val());">
					<div class="row no-padding">
						<div class="col-sm-3 col-lg-5 widget-left">
							<svg class="glyph stroked clipboard with paper"><use xlink:href="#stroked-clipboard-with-paper"/></svg>
						</div>
						<div class="col-sm-9 col-lg-7 widget-right">
							<div id="result_rec" class="large">0</div>
							<div class="text-muted">Recurso de Glosa</div>
                                                        <% 
                                                            for (int index2 = 0; index2 < Papeis.size(); index2++) {
                                                                if(Papeis.get(index2).equals("180")){
                                                                    %> <input type="hidden" name="papel_rec" id="papel_rec" value="<%= Papeis.get(index2)%>" > <%
                                                                        
                                                                }  
                                                            }
                                                            %>
                                                        <div class="text-muted" style="font-size:10px;">(Recebimento da devolução)</div>    
						</div>
                                                
					</div>
                                        <div id="alert_acesso_rec" class="alert bg-danger" role="alert" style="display:none;">
                                                            <svg class="glyph stroked cancel"><use xlink:href="#stroked-cancel"/></svg>Acesso Negado!
                                        </div>  
                            </div>
			</div>
                        <div class="col-xs-12 col-md-6 col-lg-3" id="panel2">
                                    <div class="panel panel-orange panel-widget" style="cursor:pointer;" onclick="abrirFaturamento($('#empresa').val(),$('#papel_fat').val());">
                                            <div class="row no-padding">
                                                    <div class="col-sm-3 col-lg-5 widget-left">
                                                            <svg class="glyph stroked calendar"><use xlink:href="#stroked-calendar"/></svg>
                                                    </div>
                                                    <div class="col-sm-9 col-lg-7 widget-right">
                                                            <div id="result_fat" class="large">0</div>
                                                            <div class="text-muted">Faturamento</div>
                                                            <% 
                                                            for (int index2 = 0; index2 < Papeis.size(); index2++) {
                                                                if(Papeis.get(index2).equals("181")){
                                                                    %> <input type="hidden" name="papel_fat" id="papel_fat" value="<%= Papeis.get(index2)%>" > <%
                                                                }  
                                                            }
                                                            %>
                                                            <div class="text-muted" style="font-size:10px;">(Reapresentação da devolução)</div>
                                                    </div>
                                                    
                                            </div>
                                            <div id="alert_acesso_fat" class="alert bg-danger" role="alert" style="display:none;">
                                                            <svg class="glyph stroked cancel"><use xlink:href="#stroked-cancel"/></svg>Acesso Negado!
                                            </div>                
                                    </div>
                        </div>
			<div class="col-xs-12 col-md-6 col-lg-3" id="panel3">
                            <div class="panel panel-yellow panel-widget" style="cursor:pointer;" onclick="chamaIndicadores($('#empresa').val());">
                                            <div class="row no-padding">
                                                    <div class="col-sm-3 col-lg-5 widget-left">
                                                            <svg class="glyph stroked dashboard dial"><use xlink:href="#stroked-dashboard-dial"/></svg>
                                                    </div>
                                                    <div class="col-sm-9 col-lg-7 widget-right">
                                                            <div id="result_fat" class="large"></div>
                                                            <div class="text-muted">Indicadores</div>
                                                    </div>
                                            </div>
                                    </div>
                        </div>
		</div><!--/.row-->
		
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
                                    <div id="titulo_pagina" class="panel-heading">Recurso de Glosa</div>
					<div class="panel-body">
                                            
                                            
                                            <div class="pull-right">
                                                
                                                <select class="text-primary" name="cd_multi_empresa2" id="cd_multi_empresa2" onchange="mudarEmpresa($('#cd_multi_empresa2').val());">
                                                       
                                                        <% 
                                                            String empresa = null;
                                                            for (int index2 = 0; index2 < Empresas.size(); index2++) {
                                                                if(index2 == 0){
                                                                    empresa = Empresas.get(index2);
                                                                }  
                                                        %>
                                                        <option value="<%=Empresas.get(index2)%>"> <%=NMEmpresas.get(index2)%></option>

                                                        
                                                        <%  
                                                             
                                                            }
                                                        %>
                                                </select> 
                                                    
                                                        <input class="tex" type="hidden" name="empresa" id="empresa" value="<%= empresa%>" >
                                                <input type="hidden" name="login" id="login" value="<%= session.getAttribute("login" )%>" >
                                            </div>
                                            
					</div>
                                            
                                            <div class="ui-field-contain" id="areafiltro">
                                                <table class="table"> 
                                                    <tr>
                                                        <td><input class="form-control" data-type="search" id ="pesquisa" placeholder="Pesquisar" type="search" ng-model="search"></td>
                                                        <td><input type="radio" name="optionsRadios" id="conta" value="conta" checked>Conta</td>
                                                        <td><input type="radio" name="optionsRadios" id="remessa" value="remessa">Remessa</td>
                                                        <td><input type="radio" name="optionsRadios" id="convenio" value="convenio">Convenio</td>
                                                        <td><input type="radio" name="optionsRadios" id="dt_entrega" value="dt_entrega">Periodo de Entrega</td>
                                                        <td><button id="bttpesquisar" type="submit" class="btn btn-primary">Pesquisar</button>
                                                            <!--<button type="reset" class="btn btn-default">Limpar</button></td>-->
                                                    </tr>
                                                </table>
                                            </div>
                                       
                                            
                                        <div id="DevContasTableContainer"> 
                                          
                                        </div>
                                            
                                            
				</div>
			</div>
		</div><!--/.row-->	
                <%
                }else{
                    %>
                        <div class="col-md-3 col-md-push-4">
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
        <script src="../../js/index.js" charset="UTF-8"></script>
	<script src="../../js/jquery-1.11.1.min.js"></script>
	<script src="../../js/bootstrap.min.js"></script>
	<script src="../../js/chart.min.js"></script>
	<script src="../../js/chart-data.js"></script>
	<script src="../../js/easypiechart.js"></script>
	<!--<script src="../../js/easypiechart-data.js"></script>-->
	<script src="../../js/bootstrap-datepicker.js"></script>
        <script src="../../js/bootstrap-table.js"></script>
        
        <!-- Include jTable script file. -->
        <script src="../../js/jquery-1.8.2.js" type="text/javascript"></script>
        <script src="../../js/jquery-ui-1.10.3.custom.js" type="text/javascript"></script>
        <script src="../../js/jquery.jtable.js" type="text/javascript"></script>
        <script src="../../js/jquery.jtable.pt-BR.js" type="text/javascript"></script>
        <script src="../../js/jquery.jtable.toolbarsearch" type="text/javascript"></script>
        <script src="../../js/autoNumeric.js" type=text/javascript> </script>
        <script src="../../js/jquery.validationEngine.js" type="text/javascript"></script>
        <script src="../../js/jquery.validationEngine-en.js" charset="UTF-8" ></script>
	<script>
          window.history.pushState( "./", "Rede Impar - AdministraÃ§Ã£o Hospitalar", "./" );
          
                $("#bttpesquisar").click( function()
                   {
                       $('#DevContasTableContainer').jtable('load',{cd_multi_empresa: $('#empresa').val(), pesquisa: $('#pesquisa').val(),tp_filtro: $('input[name=optionsRadios]:checked').val()});
                   }
                );
            
            
            jQuery(function ($) {
                    $.datepicker.regional['pt'] = {
                        closeText: 'Fechar',
                        prevText: '<Anterior',
                        nextText: 'Seguinte',
                        currentText: 'Hoje',
                        monthNames: ['Janeiro', 'Fevereiro', 'Mar&ccedil;o', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
                        monthNamesShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
                        dayNames: ['Domingo', 'Segunda-feira', 'Ter&ccedil;a-feira', 'Quarta-feira', 'Quinta-feira', 'Sexta-feira', 'S&aacute;bado'],
                        dayNamesShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'S&aacute;b'],
                        dayNamesMin: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'S&aacute;b'],
                        weekHeader: 'Sem',
                        dateFormat: 'dd/mm/yy',
                        firstDay: 0,
                        isRTL: false,
                        showMonthAfterYear: false,
                        yearSuffix: ''
                    };
                    $.datepicker.setDefaults($.datepicker.regional['pt']);
                });
                $('.datepicker').datepicker({
                    format: 'dd/mm/yy',                
                    language: 'pt-BR'
                });
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
          url: '../../Controller?action=getMotivos',
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
                
                $(function(){
                    $("#homemenu").mouseover(function(){
                        $("#sidebar-collapse").fadeIn(500);
                    });
                });
                $(function(){
                    $("#panel2").mouseout(function(){
                        $("#sidebar-collapse").fadeOut(500);
                    });
                    
                });
                 $(function(){
                    $("#DevContasTableContainer").mouseout(function(){
                        $("#sidebar-collapse").fadeOut(500);
                    });
                    
                });

                
                if($("#empresa").val()!= null)
                {    
                    atualizarValores();
                    mudarEmpresa($("#empresa").val());
                }
	});
</script>

</body>

</html>
