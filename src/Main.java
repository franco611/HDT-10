import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String[]args){

        ArrayList ciudades = new ArrayList();
        int contador=0;

        try{
            File file = new File("guategrafo.txt");
            Scanner input = new Scanner (file);
            while(input.hasNextLine()){
                String dato = input.nextLine();
                String[] partes = dato.split(" ");
                if(ciudades.contains(partes[0])){

                }else{
                    ciudades.add(partes[0]);
                }
                if(ciudades.contains(partes[1])){

                }else{
                    ciudades.add(partes[1]);
                }
                contador=contador+1;

            }

        }catch (Exception e) {
            e.printStackTrace();
        }

        Grafo grafito = new Grafo(contador,contador);
        try{
            File file = new File("guategrafo.txt");
            Scanner input = new Scanner (file);
            while(input.hasNextLine()){
                String dato = input.nextLine();
                String[] partes = dato.split(" ");
                grafito.insertaArista(ciudades.indexOf(partes[0]),ciudades.indexOf(partes[1]),Integer.parseInt(partes[2]));
            }

        }catch (Exception e) {
            e.printStackTrace();
        }


        grafito.impMatrix();

        int[][] origen = grafito.getMAT();
        int[][] copia = new int[origen.length][origen.length];

        for(int i=0; i<origen.length; i++){
            for(int x=0; x<origen.length; x++){
                copia[i][x] = origen[i][x];
            }
        }

        Floyd floicito = new Floyd();
        floicito.floyd(copia);
        System.out.println(Arrays.deepToString(origen));


        boolean bandera = true;
        while (bandera){

            int res = 0;
            Scanner s = new Scanner(System.in);
            System.out.println(" ");
            System.out.println("--------");
            System.out.println("Seleccione la opcion que desee realizar: ");
            System.out.println("[ 1 ] Calcular ruta.");
            System.out.println("[ 2 ] Mostrar el centro del grafo.");
            System.out.println("[ 3 ] Modificar el grafo.");
            System.out.println("[ 4 ] Salir.");

            while(true){
                try{
                    res = s.nextInt();

                    if(res>=1 && res<=4){
                        break;
                    }else{
                        System.out.println("Ingrese valores entre 1 y 4 !");
                    }
                }catch(Exception e){
                    System.out.println("Ingrese valores numericos ! ! !");
                }
            }

            if(res==1){
                String ciudad1="";
                String ciudad2="";
                s.nextLine();
                System.out.println("Ingrese el nombre de la primera ciudad");
                ciudad1 = s.nextLine();
                System.out.println("Ingrese el nombre de la segunda ciudad");
                ciudad2 = s.nextLine();

                if(ciudades.contains(ciudad1)){
                    if(ciudades.contains(ciudad2)){
                        if(copia[ciudades.indexOf(ciudad1)][ciudades.indexOf(ciudad2)]!=1000000000){

                            System.out.println("Para realizar este viaje se tardaria un tiempo de: "+copia[ciudades.indexOf(ciudad1)][ciudades.indexOf(ciudad2)]);
                            floicito.ruta(ciudades.indexOf(ciudad1),ciudades.indexOf(ciudad2),ciudades);

                        }else{
                            System.out.println("No existe una ruta para ello.");
                        }

                    }else{
                        System.out.println("La segunda ciudad no existe.");
                    }
                }else{
                    System.out.println("La primera ciudad no existe.");
                }

            }else if(res==2){
                floicito.calcen(copia,ciudades);

            }else if(res==3){
                String ciudad1="";
                String ciudad2="";
                int valor=0;
                s.nextLine();
                System.out.println("Ingrese el nombre de la primera ciudad");
                ciudad1 = s.nextLine();
                System.out.println("Ingrese el nombre de la segunda ciudad");
                ciudad2 = s.nextLine();
                System.out.println("Ingrese la distancia entre las ciudades.");
                valor = s.nextInt();

                if(ciudades.contains(ciudad1)){
                    if(ciudades.contains(ciudad2)){
                        origen[ciudades.indexOf(ciudad1)][ciudades.indexOf(ciudad2)] = valor;
                        for(int i=0; i<origen.length; i++){
                            for(int x=0; x<origen.length; x++){
                                copia[i][x] = origen[i][x];
                            }
                        }
                        floicito.floyd(copia);
                        System.out.println(Arrays.deepToString(copia));




                    }else{
                        System.out.println("La segunda ciudad no existe.");
                    }
                }else{
                    System.out.println("La primera ciudad no existe.");
                }


            }else if(res==4){
                bandera = false;
                System.out.println("Gracias por utilizar nuestros servicios");

            }



        }

    }
}
