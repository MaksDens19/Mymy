package com.mygdx.game.States;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by localhost on 5/17/2018.
 */

public class GameStateManager{
    private Stack<State> states;

    public GameStateManager() {
        states=new Stack<State>();
    }

    public void push(State state){
        states.push(state);
    }

    public void pop(){
        states.pop().dispose();
    }
    public void set(State state){
        states.pop().dispose();
        states.push(state);
    }

    public void update(float dt){
        states.peek().update(dt);
    }
    public void render(SpriteBatch spriteBatch){
        states.peek().render(spriteBatch);
    }
}
