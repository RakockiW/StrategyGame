package io.github.rakocki_wiktor.map;

import com.badlogic.gdx.graphics.Color;
import io.github.rakocki_wiktor.model.Nation;
import io.github.rakocki_wiktor.model.Province;

import java.util.ArrayList;
import java.util.Random;

public class MapPopulator {

    private static final Random RANDOM = new Random();

    public static ArrayList<Nation> populate(ArrayList<Province> provinces) {
        ArrayList<Nation> nations = new ArrayList<>();

        int nationsAmount = RANDOM.nextInt(25 - 10) + 10;

        for (int i = 0; i < nationsAmount; i++) {
            Nation nation = createRandomNation(provinces);
            nations.add(nation);
        }

        Nation wild = createWildNation(provinces);
        nations.add(wild);

        setRelationsBetweenNations(nations);

        return nations;
    }

    private static Nation createRandomNation(ArrayList<Province> provinces) {
        Nation nation = new Nation();
        nation.setColor(getRandomColor());
        nation.setName(getRandomName());

        ArrayList<Province> ownedProvinces = getRandomProvinces(provinces);
        for (Province province : ownedProvinces) {
            if (province.getNation() == null) {
                province.setNation(nation);
                nation.addProvince(province);
                nation.addGold(province.getPopulation());
                nation.addActionPoints(nation.getProvincesAmount());
            }
        }

        return nation;
    }

    private static Nation createWildNation(ArrayList<Province> provinces) {
        Nation wild = new Nation();
        wild.setName("Wild");
        wild.setColor(Color.OLIVE);

        for (Province province : provinces) {
            if (province.getNation() == null) {
                province.setNation(wild);
            }
        }

        return wild;
    }

    private static void setRelationsBetweenNations(ArrayList<Nation> nations) {
        for (Nation nation : nations) {
            for (Nation otherNation : nations) {
                if (nation != otherNation) {
                    nation.setRelation(otherNation, 0);
                }
            }
        }
    }

    private static Color getRandomColor() {
        return new Color(RANDOM.nextFloat(), RANDOM.nextFloat(), RANDOM.nextFloat(), 1f);
    }

    private static ArrayList<Province> getRandomProvinces(ArrayList<Province> provinces) {
        ArrayList<Province> randomProvinces = new ArrayList<>();

        Province rootProvince = provinces.get(RANDOM.nextInt(provinces.size()));
        randomProvinces.add(rootProvince);
        randomProvinces.addAll(rootProvince.getNeighbours());

        return randomProvinces;
    }

    private static String getRandomName() {
        String[] syllables = {"an", "ban", "tor", "ror", "mat", "bash", "lin", "xia", "zul", "bur", "ol", "tar", "ind"};
        String[] endings = {"land", "topia", "stan", "nia", "ria", "an"};

        StringBuilder name = new StringBuilder();
        int syllablesAmount = RANDOM.nextInt(3 - 1) + 1;

        for (int i = 0; i < syllablesAmount; i++) {
            name.append(syllables[RANDOM.nextInt(syllables.length)]);
        }
        name.append(endings[RANDOM.nextInt(endings.length)]);

        // Ustawienie pierwszej litery na wielką
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
