package com.example.tgeorge.temajoc;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by TGeorge (TODOSI GEORGE VASILE GRUPA 3131B AN 3 CALCULATOARE) on 04-Jan-18.
 * Clasa ce reprezinta modelul matricei 4x4
 */


public class GameModel {

    Field tablajoc[][] = new Field[4][4]; //matrice 4x4 de tip Field
    private   int scor_u = 0; //camp ce memoreaza numarul de victorii ale utilizatorului
    private   int scor_m = 0; //camp ce memoreaza numarul de victorii ale calculatorului
    public GameModel()
    {
        //constructor ce initialieaza fiecare camp ca fiind gol si de culoare gri
        for(int i = 0 ; i < 4; i++)
            for(int j = 0; j < 4; j++)
                tablajoc[i][j] = new Field(Color.GRAY,"");

    }
    //getter si settere
    public  int GetPlayerScore()
    {
        return  scor_u;
    }
    public  int GetMobileScore()
    {
        return  scor_m;
    }
    public void IncreaseUserScore()
    {
        scor_u++;
    }
    public  void IncreaseMobileScore()
    {
        scor_m++;
    }
    public  void Clear()
    {
        //metoda ce reseteaza scorul si matricea pentru a incepe un joc nou
        int scor_u = 0;
        int scor_m = 0;
        for(int i = 0 ; i < 4; i++)
            for(int j = 0; j < 4; j++) {
                tablajoc[i][j].SetColor(Color.GRAY);
                tablajoc[i][j].SetOwner("");
            }

    }
    public void DoPCTurn()
    {
        //metoda ce efectueaza mutarea calculatorului
            int i,j;
            Random rnd = new Random();

            //se cauta campuri aleatoriu pana cand se gaseste unul gol
            do {
                 i = rnd.nextInt(4);
                 j = rnd.nextInt(4);
            }
            while(!tablajoc[i][j].GetOwner().equals(""));

            //campului gasit este setat ca apartinand calculatorului
            tablajoc[i][j].SetOwner("m");
            tablajoc[i][j].SetColor(Color.GREEN);

    }

    public boolean CheckVictoryForInput(String input)
    {
        //metoda ce verifica daca jocul a fost castigat
        //parametrul input specifica a cui victorie sa se verifice

        //verificarea se face cautand campuri consecutive ce apartin aceluiasi utilizator
        //variabila consecutiv este incrementata de fiecare data cand sunt gasite campuri consecutive
        //daca variabila atinge valoarea 3 functia returneaza true
        //daca se gaseste un camp care nu este consecutiv si variabila nu a atins valoarea 3 ea este resetata la 0
        //daca nu se iese din functie cu true pana la terminarea parcurgerilor functia returneaza false

        //verificare victorie pe linie
        int consecutive = 0;
        for(int i = 0 ; i < 4; i++) {
            consecutive = 0;
            for (int j = 0; j < 4; j++) {
                if (tablajoc[i][j].GetOwner().equals(input)) {
                    consecutive++;
                    if (consecutive == 3)
                        return true;
                } else
                    consecutive = 0;
            }
        }
        //verificare victorie pe coloana
        consecutive = 0;
        for(int j = 0 ; j < 4; j++) {
            consecutive = 0;
            for (int i = 0; i < 4; i++) {
                if (tablajoc[i][j].GetOwner().equals(input)) {
                    consecutive++;
                    if (consecutive == 3)
                        return true;
                } else
                    consecutive = 0;
            }
        }
        //verificare victorie pe diagonala principala
        consecutive = 0;
        for (int i = 0; i < 4; i++)
           if (tablajoc[i][i].GetOwner().equals(input)) {
                consecutive++;
                if (consecutive == 3)
                    return true;
           } else
                consecutive = 0;

        //verificare victorie pe diagonala secundara
        consecutive = 0;
        for (int i = 0; i < 4; i++)
            if (tablajoc[i][3-i].GetOwner().equals(input)) {
                consecutive++;
                if (consecutive == 3)
                    return true;
            } else
                consecutive = 0;

        //verificare victorie diagonalele mici principale
        consecutive = 0;
        for (int i = 0; i < 3; i++)
                if (tablajoc[i][i+1].GetOwner().equals(input)) {
                    consecutive++;
                    if (consecutive == 3)
                        return true;
                } else
                    consecutive = 0;
        consecutive = 0;
        for (int i = 1; i < 4; i++)
                if (tablajoc[i][i-1].GetOwner().equals(input)) {
                    consecutive++;
                    if (consecutive == 3)
                        return true;
                } else
                    consecutive = 0;
        //verificare victorie pe diagonalele mici secundare
        consecutive = 0;
        for(int i = 1; i < 4; i++)
            if (tablajoc[i][3-i+1].GetOwner().equals(input)) {
                consecutive++;
                if (consecutive == 3)
                    return true;
            } else
                consecutive = 0;
        consecutive = 0;
        for(int i = 0; i < 3; i++)
            if (tablajoc[i][3-i-1].GetOwner().equals(input)) {
                consecutive++;
                if (consecutive == 3)
                    return true;
            } else
                consecutive = 0;
        return  false;

    }


    public boolean isRemiza()
    {
        //metoda care verifica daca jocul este o remiza

        //se cauta un camp gol iar daca este gasit se retunreaza false
        //daca nu se gasesc campuri goale functia returneaza true
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                if(tablajoc[i][j].GetOwner().equals(""))
                    return false;

        return  true;
    }
}
