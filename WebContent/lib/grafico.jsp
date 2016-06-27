<%@page import="java.lang.reflect.Field"%>
<%@page import="java.lang.reflect.Field"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.reflect.Array"%>
<%@page import="oracle.net.aso.i"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="httpservletrequest.jsp" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%  //session.getAttribute("papel")   
    String acesso = "N";
    String empresa = "";
    ArrayList<String> Papeis = (ArrayList<String>) session.getAttribute("papel");
    ArrayList<String> Empresas = (ArrayList<String>) session.getAttribute("empresas");

    for (int index = 0; index < Papeis.size(); index++) {

        if (Papeis.get(index).equals("178")) {
            acesso = "S";

        }
    }
%>

<% if (acesso == "S") {
%>


<% if (requisita("cd_multi_empresa") != "" ) {  
empresa = requisita("cd_multi_empresa");
}else
{
empresa = Empresas.get(0);
}
%>


<%  Class.forName("oracle.jdbc.driver.OracleDriver");
    // Use the following 2 files whening running inside Oracle 8i
    // Connection conn = new oracle.jdbc.driver.OracleDriver().
    //                     defaultConnection ();
    Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@//192.168.96.93:1521/orcl", "dbaimpar", "dbamvimpar");
    out.println("Conectou com sucesso");
    Statement stmt2 = conn.createStatement();

    ResultSet rs = stmt2.executeQuery(" SELECT DISTINCT (COMP) COMP, TRUNC(SUM(TOTAL), 2) TOTAL  FROM (  SELECT TO_CHAR(V.DT_ENTREGA_DA_FATURA, 'MM/YYYY') COMP, SUM(V.VL_TOTAL_CONTA) TOTAL "
            + "FROM DBAIMPAR.DW_REMESSAS_ENVIAR_EMPRESA V  WHERE V.DT_ENTREGA_DA_FATURA > TO_DATE('01/01/2016', 'dd/mm/yyyy')"
            + " AND V.CD_MULTI_EMPRESA = " + empresa
            + " GROUP BY V.DT_ENTREGA_DA_FATURA) GROUP BY COMP ORDER BY TO_DATE(COMP, 'mm/yyyy')");

%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1" name="viewport">
        <title>Chart.js - criando gráficos com a biblioteca Chart.js</title>
        <script src="Chart.min.js"></script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
        <style type="text/css">

            *{
                font-family: calibri;        
            }

            .box {
                margin: 0px auto;
                width: 70%;
            }

            .box-chart {
                width: 100%;
                margin: 0 auto;
                padding: 10px;
            }

        </style>  
        <script type="text/javascript">
            function atualiza(n) {
                var num = (parseInt(n) + 1);

                $('#meuReload').html(num);
            }

            $(document).ready(function () {
                setInterval(function () {
                    atualiza($('#meuReload').text());
                }, 1000);

            })
        </script>
    </head>

    <div id="meuReload" style="border: solid 1px">
        <script type="text/javascript">

            var myvalues = new Array();

            <%while (rs.next()) {%>
            myvalues.push(<%=rs.getString("total")%>);
            <%}%>

        </script>       

    </div>
</head>
<body>    

    <div class="box">
        <div class="box-chart">
            <canvas id="GraficoBarra" style="width:100%;"></canvas>
            <script type="text/javascript">

                var options = {
                    responsive: true
                };

                var data = {
                    labels: ["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"],
                    datasets: [
                        {
                            label: "Dados secundários",
                            fillColor: "rgba(151,187,205,0.5)",
                            strokeColor: "rgba(151,187,205,0.8)",
                            highlightFill: "rgba(151,187,205,0.75)",
                            highlightStroke: "rgba(151,187,205,1)",
                            data: myvalues
                        }
                    ]
                };

                window.onload = function () {
                    var ctx = document.getElementById("GraficoBarra").getContext("2d");
                    var BarChart = new Chart(ctx).Bar(data, options);
                }
            </script>
        </div>

    </div>




    <form name="form" method="post" action="grafico.jsp" id="form" >

        <select name="cd_multi_empresa" onchange="$('#form').submit();">

            <option value="" selected>Selecione a Empresa</option>
            <%
                for (int index2 = 0; index2 < Empresas.size(); index2++) {
            %>
            <option value="<%=Empresas.get(index2)%>"> <%=Empresas.get(index2)%></option>
            
            <%}

            %>
        </select>
    </form> 

</body>

</html>


<%} else {
        out.println("Sem acesso. Entre em contato com a TI. ");
    }%>