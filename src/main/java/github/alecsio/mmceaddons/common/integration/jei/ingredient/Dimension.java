package github.alecsio.mmceaddons.common.integration.jei.ingredient;

import github.alecsio.mmceaddons.common.integration.jei.IRequiresEquals;

public class Dimension implements IRequiresEquals<Dimension> {

    private int id;
    private String name;

    public Dimension(int id, String name) {
        this(id);
        this.name = name;
    }

    public Dimension(int id) {
        this.id = id;
    }

    @Override
    public boolean equalsTo(Dimension other) {
        return this.id == other.id && this.name.equals(other.name);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
