/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package rpg;

import java.util.Scanner;

/**
 *
 * @author adria
 */
public class RPG {
    
    static boolean[] itemsTienda = new boolean[5];
    static boolean[] itemsTiendaBoss = new boolean[5];
    
    static int enemigosDerrotados = 0;
    
    public static void main(String[] args) { 
        
        Scanner sc = new Scanner(System.in);
        int opcion;
        int opcionReroll;
        String nombre;
        boolean jugadorHaMuerto = false;
        
       
       
            System.out.println("Has despertado aturdido en unas montañas, es un paisaje desolado y no consigues ver atismo de vida");
            System.out.println("Pronto descubres que es el año 2097 y estas en la Nueva Peru. Has sufrido el peor de los destinos y no recuerdas nadas, te duele la cabeza");
            System.out.println("Tu objetivo es escapar de la Nueva Peru ");
            System.out.println("¿Como me llamo?");
            nombre = sc.nextLine();
            
            Player player = new Player(nombre);
            player.calcularFuerzaInicial();
                                 
            System.out.println("Tu ataque inicial es " + player.getAtaque() + " felicidades");
            System.out.println("Si pagas 1 de oro puedes sacar otra tirada de fuerza(Tienes " + player.getDinero() + " de oro)");
            System.out.println("1.Para hacer reroll");
            System.out.println("2.Para quedarte con tu ataque");
            opcionReroll = sc.nextInt();
            sc.nextLine();
            if(opcionReroll == 1){                
                player.calcularFuerzaInicial();
                player.setDinero(player.getDinero() - 1);
                System.out.println("Tu ataque inicial es " + player.getAtaque() + " felicidades");
            }                     
            
        do{
            
           System.out.println("##///xxX_Menu principal_Xxx?¿)¿..");
           System.out.println("1. Luchar contra enemigo");
           System.out.println("2. Comprar items");
           System.out.println("3. Consultar estadisticas");
           System.out.println("0. Para rendirte");
           opcion = sc.nextInt();
          
            switch(opcion){
                case 1:
                    jugadorHaMuerto = lucharEnemigo(player);
                    if(jugadorHaMuerto == true){
                        opcion = 0;
                    }
                break;
                case 2:
                    tienda(player);
                    break;
                case 3:
                    mostrarEst(player);
                    break;
            }   
            
        }while(opcion != 0);
    }
    
    
    private static String[] getEnemyNames(){
        
        String[] nombreEnemigos1 = {"Nicolas ", "Esqueleto ", "Perro ", "Tyrone ", "Antoñete ", "el guason ", "Peruano "};
        String[] nombreEnemigos2 = {"Maduro ", "Viejo ", "EL_Canservero ", "Alcalde ", "Sanchez ", "perro viejo ", "Es "};
        String[] tipoEnemigo = {"Igneo", "Palomares", "Lechal", "Infrarrojo", "Experto", "Maton" , "Clave"};
        String listaNombres[] = new String[16];
        for(int i = 0; i <= 15; i++){
            
            int arrayN1 = (int) (Math.random()* nombreEnemigos1.length);
            int arrayN2 = (int) (Math.random()* nombreEnemigos2.length);
            int arrayN3 = (int) (Math.random()* tipoEnemigo.length);
            String nombreEnemigo = nombreEnemigos1[arrayN1] + nombreEnemigos2[arrayN2] + tipoEnemigo[arrayN3];
            listaNombres[i] = nombreEnemigo;
        }                 
             
        return listaNombres;
    }
    
    
    
    private static boolean lucharEnemigo(Player player){
        String[] nombreEnemigos = getEnemyNames();
        int index = (int) (Math.random()* nombreEnemigos.length);
        boolean jugadorHaMuerto = false;
        Enemy enemy = new Enemy(nombreEnemigos[index]);
        int oroEnemigo = 0;
        
        if(enemigosDerrotados < 5){
            enemy.calcularFuerzaEnemigoN1();
            oroEnemigo = enemy.soltarDineroN1();
        }        
        
        if(enemigosDerrotados >= 5 && enemigosDerrotados < 9){
            enemy.calcularFuerzaEnemigoN2();
            oroEnemigo = enemy.soltarDineroN2();
        }  
        
        if(enemigosDerrotados >= 9 && enemigosDerrotados < 16 ){
            enemy.calcularFuerzaEnemigoN3();
            oroEnemigo = enemy.soltarDineroN3();
        }
        
        if(enemigosDerrotados >= 16){
            System.out.println(enemigosDerrotados);
            enemy.boss();
            jugadorHaMuerto = finalCombat(player, enemy);
            return jugadorHaMuerto;
        }
        
        System.out.println("Te topas con " + enemy.getNombre() + " tiene " + enemy.getAtaque() + " de poder");
        
        if(player.getAtaque() >= enemy.getAtaque()){
            
            System.out.println("Has derrotado a " + enemy.getNombre());        
            System.out.println("Has ganado " + oroEnemigo + " de oro");
            player.setDinero(player.getDinero() + oroEnemigo);            
            enemigosDerrotados++;            
        }
        else{
            System.out.println("Has perdido el combate");
            int daño = enemy.getAtaque() - player.getAtaque();
            player.setSalud(player.getSalud() - daño);
            System.out.println(enemy.getNombre() + " te ha infligido " + daño + " puntos de dolor");
                                                             
            if(player.getSalud() <= 0) {
                System.out.println("x`x+`´x+´+23´+3´+´3´+2+++2+´x2+-_X%3");
                System.out.println("´_.,.-..+`sX.HAS MUERT0 EN P3RU?.-.``.+.xX._");
                System.out.println("x`x+`´x+´+23´+3´+´3´+2+++2+´x2+-_X%3");
                jugadorHaMuerto = true;
            }
        }
        
        return jugadorHaMuerto;
    }
    
    
    
    private static void tienda(Player player){
        
        int idTienda = 0;
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
                    
        do{
            
            System.out.println("#########################################################");
            System.out.println("                        TIENDA              ");
            System.out.println("#########################################################");
            
            if(itemsTienda[0] == false){   
                idTienda++;
                System.out.println("1. Daga [3 de oro] [Aumenta tu daño 2 puntos]");
            }
            else{System.out.println("1.##################### AGOTADO #########################");}
            if(itemsTienda[1] == false){
                idTienda++;
                System.out.println("2. Sable  [6 de oro] [Aumenta tu daño 4 puntos]");
            }
            else{System.out.println("2.##################### AGOTADO #########################");}
            if(itemsTienda[2] == false){
                idTienda++;
                System.out.println("3. Flauta [10 de oro] [Aumenta tu daño 6 puntos]");
            }
            else{System.out.println("3.##################### AGOTADO #########################");}
            if(itemsTienda[3] == false){
                idTienda++;
                System.out.println("4. Paloma [2 de oro] [Aumenta tu salud 4 puntos]");
            }
            else{System.out.println("4.##################### AGOTADO #########################");}
            if(itemsTienda[4] == false){
                idTienda++;
                System.out.println("5. Paloma Mayor [5 de oro] [Aumenta tu salud 10 puntos]");
            }           
            else{System.out.println("5.##################### AGOTADO #########################");}
            System.out.println("0.SALIR");
            
            
            opcion = sc.nextInt();
            
            switch(opcion){
            
                case 1:
                    
                    if(itemsTienda[0] == false){
                        
                        if(player.getDinero() >= 3){
                           
                            player.setDinero(player.getDinero() - 3);
                            player.setAtaque(player.getAtaque() + 2);
                            itemsTienda[0] = true;
                        }
                        if( player.getDinero() < 3){
                            System.out.println("No tienes suficiente dinero");
                        }                     
                    }
                    else{System.out.println("EL PRODUCTO ESTA AGOTADO HE DICHO");}
                    break;
                case 2:
                    
                    if( itemsTienda[1] == false){
                        
                        if(player.getDinero() >= 6){
                           
                            player.setDinero(player.getDinero() - 6);
                            player.setAtaque(player.getAtaque() + 4);
                            itemsTienda[1] = true;
                        }
                        if( player.getDinero() < 6){
                            System.out.println("No tienes suficiente dinero");
                        }                     
                    }
                    else{System.out.println("EL PRODUCTO ESTA AGOTADO HE DICHO");}
                    break;
                
                case 3:
                    
                  if(itemsTienda[2] == false){
                        
                        if(player.getDinero() >= 10){
                           
                            player.setDinero(player.getDinero() - 10);
                            player.setAtaque(player.getAtaque() + 6);
                            itemsTienda[2] = true;
                        }
                        if( player.getDinero() < 8){
                            System.out.println("No tienes suficiente dinero");
                        }                     
                    }
                    else{System.out.println("EL PRODUCTO ESTA AGOTADO HE DICHO");}
                    break;
                    
                case 4:
                    if( itemsTienda[3] == false){
                        
                        if(player.getDinero() >= 2){
                           
                            player.setDinero(player.getDinero() - 2);
                            player.setSalud(player.getSalud() + 4);
                            itemsTienda[3] = true;
                        }
                        if( player.getDinero() < 2){
                            System.out.println("No tienes suficiente dinero");
                        }                     
                    }
                    else{System.out.println("EL PRODUCTO ESTA AGOTADO HE DICHO");}
                    break;
                    
                case 5:
                    if( itemsTienda[4] == false){
                        
                        if(player.getDinero() >= 5){
                           
                            player.setDinero(player.getDinero() - 5);
                            player.setSalud(player.getSalud() + 10);
                            itemsTienda[4] = true;
                        }
                        if( player.getDinero() < 5){
                            System.out.println("No tienes suficiente dinero");
                        }                     
                    }
                    else{System.out.println("EL PRODUCTO ESTA AGOTADO HE DICHO");}
                    break;
            }   
            
        }while(opcion != 0);                  
    }
    
    
    
    
    private static void mostrarEst(Player player){
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("###############################");
        System.out.println("######### ESTADISTICAS ########");
        System.out.println("Salud: " + player.getSalud());
        System.out.println("Ataque: " + player.getAtaque());
        System.out.println("Dinero: " + player.getDinero());
        System.out.println("###############################");
        System.out.println("###############################");
        System.out.println("Pulsa cualquier tecla para salir");
        sc.nextLine();
    }
    
    private static boolean finalCombat(Player player, Enemy enemy){

    Scanner sc = new Scanner(System.in);
    int opcion = 0;
    boolean jugadorHaMuerto = false;
    boolean combateTerminado = false;

    System.out.println("Parece que no queda nadie mas en la Nueva Peru");
    System.out.println("Caminas victorioso sobre la pila de cadaveres...");
    System.out.println(enemy.getNombre() + " aparece ante la puerta");
    System.out.println(enemy.getNombre() + ": " + player.getNombre() + " PAGA LOS IMPUESTOS");
    System.out.println("Te topas con " + enemy.getNombre() + " tiene " + enemy.getAtaque() + " de poder");

        while (!combateTerminado) {

            System.out.println(enemy.getNombre() + ": " + enemy.getSaludBoss() + " de salud");
            System.out.println(player.getNombre() + ": " + player.getSalud() + " de salud");

            System.out.println("1. Atacar");
            System.out.println("2. Pagar impuestos");
            System.out.println("3. Tienda?¿");
            System.out.println("4. Mostrar estadisticas");

            opcion = sc.nextInt();

            switch (opcion) {

                case 1: 
                    enemy.setSaludBoss(enemy.getSaludBoss() - player.getAtaque());
                    System.out.println("Has infligido " + player.getAtaque() + " de daño a " + enemy.getNombre());

                    if (enemy.getSaludBoss() <= 0) {
                        System.out.println("Has derrotado a alexelcapo, fin de las autopistas");
                        combateTerminado = true;
                        jugadorHaMuerto = true;
                        break;
                    }

                    int daño = enemy.getAtaque() - player.getAtaque();
                    player.setSalud(player.getSalud() - daño);

                    if (daño < 0) {
                        System.out.println("Eres tan poderoso que la resta te sale en positivo");
                        System.out.println("Ganas " + (-daño) + " de salud");
                    } else {
                        System.out.println(enemy.getNombre() + " te ha infligido " + daño + " de daño");
                    }

                    if (player.getSalud() <= 0) {
                        System.out.println("x`x+`´x+´+23´+3´+´3´+2+++2+´x2+-_X%3");
                        System.out.println("´_.,.-..+`sX.HAS MUERT0 EN P3RU?.-.``.+.xX._");
                        System.out.println("x`x+`´x+´+23´+3´+´3´+2+++2+´x2+-_X%3");
                        jugadorHaMuerto = true;
                        combateTerminado = true;
                    }
                    break;

                case 2: 
                    boolean impuestosFin = false;
                    if(impuestosFin == false){                        
                        combateTerminado = gestionarImpuestos(player, enemy, sc);
                        if (combateTerminado){
                            jugadorHaMuerto = true;
                        }
                    }
                    else{
                        System.out.println(enemy.getAtaque() + ": Has perdido tu oportunidad de pagar los impustos");
                    }
                    
                    break;

                case 3:
                    tiendaBoss(player);
                    break;
                case 4:
                    mostrarEst(player);

                default:
                    System.out.println("Opción no válida");
            }
        }

        return jugadorHaMuerto;
    }
    
    private static boolean gestionarImpuestos(Player player, Enemy enemy, Scanner sc) {

        System.out.println(enemy.getNombre() + ": Los impuestos son 20 de oro, los tienes?");
        System.out.println("1. Pagar");
        System.out.println("2. No pagar");

        int opcion = sc.nextInt();

        if (opcion == 1) {

            if (player.getDinero() < 20) {
                System.out.println(enemy.getNombre() + ": Un momento... NO TIENES SUFICIENTE ORO COMO SE VAN A CONSTRUIR LAS AUTOPISTAS");
                System.out.println(enemy.getNombre() + " se enfada y su poder de ataque aumenta en 5");                
                enemy.setAtaque(enemy.getAtaque() + 5);
                return false;
            }

            player.setDinero(player.getDinero() - 20);
            System.out.println(enemy.getNombre() + ": Muy bien has pagado.");
            System.out.println(enemy.getNombre() + ": Perdon se me habia olvidado, faltan 2 de oro para las autopistas.");

            System.out.println("1. Pagar 2 de oro");
            System.out.println("2. No pagar");

            opcion = sc.nextInt();

            if (opcion == 1) {

                if (player.getDinero() < 2) {
                    System.out.println(enemy.getNombre() + ": Un momento... NO TIENES SUFICIENTE ORO COMO SE VAN A CONSTRUIR LAS AUTOPISTAS");
                    System.out.println(enemy.getNombre() + ": Bueno como ha sido mi error por no avisarte de antemano no me molesta demasiado");
                    System.out.println(enemy.getNombre() + " no se enfada demasiado y su poder de ataque aumenta en 2");
                    enemy.setAtaque(enemy.getAtaque() + 2);
                    return false;
                }

                player.setDinero(player.getDinero() - 2);
                System.out.println(enemy.getNombre() + ": Gracias por ayudar a construir las autopistas.");
                System.out.println("El mundo ha sido peruanizado.");
                System.out.println("Fin del juego.");
                return true; 
            } else {
                System.out.println(enemy.getNombre() + ": Bueno no pasa nada. Toma 1 de limosna.");
                System.out.println("Recibes 1 de oro");
                player.setDinero(player.getDinero() + 1);
                return false;
            }

        } else {
            System.out.println(enemy.getNombre() + ": Como que no, multa.");
            System.out.println("Pierdes la mitad de tu oro.");
            player.setDinero(player.getDinero() /2);           
            return false;
        }
    }
    
    private static void tiendaBoss(Player player){
        int idTienda = 0;
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
                    
        do{
            
            System.out.println("#########################################################");
            System.out.println("                        TIENDA              ");
            System.out.println("#########################################################");
            
            if(itemsTiendaBoss[0] == false){   
                idTienda++;
                System.out.println("1. Rosquilla [1 de oro] [Aumenta tu salud 3 puntos]");
            }
            else{System.out.println("1.##################### AGOTADO #########################");}
            if(itemsTiendaBoss[1] == false){   
                idTienda++;
                System.out.println("2. Grumo [3 de oro] [Aumenta tu salud 6 puntos]");
            }
            else{System.out.println("2.##################### AGOTADO #########################");}
            if(itemsTiendaBoss[2] == false){
                idTienda++;
                System.out.println("3. Paloma masiva  [5 de oro] [Aumenta tu salud 8 puntos]");
            }
            else{System.out.println("3.##################### AGOTADO #########################");}
            if(itemsTiendaBoss[3] == false){
                idTienda++;
                System.out.println("4. Flauta [8 de oro] [Aumenta tu salud 15 puntos]");
            }
            else{System.out.println("4.##################### AGOTADO #########################");}
            if(itemsTiendaBoss[4] == false){
                idTienda++;
                System.out.println("5. Bolsa de mondeas de oro [12 de oro] [Aumenta tu daño 3 puntos]");
            }
            else{System.out.println("4.##################### AGOTADO #########################");}
            
            System.out.println("0.SALIR");
            
            
            opcion = sc.nextInt();
            
            switch(opcion){
            
                case 1:
                    
                    if(itemsTiendaBoss[0] == false){
                        
                        if(player.getDinero() >= 1){
                           
                            player.setDinero(player.getDinero() - 1);
                            player.setSalud(player.getSalud() + 3);
                            itemsTiendaBoss[0] = true;
                        }
                        if( player.getDinero() < 1){
                            System.out.println("No tienes suficiente dinero");
                        }                     
                    }
                    else{System.out.println("EL PRODUCTO ESTA AGOTADO HE DICHO");}
                    break;
                
                case 2:
                    
                    if(itemsTiendaBoss[1] == false){
                        
                        if(player.getDinero() >= 3){
                           
                            player.setDinero(player.getDinero() - 3);
                            player.setSalud(player.getSalud() + 6);
                            itemsTiendaBoss[1] = true;
                        }
                        if( player.getDinero() < 1){
                            System.out.println("No tienes suficiente dinero");
                        }                     
                    }
                    else{System.out.println("EL PRODUCTO ESTA AGOTADO HE DICHO");}
                    break;
                case 3:
                    
                    if( itemsTiendaBoss[2] == false){
                        
                        if(player.getDinero() >= 5){
                           
                            player.setDinero(player.getDinero() - 5);
                            player.setSalud(player.getSalud() + 8);
                            itemsTiendaBoss[2] = true;
                        }
                        if( player.getDinero() < 5){
                            System.out.println("No tienes suficiente dinero");
                        }                     
                    }
                    else{System.out.println("EL PRODUCTO ESTA AGOTADO HE DICHO");}
                    break;
                
                case 4:
                    
                  if(itemsTiendaBoss[3] == false){
                        
                        if(player.getDinero() >= 8){
                           
                            player.setDinero(player.getDinero() - 8);
                            player.setSalud(player.getSalud() + 15);
                            itemsTiendaBoss[3] = true;
                        }
                        if( player.getDinero() < 8){
                            System.out.println("No tienes suficiente dinero");
                        }                     
                    }
                    else{System.out.println("EL PRODUCTO ESTA AGOTADO HE DICHO");}
                    break;
                case 5:
                    
                  if(itemsTiendaBoss[4] == false){
                        
                        if(player.getDinero() >= 12){
                           
                            player.setDinero(player.getDinero() - 12);
                            player.setAtaque(player.getAtaque()+ 3);
                            itemsTiendaBoss[4] = true;
                        }
                        if( player.getDinero() < 12){
                            System.out.println("No tienes suficiente dinero");
                        }                     
                    }
                    else{System.out.println("EL PRODUCTO ESTA AGOTADO HE DICHO");}
                    break;   
            }               
        }while(opcion != 0);
    }
    
    
}
