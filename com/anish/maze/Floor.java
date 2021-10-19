package com.anish.maze;

import java.awt.Color;

public class Floor extends Thing {

    Floor(World world) {
        super(Color.gray, (char) 249, world);
    }

}