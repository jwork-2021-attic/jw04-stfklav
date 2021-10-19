package com.anish.monsters;

import java.awt.Color;

public class Floor extends Thing {

    Floor(World world) {
        super(Color.DARK_GRAY, (char) 250, world);
    }

}