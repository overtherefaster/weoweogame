package sophistry.weoweogame.data;

/**
 * Created by Vincent on 1/22/2018.
 */

public class Player {

    String name;
    String id;
    MarkType type;

    int numberOfWins;
    int totalMatches;

    public Player(String name, String id, MarkType type) {
        this.name = name;
        this.id = id;
        this.type = type;

        numberOfWins = 0;
        totalMatches = 0;
    }
}
