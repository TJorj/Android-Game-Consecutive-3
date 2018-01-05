package com.example.tgeorge.temajoc;

/**
 * Created by TGeorge (TODOSI GEORGE VASILE GRUPA 3131B AN 3 CALCULATOARE) on 04-Jan-18.
 * Clasa ce reprezinta modelul matricei 4x4
 */

public class Field {

    private int culoare ;  //specifica ce culoare trebuie sa aibe butonul aosciat campului
    private  String owner;  //specifica daca un camp este liber sau ocupat de utlizator sau mobil
    private  String tag;    //numele campului pentru asocierea cu un buton
    private  int id;        //id utilizat pentru accesarea usoara a butonul asociat din vectorul de butoane
    private static int i=0; //contor folosit pentru numerotarea automata a tagurilor si id-urilor in construcotr

    public Field(int _culoare, String _owner)
    {
        //constructor
        culoare = _culoare;
        owner = _owner;
        tag = String.valueOf(i+1);
        i++;
        id=i;
    }
    //getter si settere
    public int GetColor()
    {
        return culoare;
    }
    public  String GetOwner()
    {
        return  owner;
    }
    public  String GetTag()
    {
        return  tag;
    }
    public  int GetId()
    {
        return id;
    }
    public void SetColor(int _culoare)
    {
        culoare = _culoare;
    }
    public  void SetOwner(String _owner)
    {
        owner = _owner;
    }

    public boolean IsOwned() {
        //metoda ce returneaza fals daca un camp este liber sau adevarat daca este ocupat
        if(owner.equals(""))
            return false;
        else
            return true;
    }
}
