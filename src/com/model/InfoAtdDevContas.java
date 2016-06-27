/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

/**
 *
 * @author jonei
 */
public class InfoAtdDevContas {
    private int cd_atendimento;
    private String nm_paciente;
    private String nm_convenio;
    private int cd_remessa;
    private int cd_fatura;
    private String dt_entrega_da_fatura;
    private String dt_prevista_para_pgto;
    private int cd_conta;
    private String vl_total;
    private String vl_recebido;
    private String dt_pagamento;

    public String getVl_recebido() {
        return vl_recebido;
    }

    public void setVl_recebido(String vl_recebido) {
        this.vl_recebido = vl_recebido;
    }

    public String getDt_pagamento() {
        return dt_pagamento;
    }

    public void setDt_pagamento(String dt_pagamento) {
        this.dt_pagamento = dt_pagamento;
    }

    
    
    public String getVl_total() {
        return vl_total;
    }

    public void setVl_total(String vl_total) {
        this.vl_total = vl_total;
    }

  

    public int getCd_conta() {
        return cd_conta;
    }

    public void setCd_conta(int cd_conta) {
        this.cd_conta = cd_conta;
    }

    public int getCd_atendimento() {
        return cd_atendimento;
    }

    public void setCd_atendimento(int cd_atendimento) {
        this.cd_atendimento = cd_atendimento;
    }

    public String getNm_paciente() {
        return nm_paciente;
    }

    public void setNm_paciente(String nm_paciente) {
        this.nm_paciente = nm_paciente;
    }

    public String getNm_convenio() {
        return nm_convenio;
    }

    public void setNm_convenio(String nm_convenio) {
        this.nm_convenio = nm_convenio;
    }

    public int getCd_remessa() {
        return cd_remessa;
    }

    public void setCd_remessa(int cd_remessa) {
        this.cd_remessa = cd_remessa;
    }

    public int getCd_fatura() {
        return cd_fatura;
    }

    public void setCd_fatura(int cd_fatura) {
        this.cd_fatura = cd_fatura;
    }

    public String getDt_entrega_da_fatura() {
        return dt_entrega_da_fatura;
    }

    public void setDt_entrega_da_fatura(String dt_entrega_da_fatura) {
        this.dt_entrega_da_fatura = dt_entrega_da_fatura;
    }

    public String getDt_prevista_para_pgto() {
        return dt_prevista_para_pgto;
    }

    public void setDt_prevista_para_pgto(String dt_prevista_para_pgto) {
        this.dt_prevista_para_pgto = dt_prevista_para_pgto;
    }
    
    
    
}
