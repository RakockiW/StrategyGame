package io.github.rakocki_wiktor.map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Colors;
import com.badlogic.gdx.utils.ObjectMap;
import io.github.rakocki_wiktor.model.Nation;
import io.github.rakocki_wiktor.model.Province;

import java.util.ArrayList;
import java.util.Random;

public class MapPopulator {

    private final ArrayList<Province> provinces;
    private ArrayList<Nation> nations;

    public MapPopulator(ArrayList<Province> provinces) {
        this.provinces = provinces;
    }

    public ArrayList<Nation> populate() {
        Random random = new Random();

        int nationsAmount = random.nextInt(25 - 10) + 10;

        for (int i = 0; i < nationsAmount; i++) {
            Nation nation = new Nation();
            Color color = getRandomColor();
            String name = getRandomName();
            ArrayList<Province> ownedProvinces = getRandomProvinces();
            nation.setColor(color);
            nation.setName(name);
            for (Province province : ownedProvinces) {
                province.setOwner(nation);
                nation.addProvince(province);
            }

        }

        return nations;
    }

    private Color getRandomColor() {
        ObjectMap<String, Color> colors = Colors.getColors();
        int size = colors.size;

        int index = new Random().nextInt(size);
        int i = 0;

        for (ObjectMap.Entry<String, Color> entry : colors) {
            if (i == index) {
                return entry.value;
            }
            i++;
        }
        return null;
    }

    private ArrayList<Province> getRandomProvinces() {

        ArrayList<Province> randomProvinces = new ArrayList<>();

        //int neighboursAmount = new Random().nextInt(6);

        Province rootProvince = provinces.get(new Random().nextInt(provinces.size()));
        randomProvinces.add(rootProvince);

        randomProvinces.addAll(rootProvince.getNeighbours());

        return randomProvinces;
    }

    private String getRandomName() {
        Random random = new Random();
        String[] syllabes = {"an", "ban", "tor", "xor", "mat", "bash", "lin", "xia", "zul", "bur", "ol"};
        String[] endings = {"land", "topia", "stan", "nia", "ria", "an"};
        StringBuilder name = new StringBuilder();

        int syllabesAmount = random.nextInt(3-1) + 1;

        for (int i = 0; i < syllabesAmount; i++) {
            name.append(syllabes[random.nextInt(syllabes.length)]);
        }
        name.append(endings[random.nextInt(endings.length)]);
        String stringName = name.toString();
        stringName = name.substring(0, 1).toUpperCase() + stringName.substring(1);
        return stringName;
    }
}
