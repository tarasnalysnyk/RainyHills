package test.task.service.impl;

import test.task.service.RainyHillsService;

import javax.enterprise.context.RequestScoped;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author Taras Nalysnyk
 */
@RequestScoped
public class RainyHillsServiceImpl implements RainyHillsService {

    /**
     * This method takes an array as an input, and calculates the volume of water
     * which remained after the rain, in units.
     * @param array the input array
     * @return calculates the volume of water
     * @throws IllegalArgumentException if input array is null/empty or contains null values
     */
    @Override
    public BigInteger calculateVolume(Integer[] array) {

        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Input array should not be null/empty");
        }

        if (Arrays.stream(array).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Input array cannot contains null values");
        }

        BigInteger volume = BigInteger.ZERO;

        Integer leftIndex = 0;
        Integer rightIndex = array.length - 1;

        Integer leftValue = array[leftIndex];
        Integer rightValue = array[rightIndex];

        while(!leftIndex.equals(rightIndex)) {

            if (leftValue >= rightValue) {

                if (array[rightIndex - 1] < rightValue) {
                    volume = volume.add(
                            BigInteger.valueOf(rightValue).subtract(BigInteger.valueOf(array[--rightIndex])));
                } else {
                    rightIndex--;
                    rightValue = array[rightIndex];
                }

            } else {

                if (array[leftIndex + 1] < leftValue) {
                    volume = volume.add(
                            BigInteger.valueOf(leftValue).subtract(BigInteger.valueOf(array[leftIndex + 1])));
                    leftIndex++;
                } else {
                    leftIndex++;
                    leftValue = array[leftIndex];
                }

            }

        }

        return volume;
    }

}
