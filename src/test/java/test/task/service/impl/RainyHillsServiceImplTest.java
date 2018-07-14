package test.task.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import test.task.service.RainyHillsService;

import java.math.BigInteger;

public class RainyHillsServiceImplTest {

    private RainyHillsService rainyHillsService;

    @Before
    public void setUp() {
        rainyHillsService = new RainyHillsServiceImpl();
    }

    @Test
    public void calculateVolumeSanityTest1() {
        Assert.assertEquals(BigInteger.valueOf(2), rainyHillsService.calculateVolume(new Integer[]{3, 2, 4, 1, 2}));
    }

    @Test
    public void calculateVolumeSanityTest2() {
        Assert.assertEquals(BigInteger.valueOf(4), rainyHillsService.calculateVolume(
                new Integer[]{1, 3, 2, 4, 1, 2, 3, 1}));
    }

    @Test
    public void calculateVolumeSanityTest3() {
        Assert.assertEquals(BigInteger.valueOf(8), rainyHillsService.calculateVolume(new Integer[]{4, 1, 1, 0, 2, 3}));
    }

    @Test
    public void calculateVolumeSanityTest4() {
        Assert.assertEquals(BigInteger.valueOf(2), rainyHillsService.calculateVolume(
                new Integer[]{1, 3, 2, 3, 2, 3, 1}));
    }

    @Test
    public void calculateVolumeSanityTest5() {
        Assert.assertEquals(BigInteger.valueOf(7), rainyHillsService.calculateVolume(new Integer[]{4, 1, 3, 1, 4}));
    }

    @Test
    public void calculateVolumeSanityTest6() {
        Assert.assertEquals(BigInteger.valueOf(1), rainyHillsService.calculateVolume(new Integer[]{1, 2, 1, 2, 4}));
    }

    @Test
    public void calculateVolumeSanityTest7() {
        Assert.assertEquals(BigInteger.valueOf(0), rainyHillsService.calculateVolume(new Integer[]{0, 0}));
    }

    @Test
    public void calculateVolumeSanityTest8() {
        Assert.assertEquals(BigInteger.valueOf(14), rainyHillsService.calculateVolume(new Integer[]{7, 0, 7, 0, 8}));
    }

    @Test
    public void calculateVolumeSanityTest9() {
        Assert.assertEquals(BigInteger.valueOf(14), rainyHillsService.calculateVolume(new Integer[]{8, 0, 7, 0, 7}));
    }

    @Test
    public void calculateVolumeWithOneHill() {
        Assert.assertEquals(BigInteger.valueOf(0), rainyHillsService.calculateVolume(new Integer[]{4}));
    }

    @Test
    public void calculateVolumeWithNegativeValue() {
        Assert.assertEquals(BigInteger.valueOf(2), rainyHillsService.calculateVolume(
                new Integer[]{1, -1, 1}));
    }

    @Test
    public void calculateVolumeWithBigValues() {
        Assert.assertEquals(BigInteger.valueOf(Integer.MAX_VALUE).subtract(BigInteger.valueOf(Integer.MIN_VALUE)),
                rainyHillsService.calculateVolume(
                        new Integer[]{Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE}));
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateVolumeWithZeroHills() {
        Assert.assertEquals(BigInteger.valueOf(0), rainyHillsService.calculateVolume(new Integer[]{}));
    }

    @Test(expected = IllegalArgumentException.class)
    public void calculateVolumeWithNullValue() {
        Assert.assertEquals(BigInteger.valueOf(0), rainyHillsService.calculateVolume(new Integer[]{1, null, 2, 4}));
    }
}