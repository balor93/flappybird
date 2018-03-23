package Clases;

import Interfaz.Game;

public class FlappyMovement extends Thread {

    public int deltaTime;
    public final Game parent;
    public double timeInit;
    public static boolean stopThread;
    public static final int v0=-30;
    public static final int ACELERACION=9;
    //private static final int TIME_FLAPPING=5;
    public int yInit=0;
  

    public FlappyMovement(Game parent) {
        this.deltaTime = 10;
        this.parent = parent;
    }

    @Override
    public void run() {
        timeInit=System.currentTimeMillis();
        stopThread = false;
        int x=Game.jFlappy.getLocation().x;
        yInit=Game.jFlappy.getLocation().y;
        while (true) {
            if (stopThread) {
                break;
                }
            
            double time=(System.currentTimeMillis()-timeInit)/100.0f;
            int y = (int) (yInit+v0*time+0.5*ACELERACION*time*time);
            try {
                    Thread.sleep(getDeltaTime());
                    Game.jFlappy.setLocation(x, (y + 1));
                    
            } catch (InterruptedException e) {
                    System.out.println("Ocurrio un problema " + e);
                }
            
            this.parent.validarChoqueTubos();
            this.parent.detectColision();
        }
    }
     

    public void jump() {
        timeInit=System.currentTimeMillis();
        yInit=Game.jFlappy.getLocation().y;
        
    }


   
    
    public int getDeltaTime() {
        return deltaTime;
    }

    public void setDeltaTime(int deltaTime) {
        this.deltaTime = deltaTime;
    }

    public boolean isStopThread() {
        return stopThread;
    }

}
