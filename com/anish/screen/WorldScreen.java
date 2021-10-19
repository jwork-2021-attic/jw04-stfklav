package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;

import com.anish.maze.Calabash;
import com.anish.maze.World;
import com.anish.maze.Maze;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private Maze theMaze;
    String[] walkSteps;

    public WorldScreen() {
        world = new World();
        theMaze = new Maze(world);
    }

    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    private int index = 0;

    @Override
    public Screen respondToUserInput(KeyEvent key) {
        if (index < this.walkSteps.length) {
            //this.execute(bros, sortSteps[i]);
            index++;
        }
        return this;
    }

}
