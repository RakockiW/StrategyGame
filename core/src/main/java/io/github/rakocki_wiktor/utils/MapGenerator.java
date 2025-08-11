package io.github.rakocki_wiktor.utils;


import io.github.rakocki_wiktor.model.AIPlayer;
import io.github.rakocki_wiktor.model.Player;
import io.github.rakocki_wiktor.model.Province;
import io.github.rakocki_wiktor.render.Vertex;

import java.util.ArrayList;
import java.util.Random;

public class MapGenerator {

    private final int TILESIZE = 100;
    private final int MAP_HEIGHT, MAP_WIDTH;

    public MapGenerator(int mapHeight, int mapWidth) {
        MAP_HEIGHT = mapHeight;
        MAP_WIDTH = mapWidth;
    }


    public ArrayList<Province> generateProvinces() {
        Random random = new Random();
        ArrayList<Province> provinces = new ArrayList<>();
        int heightInTiles = MAP_HEIGHT / TILESIZE;
        int widthInTiles = MAP_WIDTH / TILESIZE;
        Vertex[][] verticesGrid = new Vertex[2 * widthInTiles + 1][2 * heightInTiles + 1];


        for (int i = 0; i < verticesGrid.length; i++) {
            for (int j = 0; j < verticesGrid[0].length; j++) {
                float x = (i / 2f) * TILESIZE;
                float y = (j / 2f) * TILESIZE;
                verticesGrid[i][j] = new Vertex(x, y);
            }
        }

        for (Vertex[] vertexRow : verticesGrid) {
            for (Vertex vertex : vertexRow) {
                vertex.setOffsetX(random.nextFloat(20f + 20f) - 20);
                vertex.setOffsetY(random.nextFloat(20f + 20f) - 20);
            }
        }

        for (int x = 0; x < widthInTiles; x++) {
            for (int y = 0; y < heightInTiles; y++) {
                Vertex[] provinceVertices = new Vertex[8];
                provinceVertices[0] = verticesGrid[2*x][2*y];
                provinceVertices[1] = verticesGrid[2*x+1][2*y];
                provinceVertices[2] = verticesGrid[2*x+2][2*y];
                provinceVertices[3] = verticesGrid[2*x+2][2*y+1];
                provinceVertices[4] = verticesGrid[2*x+2][2*y+2];
                provinceVertices[5] = verticesGrid[2*x+1][2*y+2];
                provinceVertices[6] = verticesGrid[2*x][2*y+2];
                provinceVertices[7] = verticesGrid[2*x][2*y+1];

                float[] vertices = new float[16];
                for (int i = 0; i < 8; i++) {
                    vertices[i * 2] = provinceVertices[i].getX();
                    vertices[i*2+1] = provinceVertices[i].getY();
                }
                int armySize = random.nextInt(100);
                Player player;
                if (random.nextInt(3) > 1) {
                    player = new Player();
                }
                else {
                    player = new AIPlayer();
                }
                Province province = new Province(vertices, 1, armySize, player );
                provinces.add(province);

            }
        }
        return provinces;

    }

}
