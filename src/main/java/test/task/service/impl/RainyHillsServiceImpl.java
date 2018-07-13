package test.task.service.impl;

import test.task.service.RainyHillsService;

import javax.enterprise.context.RequestScoped;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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

        int maxVal = Arrays.stream(array).mapToInt(i -> i).max().getAsInt();
        int maxIndex = Arrays.asList(array).indexOf(maxVal);

        Integer[] leftRange = Arrays.copyOfRange(array, 0, maxIndex + 1);
        volume = volume.add(calculateVolumeForRange(leftRange, maxVal, true));

        Integer[] rightRange = Arrays.copyOfRange(array, maxIndex, array.length);
        volume = volume.add(calculateVolumeForRange(rightRange, maxVal, false));

        return volume;
    }

    private BigInteger calculateVolumeForRange(Integer[] array, int maxVal, boolean isLeft) {
        int minForRange = Arrays.stream(array).mapToInt(i -> i).min().orElse(maxVal);
        BigInteger volume = BigInteger.ZERO;
        if (minForRange != maxVal) {

            int ariaMax = Arrays.stream(
                    Arrays.copyOfRange(array, (isLeft ? 0 : 1), (isLeft ? (array.length - 1) : array.length)))
                    .mapToInt(i -> i)
                    .max()
                    .orElse(maxVal);

            int ariaMaxIndex = indexOfWithExclude(array, ariaMax, isLeft,
                    Collections.singletonList((isLeft ? (array.length - 1) : 0)));

            if (ariaMaxIndex == -1) {
                return volume;
            }
            volume = volume.add(calculateWaterAmount(ariaMax, Arrays.copyOfRange(
                    array, (isLeft ? ariaMaxIndex : 0), (isLeft ? array.length : ariaMaxIndex))));
            volume = volume.add(calculateVolumeForRange(Arrays.copyOfRange(
                    array, (isLeft ? 0 : ariaMaxIndex), (isLeft ? (ariaMaxIndex + 1) : array.length)), ariaMax, isLeft));
        }

        return volume;
    }

    private BigInteger calculateWaterAmount(Integer minLevel, Integer[] array) {
        BigInteger volume = BigInteger.ZERO;
        for (Integer anArray : array) {
            BigInteger result = BigInteger.valueOf(minLevel).subtract(BigInteger.valueOf(anArray));
            if (result.compareTo(BigInteger.ZERO) > 0) {
                volume = volume.add(result);
            }
        }
        return volume;
    }

    private int indexOfWithExclude(Integer[] array, Integer val, boolean last, List<Integer> excludes) {
        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (val.equals(array[i]) && !excludes.contains(i)) {
                if (last) {
                    index = i;
                } else {
                    return i;
                }
            }
        }
        return index;
    }
}
