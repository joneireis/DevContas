package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CrudDao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.model.DevContas;
import com.model.RetornaPesquisa;
import java.lang.reflect.Type;
import java.util.Date;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.model.Grafico;
import com.model.InfoAtdDevContas;


public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();

	private CrudDao dao;

	public Controller() {
		dao = new CrudDao();
	}
       /* public class DateSerializer implements JsonSerializer<Object> 
        {
            public JsonElement serialize(Date date, Type typeOfT, JsonSerializationContext context)
            {
                return new JsonPrimitive("/Date(" + date.getTime() + ")/");
            }

            public JsonElement serialize(Object arg0, Type arg1,
                    JsonSerializationContext arg2) {

                Date date = (Date) arg0;
                return new JsonPrimitive("/Date(" + date.getTime() + ")/");
            }
        }
        */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
                //Listas para retorno 
		List<DevContas> devContasList = new ArrayList<DevContas>();
                List<RetornaPesquisa> motivoDevList = new ArrayList<RetornaPesquisa>();
		List<InfoAtdDevContas> infoatdList = new ArrayList<InfoAtdDevContas>();  
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");
                
		if (action != null) {
			try {
				if (action.equals("list")) {
                                    
                                         int emp = Integer.parseInt(request.getParameter("cd_multi_empresa"));
                                        String pesquisa = request.getParameter("pesquisa");
                                        String tp_filtro = request.getParameter("tp_filtro");
                                        int totalReg = 0;
                                        String jtPageSize = request.getParameter("jtPageSize");
                                        String jtStartIndex = request.getParameter("jtStartIndex");
					// Fetch Data from DevContas Table
					devContasList = dao.getAllDevContas(emp,pesquisa,tp_filtro,jtStartIndex,jtPageSize);
                                         if (devContasList.size() > 0){
                                            totalReg = devContasList.get(0).getTotal_reg();
                                        }
					// Return in the format required by jTable plugin
					JSONROOT.put("Result", "OK");
					JSONROOT.put("TotalRecordCount", totalReg);
                                        JSONROOT.put("Records", devContasList);
                                            
					// Convert Java Object to Json
					String jsonArray = gson.toJson(JSONROOT);
					response.getWriter().print(jsonArray);
                                        
                                        
				}
                                else if (action.equals("infoatd")) {
                                        int conta = Integer.parseInt(request.getParameter("cd_conta"));
                                        String tp_conta = request.getParameter("cd_tipo_conta");
					int empresa = Integer.parseInt(request.getParameter("cd_multi_empresa"));
                                        // Fetch Data from DevContas Table
					infoatdList = dao.getInfoAtd(conta,tp_conta,empresa);
                                           
					// Return in the format required by jTable plugin
					JSONROOT.put("Result", "OK");
					JSONROOT.put("Records", infoatdList);

					// Convert Java Object to Json
					String jsonArray = gson.toJson(JSONROOT);

					response.getWriter().print(jsonArray);
                                        
                                        
				} else if (action.equals("getMotivos")) {
                                        motivoDevList = dao.getAllMotivoDev();
                                   
                                        // Return in the format required by jTable plugin
					JSONROOT.put("Result", "OK");
					JSONROOT.put("Records", motivoDevList);

					// Convert Java Object to Json
					String jsonArray = gson.toJson(JSONROOT);

					response.getWriter().print(jsonArray);
				} else if (action.equals("create")) {
					DevContas devContas = new DevContas();
					/*if (request.getParameter("cd_devolucao_contas") != null) {
						int cd_devolucao_contas = Integer.parseInt(request.getParameter("cd_devolucao_contas"));
						devContas.setCd_devolucao_contas(cd_devolucao_contas);
					}*/

					if (request.getParameter("cd_multi_empresa") != null) {
						int cd_multi_empresa = Integer.parseInt(request.getParameter("cd_multi_empresa"));
						devContas.setCd_multi_empresa(cd_multi_empresa);
					}

					if (request.getParameter("cd_conta") != null) {
						int cd_conta = Integer.parseInt(request.getParameter("cd_conta"));
						devContas.setCd_conta(cd_conta);
					}

					if (request.getParameter("cd_tipo_conta") != null) {
						String cd_tipo_conta = request.getParameter("cd_tipo_conta");
						devContas.setCd_tipo_conta(cd_tipo_conta);
					}

					if (request.getParameter("cd_motivo_dev") != null) {
						int cd_motivo_dev = Integer.parseInt(request.getParameter("cd_motivo_dev"));
						devContas.setCd_motivo_dev(cd_motivo_dev);
					}
                                        
                                        if (request.getParameter("login") != null) {
						String cd_user_insert = request.getParameter("login");
						devContas.setCd_user_insert(cd_user_insert);
					}
                                        if (request.getParameter("dt_devolucao") != null) {
						String dt_devolucao = request.getParameter("dt_devolucao");
                                                
						devContas.setDt_devolucao(dt_devolucao);
					}
                                        
                                        if (request.getParameter("ds_obs") != null) {
						String ds_obs = request.getParameter("ds_obs");
						devContas.setDs_obs(ds_obs);
					}
					if (action.equals("create")) {
						// Create new record
						dao.addDevContas(devContas);
					} else if (action.equals("update")) {
						// Update existing record
						dao.updateDevContas(devContas);
					}

					// Return in the format required by jTable plugin
					JSONROOT.put("Result", "OK");
					JSONROOT.put("Record", devContas);

					// Convert Java Object to Json
					String jsonArray = gson.toJson(JSONROOT);
					response.getWriter().print(jsonArray);
				}  else if (action.equals("update")) {
					DevContas devContas = new DevContas();
					if (request.getParameter("cd_devolucao_contas") != null) {
						int cd_devolucao_contas = Integer.parseInt(request.getParameter("cd_devolucao_contas"));
						devContas.setCd_devolucao_contas(cd_devolucao_contas);
					}

					if (request.getParameter("cd_multi_empresa") != null) {
						int cd_multi_empresa = Integer.parseInt(request.getParameter("cd_multi_empresa"));
						devContas.setCd_multi_empresa(cd_multi_empresa);
					}

					if (request.getParameter("cd_conta") != null) {
						int cd_conta = Integer.parseInt(request.getParameter("cd_conta"));
						devContas.setCd_conta(cd_conta);
					}

					if (request.getParameter("cd_tipo_conta") != null) {
						String cd_tipo_conta = request.getParameter("cd_tipo_conta");
						devContas.setCd_tipo_conta(cd_tipo_conta);
					}

					if (request.getParameter("cd_motivo_dev") != null) {
						int cd_motivo_dev = Integer.parseInt(request.getParameter("cd_motivo_dev"));
						devContas.setCd_motivo_dev(cd_motivo_dev);
					}
                                        
                                        if (request.getParameter("dt_devolucao") != null) {
						String dt_devolucao = request.getParameter("dt_devolucao");
                                                
						devContas.setDt_devolucao(dt_devolucao);
					}
                                        
                                        if (request.getParameter("ds_obs") != null) {
						String ds_obs = request.getParameter("ds_obs");
						devContas.setDs_obs(ds_obs);
					}
                                        
                                        dao.updateDevContas(devContas);    
                                       
					// Return in the format required by jTable plugin
					JSONROOT.put("Result", "OK");
					JSONROOT.put("Record", devContas);

					// Convert Java Object to Json
					String jsonArray = gson.toJson(JSONROOT);
					response.getWriter().print(jsonArray);
				}  else if (action.equals("updatefat")) {
					DevContas devContas = new DevContas();
                                        if (request.getParameter("cd_devolucao_contas") != null) {
						int cd_devolucao_contas = Integer.parseInt(request.getParameter("cd_devolucao_contas"));
						devContas.setCd_devolucao_contas(cd_devolucao_contas);
					}
					if (request.getParameter("login") != null) {
						String cd_user_rg_alter = request.getParameter("login");
						devContas.setCd_user_rg_alter(cd_user_rg_alter);
					}
                                        if (request.getParameter("login") != null) {
						String cd_user = request.getParameter("login");
						devContas.setCd_user_insert(cd_user);
					}
                                        if (request.getParameter("login") != null) {
						String cd_user = request.getParameter("login");
						devContas.setCd_user_reap_alt(cd_user);
					}
                                        if (request.getParameter("login") != null) {
						String cd_user = request.getParameter("login");
						devContas.setCd_user_reapresentado(cd_user);
					}
                                        
					if (request.getParameter("cd_conta") != null) {
						int cd_conta = Integer.parseInt(request.getParameter("cd_conta"));
						devContas.setCd_conta(cd_conta);
					}
                                        
                                        if (request.getParameter("cd_conta_nova") != null) {
						int cd_conta_nova = Integer.parseInt(request.getParameter("cd_conta_nova"));
						devContas.setCd_conta_nova(cd_conta_nova);
					}
                                        if (request.getParameter("cd_motivo_dev") != null) {
						int cd_motivo_dev = Integer.parseInt(request.getParameter("cd_motivo_dev"));
						devContas.setCd_motivo_dev(cd_motivo_dev);
					}
                                        if (request.getParameter("dt_reapresentacao") != null) {
						String dt_reapresentacao = request.getParameter("dt_reapresentacao");
						devContas.setDt_reapresentacao(dt_reapresentacao);
					}
                                        if (request.getParameter("vl_reapresentado") != null) {
						String vl_reapresentado = request.getParameter("vl_reapresentado");
                                                     //  vl_reapresentado = vl_reapresentado.replace("R$",""); //remove the R$
                                                     //  vl_reapresentado = vl_reapresentado.replace(".",""); //replacing the '.' to nothing
                                                     //  vl_reapresentado = vl_reapresentado.replace(",","."); //replacing the ',' to '.'
						devContas.setVl_reapresentado(vl_reapresentado);
					}
                                        if (request.getParameter("ds_obs_fat") != null) {
						String ds_obs_fat = request.getParameter("ds_obs_fat");
						devContas.setDs_obs_fat(ds_obs_fat);
					}
                                        
                                        dao.updateDevContasFat(devContas);
                                        
                                      
                                       
					// Return in the format required by jTable plugin
					JSONROOT.put("Result", "OK");
					JSONROOT.put("Record", devContas);

					// Convert Java Object to Json
					String jsonArray = gson.toJson(JSONROOT);
					response.getWriter().print(jsonArray);
				} else if (action.equals("delete")) {
					// Delete record
					if (request.getParameter("cd_devolucao_contas") != null) {
						int cd_devolucao_contas = Integer.parseInt(request.getParameter("cd_devolucao_contas"));
						dao.deleteDevContas(cd_devolucao_contas,request.getParameter("login"));
                                        
						// Return in the format required by jTable plugin
						JSONROOT.put("Result", "OK");

						// Convert Java Object to Json
						String jsonArray = gson.toJson(JSONROOT);
						response.getWriter().print(jsonArray);
					}
				} else if (action.equals("getContador")){
                                    char tipo;
                                    int emp = Integer.parseInt(request.getParameter("cd_multi_empresa"));
                                    String valor;
                                    tipo = request.getParameter("tipo").charAt(0);
                                   
                                    valor = dao.getTotal(tipo,emp);
                                    
                                    // Return in the format required by jTable plugin
					JSONROOT.put("Result", "OK");
					//JSONROOT.put("TotalRecordCount", devContasList.size());
                                        JSONROOT.put("Records", valor);
                                            
					// Convert Java Object to Json
					String jsonArray = gson.toJson(JSONROOT);

					response.getWriter().print(jsonArray);

                                } else if (action.equals("getGrafico")){
                                    
                                    int tipo;
                                    int emp = Integer.parseInt(request.getParameter("cd_multi_empresa"));
                                    String valor;
                                    tipo = request.getParameter("tipo").charAt(0);
                                    
                                    Grafico grafico = new Grafico();
                                    
                                    grafico = (Grafico) dao.getGraficoBarras(emp);
                                    
                                   
                                    
                                    // Return in the format required by jTable plugin
					JSONROOT.put("Result", "OK");
					//JSONROOT.put("TotalRecordCount", devContasList.size());
                                        JSONROOT.put("Records", grafico);
                                            
					// Convert Java Object to Json
					String jsonArray = gson.toJson(JSONROOT);

					response.getWriter().print(jsonArray);
                                    
                                }
			} catch (Exception ex) {
				JSONROOT.put("Result", "ERROR");
				JSONROOT.put("Message", ex.getMessage());
				String error = gson.toJson(JSONROOT);
				response.getWriter().print(error);
			}
		}
	}
}