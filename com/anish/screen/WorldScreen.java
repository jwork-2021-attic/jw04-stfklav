package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.anish.monsters.BubbleSorter;
import com.anish.monsters.SelectSorter;
import com.anish.monsters.MonsterMatrix;
import com.anish.monsters.World;
import com.anish.monsters.Monster;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private MonsterMatrix myMatrix;
    private Monster[] monsters;
    public static final int ROW = 16;
    public static final int COL = 16;
    String[] sortSteps;

    public WorldScreen() {
        world = new World();

        myMatrix = new MonsterMatrix(ROW, COL);

        int []beginColor = {199,21,133};
        int []midColor = {195,207,226};
        int []endColor = {0,0,139};

        myMatrix.setMonster(beginColor, midColor, endColor, world);

        monsters = myMatrix.unidimensional();

        SelectSorter<Monster> b = new SelectSorter<>();
        b.load(monsters);
        b.sort();

        sortSteps = this.parsePlan(b.getPlan());
    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(Monster[] monsters, String step) {
        String[] couple = step.split("<->");
        getBroByRank(monsters, Integer.parseInt(couple[0])).swap(getBroByRank(monsters, Integer.parseInt(couple[1])));
    }

    private Monster getBroByRank(Monster[] monsters, int rank) {
        for (Monster m : monsters) {
            if (m.getRank() == rank) {
                return m;
            }
        }
        return null;
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    int index = 0;

    @Override
    public Screen respondToUserInput(KeyEvent key) {

        if (index < this.sortSteps.length) {
            this.execute(monsters, sortSteps[index]);
            index++;
        }

        return this;
    }

}
