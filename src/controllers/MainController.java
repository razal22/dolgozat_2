/*
* File: MainController.java
* Author: Nagy József
* Refactor: Plesovszki István
* Copyright: 2021, Nagy József 
* Date: 2021-09-11
* Licenc: MIT
*
*/
package controllers;

import java.util.Random;
import views.MainWindow;

public class MainController {

    enum Round  
    {
        PREFLOP,
        FLOP,
        TURN,
        RIVER,
        SHOW
    }

    MainWindow mainWindow;
    String[] cards = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "B", "D", "K", "A"};
    Round round = Round.PREFLOP; 
    
    public MainController(MainWindow mainWindow) 
    {
        this.mainWindow = mainWindow;
        this.initEvent();
    }

    private int getRandom() 
    {
        Random random = new Random();
        return random.nextInt(13);
    }

    private void showFlop()
    {
        int flop1=getRandom();
        int flop2=getRandom();
        int flop3=getRandom();

        String flop1Str=cards[flop1];
        String flop2Str=cards[flop2];
        String flop3Str=cards[flop3];
                    
        this.mainWindow.flop1Btn.setText("♦" + flop1Str);
        this.mainWindow.flop2Btn.setText(flop2Str);
        this.mainWindow.flop3Btn.setText(flop3Str);
        this.mainWindow.flop1Btn.setVisible(true);
        this.mainWindow.flop2Btn.setVisible(true);
        this.mainWindow.flop3Btn.setVisible(true);
        this.round = Round.FLOP;
    }

    private void showTurn()
    {
        int turn = getRandom();
        this.mainWindow.turnButton.setText(cards[turn]);
        this.mainWindow.turnButton.setVisible(true);
        this.round=Round.TURN;
    }
    
    private void showRiver()
    {
        int river=getRandom();
        this.mainWindow.riverButton.setText(cards[river]);
        this.mainWindow.riverButton.setVisible(true);
        this.round = Round.RIVER;
    }

    public void initEvent() 
    {
        this.mainWindow.startBtn.addActionListener(
            event -> {
                Random random = new Random();
                int humanCard1 = random.nextInt(13);
                int humanCard2 = random.nextInt(13);

                random = null;
                String humanCard1Str = cards[humanCard1];
                String humanCard2Str = cards[humanCard2];
                this.mainWindow.humanCard1Btn.setText(humanCard1Str);
                this.mainWindow.humanCard2Btn.setText(humanCard2Str);


                System.out.printf("%d %d\n", humanCard1, humanCard2);

            });
        this.mainWindow.stopBtn.addActionListener (
            event -> {                
                System.out.println("Állj");
            });

        this.mainWindow.nextBtn.addActionListener(
            event -> {

                if (this.round == Round.PREFLOP) {
                  
                    showFlop();
                    
                }
                else if (this.round == Round.FLOP) {

                    showTurn();
                }
                else if (this.round == Round.TURN) {
                    
                    showRiver();

                } 
            }
        );

    }

}
