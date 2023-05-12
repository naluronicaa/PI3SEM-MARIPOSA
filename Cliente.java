package com.classes.clientes;

public class Cliente {
    private String email;
    private String senha;
    private static int id = 0;
    
    public static int getId(){
        return id;
    }
    
    public static void setId(int idNovo){
        id = idNovo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    
}
