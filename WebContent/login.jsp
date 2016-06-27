<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>

<%@include file="./lib/conexao.jsp" %>
<%@include file="./lib/httpservletrequest.jsp" %>

<%  session.removeAttribute("login");
    if (requisita("usuario") != "") {
        try {
            // String sqlacesso = "select cd_usuario_portal,cd_usuario_portal FROM DBACP.USUARIO_PORTAL where cd_usuario_portal = '" + requisita("usuario") + "' and cd_usuario_portal = '" + requisita("senha") + "'";
            String sqlacesso = "SELECT UP.CD_USUARIO_PORTAL, nvl(initcap(substr(up.nm_usuario,1,instr(up.nm_usuario,' '))),initcap(up.nm_usuario)) nm_usuario,"
                    + "  DBAPORTAL.FNC_SE_CRIPTOGRAFIA('" + requisita("usuario") + "', '" + requisita("senha") + "') VALIDACAO,"
                    + "  UP.DS_SENHA"
                    + "  FROM DBACP.USUARIO_PORTAL UP"
                    + "  WHERE UPPER(UP.CD_USUARIO_PORTAL) = upper('" + requisita("usuario") + "')";

            String sqlpapel = "SELECT U.CD_USUARIO_PORTAL, U.CD_PAPEL FROM DBACP.USUARIO_PORTAL_PAPEL U "
                    + "WHERE upper(U.CD_USUARIO_PORTAL) = upper('" + requisita("usuario") + "')"
                    + " AND U.CD_PAPEL IN (178,179)";

      
              String sqlempresa = "SELECT O.CD_MULTI_EMPRESA_MV2000 CD_MULTI_EMPRESA,   O.DS_ORGANIZACAO          DS_MULTI_EMPRESA"
                    + "  FROM DBAPORTAL.ORGANIZACAO  O,"
                    + "       DBAPORTAL.CENTRO_CUSTO C,    "
                    + "   DBACP.USUARIO_PORTAL   UP,"
                    + "       DBAPORTAL.PERFIL_SETOR PS"
                    + " WHERE O.CD_ORGANIZACAO = C.CD_ORGANIZACAO"
                    + "   AND PS.ID_USUARIO_PORTAL = UP.ID_USUARIO_PORTAL"
                    + "   AND PS.CD_CENTRO_CUSTO = C.CD_CENTRO_CUSTO"
                    + "   AND UPPER(UP.CD_USUARIO_PORTAL) =  upper('" + requisita("usuario") + "')"
                    + "   AND UPPER(TRIM(C.DS_CENTRO_CUSTO)) LIKE UPPER('Painel de Indicadores')";


            Statement stmt = conn.createStatement();
            Statement stmt2 = conn.createStatement();
            Statement stmt3 = conn.createStatement();

            ResultSet rs = stmt.executeQuery(sqlacesso);
            ResultSet rs2 = stmt2.executeQuery(sqlpapel);
            ResultSet rs3 = stmt3.executeQuery(sqlempresa);

        

            int Size = 0;
          
            
            ArrayList<String> papeis = new ArrayList<String>();//creating arraylist
            ArrayList<String> empresas = new ArrayList<String>();
            ArrayList<String> nmempresas = new ArrayList<String>();
           
            if (rs.next()) {

                
                if (rs.getString("VALIDACAO").equals(rs.getString("DS_SENHA"))) {

                    while (rs2.next()) {
                        papeis.add(rs2.getString("CD_PAPEL"));
                     Size = rs2.getRow();
                  
                    }

                   while (rs3.next()) {
                       empresas.add(rs3.getString("cd_multi_empresa"));
                       nmempresas.add(rs3.getString("ds_multi_empresa"));
                    }
  
                    if (Size == 0) {

                        papeis.add("0");

                    }
                     
                    session.setAttribute("empresas", empresas);
                    session.setAttribute("nm_empresas", nmempresas);
                    session.setAttribute("papel", papeis);
                    session.setAttribute("login", requisita("usuario"));
                    session.setAttribute("nm_usuario", rs.getString("NM_USUARIO"));
                    
                    
                    
     //  out.println(sqlempresa);

//


%>


<jsp:forward page="index.jsp" />

<%   } else {
                    out.println("login e senha Invalidos");
                }
            }

        } catch (Exception e) {

            System.out.println("Erro" + e);
        }
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Rede Impar - Administração Hospitalar</title>
        <link rel="apple-touch-icon" sizes="60x60" href="/img/apple-icon-60x60.png">
        <link rel="icon" type="image/png" sizes="32x32" href="/img/favicon-32x32.png">
        <link rel="shortcut icon" type="image/png" href="/img/favicon-32x32.png"/>
        <link rel='shortcut icon' type='image/x-icon' href='./favicon.ico' />
        <link rel="manifest" href="/manifest.json">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/datepicker3.css" rel="stylesheet">
        <link href="css/styles.css" rel="stylesheet">
        

        <!--[if lt IE 9]>
        <script src="js/html5shiv.js"></script>
        <script src="js/respond.min.js"></script>
        <![endif]-->

    </head>
    <body>
	    <div style="text-align:left;margin-top:-50px;color:#30A5FF; font-family:Verdana; font-size:18px; text-decoration:underline;"><img src="img/rede_impar.gif" width="10%" height="10%" alt=""/><span> - Bi Web</span></div>
        <div class="row">
            
            <div class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">Log in</div>
                    <div class="panel-body">
                        <form name="form_login" method="post" action="" >

                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control"  id ="usuario" placeholder="Login do Painel de Indicadores" name="usuario" type="text" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control"  id ="senha" placeholder="Senha" name="senha" type="password" value="">
                                </div>
                              <div class="checkbox" style="display:none;">
                                    <label>
                                        <input name="remember" type="checkbox" value="Remember Me">Lembrar senha
                                    </label>
                                </div>
                                <input type="submit" class="btn btn-primary" name="Login" id="Login" value="Entrar">
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div><!-- /.col-->
        </div><!-- /.row -->


    </body>

</html>
