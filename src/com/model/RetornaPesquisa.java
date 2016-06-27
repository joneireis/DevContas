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
public class RetornaPesquisa {

    public int getValue() {
        return Value;
    }

    public void setValue(int Value) {
        this.Value = Value;
    }

    public String getDisplayText() {
        return DisplayText;
    }

    public void setDisplayText(String DisplayText) {
        this.DisplayText = DisplayText;
    }
    private int Value;
    private String DisplayText;
}
