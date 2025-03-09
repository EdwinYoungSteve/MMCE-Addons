package github.alecsio.mmceaddons.common.integration.jei.ingredient;

import hellfirepvp.modularmachinery.common.machine.IOType;

public class Radiation {
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
}
