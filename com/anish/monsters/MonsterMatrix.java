package com.anish.monsters;

import java.util.*;
import java.awt.Color;

public class MonsterMatrix
{
    private Monster[][] theMatrix; // 放着要排序事物的二维矩阵
    private int row;//行
    private int col;//列

    public MonsterMatrix(int row0, int col0)
    {
        row = row0;
        col = col0;
        theMatrix = new Monster[row][col];
    }

    public void setMonster(int[] beginColor,int[]midColor, int[] endColor, World world)
    {//三色渐变
        int step = row * col;
        int num1;
        int num2;
        if(step % 2 == 0){
            num1 = num2 = step/2;
        }else{
            num1 = step/2 +1;
            num2 = step - num1;
        }

        //System.out.println(num1);

        for (int i = 1; i <= step; i++) 
        {
            int []temp = {0, 0, 0};
            int order = i;
            if(i <= num1){
                order = i; 
                for (int j = 0; j < beginColor.length; j++) 
                {
                    int bca = beginColor[j];
                    int eca = midColor[j];
                    //这个就是算法，RGB三色都按同样的算法
                    if(eca > bca){
                        temp[j] = bca + (eca - bca) / num1 * order;
                    }else{
                        temp[j] = bca - Math.abs(eca - bca) / num1 * order;
                    }
                    
                    //System.out.println(temp[j]);
                }
            }else{
                order = i - num1;
                for (int j = 0; j < midColor.length; j++) 
                {
                    int bca = midColor[j];
                    int eca = endColor[j];
                    //这个就是算法，RGB三色都按同样的算法
                    if(eca > bca){
                        temp[j] = bca + (eca - bca) / num2 * order;
                    }else{
                        temp[j] = bca - Math.abs(eca - bca) / num2 * order;
                    }
                }
            }
            //System.out.println(temp[0]+","+temp[1]+","+temp[2]);            
            
            //算出 r g b以后建立Monster，Color与rank相配，位置（下标）打乱
            Random random2 = new Random();
            int position = random2.nextInt(row * col);

            int therow = position / col;
            int thecol = position % col;
            if(theMatrix[therow][thecol] == null){
                theMatrix[therow][thecol] = new Monster(new Color(temp[0],temp[1],temp[2]), i - 1, world);
            }else{
                while(theMatrix[therow][thecol] != null){
                    position = (position + 1) % (row * col);
                    therow = position / col;
                    thecol = position % col;
                }
                theMatrix[therow][thecol] = new Monster(new Color(temp[0],temp[1],temp[2]), i - 1, world);
            }
        }
        
        //放进world中
        int xstart = (world.WIDTH - 2 * col - 1)/2;
        int ystart = (world.HEIGHT - 2 * row - 1)/2;
        for(int i = 0; i < row; ++i){
            for(int j = 0; j< col; ++j)
            {
                world.put(theMatrix[i][j], xstart + 2 * i, ystart + 2 * j);
            }
        }
        return;
    }

    public Monster[] unidimensional()
    {//转化为一维数组好排序
        Monster[] monsters = new Monster[row * col];
        for(int i = 0; i < row; ++i)
        {
            for(int j = 0; j<col; ++j)
            {
                monsters[i*col+j] = theMatrix[i][j];
            }
        }
        return monsters;
    }
}