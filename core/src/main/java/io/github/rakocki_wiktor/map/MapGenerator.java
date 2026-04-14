package io.github.rakocki_wiktor.map;


import io.github.rakocki_wiktor.model.Province;
import io.github.rakocki_wiktor.render.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapGenerator {

    private static final int TILE_SIZE = 100;
    private static final float OFFSET_RANGE = 20f;

    public static ArrayList<Province> generateProvinces(int mapHeight, int mapWidth) {

        Random random = new Random();

        int widthInTiles = mapWidth / TILE_SIZE;
        int heightInTiles = mapHeight / TILE_SIZE;

        Vertex[][] vertices = createVertexGrid(widthInTiles, heightInTiles, random);

        Province[][] provinceGrid = new Province[widthInTiles][heightInTiles];
        ArrayList<Province> provinces = new ArrayList<>();

        for (int x = 0; x < widthInTiles; x++) {
            for (int y = 0; y < heightInTiles; y++) {

                Province province = createProvince(vertices, x, y, random);
                provinceGrid[x][y] = province;
                provinces.add(province);
            }
        }

        linkNeighbours(provinceGrid, widthInTiles, heightInTiles);

        return provinces;
    }

    private static Vertex[][] createVertexGrid(int widthTiles, int heightTiles, Random random) {

        Vertex[][] grid = new Vertex[2 * widthTiles + 1][2 * heightTiles + 1];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                float x = (i / 2f) * TILE_SIZE;
                float y = (j / 2f) * TILE_SIZE;

                Vertex v = new Vertex(x, y);
                applyRandomOffset(v, random);

                grid[i][j] = v;
            }
        }

        return grid;
    }

    private static void applyRandomOffset(Vertex v, Random random) {
        v.setOffsetX(random.nextFloat() * 2 * OFFSET_RANGE - OFFSET_RANGE);
        v.setOffsetY(random.nextFloat() * 2 * OFFSET_RANGE - OFFSET_RANGE);
    }

    private static Province createProvince(Vertex[][] grid, int x, int y, Random random) {

        Vertex[] v = new Vertex[] {
            grid[2*x][2*y],
            grid[2*x+1][2*y],
            grid[2*x+2][2*y],
            grid[2*x+2][2*y+1],
            grid[2*x+2][2*y+2],
            grid[2*x+1][2*y+2],
            grid[2*x][2*y+2],
            grid[2*x][2*y+1]
        };

        float[] vertices = toFloatArray(v);

        return new Province(
            vertices,
            1,
            random.nextInt(100),
            random.nextInt(1000)
        );
    }

    private static float[] toFloatArray(Vertex[] vertices) {
        float[] arr = new float[vertices.length * 2];

        for (int i = 0; i < vertices.length; i++) {
            arr[i * 2] = vertices[i].getX();
            arr[i * 2 + 1] = vertices[i].getY();
        }

        return arr;
    }

    private static void linkNeighbours(Province[][] grid, int w, int h) {
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {

                Province p = grid[x][y];

                if (y + 1 < h) p.addNeighbour(grid[x][y + 1]);
                if (y - 1 >= 0) p.addNeighbour(grid[x][y - 1]);
                if (x - 1 >= 0) p.addNeighbour(grid[x - 1][y]);
                if (x + 1 < w) p.addNeighbour(grid[x + 1][y]);
            }
        }
    }
}
