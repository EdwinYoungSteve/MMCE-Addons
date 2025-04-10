package github.alecsio.mmceaddons.common.tile.wrapper;

import java.util.concurrent.locks.Lock;

public class BaseHelperWrapper {

    // Calls this callable under the provided lock
    static <T> T withLock(Lock lock, java.util.concurrent.Callable<T> callable) {
        lock.lock();
        try {
            return callable.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    // Calls this runnable under the provided lock
    static void withLock(Lock lock, Runnable runnable) {
        lock.lock();
        try {
            runnable.run();
        } finally {
            lock.unlock();
        }
    }
}
