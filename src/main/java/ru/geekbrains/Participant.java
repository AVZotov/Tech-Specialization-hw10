package ru.geekbrains;

import lombok.Getter;

import java.util.Random;

public abstract class Participant {
    protected final Random random = new Random();
    @Getter
    protected int selection;

    public void makeSelection(){
        this.selection = random.nextInt(3);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
