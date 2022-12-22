package utilisateur;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeMap;

import consocarbone.*;

public class IO {

    /**
     * Gère les interactions globales avec l'utilisateur, depuis le terminal
     * 
     * @exception NumberFormatException
     * @exception IOException
    */
    public static void run(){
        try{    
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            int entree = -1;
            //En fonction du choix de l'utilisateur, on fait apelle aux fonctions adaptees, tant que l'utilisateur ne tape pas 0
            while(entree!=0){
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
                else System.out.println("Veuillez saisir un numero correct.");
            }
            reader.close();
        }
        catch(IOException e) {
            System.out.println("Probleme lors de la lecture du choix.");
        }
        catch(NumberFormatException e){
            System.out.println("Veuillez saisir un nombre");
        }
        catch(Exception e) {e.printStackTrace();}
    }


    //Affiche les choix proposes par le programme
    public static void menu(){
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("Bienvenue dans le calculateur d'empreinte carbone.\n");
        System.out.println("Tapez 0 pour sortir du menu.");
        System.out.println("Tapez 1 pour entrer saisir vos informations manuellement.");
        System.out.println("Tapez 2 pour lire vos informations depuis un fichier texte.");
        System.out.println("---------------------------------------------------------------------------------------");
    }


    //Propose a l'utilisateur de retourner au menu ou non, après une action
    public static boolean retourMenu(BufferedReader reader) throws IOException{
        try{    
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
        catch(NumberFormatException e){
            System.out.println("Veuillez saisir un nombre");
        }
        return false;
    }


    //Initialise un utilisateur a partir des entree de l'utilisateur dans le terminal
    public static void initialisationManuelle(BufferedReader reader){
        try{    
            System.out.println("Nous allons donc vous demander de saisir des informations afin de quantifier votre empreinte carbone et vous faire des recommendations adaptees.");
            
            
            //Lecture du nombre de vehicules et des informations necessaire a leur initialisation
            //Enregistre les vehicules dans une liste, leur nom dans une map indicee par l'attribut "ID"
            //On demande a l'utilisateur de nommer les vehicules
            //Permet de referencer chaque vehicule par un nom explicite pour l'utilisateur au moment des recommendations
            //On appelle ajoutVehicule pour gerer l'ajout du vehicule a la liste, autant de fois qu'on a de vehicule
            ArrayList<Transport> vehicules = new ArrayList<Transport>();
            TreeMap<Integer,String> nomsVehicules = new TreeMap<Integer,String>();
            int nbVehicules = -1;
            
            while(nbVehicules<0){    
                System.out.println("Combien de vehicules possedez vous ?");
                nbVehicules = Integer.parseInt(reader.readLine());
                if (nbVehicules == 0){
                    vehicules.add(new Transport());
                }
                else if (nbVehicules==1){
                    ajoutVehicule(vehicules, reader, nomsVehicules);
                }
                else if (nbVehicules>1){
                    int cpt=1;
                    while(cpt<=nbVehicules){
                        System.out.println("Vehicule n°" + cpt);
                        ajoutVehicule(vehicules, reader, nomsVehicules);
                        cpt+=1;
                    }
                }
                else {
                    System.out.println("Veuillez saisir un entier positif");
                }
            }


            //Lecture du nombre de logements et des informations necessaire a leur initialisation
            //Enregistre les logements dans une liste, leur nom dans une map indicee par l'attribut "ID" 
            //On demande a l'utilisateur de nommer les logements
            //Permet de referencer chaque logement par un nom explicite pour l'utilisateur au moment des recommendations
            //On appelle ajoutLogement pour gerer l'initialisation et l'ajout du/des logement(s) a la liste
            ArrayList<Logement> logements = new ArrayList<Logement>();
            TreeMap<Integer,String> nomsLogements = new TreeMap<Integer,String>();
            int nbLogements = -1;            
            while(nbLogements<0){    
                System.out.println("Combien de logements possedez vous ?");
                nbLogements = Integer.parseInt(reader.readLine());
                if (nbLogements == 0){
                    logements.add(new Logement());
                }
                else if (nbLogements==1){
                    ajoutLogement(logements, reader, nomsLogements);
                }
                else if (nbLogements > 1){
                    int cpt=1;
                    while(cpt<=nbLogements){
                        System.out.println("Logement n°" + cpt);
                        ajoutLogement(logements, reader, nomsLogements);
                        cpt+=1;
                    }
                }
                else{
                    System.out.println("Veuillez saisir un entier positif");
                }
            }


            //Lecture du nombre de trajet en avion pour chaque longueur de courrier
            //puis initialisation d'un Avion et ajout a la liste pour chaque longueur dont le nombre de trajet est postive
            ArrayList<Avion> trajetsAvion = new ArrayList<Avion>();
            int nbTrajets = -1;
            while(nbTrajets<0){    
                System.out.println("Combien de trajets long courrier en avion faites vous par an ?");
                nbTrajets = Integer.parseInt(reader.readLine());
                if (nbTrajets>0){
                    trajetsAvion.add(new Avion(nbTrajets,Longueur.Long));
                }
                else if (nbTrajets<0){
                    System.out.println("Veuillez saisir un entier positif ou nul");
                }
            }

            nbTrajets = -1;
            while(nbTrajets<0){    
                System.out.println("Combien de trajets moyen courrier en avion faites vous par an ?");
                nbTrajets = Integer.parseInt(reader.readLine());
                if (nbTrajets>0){
                    trajetsAvion.add(new Avion(nbTrajets,Longueur.Moyen));
                }
                else if (nbTrajets<0){
                    System.out.println("Veuillez saisir un entier positif ou nul");
                }
            }
            
            nbTrajets = -1;
            while(nbTrajets<0){    
                System.out.println("Combien de trajets petit courrier en avion faites vous par an ?");
                nbTrajets = Integer.parseInt(reader.readLine());
                if (nbTrajets>0){
                    trajetsAvion.add(new Avion(nbTrajets,Longueur.Petit));
                }
                else if (nbTrajets<0){
                    System.out.println("Veuillez saisir un entier positif ou nul");
                }
            }


            //Lecture des taux de repas a base de boeuf et vegetariens et initialisation d'une instance de la classe Alimentation
            double txBoeuf = -1;
            double txVege = -1;
            while((txBoeuf+txVege)>1 | (txBoeuf+txVege)<0){
                while(txBoeuf>1 | txBoeuf<0){
                    System.out.println("Quelle proportion de vos repas est a base de boeuf ? (en pourcents)");
                    txBoeuf = Double.parseDouble(reader.readLine())/100;
                    if(txBoeuf>1 | txBoeuf<0) System.out.println("Veuillez saisir une valeur entre 0 et 100 inclus");
                }                

                while(txVege>1 | txVege<0){    
                    System.out.println("Quelle proportion de vos repas est vegetarienne ? (en pourcents)");
                    txVege = Double.parseDouble(reader.readLine())/100;
                    if(txVege>1 | txVege<0) System.out.println("Veuillez saisir une valeur entre 0 et 100 inclus");
                }                

                if((txBoeuf+txVege)>1 | (txBoeuf+txVege)<0){
                    System.out.println("Veuillez saisir des valeurs dont la somme est entre 0 et 100.");
                }
            }            
            Alimentation alimentation = new Alimentation(txBoeuf,txVege);

            //Lecture du montant annuel des depenses de consommations et initialisation d'une instance de la classe BienConso
            double depenses=-1;
            while(depenses<0){
                System.out.println("Quelle montant d'argent dépensez vous annuellement dans des biens de consommation?");
                depenses = Double.parseDouble(reader.readLine());
                if (depenses<0) System.out.println("Veuillez saisir une valeur positive ou nulle");
            }            
            BienConso bienConso = new BienConso(depenses);

            //Initialisation d'une instance de la classe Utilisateur
            //Appel aux fonctions de la classe Utilisateur pour faire un bilan a l'utilisateur de son empreinte carbone
            //et lui faire des recommendations basees sur ce bilan
            Utilisateur utilisateur = new Utilisateur(alimentation, bienConso, logements, vehicules, trajetsAvion);
            System.out.println("---------------------------------------------------------------------------------------");
            utilisateur.detaillerEmpreinte();
            System.out.println("---------------------------------------------------------------------------------------");
            utilisateur.recommendations(nomsVehicules,nomsLogements);
            System.out.println("---------------------------------------------------------------------------------------");
        }
        catch(IOException e) {
            System.out.println("Probleme lors de la lecture d'une information.");
        }
        catch(NumberFormatException e){
            System.out.println("Veuillez saisir un nombre quand demande");
        }
        catch(Exception e) {e.printStackTrace();}
    }


    //Lit l'age, de l'utilisation et de la taille d'un vehicule
    //Initialise une instance de la classe Transport, l'ajoute à la liste des vehicules, lui associe un nom dans la map nomsVehicules
    public static void ajoutVehicule(ArrayList<Transport> vehicules, BufferedReader reader, TreeMap<Integer,String> nomsVehicules) throws IOException, NumberFormatException{
        //Lecture du nom du vehicule
        System.out.println("Veuillez saisir un nom pour ce vehicule.");
        String nom = reader.readLine();

        //Lecture de l'age
        int age = -1;
        while(age<=0){
            System.out.println("Quel age, en annee, ce vehicule a-t-il ?");
            age = Integer.parseInt(reader.readLine());
            if(age<=0) System.out.println("vehicules'age de votre vehicule doit etre strictement positif.");
        }        

        //Lecture des kilometres parcourus
        int kilom = -1;
        while(kilom<0){
            System.out.println("Quelle distance, en kilometres, parcourez vous en une annee avec ce vehicule ?");
            kilom = Integer.parseInt(reader.readLine());
            if(kilom<0) System.out.println("Le nombre de kilometre parcouru doit etre positif ou nul.");
        }        

        //Lecture de la taille du vehicule puis initialisation de l'objet
        //Puis ajout a la liste des vehicules et du nom au repertoire des nom
        int taille = -1;
        while(taille != 0 & taille != 1){   
            System.out.println("Veuillez taper 1 si ce vehicule est de petite taille, 0 sinon.");
            taille = Integer.parseInt(reader.readLine()); 
            if (taille==1){
                Transport nouveau = new Transport(true, Taille.P, kilom, age);
                vehicules.add(nouveau);
                nomsVehicules.put(nouveau.getID(),nom);
            }
            else if (taille==0){
                Transport nouveau = new Transport(true, Taille.G, kilom, age);
                vehicules.add(nouveau);
                nomsVehicules.put(nouveau.getID(),nom);
            }
            else{
                System.out.println("Veuillez saisir 0 ou 1");
            }
        }        
    }


    //Lit la superficie et la classe energetique d'un logement
    //Initialise une instance de la classe Logement, l'ajoute à la liste des logements, lui associe un nom dans la map nomsLogements
    public static void ajoutLogement(ArrayList<Logement> logements, BufferedReader reader, TreeMap<Integer,String> nomsLogements) throws IOException, NumberFormatException{
        //Lecture du nom du logement
        System.out.println("Veuillez saisir un nom pour ce logement.");
        String nom = reader.readLine();

        //Lecture de la superficie du logement
        int superficie = -1;
        while(superficie<=0){
            System.out.println("Quelle est la superficie, en metre^2, de ce logement ?");
            superficie = Integer.parseInt(reader.readLine());
            if (superficie<=0) System.out.println("Veuillez saisir une valeur strictement positive");
        }        

        //Lecture de la classe energetique du logement puis initialisation de l'objet
        //Puis ajout a la liste des logements et du nom au repertoire des nom
        char classe = 'Z';
        while(classe!='A'&classe!='B'&classe!='C'&classe!='D'&classe!='E'&classe!='F'&classe!='G'){   
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
                System.out.println("Veuillez saisir une classe energetique entre A et G, en majuscule.");
                continue;
            }
            logements.add(nouveau);
            nomsLogements.put(nouveau.getID(),nom);
        }        
    }


    //Initialise un utilisateur a partir d'un fichier passe en entree dans le terminal
    public static void initialisationFichier(BufferedReader reader) throws ErrFormatFichier{
        System.out.println("Veuillez saisir le nom du fichier contenant les informations necessaires au calcul de l'empreinte carbone. Veuillez vous assurer qu'il respecte le format indique dans le fichier README.");
        try{
            String nomFichier = reader.readLine();         
            try{
                //Initialisation de la liste de vehicules, de la map de leurs noms et du lecteur de fichier
                ArrayList<Transport> vehicules = new ArrayList<Transport>();
                TreeMap<Integer,String> nomsVehicules = new TreeMap<Integer,String>();
                BufferedReader readerF = new BufferedReader(new FileReader(nomFichier));

                //Lecture du nombre de vehicules
                int nbVehicules = Integer.parseInt(readerF.readLine());
                //Lecture et isolation des informations liees au(x) vehicule(s), situees sur la meme ligne
                //puis appel(s) à ajoutVehiculeFichier pour initialiser les instances de Transport 
                //et les ajouter a la liste de vehicules et leurs noms à la liste des noms
                if (nbVehicules == 0){
                    vehicules.add(new Transport());
                }
                else if (nbVehicules==1){
                    String[] infoVehicule = readerF.readLine().split(", ");
                    ajoutVehiculeFichier(vehicules, nomsVehicules, infoVehicule);
                    if(infoVehicule.length!=4){
                        readerF.close();
                        throw new ErrFormatFichier("Le nombre de caracteristiques du vehicule n'est pas 4");
                    }
                }
                else if (nbVehicules>1){
                    int cpt=1;
                    while(cpt<=nbVehicules){
                        String[] infoVehicule = readerF.readLine().split(", ");
                        if(infoVehicule.length!=4){
                            readerF.close();
                            throw new ErrFormatFichier("Le nombre de caracteristiques du vehicule n°"+cpt+" n'est pas 4");
                        }
                        ajoutVehiculeFichier(vehicules, nomsVehicules, infoVehicule);
                        cpt+=1;
                    }
                }
                else{
                    readerF.close();
                    throw new ErrFormatFichier("Le nombre de vehicules incrit n'est pas positif");
                }


                //Initialisation de la liste de vehicules et de la map de leurs noms
                ArrayList<Logement> logements = new ArrayList<Logement>();
                TreeMap<Integer,String> nomsLogements = new TreeMap<Integer,String>();

                //Lecture du nombre de vehicules
                int nbLogements = Integer.parseInt(readerF.readLine());
                //Lecture et isolation des informations liees au(x) logement(s), situees sur la meme ligne
                //puis appel(s) à ajoutLogementFichier pour initialiser les instances de Logement 
                //et les ajouter a la liste de logements et leurs noms à la liste des noms
                if (nbLogements == 0){
                    logements.add(new Logement());
                }
                else if (nbLogements==1){
                    String[] infoLogement = readerF.readLine().split(", ");
                    if(infoLogement.length!=3){
                        readerF.close();
                        throw new ErrFormatFichier("Le nombre de caracteristiques du logement n'est pas 3");
                    }
                    ajoutLogementFichier(logements, nomsLogements, infoLogement);
                }
                else if (nbLogements>1){
                    int cpt=1;
                    while(cpt<=nbLogements){
                        String[] infoLogement = readerF.readLine().split(", ");
                        if(infoLogement.length!=3){
                            readerF.close();
                            throw new ErrFormatFichier("Le nombre de caracteristiques du logement n°"+cpt+" n'est pas 3");
                        }
                        ajoutLogementFichier(logements, nomsLogements, infoLogement);
                        cpt+=1;
                    }
                }
                else{
                    readerF.close();
                    throw new ErrFormatFichier("Le nombre de logements incrit n'est pas positif");
                }

                //Initialisation d'une liste d'Avion et lecture des nombres de trajets pour chaque longueur
                //puis initialisation d'un Avion et ajout a la liste pour chaque longueur dont le nombre de trajet est postive
                ArrayList<Avion> trajetsAvion = new ArrayList<Avion>();
                String[] nbTrajets = readerF.readLine().split(", ");
                if(nbTrajets.length!=3){
                    readerF.close();
                    throw new ErrFormatFichier("Le nombre de trajets sur la ligne concernant les trajets en avion n'est pas 3");
                }
                int trajetsLong = Integer.parseInt(nbTrajets[0]);
                int trajetsMoyen = Integer.parseInt(nbTrajets[1]);
                int trajetsPetit = Integer.parseInt(nbTrajets[2]);
                if(trajetsLong < 0 | trajetsMoyen < 0 | trajetsPetit < 0){
                    readerF.close();
                    throw new ErrFormatFichier("Un des nombre de trajet en avion n'est pas positif");
                }
                if (trajetsLong>0) trajetsAvion.add(new Avion(trajetsLong, Longueur.Long));
                if (trajetsMoyen>0) trajetsAvion.add(new Avion(trajetsMoyen, Longueur.Moyen));
                if (trajetsPetit>0) trajetsAvion.add(new Avion(trajetsPetit, Longueur.Petit));
                
                //Lecture des taux de repas a base de boeuf et vegetariens et initialisation d'un instance de la classe Alimentation
                String[] ligne = readerF.readLine().split(", ");
                if(ligne.length!=2){
                    readerF.close();
                    throw new ErrFormatFichier("Le nombre de taux sur la ligne concernant l'alimentation n'est pas 2");
                }
                double txBoeuf = Double.parseDouble(ligne[0])/100;
                double txVege = Double.parseDouble(ligne[1])/100;
                if(txBoeuf>1 | txBoeuf<0 | txVege>1 | txVege<0 | (txBoeuf+txVege)>1 | (txBoeuf+txVege)<0){
                    readerF.close();
                    throw new ErrFormatFichier("Les taux de repas ou leur somme ne sont pas entre 0 et 100 inclus.");
                }                
                Alimentation alimentation = new Alimentation(txBoeuf,txVege);
    
                //Lecture du moment de depenses en biens de consommation et initialisation d'une instance de la classe BienConso
                double depenses = Double.parseDouble(readerF.readLine());
                if(depenses<0){
                    readerF.close();
                    throw new ErrFormatFichier("Le montant de depenses en biens de consommation n'est pas positif.");
                }
                BienConso bienConso = new BienConso(depenses);
    
                //Initialisation d'une instance de la classe Utilisateur
                //Appel aux fonctions de la classe Utilisateur pour faire un bilan a l'utilisateur de son empreinte carbone
                //et lui faire des recommendations basees sur ce bilan
                Utilisateur utilisateur = new Utilisateur(alimentation, bienConso, logements, vehicules, trajetsAvion);
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
            catch(NumberFormatException e){
                System.out.println("Veuillez mettre un nombre quand necessaire");
            }
        }
        catch(IOException e) {
            System.out.println("Probleme lors de la lecture du nom du fichier.");
            e.printStackTrace();
        }
        catch(Exception e) {e.printStackTrace();}
    }


    //Recoit l'age, de l'utilisation et de la taille d'un vehicule dans un tableau de Strings
    //Initialise une instance de la classe Transport, l'ajoute à la liste des vehicules, lui associe un nom dans la map nomsVehicules
    public static void ajoutVehiculeFichier(ArrayList<Transport> vehicules, TreeMap<Integer,String> nomsVehicules, String[] infoVehicule) throws ErrFormatFichier,NumberFormatException{
        //Lecture du nom du vehicule
        String nom = infoVehicule[0];

        //Lecture de l'age du vehicule
        int age = Integer.parseInt(infoVehicule[1]);
        if(age<=0){
            throw new ErrFormatFichier("L'age du vehicule doit etre strictement positif.");
        }

        //Lecture des kilometres parcourus
        int kilom = Integer.parseInt(infoVehicule[2]);
        if(kilom<0){
            throw new ErrFormatFichier("Le nombre de kilometres parcourus par le vehicule doit etre positif ou nul.");
        }

        //Lecture de la taille du vehicule puis initialisation de l'objet
        //Puis ajout a la liste des vehicules et du nom au repertoire des nom
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


    //Recoit la superficie et la classe energetique d'un logement dans un tableau de Strings
    //Initialise une instance de la classe Logement, l'ajoute à la liste des logements, lui associe un nom dans la map nomsLogements
    public static void ajoutLogementFichier(ArrayList<Logement> logements, TreeMap<Integer,String> nomsLogements, String[] infoLogement) throws ErrFormatFichier,NumberFormatException{
        //Lecture du nom du logement
        String nom = infoLogement[0];

        //Lecture de la superficie du logement
        int superficie = Integer.parseInt(infoLogement[1]);
        if(superficie<=0){
            throw new ErrFormatFichier("La superficie du logement doit etre strictement positif.");
        }

        //Lecture de la classe energetique du logement puis initialisation de l'objet
        //Puis ajout a la liste des logements et du nom au repertoire des nom
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
