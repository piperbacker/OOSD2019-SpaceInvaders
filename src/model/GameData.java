package model;

import java.util.ArrayList;

public class GameData { // will hold everything drawn on screen

    public ArrayList<GameFigure> fixedObject = new ArrayList<>(); // objects that are always in game
    public ArrayList<GameFigure> friendObject = new ArrayList<>();
    public ArrayList<GameFigure> enemyObject = new ArrayList<>();

    public void update() {

        ArrayList<GameFigure> remove = new ArrayList<>();
        for (var fig: fixedObject) {
            if (fig.done) {
                remove.add(fig);
            }else {
                fig.update();
            }
        }
        fixedObject.removeAll(remove);

        remove.clear();
        for (var fig: friendObject) {
            if (fig.done) {
                remove.add(fig);
            }else {
                fig.update();
            }
        }
        friendObject.removeAll(remove);

        remove.clear();
        for (var fig: enemyObject) {
            if (fig.done) {
                remove.add(fig);
            }else {
                fig.update();
            }
        }
        enemyObject.removeAll(remove);

    }

    public void clear () {
        fixedObject.clear();
        friendObject.clear();
        enemyObject.clear();
    }
}
