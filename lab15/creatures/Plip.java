package creatures;

import huglife.*;

import java.awt.Color;
import java.util.Map;
import java.util.List;

/**
 * An implementation of a motile pacifist photosynthesizer.
 *
 * @author Josh Hug
 */
public class Plip extends Creature {

    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * creates plip with energy equal to E.
     */
    public Plip(double e) {
        super("plip");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /**
     * creates a plip with energy equal to 1.
     */
    public Plip() {
        this(1);
    }

    /**
     * Should return a color with red = 99, blue = 76, and green that varies
     * linearly based on the energy of the Plip. If the plip has zero energy,
     * it should have a green value of 63. If it has max energy, it should
     * have a green value of 255. The green value should vary with energy
     * linearly in between these two extremes. It's not absolutely vital
     * that you get this exactly correct.
     */
    public Color color() {
        g = (int) (63 + (255 - 63) * energy / 2.0);
        r = 99;
        b = 76;
        return color(r, g, b);
    }

    /**
     * Do nothing with C, Plips are pacifists.
     */
    public void attack(Creature c) {
    }

    /**
     * Plips should lose 0.15 units of energy when moving. If you want to
     * to avoid the magic number warning, you'll need to make a
     * private static final variable. This is not required for this lab.
     */
    public void move() {
        energy = energy - 0.15;
    }


    /**
     * Plips gain 0.2 energy when staying due to photosynthesis.
     */
    public void stay() {
        energy = energy + 0.2;
        if (energy > 2) {
            energy = 2;
        }
    }

    /**
     * Plips and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Plip.
     */
    public Plip replicate() {
        Plip offspring = new Plip(this.energy / 2);
        this.energy = this.energy / 2;
        return offspring;
    }

    /**
     * Plips take exactly the following actions based on NEIGHBORS:
     * 1. If no empty adjacent spaces, STAY.
     * 2. Otherwise, if energy >= 1, REPLICATE.
     * 3. Otherwise, if any Cloruses, MOVE with 50% probability.
     * 4. Otherwise, if nothing else, STAY
     * <p>
     * Returns an object of type Action. See Action.java for the
     * scoop on how Actions work. See SampleCreature.chooseAction()
     * for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        List<Direction> enemies = getNeighborsOfType(neighbors, "clorus");
        if (empties.size() == 0) {
            return new Action(Action.ActionType.STAY);
        } else if (this.energy > 1.0) {
            Direction dir = HugLifeUtils.randomEntry(empties);
            return new Action(Action.ActionType.REPLICATE, dir);
        } else if (enemies.size() != 0) {
            Direction dir = HugLifeUtils.randomEntry(empties);
            return new Action(Action.ActionType.MOVE, dir);
        } else {
            return  new Action(Action.ActionType.STAY);
        }
    }

    @Override
    public String name() {
        return "plip";
    }
}
