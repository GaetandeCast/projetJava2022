package utilisateur;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeMap;

import consocarbone.*;

public class Main {
    public static void main(String[] args) {
        run();
    }

    public static void run(){
        try{    
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int entree = -1;
            do{
                entree = -1;
                menu();
                entree = Integer.parseInt(reader.readLine());
                if(entree==0) break;
                else if (entree ==1) {
                    initialisationManuelle(reader);
                    if (!retourMenu(reader)) break;
                }
                else if (entree == 2) {
                    initialisationFichier(reader);
                    if(!retourMenu(reader)) break;
                }
                //else if (entree == 3) simulationPolitique(reader);
                else System.out.println("Veuillez saisir un numero correct.");
            }
            while(entree!=0);
            reader.close();
        }
        catch(IOException e) {
            System.out.println("Probleme lors de la lecture du choix.");
        }
        catch(Exception e) {e.printStackTrace();}
    }

    public static void menu(){
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Bienvenue dans le calculateur d'empreinte carbone.\n");
        System.out.println("Tapez 0 pour sortir du menu.");
        System.out.println("Tapez 1 pour entrer saisir vos informations manuellement.");
        System.out.println("Tapez 2 pour lire vos informations depuis un fichier texte.");
        System.out.println("Tapez 3 pour simuler des mesures politiques affectant l'empreinte carbone des français.");
        System.out.println("---------------------------------------------------------------------------------------");
    }

    public static boolean retourMenu(BufferedReader reader) throws IOException{
        System.out.println("Tapez 1 pour retourner au menu, tapez 0 pour quitter le programme");
        int entree = Integer.parseInt(reader.readLine());
        if(entree==0) return false;
        else if (entree==1) return true;
        else {
            System.out.println("Veuillez saisir 1 ou 0");
            retourMenu(reader);
        }
        return false;
    }

    public static void initialisationManuelle(BufferedReader reader){
        try{    
            System.out.println("Nous allons donc vous demander de saisir des informations afin de quantifier votre empreinte carbone et vous faire des recommendations adaptees.");
            
            
            ArrayList<Transport> vehicules = new ArrayList<Transport>();
            System.out.println("Combien de vehicules possedez vous ?");
            int nbVehicules = Integer.parseInt(reader.readLine());
            TreeMap<Integer,String> nomsVehicules = new TreeMap<Integer,String>();
            if (nbVehicules == 0){
                vehicules.add(new Transport());
            }
            else if (nbVehicules==1){
                ajoutVehicule(vehicules, reader, nomsVehicules);
            }
            else{
                int cpt=1;
                while(cpt<=nbVehicules){
                    System.out.println("Vehicule n°" + cpt);
                    ajoutVehicule(vehicules, reader, nomsVehicules);
                    cpt+=1;
                }
            }

            
            ArrayList<Logement> logements = new ArrayList<Logement>();
            System.out.println("Combien de logements possedez vous ?");
            int nbLogements = Integer.parseInt(reader.readLine());
            TreeMap<Integer,String> nomsLogements = new TreeMap<Integer,String>();
            if (nbLogements == 0){
                logements.add(new Logement());
            }
            else if (nbLogements==1){
                ajoutLogement(logements, reader, nomsLogements);
            }
            else{
                int cpt=1;
                while(cpt<=nbLogements){
                    System.out.println("Logement n°" + cpt);
                    ajoutLogement(logements, reader, nomsLogements);
                    cpt+=1;
                }
            }


            double txBoeuf = -1;
            double txVege = -1;
            do{    
                System.out.println("Quelle proportion de vos repas est a base de boeuf ? (en pourcents)");
                txBoeuf = Integer.parseInt(reader.readLine())/100;

                System.out.println("Quelle proportion de vos repas est vegetarienne ? (en pourcents)");
                txVege = Integer.parseInt(reader.readLine())/100;
                if(txBoeuf>1 | txBoeuf<0 | txVege>1 | txVege<0 | (txBoeuf+txVege)>1 | (txBoeuf+txVege)<0) {
                    System.out.println("Veuillez saisir des valeurs comprises entre 0 et 100, dont la somme est également entre 0 et 100.");
                }
            }
            while(txBoeuf>1 | txBoeuf<0 | txVege>1 | txVege<0 | (txBoeuf+txVege)>1 | (txBoeuf+txVege)<0);
            Alimentation alimentation = new Alimentation(txBoeuf,txVege);


            double depenses=0;
            System.out.println("Quelle montant d'argent dépensez vous annuellement dans des biens de consommation?");
            depenses = Integer.parseInt(reader.readLine());
            BienConso bienConso = new BienConso(depenses);

            Utilisateur utilisateur = new Utilisateur(alimentation, bienConso, logements, vehicules);
            System.out.println("---------------------------------------------------------------------------------------");
            utilisateur.detaillerEmpreinte();
            utilisateur.recommendations(nomsVehicules,nomsLogements);
            System.out.println("---------------------------------------------------------------------------------------");
        }
        catch(IOException e) {
            System.out.println("Probleme lors de la lecture d'une information.");
        }
        catch(Exception e) {e.printStackTrace();}
    }

    public static void ajoutVehicule(ArrayList<Transport> l, BufferedReader reader, TreeMap<Integer,String> nomsVehicules) throws IOException{
        System.out.println("Veuillez saisir un nom pour ce vehicule.");
        String nom = reader.readLine();

        int age = -1;
        do{
            System.out.println("Quel age, en annee, ce vehicule a-t-il ?");
            age = Integer.parseInt(reader.readLine());
            if(age<=0) System.out.println("l'age de votre vehicule doit etre strictement positif.");
        }
        while(age<=0);

        int kilom = -1;
        do{
            System.out.println("Quelle distance, en kilometres, parcourez vous en une annee avec ce vehicule ?");
            kilom = Integer.parseInt(reader.readLine());
            if(kilom<0) System.out.println("Le nombre de kilometre parcourut doit etre positif ou nul.");
        }
        while(kilom<0);

        int taille = -1;
        do{   
            System.out.println("Veuillez taper 1 si ce vehicule est de petite taille, 0 sinon.");
            taille = Integer.parseInt(reader.readLine()); 
            if (taille==1){
                Transport nouveau = new Transport(true, Taille.P, kilom, age);
                l.add(nouveau);
                nomsVehicules.put(nouveau.getID(),nom);
            }
            else if (taille==0){
                Transport nouveau = new Transport(true, Taille.G, kilom, age);
                l.add(nouveau);
                nomsVehicules.put(nouveau.getID(),nom);
            }
            else{
                System.out.println("Votre entree n'est pas correcte");
            }
        }
        while(taille != 0 & taille != 1);
    }

    public static void ajoutLogement(ArrayList<Logement> l, BufferedReader reader, TreeMap<Integer,String> nomsLogements) throws IOException{
        System.out.println("Veuillez saisir un nom pour ce logement.");
        String nom = reader.readLine();
        System.out.println("Quelle est la superficie, en metre^2, de ce logement ?");
        int superficie = Integer.parseInt(reader.readLine());
        char classe = 'Z';
        do{   
            System.out.println("Veuillez saisir la classe energetique de votre logement, en majuscule.");
            String input = reader.readLine();
            classe = input.charAt(0);
            Logement nouveau;
            if (classe=='A'){nouveau  = new Logement(superficie, CE.A);}
            else if (classe=='B'){nouveau = new Logement(superficie, CE.B);}
            else if (classe=='C'){nouveau = new Logement(superficie, CE.C);}
            else if (classe=='D'){nouveau = new Logement(superficie, CE.D);}
            else if (classe=='E'){nouveau = new Logement(superficie, CE.E);}
            else if (classe=='F'){nouveau = new Logement(superficie, CE.F);}
            else if (classe=='G'){nouveau = new Logement(superficie, CE.G);}
            else {
                System.out.println("Votre entree n'est pas correcte");
                continue;
            }
            l.add(nouveau);
            nomsLogements.put(nouveau.getID(),nom);
        }
        while(classe!='A'&classe!='B'&classe!='C'&classe!='D'&classe!='E'&classe!='F'&classe!='G');
    }

    public static void initialisationFichier(BufferedReader reader) throws ErrFormatFichier{
        System.out.println("Veuillez saisir le nom du fichier contenant les informations necessaires au calcul de l'empreinte carbone. Veuillez vous assurer qu'il respecte le format indique dans le fichier README.");
        try{
            String nomFichier = reader.readLine();         
            try{
                BufferedReader readerF = new BufferedReader(new FileReader(nomFichier));

                int nbVehicules = Integer.parseInt(readerF.readLine());
                ArrayList<Transport> vehicules = new ArrayList<Transport>();
                TreeMap<Integer,String> nomsVehicules = new TreeMap<Integer,String>();
                if (nbVehicules == 0){
                    vehicules.add(new Transport());
                }
                else if (nbVehicules==1){
                    String[] infoVehicule = readerF.readLine().split(", ");
                    ajoutVehiculeFichier(vehicules, nomsVehicules, infoVehicule);
                    }
                else if (nbVehicules>1){
                    int cpt=1;
                    while(cpt<=nbVehicules){
                        String[] infoVehicule = readerF.readLine().split(", ");
                        ajoutVehiculeFichier(vehicules, nomsVehicules, infoVehicule);
                        cpt+=1;
                    }
                }
                else{
                    readerF.close();
                    throw new ErrFormatFichier("Le nombre de vehicule incrit n'est pas positif");
                }


                int nbLogements = Integer.parseInt(readerF.readLine());
                ArrayList<Logement> logements = new ArrayList<Logement>();
                TreeMap<Integer,String> nomsLogements = new TreeMap<Integer,String>();
                if (nbLogements == 0){
                    logements.add(new Logement());
                }
                else if (nbLogements==1){
                    String[] infoLogement = readerF.readLine().split(", ");
                    ajoutLogementFichier(logements, nomsLogements, infoLogement);
                }
                else if (nbLogements>1){
                    int cpt=1;
                    while(cpt<=nbLogements){
                        String[] infoLogement = readerF.readLine().split(", ");
                        ajoutLogementFichier(logements, nomsLogements, infoLogement);
                        cpt+=1;
                    }
                }
                else{
                    readerF.close();
                    throw new ErrFormatFichier("Le nombre de logements incrit n'est pas positif");
                }


                String[] ligne = readerF.readLine().split(", ");
                double txBoeuf = Integer.parseInt(ligne[0])/100;
                double txVege = Integer.parseInt(ligne[1])/100;
                if(txBoeuf>1 | txBoeuf<0 | txVege>1 | txVege<0 | (txBoeuf+txVege)>1 | (txBoeuf+txVege)<0){
                    readerF.close();
                    throw new ErrFormatFichier("Les taux de repas ou leur somme ne sont pas entre 0 et 100 inclus.");
                }
                
                Alimentation alimentation = new Alimentation(txBoeuf,txVege);
    
    
                double depenses = Integer.parseInt(readerF.readLine());
                if(depenses<0){
                    readerF.close();
                    throw new ErrFormatFichier("Le montant de depenses en biens de consommation n'est pas positif.");
                }
                BienConso bienConso = new BienConso(depenses);
    
                Utilisateur utilisateur = new Utilisateur(alimentation, bienConso, logements, vehicules);
                System.out.println("---------------------------------------------------------------------------------------");
                utilisateur.detaillerEmpreinte();
                utilisateur.recommendations(nomsVehicules,nomsLogements);
                System.out.println("---------------------------------------------------------------------------------------");
            
                readerF.close();
            }
            catch(IOException e){
                System.out.println("Probleme lors de la lecture dans le fichier "+nomFichier);
                e.printStackTrace();
            }
        }
        catch(IOException e) {
            System.out.println("Probleme lors de la lecture du nom du fichier.");
            e.printStackTrace();
        }
        catch(Exception e) {e.printStackTrace();}
    }

    public static void ajoutVehiculeFichier(ArrayList<Transport> vehicules, TreeMap<Integer,String> nomsVehicules, String[] infoVehicule) throws ErrFormatFichier{
        String nom = infoVehicule[0];

        int age = Integer.parseInt(infoVehicule[1]);
        if(age<=0){
            throw new ErrFormatFichier("L'age du vehicule doit etre strictement positif.");
        }

        int kilom = Integer.parseInt(infoVehicule[2]);
        if(kilom<0){
            throw new ErrFormatFichier("Le nombre de kilometres parcouruts par le vehicule doit etre positif ou nul.");
        }

        String taille = infoVehicule[3];                  
        if (taille.equals("petit")){
            Transport nouveau = new Transport(true, Taille.P, kilom, age);
            vehicules.add(nouveau);
            nomsVehicules.put(nouveau.getID(),nom);
        }
        else if (taille.equals("grand")){
            Transport nouveau = new Transport(true, Taille.G, kilom, age);
            vehicules.add(nouveau);
            nomsVehicules.put(nouveau.getID(),nom);
        }
        else {
            throw new ErrFormatFichier("La syntaxe de l'ecriture de la taille du vehicule n'est pas correcte.");
        }
    }

    public static void ajoutLogementFichier(ArrayList<Logement> logements, TreeMap<Integer,String> nomsLogements, String[] infoLogement) throws ErrFormatFichier{
        String nom = infoLogement[0];

        int superficie = Integer.parseInt(infoLogement[1]);
        if(superficie<=0){
            throw new ErrFormatFichier("La superficie du logement doit etre strictement positif.");
        }

        char classe = infoLogement[2].charAt(0);
        Logement nouveau;
        if (classe=='A'){nouveau  = new Logement(superficie, CE.A);}
        else if (classe=='B'){nouveau = new Logement(superficie, CE.B);}
        else if (classe=='C'){nouveau = new Logement(superficie, CE.C);}
        else if (classe=='D'){nouveau = new Logement(superficie, CE.D);}
        else if (classe=='E'){nouveau = new Logement(superficie, CE.E);}
        else if (classe=='F'){nouveau = new Logement(superficie, CE.F);}
        else if (classe=='G'){nouveau = new Logement(superficie, CE.G);}
        else {
            throw new ErrFormatFichier("La syntaxe de l'ecriture de la classe energetique du logement n'est pas correcte.");
        }
        logements.add(nouveau);

        nomsLogements.put(nouveau.getID(),nom);        
    }
}
