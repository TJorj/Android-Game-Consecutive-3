package com.example.tgeorge.temajoc;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by TGeorge (TODOSI GEORGE VASILE GRUPA 3131B AN 3 CALCULATOARE) on 04-Jan-18.
 * Clasa ce reprezinta modelul matricei 4x4
 */
public class MainActivity extends AppCompatActivity {
    GameModel Game;     //instana a clasei care reprezinta modelul jocului
    Button buttons[]; //vector in care salvam toate butoanele din view pt a le putea actualiza cand se fac schimbari in matricea interna
    TextView txt, txt2; //labeluri ce vor fi updatate cu starea jocului

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Game = new GameModel();
        buttons = new Button[17];
        for(int i = 0; i < 17; i++)
        {
            //preluam fiecare buton din layout dupa ID si il punem in vector
            int id = getResources().getIdentifier("button"+i,"id",getPackageName());
            buttons[i]= (Button) findViewById(id);
        }
        for (int i = 1; i < 17; i++)
            buttons[i].setBackgroundResource(android.R.drawable.btn_default);
        txt = findViewById(R.id.textView);
        txt2 = findViewById(R.id.textView2);
    }

    public void onButtonClick(View view) {
        {
            //metoda handler pentru evenimentul de apasare a unui buton
            String user = "u";
            String pc = "m";
            Button b;
            String name = view.getTag().toString(); //preluam numele butonului apasat

           for (int i = 0 ; i < 4; i++)
               for(int j = 0; j < 4; j++)
            {
                if (Game.tablajoc[i][j].GetTag().equals(name)) //cautam campul asociat butonului in matrice
                {
                   if(!Game.tablajoc[i][j].IsOwned())   //daca campul este liber il asociem utilizatorului si actualizam grafic butonul
                   {
                          b = (Button) view;
                          Game.tablajoc[i][j].SetOwner(user);
                          Game.tablajoc[i][j].SetColor(Color.YELLOW);
                          b.setText(Game.tablajoc[i][j].GetOwner());
                          b.setBackgroundColor(Game.tablajoc[i][j].GetColor());
                          if(Game.CheckVictoryForInput(user)) //daca utilizatorul a castigat updatam labelurile, incrementam scorul si resetam jocul si butoanele
                           {
                               txt.setText("Victorie Utilizator");
                               Game.IncreaseUserScore();
                               txt2.setText("Scor U-M\n" +Game.GetPlayerScore() +" - " + Game.GetMobileScore());
                               Clear();
                           }
                           else if (Game.isRemiza()==false) //daca utilizatorul nu a castigat si nu este remiza facem mutarea calculatorului si actualizam grafic butoanele
                           {
                               Game.DoPCTurn();
                               UpdateViews();
                               if (Game.CheckVictoryForInput(pc))  //daca calculatorul a castigat actualizam labelurile, incrementam scorul si resetam jocul si butoanele
                               {
                                   txt.setText("Victorie Calculator");
                                   Game.IncreaseMobileScore();
                                   txt2.setText("Scor U-M\n" +Game.GetPlayerScore() +" - " + Game.GetMobileScore());
                                   Clear();
                               }
                           }
                           else //daca este remiza actualizam labelurile si resetam butonul si jocul
                          {
                              txt.setText("Remiza");
                              Clear();
                          }



                   }
                }
            }
        }
    }
    private  void Clear()
    {
        //metoda ce reseteaza matricea interna a jocului si butoanele
        Game.Clear();
        for (int i = 1; i < 17; i++)
        {
            buttons[i].setBackgroundResource(android.R.drawable.btn_default);
            buttons[i].setText("");
        }

    }

    private void UpdateViews()
    {
        //metoda ce updateaza grafic butoanele pentru a corespunde starii interne din matrice dupa mutarea calculatorului
        for(int i = 0 ; i < 4; i++)
            for(int j = 0; j < 4; j++) {

             if(Game.tablajoc[i][j].GetOwner().equals("m"))
               {
                 buttons[Game.tablajoc[i][j].GetId()].setText(Game.tablajoc[i][j].GetOwner());
                 buttons[Game.tablajoc[i][j].GetId()].setBackgroundColor(Game.tablajoc[i][j].GetColor());
               }
            }

    }

    public void onRenuntClick(View view) {
        //metoda apelata la apasarea butonului renunt ce are acelasi efect ca pierderea jocului
        txt.setText("Victorie Calculator");
        Game.IncreaseMobileScore();
        txt2.setText("Scor U-M\n" +Game.GetPlayerScore() +" - " + Game.GetMobileScore());
        Clear();
    }
}
