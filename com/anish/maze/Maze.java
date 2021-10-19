package com.anish.maze;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Stack;
import java.util.Random;
import java.util.Arrays;

public class Maze {
    private World world;
    private int[][] maze;
    private Node start;
    private Node finish;
    private Stack<Node> stack = new Stack<>();

    public Maze(World w)
    {
        world = w;
        maze = new int[world.HEIGHT][world.WIDTH];
      
        //生成一个迷宫
        MazeGenerator mazeGenerator = new MazeGenerator(World.HEIGHT);
        mazeGenerator.generateMaze();

        maze = mazeGenerator.getMaze();
        for(int i = 0; i<World.HEIGHT; ++i)
        {
            for(int j = 0; j< World.HEIGHT; ++j)
            {
                if(maze[i][j] == 1){
                    world.put(new Floor(this.world), i, j);
                }
            }
        }
        Random random = new Random();
        int temp = random.nextInt(World.WIDTH);
        if(maze[0][temp] == 1){
            world.put(new Flag(new Color(0,255,0), world), 0, temp);
        }else{
            while(maze[0][temp] != 1){
                temp = (temp+1) % World.WIDTH;
            }
            world.put(new Flag(new Color(0,255,0), world), 0, temp);
        }
        start = new Node(0, temp);

        temp = random.nextInt(World.WIDTH);
        if(maze[World.HEIGHT - 1][temp] == 1){
            world.put(new Flag(new Color(220,20,60), world), world.HEIGHT - 1, temp);
        }else{
            while(maze[world.HEIGHT - 1][temp] != 1){
                temp = (temp+1) % world.WIDTH;
            }
            world.put(new Flag(new Color(220,20,60), world), world.HEIGHT - 1, temp);
        }
        finish = new Node(world.HEIGHT - 1, temp);
    }
    
    private String plan = "";

    //上下左右四个方向
    int [][]towards = {{1, 0}, {-1, 0}, {0, 1}, {0,-1}};

    public void solve()
    {//从start走到finish，dfs
        //状态变化，0表示尚未遍历到，1表示已遍历
        int [][]status = new int[world.HEIGHT][world.WIDTH];
        stack.push(start);
        status[start.y][start.x] = 1;
        while (!stack.empty()) {
            Node next = stack.pop();

            //遍历周边节点
            for(int i=0; i<4; ++i)
            {
                int x0 = next.x + towards[i][1];
                int y0 = next.y + towards[i][0];
                if(x0>=0 && x0<World.HEIGHT && y0>=0 && y0<world.WIDTH 
                && maze[y0][x0] == 1 && status[y0][x0] == 0)
                {
                    status[y0][x0] = 1;
                    if(x0 == finish.x && y0 == finish.y)
                    {//找到终点

                    }
                }
            }
        }
    }
    
    public String getPlan() {
        return this.plan;
    }
}
