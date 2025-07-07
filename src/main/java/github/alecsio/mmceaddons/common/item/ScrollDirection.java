package github.alecsio.mmceaddons.common.item;

public enum ScrollDirection {
    UP, DOWN;

    public static ScrollDirection getFrom(boolean bool) {
        return bool ? UP : DOWN;
    }
}
