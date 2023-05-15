package dataStructures;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lzn
 * @date 2023/04/16 09:51
 * @Description
 *
 * Question 1: Design a threadsafe read/write memory buffer made of contiguous memory (byte[]).
 * Follow ups: How would you make sure to avoid deadlock when using it in code?
 */
public class CircularBuffer {

    private final byte[] buffer;

    private final int capacity;

    private final Lock lock;

    /**
     * Used to signal threads waiting to read from the buffer when it's not empty
     * This condition is signaled by the `write` method when it writes data to the buffer, allowing waiting threads to continue reading
     */
    private final Condition notEmpty;

    /**
     * Used to signal threads waiting to write to the buffer when it's not full
     * This condition is signaled by the `read` method when it reads data from the buffer and frees up space, allowing waiting threads to continue writing
     */
    private final Condition notFull;

    /**
     * Current size of the buffer
     */
    private int currentSize;

    private int readIndex;

    private int writeIndex;

    public CircularBuffer(int capacity) {
        buffer = new byte[capacity];
        this.capacity = capacity;
        currentSize = 0;
        lock = new ReentrantLock();
        notEmpty = lock.newCondition();
        notFull = lock.newCondition();
        readIndex = 0;
        writeIndex = 0;
    }

    public byte[] read(int length) {
        lock.lock();
        byte[] data = new byte[]{};
        try {

            // Wait for buffer to have data available
            while (currentSize < length) {
                notEmpty.await();
            }

            data = new byte[length];
            int bytesToRead = 0;
            while(bytesToRead < length){
                int bytesAvailable = capacity - readIndex;
                int bytesCopy = Math.min(bytesAvailable, length - bytesToRead);
                System.arraycopy(buffer, readIndex, data, bytesToRead, bytesCopy);
                readIndex = (readIndex + bytesCopy) % capacity;
                bytesToRead += bytesCopy;
            }

            currentSize -= length;
            notFull.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return data;
    }

    public void write(byte[] data){
        lock.lock();

        try {
            while(currentSize == capacity){
                notFull.await();
            }

            if(data.length > capacity - currentSize){
                throw new IllegalArgumentException("Data length exceed buffer size");
            }

            int bytesToWrite = data.length;
            int bytesWritten = 0;
            while(bytesToWrite > 0){
                int bytesAvailable = capacity - bytesToWrite;
                int bytesToCopy = Math.min(bytesAvailable, bytesToWrite);
                System.arraycopy(data, bytesWritten, buffer, writeIndex, bytesToCopy);
                writeIndex = (writeIndex + bytesToCopy) % capacity;
                bytesToWrite -= bytesToCopy;
                bytesWritten += bytesToCopy;
            }

            currentSize += data.length;
            notEmpty.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
