package rpg;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.lang.Math;
        
public class Player {
    
   private String nombre;
   private int puntosSalud = 20;
   private int puntosAtaque = 0;
   private int dinero = 2;
   
   public Player(String nombre){
       
       this.nombre = nombre;
       
   }
   
    public String getNombre(){
    return nombre;
    }
    
    public int getSalud(){
        return puntosSalud;
    }
    
    public int getAtaque(){
        return puntosAtaque;
    }
    
    public int getDinero(){
        return dinero;
    }
    
    
    public void setNombre(String nombre){
    this.nombre = nombre;
    }
    
    public void setSalud(int salud){
        this.puntosSalud = salud;
    }
    
    public void setAtaque(int ataque){
        this.puntosAtaque = ataque;
    }
    
    public void setDinero(int dinero){
        this.dinero = dinero;
    }
    
    
    public void calcularFuerzaInicial(){
        
        int ataqueInicial = (int) (Math.random()*3) +2;
        puntosAtaque = ataqueInicial;
    }
   
}
