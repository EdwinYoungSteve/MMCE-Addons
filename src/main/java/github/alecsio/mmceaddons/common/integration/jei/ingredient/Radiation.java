package github.alecsio.mmceaddons.common.integration.jei.ingredient;

import github.alecsio.mmceaddons.common.integration.jei.IRequiresEquals;
import hellfirepvp.modularmachinery.common.machine.IOType;

public class Radiation implements IRequiresEquals<Radiation> {
    private final double amount;
    private final IOType ioType;

    public Radiation(double amount, IOType ioType) {
        this.amount = amount;
        this.ioType = ioType;
    }

    public double getAmount() {
        return amount;
    }

    public IOType getIoType() {
        return ioType;
    }

    @Override
    public boolean equalsTo(Radiation other) {
        return amount == other.amount && ioType == other.ioType;
    }
}
