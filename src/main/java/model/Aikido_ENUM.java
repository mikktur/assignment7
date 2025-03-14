package model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter()
public enum Aikido_ENUM implements AttributeConverter<Aikido_ENUM, Integer> {
    NOBELT(0), WHITE(1), YELLOW(2), ORANGE(3), GREEN(4), BLUE(5),BROWN(6), RED(7), BLACK(8);

    private int rank;

    Aikido_ENUM(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public static Aikido_ENUM rankToEnum(int rank) {
        for (Aikido_ENUM akidoTiersEnum : Aikido_ENUM.values()) {
            if (akidoTiersEnum.getRank() == rank) {
                return akidoTiersEnum;
            }
        }
        return null;
    }

    @Override
    public Integer convertToDatabaseColumn(Aikido_ENUM a) {
        return a.getRank();
    }

    @Override
    public Aikido_ENUM convertToEntityAttribute(Integer integer) {
        return rankToEnum(integer);
    }
}
