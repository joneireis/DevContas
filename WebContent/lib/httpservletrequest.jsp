<%!
    HttpServletRequest httpservletrequest;
    String requisita(String valor) {

        String temp = httpservletrequest.getParameter(valor);
        if (temp == null) {
            temp = "";
        }
        return temp;
    }
%>

<%

httpservletrequest = request;
%>

