package com.mitocode.controller;

import com.mitocode.ejb.TelefonoFacadeLocal;
import com.mitocode.model.Telefono;
import com.mitocode.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class TelefonoController implements Serializable {

    @EJB
    private TelefonoFacadeLocal telefonoEJB;
    @Inject
    private Telefono telefono;
    private List<Telefono> telefonos;

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    public Telefono getTelefono() {
        return telefono;
    }

    public void setTelefono(Telefono telefono) {
        this.telefono = telefono;
    }
    
    @PostConstruct
    public void init(){
        telefonos = telefonoEJB.findAll();
    }

    public void registrar() {
        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        telefono.setPersona(us.getCodigo());
        telefonoEJB.create(telefono);
        telefonos = telefonoEJB.findAll();
    }
}