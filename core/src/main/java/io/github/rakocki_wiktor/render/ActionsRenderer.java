package io.github.rakocki_wiktor.render;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.rakocki_wiktor.logic.actions.*;
import io.github.rakocki_wiktor.model.GameStateData;
import io.github.rakocki_wiktor.model.Province;

public class ActionsRenderer {
    private final ShapeRenderer shape;
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final OrthographicCamera camera;


    public ActionsRenderer(ShapeRenderer shape, SpriteBatch batch, BitmapFont font, OrthographicCamera camera) {
        this.shape = shape;
        this.batch = batch;
        this.font = font;
        this.camera = camera;
    }

    public void render(GameStateData gameStateData) {
        for (Action action : gameStateData.getActions()) {
            if (action.getType() == ActionType.ATTACK) renderAttackAction((AttackAction) action);
            if (action.getType() == ActionType.RECRUIT) renderRecruitAction((RecruitAction) action);
            if (action.getType() == ActionType.MOVE) renderMoveAction((MoveUnitsAction) action);
        }
    }

    private void renderAttackAction(AttackAction action) {
        float[] source = action.getProvinceAttacking().getCenter();
        float[] destination = action.getProvinceAttacked().getCenter();
        renderLine(source, destination, action.getAttackingArmySize());

        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.circle(destination[0], destination[1], 5);
        shape.end();


    }

    private void renderMoveAction(MoveUnitsAction action) {
        float[] source = action.getSourceProvince().getCenter(); //
        float[] destination = action.getDestinationProvince().getCenter(); //
        renderLine(source, destination, action.getArmySize());

        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.rect(destination[0], destination[1], 5, 5); //
        shape.end();

    }

    private void renderLine(float[] source, float[] destination, int armySize) {
        shape.setColor(Color.BLACK);
        shape.setProjectionMatrix(camera.combined);
        shape.begin(ShapeRenderer.ShapeType.Line);
        shape.line(source[0], source[1], destination[0], destination[1]);
        shape.end();

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        float[] center = {
            (source[0] + destination[0]) / 2,
            (source[1] + destination[1]) / 2
        };

        font.draw(batch, String.valueOf(armySize), center[0], center[1]);
        batch.end();

    }

    private void renderRecruitAction(RecruitAction action) {
        float[] center = action.getProvince().getCenter();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        font.draw(batch, "R: " + action.getRecruitedPopulation(), center[0], center[1] + 3);
        batch.end();
    }
}
