/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

/**
 *
 * @author user
 */
public class RetornaProducaoMob {

    private int comp;
    private  float total;
    private int cd_multi_empresa;

    public void setcomp(int comp) {
        this.comp = comp;
    }

    public int getcomp() {
        return this.comp;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getTotal() {
        return total;
    }

    public int getCd_multi_empresa() {
        return cd_multi_empresa;
    }

    public void setCd_multi_empresa(int cd_multi_empresa) {
        this.cd_multi_empresa = cd_multi_empresa;
    }
    
}
