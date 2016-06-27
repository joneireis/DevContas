package com.model;

import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author jonei
 */
public class DevContas {

    private int cd_multi_empresa;
    private int cd_convenio;
    private int cd_conta;
    private int vl_total_conta;
    private String cd_user_insert;
    private String ds_obs;
    private String cd_user_reapresentado;
    private Date dt_user_reapresentado;
    private String tp_status;
    private Date dt_intativo;
    private String cd_user_inativo;
    private String cd_tipo_conta;
    private int cd_motivo_dev;
    private String vl_reapresentado;
    private String dt_devolucao;
    private String cd_user_rg_alter;
    private int cd_conta_nova;
    private String dt_reapresentacao;
    private String ds_obs_fat;
    private String cd_user_reap_alt;
    private String dt_user_reap_alt;
    private int total_reg;

    public int getTotal_reg() {
        return total_reg;
    }

    public void setTotal_reg(int total_reg) {
        this.total_reg = total_reg;
    }

    public String getVl_reapresentado() {
        return vl_reapresentado;
    }

    public void setVl_reapresentado(String vl_reapresentado) {
        this.vl_reapresentado = vl_reapresentado;
    }
    
    public String getCd_user_reap_alt() {
        return cd_user_reap_alt;
    }

    public void setCd_user_reap_alt(String cd_user_reap_alt) {
        this.cd_user_reap_alt = cd_user_reap_alt;
    }

    public String getDt_user_reap_alt() {
        return dt_user_reap_alt;
    }

    public void setDt_user_reap_alt(String dt_user_reap_alt) {
        this.dt_user_reap_alt = dt_user_reap_alt;
    }

    public int getCd_conta_nova() {
        return cd_conta_nova;
    }

    public void setCd_conta_nova(int cd_conta_nova) {
        this.cd_conta_nova = cd_conta_nova;
    }

    public String getDt_reapresentacao() {
        return dt_reapresentacao;
    }

    public void setDt_reapresentacao(String dt_reapresentacao) {
        this.dt_reapresentacao = dt_reapresentacao;
    }

    public String getDs_obs_fat() {
        return ds_obs_fat;
    }

    public void setDs_obs_fat(String ds_obs_fat) {
        this.ds_obs_fat = ds_obs_fat;
    }
    

    public String getCd_user_rg_alter() {
        return cd_user_rg_alter;
    }

    public void setCd_user_rg_alter(String cd_user_rg_alter) {
        this.cd_user_rg_alter = cd_user_rg_alter;
    }
    

    public String getDt_devolucao() {
        return dt_devolucao;
    }

    public void setDt_devolucao(String dt_devolucao) {
        this.dt_devolucao = dt_devolucao;
    }

     
    public int getCd_motivo_dev() {
        return cd_motivo_dev;
    }

    public void setCd_motivo_dev(int cd_motivo_dev) {
        this.cd_motivo_dev = cd_motivo_dev;
    }

    public String getCd_tipo_conta() {
        return cd_tipo_conta;
    }

    public void setCd_tipo_conta(String cd_tipo_conta) {
        this.cd_tipo_conta = cd_tipo_conta;
    }
    private int cd_devolucao_contas;

    public int getCd_devolucao_contas() {
        return cd_devolucao_contas;
    }

    public void setCd_devolucao_contas(int cd_devolucao_contas) {
        this.cd_devolucao_contas = cd_devolucao_contas;
    }
    public int getCd_multi_empresa() {
        return cd_multi_empresa;
    }

    public void setCd_multi_empresa(int cd_multi_empresa) {
        this.cd_multi_empresa = cd_multi_empresa;
    }

    public int getCd_convenio() {
        return cd_convenio;
    }

    public void setCd_convenio(int cd_convenio) {
        this.cd_convenio = cd_convenio;
    }

    public int getCd_conta() {
        return cd_conta;
    }

    public void setCd_conta(int cd_conta) {
        this.cd_conta = cd_conta;
    }

    public int getVl_total_conta() {
        return vl_total_conta;
    }

    public void setVl_total_conta(int vl_total_conta) {
        this.vl_total_conta = vl_total_conta;
    }

    public String getCd_user_insert() {
        return cd_user_insert;
    }

    public void setCd_user_insert(String cd_user_insert) {
        this.cd_user_insert = cd_user_insert;
    }

    public String getDs_obs() {
        return ds_obs;
    }

    public void setDs_obs(String ds_obs) {
        this.ds_obs = ds_obs;
    }

    public String getCd_user_reapresentado() {
        return cd_user_reapresentado;
    }

    public void setCd_user_reapresentado(String cd_user_reapresentado) {
        this.cd_user_reapresentado = cd_user_reapresentado;
    }

    public Date getDt_user_reapresentado() {
        return dt_user_reapresentado;
    }

    public void setDt_user_reapresentado(Date dt_user_reapresentado) {
        this.dt_user_reapresentado = dt_user_reapresentado;
    }

    public String getTp_status() {
        return tp_status;
    }

    public void setTp_status(String tp_status) {
        this.tp_status = tp_status;
    }

    public Date getDt_intativo() {
        return dt_intativo;
    }

    public void setDt_intativo(Date dt_intativo) {
        this.dt_intativo = dt_intativo;
    }

    public String getCd_user_inativo() {
        return cd_user_inativo;
    }

    public void setCd_user_inativo(String cd_user_inativo) {
        this.cd_user_inativo = cd_user_inativo;
    }
}
