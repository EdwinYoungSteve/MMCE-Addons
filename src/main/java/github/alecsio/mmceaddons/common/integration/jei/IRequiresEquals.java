package github.alecsio.mmceaddons.common.integration.jei;

/**
 * I know the Object.equals exists, but I wanted a somewhat clean way to enforce that some classes have a different implementation that actually ensures whether two objects are equal or not.
 */
public interface IRequiresEquals<T> {
    boolean equalsTo(T other);
}
