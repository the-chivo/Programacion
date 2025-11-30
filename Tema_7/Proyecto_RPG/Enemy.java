package rpg;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


public class Enemy {
    
    private String nombre;
    private int puntosAtaque = 0;
    private int saludBoss = 0;

    public void setPuntosAtaque(int puntosAtaque) {
        this.puntosAtaque = puntosAtaque;
    }

    public int getSaludBoss() {
        return saludBoss;
    }
    
    public Enemy(String nombre){
        this.nombre = nombre;
    }

    public void setSaludBoss(int saludBoss) {
        this.saludBoss = saludBoss;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public int getAtaque(){
        return puntosAtaque;
    }
    
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public void setAtaque(int ataque){
        this.puntosAtaque = ataque;
    }
    
    public void calcularFuerzaEnemigoN1(){
    
        int ataqueInicial = (int) (Math.random()*5) +1;
        puntosAtaque = ataqueInicial;
    }
        
    public int soltarDineroN1(){
        
        int dineroEnemigo = (int) (Math.random()*2) +1; 
        return dineroEnemigo;
    }
    
    public void calcularFuerzaEnemigoN2(){
    
        int ataqueInicial = (int) (Math.random()*9) +5;
        puntosAtaque = ataqueInicial;
    }
        
    public int soltarDineroN2(){
        
        int dineroEnemigo = (int) (Math.random()*4) +1; 
        return dineroEnemigo;
    }
    
    public void calcularFuerzaEnemigoN3(){
    
        int ataqueInicial = (int) (Math.random()*8) +9;
        puntosAtaque = ataqueInicial;
    }
        
    public int soltarDineroN3(){
        
        int dineroEnemigo = (int) (Math.random()*4) + 2; 
        return dineroEnemigo;
    }
    
    public void boss(){
        
        puntosAtaque = 22;
        nombre = "Alexelcapo";
        saludBoss = 100;
    }
}
