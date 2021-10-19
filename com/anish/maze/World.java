package com.anish.maze;

import java.awt.Color;
import java.util.Random;

public class World {

    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;

    private Tile<Thing>[][] tiles;

    public World() {

        if (tiles == null) {
            tiles = new Tile[WIDTH][HEIGHT];
        }

        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                tiles[i][j] = new Tile<>(i, j);
                tiles[i][j].setThing(new Wall(this));//迷宫用墙
            }
        }
    }

    public Thing get(int x, int y) {
        return this.tiles[x][y].getThing();
    }

    public void put(Thing t, int x, int y) {
        this.tiles[x][y].setThing(t);
    }

    public void setMaze(){
        MazeGenerator mazeGenerator = new MazeGenerator(HEIGHT);
        mazeGenerator.generateMaze();

        int [][]maze = mazeGenerator.getMaze();
        for(int i = 0; i<HEIGHT; ++i)
        {
            for(int j = 0; j< WIDTH; ++j)
            {
                if(maze[i][j] == 1){
                    this.put(new Floor(this), i, j);
                }
            }
        }
        Random random = new Random();
        int temp = random.nextInt(WIDTH);
        if(maze[0][temp] == 1){
            this.put(new Flag(new Color(0,255,0), this), 0, temp);
        }else{
            while(maze[0][temp] != 1){
                temp = (temp+1) % WIDTH;
            }
            this.put(new Flag(new Color(0,255,0), this), 0, temp);
        }

        temp = random.nextInt(WIDTH);
        if(maze[HEIGHT - 1][temp] == 1){
            this.put(new Flag(new Color(220,20,60), this), HEIGHT - 1, temp);
        }else{
            while(maze[HEIGHT - 1][temp] != 1){
                temp = (temp+1) % WIDTH;
            }
            this.put(new Flag(new Color(220,20,60), this), HEIGHT - 1, temp);
        }

    }

}
