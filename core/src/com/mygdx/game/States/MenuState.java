package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.GameStart;

/**
 * Created by localhost on 5/17/2018.
 */

public class MenuState extends State{
    private static final float BUTTON_PLAY_WIDTH = 60;
    private static final float BUTTON_PLAY_HEIGHT = 60;

    private Music music;
    private ImageButton btnPlayMusic;
    private ImageButton btnStartPlay;

    private Texture btnPlayTextureOff;
    private Texture btnPlayTextureOn;

    private Texture background;
    private Texture btnExit;
    private Texture btnPlay;

    private Stage stage;

    private boolean isPlayMusic = false;


    public MenuState(GameStateManager gameStateManager) {
        super(gameStateManager);
//        background = new Texture("android/assets/bg.jpg");
//        playBtn = new Texture("android/assets/btn_play.png");
        //background = new Texture("");
        btnExit = new Texture("android/assets/exit.png");
        btnPlay = new Texture("android/assets/play-button.png");

        stage = new Stage(new ScreenViewport());
        music = Gdx.audio.newMusic(Gdx.files.internal("android/assets/music.mp3"));
        music.setLooping(true);
        music.setVolume(1f);

        btnPlayTextureOff = new Texture("android/assets/volume-off.png");
        btnPlayTextureOn = new Texture("android/assets/volume-on.png");

        btnPlayMusic = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnPlayTextureOn)));
        btnPlayMusic.setPosition(50,GameStart.HEIGHT-70);
        btnPlayMusic.setSize(BUTTON_PLAY_WIDTH, BUTTON_PLAY_HEIGHT);
        btnPlayMusic.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(isPlayMusic){
                    music.play();
                    btnPlayMusic.setBackground(new TextureRegionDrawable(new TextureRegion(btnPlayTextureOn)));
                    isPlayMusic = false;
                }else{
                    music.stop();
                    btnPlayMusic.setBackground(new TextureRegionDrawable(new TextureRegion(btnPlayTextureOff)));
                }
                return true;
            }
        });

        btnStartPlay = new ImageButton(new TextureRegionDrawable(new TextureRegion(btnPlay)));
        btnStartPlay.setPosition((GameStart.WIDTH / 2) - 50, GameStart.HEIGHT / 2);
        btnStartPlay.setSize(100, 100);
        btnStartPlay.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return super.touchDown(event, x, y, pointer, button);
            }
        });

        stage.addActor(btnStartPlay);
        stage.addActor(btnPlayMusic);

        Gdx.input.setInputProcessor(stage);


    }

    @Override
    protected void handleInput() {
        gameStateManager.set(new PlayState(gameStateManager));
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render(SpriteBatch spriteBatch) {
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0, GameStart.WIDTH, GameStart.HEIGHT);
    }

    @Override
    public void dispose() {

    }
}
