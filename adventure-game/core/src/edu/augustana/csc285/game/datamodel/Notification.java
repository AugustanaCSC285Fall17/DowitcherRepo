package edu.augustana.csc285.game.datamodel;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Notification extends Dialog {

static Skin skin = new Skin(Gdx.files.internal("skin/Holo-dark-mdpi.json"));
Label label;
public Notification(String text) {
    super(text, skin);
    setSize(Gdx.graphics.getWidth()/4, Gdx.graphics.getHeight()/4);
    setPosition(550, 0);
    label = new Label(text,skin);
    add(label).bottom();

    label.setColor(Color.WHITE);
    setKeepWithinStage(false);
    show();
}

public void show(){

    MoveToAction In = new MoveToAction();
    In.setPosition(getX(),getHeight());
    In.setDuration(0.5f);

    MoveToAction Out = new MoveToAction();
    Out.setPosition(getX(), Gdx.graphics.getHeight() + getHeight());
    Out.setDuration(1f);


    SequenceAction action = new SequenceAction(In, Actions.delay(0.5f), Out, Actions.removeActor());
    addAction(action);
}



}

