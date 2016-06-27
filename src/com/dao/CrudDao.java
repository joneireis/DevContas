package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.jdbc.DBUtility;
import com.model.DevContas;
import com.model.Grafico;
import com.model.GraficoDatasets;
import com.model.InfoAtdDevContas;
import com.model.RetornaPesquisa;
import java.sql.Array;
import java.text.ParseException;



public class CrudDao {

private Connection dbConnection;
private PreparedStatement pStmt;

public CrudDao() {
	dbConnection = DBUtility.getConnection();
}

public void addDevContas(DevContas devContas) {
	
        String sql_codigo = "SELECT DBAIMPAR.SEQ_DEVOLUCAO_CONTAS.NEXTVAL CODIGO FROM DUAL";
        String insertQuery = "INSERT INTO dbaimpar.tb_devolucao_contas(CD_DEVOLUCAO_CONTAS, CD_MULTI_EMPRESA, " +
			"CD_TIPO_CONTA, CD_CONTA, CD_MOTIVO_DEV, CD_USER_INSERT, DT_USER_INSERT, DT_DEVOLUCAO, DS_OBS, TP_STATUS) VALUES (?,?,?,?,?,?,sysdate,to_date(?,'dd/mm/rrrr'),?, 'A')";
	try {
                Statement stmt = dbConnection.createStatement();
		ResultSet rs = stmt.executeQuery(sql_codigo);
		rs.next();
                devContas.setCd_devolucao_contas(rs.getInt("CODIGO"));
                
		pStmt = dbConnection.prepareStatement(insertQuery);
		pStmt.setInt(1, devContas.getCd_devolucao_contas());
                pStmt.setInt(2, devContas.getCd_multi_empresa());
		pStmt.setString(3, devContas.getCd_tipo_conta());
		pStmt.setInt(4, devContas.getCd_conta());
                pStmt.setInt(5, devContas.getCd_motivo_dev());
                pStmt.setString(6, devContas.getCd_user_insert());
                pStmt.setString(7, devContas.getDt_devolucao());
                pStmt.setString(8, devContas.getDs_obs());
                
		pStmt.executeUpdate();
                
                String sql_dtdev2 = "SELECT DT_DEVOLUCAO from dbaimpar.tb_devolucao_contas where cd_devolucao_contas = "+rs.getInt("CODIGO");
                Statement stmt2 = dbConnection.createStatement();
		ResultSet rs2 = stmt2.executeQuery(sql_dtdev2);
		rs2.next();
                devContas.setDt_devolucao(rs2.getDate("DT_DEVOLUCAO").toString());
                
                
	} catch (SQLException e) {
		System.err.println(e.getMessage());
	}
}
public void deleteDevContas(int cd_devolucao_contas, String login) {
	String deleteQuery = "update dbaimpar.tb_devolucao_contas set tp_status = 'I', cd_user_inativo = ?, dt_inativo=sysdate WHERE cd_devolucao_contas = ?";
	try {
		pStmt = dbConnection.prepareStatement(deleteQuery);
		pStmt.setString(1, login);
                pStmt.setInt(2, cd_devolucao_contas);
		pStmt.executeUpdate();
	} catch (SQLException e) {
		System.err.println(e.getMessage());
	}
}
public void updateDevContas(DevContas devContas) throws ParseException {
	  String updateQuery = "UPDATE dbaimpar.tb_devolucao_contas SET CD_MULTI_EMPRESA = ?,  CD_CONTA = ?, CD_TIPO_CONTA = ?, DT_DEVOLUCAO = to_date(? ,'dd/mm/yyyy'), cd_motivo_dev = ?, ds_obs = ?, cd_user_rg_alter=?, dt_user_rg_alter=sysdate WHERE cd_devolucao_contas = ?";
	try {
                pStmt = dbConnection.prepareStatement(updateQuery);		
		pStmt.setInt(1, devContas.getCd_multi_empresa());
		pStmt.setInt(2, devContas.getCd_conta());
		pStmt.setString(3, devContas.getCd_tipo_conta());
                pStmt.setString(4, devContas.getDt_devolucao());
                pStmt.setInt(5, devContas.getCd_motivo_dev());
                pStmt.setString(6, devContas.getDs_obs());
                pStmt.setString(7, devContas.getCd_user_rg_alter());
                pStmt.setInt(8, devContas.getCd_devolucao_contas());
                pStmt.executeUpdate();
                
                String sql_dtdev = "SELECT DT_DEVOLUCAO from dbaimpar.tb_devolucao_contas where cd_devolucao_contas = "+devContas.getCd_devolucao_contas();
                Statement stmt = dbConnection.createStatement();
		ResultSet rs = stmt.executeQuery(sql_dtdev);
		rs.next();
                devContas.setDt_devolucao(rs.getDate("DT_DEVOLUCAO").toString());
                
                
                
	} catch (SQLException e) {
		System.err.println(e.getMessage());
	}
}
public void updateDevContasFat(DevContas devContas) throws SQLException {
	  String updateQuery = "";
          String getInsOrUpdate = "select CD_USER_REAPRESENTADO from dbaimpar.tb_devolucao_contas where cd_devolucao_contas = "+devContas.getCd_devolucao_contas();
          Statement stmt1 = dbConnection.createStatement();
          ResultSet rs1 = stmt1.executeQuery(getInsOrUpdate);
          rs1.next();
          if (rs1.getString("CD_USER_REAPRESENTADO") == null){
            updateQuery = "UPDATE dbaimpar.tb_devolucao_contas SET CD_CONTA_NOVA = ?, DT_REAPRESENTACAO = to_date(? ,'dd/mm/yyyy'), vl_reapresentado = to_number(?, '99G999G999G999D99'), DS_OBS_FAT = ?, CD_USER_REAPRESENTADO = ?, DT_USER_REAPRESENTADO = sysdate WHERE cd_devolucao_contas = ?";
          }else{
            updateQuery = "UPDATE dbaimpar.tb_devolucao_contas SET CD_CONTA_NOVA = ?, DT_REAPRESENTACAO = to_date(? ,'dd/mm/yyyy'), vl_reapresentado = to_number(?, '99G999G999G999D99'), DS_OBS_FAT = ?, CD_USER_REAPRE_ALT = ?, DT_USER_REAPRE_ALT = sysdate WHERE cd_devolucao_contas = ?";
          }
	try {
                pStmt = dbConnection.prepareStatement(updateQuery);		
		pStmt.setInt(1, devContas.getCd_conta_nova());
		pStmt.setString(2, devContas.getDt_reapresentacao());
		pStmt.setString(3, devContas.getVl_reapresentado());
                pStmt.setString(4, devContas.getDs_obs_fat());
                pStmt.setString(5, devContas.getCd_user_insert());
                pStmt.setInt(6, devContas.getCd_devolucao_contas());
                pStmt.executeUpdate();
                
                String sql_dtdev = "SELECT DT_REAPRESENTACAO, CD_CONTA, CD_MOTIVO_DEV from dbaimpar.tb_devolucao_contas where cd_devolucao_contas = "+devContas.getCd_devolucao_contas();
                Statement stmt = dbConnection.createStatement();
		ResultSet rs = stmt.executeQuery(sql_dtdev);
		rs.next();
                devContas.setDt_reapresentacao(rs.getDate("DT_REAPRESENTACAO").toString());
                devContas.setCd_conta(rs.getInt("CD_CONTA"));
                devContas.setCd_motivo_dev(rs.getInt("CD_MOTIVO_DEV"));
                
                
                
	} catch (SQLException e) {
		System.err.println(e.getMessage());
	}
}
public List<DevContas> getAllDevContas(int cd_multi_empresa, String pesquisa, String tp_filtro, String jtStartIndex, String jtPageSize) throws ParseException {
    
	List<DevContas> devContass = new ArrayList<DevContas>();
        String query = "";
        String convenio = "convenio";
        String conta = "conta";
        String remessa = "remessa";
        String dt_entrega = "dt_entrega";
        if(pesquisa != null  && tp_filtro != null){
            if(!pesquisa.equals(query)){
                    if(tp_filtro.equals(conta) || tp_filtro == conta){
                        query = "SELECT * FROM (SELECT CD_DEVOLUCAO_CONTAS," +
                                "       CD_MULTI_EMPRESA," +
                                "       CD_CONTA," +
                                "       CD_TIPO_CONTA," +
                                "       CD_MOTIVO_DEV," +
                                "       DT_DEVOLUCAO," +
                                "       DS_OBS, CD_CONTA_NOVA, DT_REAPRESENTACAO, replace(to_char(vl_reapresentado, '99G999G999G999D99'),' ','')vl_reapresentado, DS_OBS_FAT, row_number() OVER (ORDER BY 3) linha, 1 total " +
                                "  FROM DBAIMPAR.TB_DEVOLUCAO_CONTAS" +
                                " WHERE CD_MULTI_EMPRESA = "+ cd_multi_empresa +
                                "   AND TP_STATUS = 'A'" +
                                "   AND CD_CONTA = " + pesquisa +
                                " ORDER BY 3) WHERE LINHA >= "+jtStartIndex+" AND LINHA <="+(jtStartIndex + jtPageSize);
                    }
                    if(tp_filtro.equals(remessa) || tp_filtro == remessa){
                        
                        query = "SELECT * FROM (SELECT CD_DEVOLUCAO_CONTAS, " +
                                "                       CD_MULTI_EMPRESA, " +
                                "                       CD_CONTA, " +
                                "                       CD_TIPO_CONTA, " +
                                "                       CD_MOTIVO_DEV, " +
                                "                       DT_DEVOLUCAO, " +
                                "                       DS_OBS, CD_CONTA_NOVA, DT_REAPRESENTACAO, replace(to_char(vl_reapresentado, '99G999G999G999D99'),' ','')vl_reapresentado, DS_OBS_FAT, row_number() OVER (ORDER BY 3) linha, " +
                                "                       (select count(*)total  FROM DBAIMPAR.TB_DEVOLUCAO_CONTAS " +
                                "                                              WHERE CD_MULTI_EMPRESA = " + cd_multi_empresa +
                                "                                               AND TP_STATUS = 'A' " +
                                "                                               AND CD_CONTA IN (SELECT dc.cd_conta_ffcv from dbaimpar.dw_v_contas_ffcv dc where dc.cd_remessa = "+pesquisa+" and dc.cd_multi_empresa = "+cd_multi_empresa+") " +
                                "                       )total" +
                                "                  FROM DBAIMPAR.TB_DEVOLUCAO_CONTAS " +
                                "                 WHERE CD_MULTI_EMPRESA = " + cd_multi_empresa +
                                "                   AND TP_STATUS = 'A' " +
                                "                   AND CD_CONTA IN (SELECT dc.cd_conta_ffcv from dbaimpar.dw_v_contas_ffcv dc where dc.cd_remessa = "+pesquisa+" and dc.cd_multi_empresa = "+cd_multi_empresa+") " +
                                "                 ORDER BY 3) WHERE LINHA >= "+jtStartIndex+" AND LINHA <="+(jtStartIndex + jtPageSize);
                    }
                    if(tp_filtro.equals(convenio) || tp_filtro == convenio){
                        
                        query = "SELECT * FROM (SELECT CD_DEVOLUCAO_CONTAS," +
                                "       CD_MULTI_EMPRESA," +
                                "       CD_CONTA," +
                                "       CD_TIPO_CONTA," +
                                "       CD_MOTIVO_DEV," +
                                "       DT_DEVOLUCAO," +
                                "       DS_OBS, CD_CONTA_NOVA, DT_REAPRESENTACAO, replace(to_char(vl_reapresentado, '99G999G999G999D99'),' ','')vl_reapresentado, DS_OBS_FAT, row_number() OVER (ORDER BY 3) linha," +
                                "       (select count(*)total  FROM DBAIMPAR.TB_DEVOLUCAO_CONTAS"  +
                                "                              WHERE CD_MULTI_EMPRESA =  "+ cd_multi_empresa +
                                                            "   AND TP_STATUS = 'A'" +
                                                            "   AND CD_CONTA IN (SELECT dc.cd_conta_ffcv from dbaimpar.dw_v_contas_ffcv dc where dc.cd_convenio = "+pesquisa+" and dc.cd_multi_empresa = "+cd_multi_empresa+") )total" + 
                                "  FROM DBAIMPAR.TB_DEVOLUCAO_CONTAS" +
                                " WHERE CD_MULTI_EMPRESA = " + cd_multi_empresa +
                                "   AND TP_STATUS = 'A'" +
                                "   AND CD_CONTA IN (SELECT dc.cd_conta_ffcv from dbaimpar.dw_v_contas_ffcv dc where dc.cd_convenio = "+pesquisa+" and dc.cd_multi_empresa = "+cd_multi_empresa+") " +
                                "ORDER BY 3) WHERE LINHA >= "+jtStartIndex+" AND LINHA <="+(jtStartIndex + jtPageSize);
                    }
                    if(tp_filtro.equals(dt_entrega) || tp_filtro == dt_entrega){
                        query = "SELECT * FROM (SELECT CD_DEVOLUCAO_CONTAS," +
                                "       CD_MULTI_EMPRESA," +
                                "       CD_CONTA," +
                                "       CD_TIPO_CONTA," +
                                "       CD_MOTIVO_DEV," +
                                "       DT_DEVOLUCAO," +
                                "       DS_OBS, CD_CONTA_NOVA, DT_REAPRESENTACAO, replace(to_char(vl_reapresentado, '99G999G999G999D99'),' ','')vl_reapresentado, DS_OBS_FAT, row_number() OVER (ORDER BY 3) linha," +
                                "       (select count(*)total  FROM DBAIMPAR.TB_DEVOLUCAO_CONTAS"  +
                                "                              WHERE CD_MULTI_EMPRESA =  "+ cd_multi_empresa +
                                                            "   AND TP_STATUS = 'A'" +
                                                            "   AND CD_CONTA IN (SELECT dc.cd_conta_ffcv from dbaimpar.dw_v_contas_ffcv dc where TO_CHAR(dc.DT_ENTREGA_DA_FATURA,'MM/RRRR') = '" + pesquisa +"' and dc.cd_multi_empresa = "+cd_multi_empresa+") )total" + 
                                "  FROM DBAIMPAR.TB_DEVOLUCAO_CONTAS" +
                                " WHERE CD_MULTI_EMPRESA = " + cd_multi_empresa +
                                "   AND TP_STATUS = 'A'" +
                                "   AND CD_CONTA IN (SELECT dc.cd_conta_ffcv from dbaimpar.dw_v_contas_ffcv dc where TO_CHAR(dc.DT_ENTREGA_DA_FATURA,'MM/RRRR') = '" + pesquisa +"' and dc.cd_multi_empresa = "+cd_multi_empresa+")" +
                                "ORDER BY 3) WHERE LINHA >= "+jtStartIndex+" AND LINHA <="+(jtStartIndex + jtPageSize);
                    }
            }else{
                query = "SELECT * FROM (SELECT CD_DEVOLUCAO_CONTAS,CD_MULTI_EMPRESA,CD_CONTA,CD_TIPO_CONTA,CD_MOTIVO_DEV, dt_devolucao,DS_OBS, CD_CONTA_NOVA, DT_REAPRESENTACAO, replace(to_char(vl_reapresentado, '99G999G999G999D99'),' ','')vl_reapresentado, DS_OBS_FAT, row_number() OVER (ORDER BY 3) linha, (select count(*)total from dbaimpar.tb_devolucao_contas where cd_multi_empresa = "+cd_multi_empresa+" and tp_status = 'A')total  FROM dbaimpar.tb_devolucao_contas where cd_multi_empresa = "+cd_multi_empresa+" and tp_status = 'A' ORDER BY 3)WHERE LINHA >= "+jtStartIndex+" AND LINHA <="+(jtStartIndex + jtPageSize) +"";
            }
            
        }else{
            query = "SELECT * FROM (SELECT CD_DEVOLUCAO_CONTAS,CD_MULTI_EMPRESA,CD_CONTA,CD_TIPO_CONTA,CD_MOTIVO_DEV, dt_devolucao,DS_OBS, CD_CONTA_NOVA, DT_REAPRESENTACAO, replace(to_char(vl_reapresentado, '99G999G999G999D99'),' ','')vl_reapresentado, DS_OBS_FAT, row_number() OVER (ORDER BY 3) linha, (select count(*)total from dbaimpar.tb_devolucao_contas where cd_multi_empresa = "+cd_multi_empresa+" and tp_status = 'A')total  FROM dbaimpar.tb_devolucao_contas where cd_multi_empresa = "+cd_multi_empresa+" and tp_status = 'A' ORDER BY 3)WHERE LINHA >= "+jtStartIndex+" AND LINHA <="+(jtStartIndex + jtPageSize) +"";
        }
        try {
		Statement stmt = dbConnection.createStatement();
               
		ResultSet rs = stmt.executeQuery(query);
               
		while (rs.next()) {
                    
			DevContas devContas = new DevContas();
                        //Date date = new Date(parseInt(rs.getDate("DT_DEVOLUCAO").toString().replace("/Date(", "").replace(")/", ""), 10));
                        
			devContas.setCd_devolucao_contas(rs.getInt("CD_DEVOLUCAO_CONTAS"));
			devContas.setCd_multi_empresa(rs.getInt("CD_MULTI_EMPRESA"));
			devContas.setCd_conta(rs.getInt("CD_CONTA"));
                        devContas.setCd_tipo_conta(rs.getString("CD_TIPO_CONTA"));
                        devContas.setCd_motivo_dev(rs.getInt("CD_MOTIVO_DEV"));
                        
                        java.sql.Date diadev = rs.getDate("DT_DEVOLUCAO");
                        //java.sql.Date diareap = rs.getDate("DT_REAPRESENTACAO");
                        if(rs.wasNull()) {
                        }else{
                            devContas.setDt_devolucao(diadev.toString());
                            
                            java.sql.Date diareap = rs.getDate("DT_REAPRESENTACAO");
                            if(rs.wasNull()) {
                            }else{
                                devContas.setDt_reapresentacao(diareap.toString());
                                devContas.setCd_conta_nova(rs.getInt("CD_CONTA_NOVA"));
                            }
                            
                            devContas.setDs_obs_fat(rs.getString("DS_OBS_FAT"));
                            devContas.setVl_reapresentado(rs.getString("VL_REAPRESENTADO"));
                        }
                        
                        devContas.setDs_obs(rs.getString("DS_OBS"));
                        //
                        devContas.setTotal_reg(rs.getInt("TOTAL"));
			devContass.add(devContas);
		}
	} catch (SQLException e) {
		System.err.println(e.getMessage());
	}
	return devContass;
}
public List<RetornaPesquisa> getAllMotivoDev() {
        List<RetornaPesquisa> motivoDevs = new ArrayList<RetornaPesquisa>();
        String query = "select CD_MOTIVO, DS_MOTIVO from dbamv.MOTIVO_ITEM_GLOSA order by DS_MOTIVO";
        try {
		Statement stmt = dbConnection.createStatement();
                
		ResultSet rs = stmt.executeQuery(query);
                
		while (rs.next()) {
			RetornaPesquisa motivoDev = new RetornaPesquisa();
                        
                        
			motivoDev.setValue(rs.getInt("CD_MOTIVO"));
			motivoDev.setDisplayText(rs.getNString("DS_MOTIVO"));

			motivoDevs.add(motivoDev);
		}
	} catch (SQLException e) {
		System.err.println(e.getMessage());
	}
        return motivoDevs;
}
public Grafico getGraficoBarras(int empresa) {
        String query =  "SELECT ANO,MES,PERIODO, SUM(QT_DEVOLVIDAS)QT_DEVOLVIDAS , SUM(QT_REAPRESENTADAS)QT_REAPRESENTADAS " +
                        "FROM (" +
                        "      SELECT TO_CHAR(A.DT_REAPRESENTACAO,'mm')MES, TO_CHAR(A.DT_REAPRESENTACAO,'yyyy')ANO, TO_CHAR(A.DT_REAPRESENTACAO,'mm/yyyy')PERIODO,0 QT_DEVOLVIDAS, COUNT(A.CD_DEVOLUCAO_CONTAS)QT_REAPRESENTADAS " +
                        "      FROM DBAIMPAR.TB_DEVOLUCAO_CONTAS A" +
                        "      WHERE A.DT_REAPRESENTACAO IS NOT NULL" +
                        "        AND A.CD_MULTI_EMPRESA = 2" +
                        "        AND A.TP_STATUS = 'A'" +
                        "        AND  TRUNC(A.DT_REAPRESENTACAO) BETWEEN TRUNC(SYSDATE-180) AND TRUNC(SYSDATE)" +
                        "      GROUP BY TO_CHAR(A.DT_REAPRESENTACAO,'mm/yyyy'),TO_CHAR(A.DT_REAPRESENTACAO,'mm'), TO_CHAR(A.DT_REAPRESENTACAO,'yyyy')" +
                        "      UNION ALL" +
                        "      SELECT TO_CHAR(A.DT_DEVOLUCAO,'mm')MES, TO_CHAR(A.DT_DEVOLUCAO,'yyyy')ANO, TO_CHAR(A.DT_DEVOLUCAO,'mm/yyyy')PERIODO,COUNT(A.CD_DEVOLUCAO_CONTAS)QT_DEVOLVIDAS, 0 QT_REAPRESENTADAS" +
                        "      FROM DBAIMPAR.TB_DEVOLUCAO_CONTAS A" +
                        "      WHERE A.DT_REAPRESENTACAO IS NULL" +
                        "        AND A.CD_MULTI_EMPRESA = " + empresa +
                        "        AND A.TP_STATUS = 'A'" +
                        "        AND  TRUNC(A.DT_DEVOLUCAO) BETWEEN TRUNC(SYSDATE-180) AND TRUNC(SYSDATE) " +
                        "      GROUP BY TO_CHAR(A.DT_DEVOLUCAO,'mm/yyyy'),TO_CHAR(A.DT_DEVOLUCAO,'mm'), TO_CHAR(A.DT_DEVOLUCAO,'yyyy') " +
                        "      )" +
                        "GROUP BY PERIODO, ANO, MES " +
                        "ORDER BY ANO DESC, MES DESC";
        
        List<String> data = new ArrayList<>();
        List<String> data2 = new ArrayList<>();
        List<String> label = new ArrayList<>();
        
        Grafico grafico = new Grafico();
        
        
        List<GraficoDatasets> datasets = new ArrayList<>();
        
        
        GraficoDatasets dataset = new GraficoDatasets();
                        dataset.setLabel("Contas de Devolvidas");
                        dataset.setFillColor("rgba(48, 164, 255, 0.2)");
                        dataset.setStrokeColor("rgba(48,164,255,1)");
                        dataset.setPointColor("rgba(220,356,48,1)");
                        dataset.setStrokeColor("#fff");
                        dataset.setPointHighlightFill("#fff");
                        dataset.setPointHighlightStroke("rgba(48, 164, 255, 1)");
                        
        GraficoDatasets dataset2 = new GraficoDatasets();
                        dataset2.setLabel("Contas de Reapresentadas");
                        dataset2.setFillColor("rgba(255, 42, 47, 0.2)");
                        dataset2.setStrokeColor("rgba(48,164,255,1)");
                        dataset2.setPointColor("rgba(220,356,48,1)");
                        dataset2.setStrokeColor("#fff");
                        dataset2.setPointHighlightFill("#fff");
                        dataset2.setPointHighlightStroke("rgba(48, 164, 255, 1)");
        try {
		Statement stmt = dbConnection.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                
		while (rs.next()) {
                    label.add(rs.getString("PERIODO"));
                    data.add(rs.getString("QT_DEVOLVIDAS"));
                    data2.add(rs.getString("QT_REAPRESENTADAS"));
                }
                dataset.setData(data);
                dataset2.setData(data2);
                datasets.add(dataset);
                datasets.add(dataset2);
                
                grafico.setLabels(label);
                grafico.setDatasets(datasets);
                
	} catch (SQLException e) {
		System.err.println(e.getMessage());
	}
        return grafico;
}



public String getTotal(char tipo, int empresa){
    String valor="";
    String query="";
    if (tipo == 'R')
        query = "select to_char(count(*))total from dbaimpar.tb_devolucao_contas where tp_status = 'A' and vl_reapresentado is null and cd_multi_empresa = "+empresa;
    else
        query = "select to_char(count(*))total from dbaimpar.tb_devolucao_contas where tp_status = 'A' and vl_reapresentado is not null and cd_multi_empresa = "+empresa;
    
    try {
		
                Statement stmt = dbConnection.createStatement();
		ResultSet rs = stmt.executeQuery(query);
                
		while (rs.next()) {
			valor = rs.getString("TOTAL");
		}
	} catch (SQLException e) {
		System.err.println(e.getMessage());
	}
        return valor;
}
public List<InfoAtdDevContas> getInfoAtd(int cd_conta, String tp_conta, int empresa) throws SQLException{
        List<InfoAtdDevContas> InfoAtds = new ArrayList<InfoAtdDevContas>();
         String query = null;
         
        if (tp_conta.equals("A")){
            query = "SELECT A.CD_ATENDIMENTO, " +
                    "       A.NM_PACIENTE, " +
                    "       C.NM_CONVENIO, " +
                    "       A.CD_REMESSA, " +
                    "       A.DT_ENTREGA_DA_FATURA, " +
                    "       A.CD_CONTA_FFCV CD_CONTA, " +
                    "       TO_CHAR(A.SUM_VL_TOTAL_CONTA,'L99G999G999G999D99') VL_TOTAL, " +
                    "       (SELECT MAX(R.DT_PAGAMENTO)DT_PAGAMENTO FROM DBAIMPAR.DW_FFCV_TOTAL_REC R WHERE R.CD_ATENDIMENTO = A.CD_ATENDIMENTO AND R.CD_REMESSA = A.CD_REMESSA AND R.CD_CONTA = "+cd_conta+" AND R.CD_MULTI_EMPRESA = "+empresa+" )DT_PAGAMENTO, " +
                    "       (SELECT TO_CHAR(SUM(R.VL_RECEBIDO),'L99G999G999G9990D99') VL_TOTAL FROM DBAIMPAR.DW_FFCV_TOTAL_REC R WHERE R.CD_ATENDIMENTO = A.CD_ATENDIMENTO AND R.CD_REMESSA = A.CD_REMESSA AND R.CD_CONTA = "+cd_conta+" AND R.CD_MULTI_EMPRESA = "+empresa+" )VL_RECEBIDO " +
                    "FROM DBAIMPAR.DW_V_CONTAS_FFCV A, DBAIMPAR.DW_CONVENIO C " +
                    "WHERE A.CD_CONTA_FFCV = "+ cd_conta +
                    "  AND A.CD_MULTI_EMPRESA = " + empresa +
                    "  AND SUBSTR(A.TP_CONTA, 0, 1) = 'A' " +
                    "  AND A.CD_CONVENIO = C.CD_CONVENIO " +
                    "  AND C.CD_MULTI_EMPRESA = A.CD_MULTI_EMPRESA";
        }else if (tp_conta.equals("I")){
            query = "SELECT A.CD_ATENDIMENTO, " +
                    "       A.NM_PACIENTE, " +
                    "       C.NM_CONVENIO, " +
                    "       A.CD_REMESSA, " +
                    "       A.DT_ENTREGA_DA_FATURA, " +
                    "       A.CD_CONTA_FFCV CD_CONTA, " +
                    "       TO_CHAR(A.SUM_VL_TOTAL_CONTA,'L99G999G999G999D99') VL_TOTAL, " +
                    "       (SELECT MAX(R.DT_PAGAMENTO)DT_PAGAMENTO FROM DBAIMPAR.DW_FFCV_TOTAL_REC R WHERE R.CD_ATENDIMENTO = A.CD_ATENDIMENTO AND R.CD_REMESSA = A.CD_REMESSA AND R.CD_CONTA = "+cd_conta+" AND R.CD_MULTI_EMPRESA = "+empresa+" )DT_PAGAMENTO, " +
                    "       (SELECT TO_CHAR(SUM(R.VL_RECEBIDO),'L99G999G999G9990D99') VL_TOTAL FROM DBAIMPAR.DW_FFCV_TOTAL_REC R WHERE R.CD_ATENDIMENTO = A.CD_ATENDIMENTO AND R.CD_REMESSA = A.CD_REMESSA AND R.CD_CONTA = "+cd_conta+" AND R.CD_MULTI_EMPRESA = "+empresa+" )VL_RECEBIDO " +
                    "FROM DBAIMPAR.DW_V_CONTAS_FFCV A, DBAIMPAR.DW_CONVENIO C " +
                    "WHERE A.CD_CONTA_FFCV = "+ cd_conta +
                    "  AND A.CD_MULTI_EMPRESA = " + empresa +
                    "  AND SUBSTR(A.TP_CONTA, 0, 1) = 'H' " +
                    "  AND A.CD_CONVENIO = C.CD_CONVENIO " +
                    "  AND C.CD_MULTI_EMPRESA = A.CD_MULTI_EMPRESA";
        }
        try {
		
                Statement stmt = dbConnection.createStatement();
		ResultSet rs = stmt.executeQuery(query);
                
		while (rs.next()) {
			InfoAtdDevContas InfoAtd = new InfoAtdDevContas();
                        InfoAtd.setCd_atendimento(rs.getInt("CD_ATENDIMENTO"));
                        InfoAtd.setCd_conta(rs.getInt("CD_CONTA"));
			InfoAtd.setNm_paciente(rs.getString("NM_PACIENTE"));
                        InfoAtd.setNm_convenio(rs.getString("NM_CONVENIO"));
                        InfoAtd.setCd_remessa(rs.getInt("CD_REMESSA"));
                        InfoAtd.setVl_total(rs.getString("VL_TOTAL"));
                        java.sql.Date diaent = rs.getDate("DT_ENTREGA_DA_FATURA");
                        java.sql.Date diarec = rs.getDate("DT_PAGAMENTO");
                        
                        if(rs.wasNull()) {
                           if(diaent != null){
                               InfoAtd.setDt_entrega_da_fatura(diaent.toString());
                           }
                           if( diarec != null){
                               InfoAtd.setDt_pagamento(diarec.toString());
                           }
                        }else{
                           if(diaent != null){
                               InfoAtd.setDt_entrega_da_fatura(diaent.toString());
                           }
                           if( diarec != null){
                               InfoAtd.setDt_pagamento(diarec.toString());
                           }
                        }
                        
                        InfoAtd.setVl_recebido(rs.getString("VL_RECEBIDO"));

			InfoAtds.add(InfoAtd);
		}
	} catch (SQLException e) {
		System.err.println(e.getMessage());
	}
        return InfoAtds;
}

}