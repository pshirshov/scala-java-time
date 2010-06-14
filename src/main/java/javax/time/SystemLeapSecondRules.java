/*
 * Copyright (c) 2010, Stephen Colebourne & Michael Nascimento Santos
 *
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 *  * Neither the name of JSR-310 nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package javax.time;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicReference;

import javax.time.calendar.LocalDate;
import javax.time.scales.LeapSeconds;

/**
 * System default leap second rules.
 * <p>
 * SystemLeapSecondRules is immutable and thread-safe.
 *
 * @author Stephen Colebourne
 */
final class SystemLeapSecondRules extends LeapSecondRules {

    /**
     * Singleton.
     */
    static SystemLeapSecondRules INSTANCE = new SystemLeapSecondRules();
    /**
     * Serialization version.
     */
    private static final long serialVersionUID = 1L;
    /**
     * The table of leap second dates.
     */
    private AtomicReference<Data> dataRef = new AtomicReference<Data>(loadLeapSeconds());

    /** Data holder. */
    private static class Data implements Serializable {
        /** Serialization version. */
        private static final long serialVersionUID = 1L;
        /** The table of leap second dates. */
        private long[] dates;
        /** The table of TAI offsets. */
        private int[] offsets;
    }

    //-----------------------------------------------------------------------
    /**
     * Restricted constructor.
     */
    private SystemLeapSecondRules() {
    }

    //-----------------------------------------------------------------------
    /**
     * Adds a new leap second to the system.
     * <p>
     * This method registers a new leap second with the system leap second rules.
     * All calculations will be affected immediately that the method is called.
     * Calling the method is thread-safe and its effects are visible in all threads.
     *
     * @param mjDay  the modified julian date that the leap second occurs at the end of
     * @param leapAdjustment  the leap seconds to add/remove at the end of the day
     * @throws IllegalArgumentException if the day is before the last known leap second
     * @throws ConcurrentModificationException if another thread updates the rules at the same time
     */
    void registerLeapSecond(long mjDay, int leapAdjustment) {
        Data data = dataRef.get();
        if (mjDay <= data.dates[data.dates.length - 1]) {
            throw new IllegalArgumentException("Date must be after the last configured leap second date");
        }
        if (leapAdjustment != -1 && leapAdjustment != 1 && leapAdjustment != 2) {
            throw new IllegalArgumentException("Leap adjustment must be -1, 1 or 2");
        }
        long[] dates = Arrays.copyOf(data.dates, data.dates.length + 1);
        int[] offsets = Arrays.copyOf(data.offsets, data.offsets.length + 1);
        dates[dates.length - 1] = mjDay;
        offsets[offsets.length - 1] = offsets[offsets.length - 2] + leapAdjustment;
        Data newData = new Data();
        newData.dates = dates;
        newData.offsets = offsets;
        if (dataRef.compareAndSet(data, newData) == false) {
            throw new ConcurrentModificationException("Unable to update leap second rules as they have already been updated");
        }
    }

    //-----------------------------------------------------------------------
    @Override
    public String getName() {
        return "System";
    }

    @Override
    public int getLeapSecondAdjustment(long mjDay) {
        Data data = dataRef.get();
        int pos = Arrays.binarySearch(data.dates, mjDay);
        return pos > 0 ? data.offsets[pos] - data.offsets[pos - 1] : 0;
    }

    @Override
    public int getTAIOffset(long mjDay) {
        Data data = dataRef.get();
        int pos = Arrays.binarySearch(data.dates, mjDay);
        pos = (pos < 0 ? -(pos + 1) : pos);
        return pos > 0 ? data.offsets[pos - 1] : 10;
    }

    @Override
    public long[] getLeapSecondDates() {
        Data data = dataRef.get();
        return data.dates.clone();
    }

    /**
     * Loads the leap seconds from file.
     * @return an array of two arrays - leap seconds dates and amounts
     */
    private static Data loadLeapSeconds() {
        InputStream in = LeapSeconds.class.getResourceAsStream("/javax/time/LeapSeconds.txt");
        if (in == null) {
            throw new CalendricalException("LeapSeconds.txt resource missing");
        }
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
            Map<Long, Integer> leaps = new TreeMap<Long, Integer>();
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                line = line.trim();
                if (line.length() > 0 && line.charAt(0) != '#') {
                    String[] split = line.split(" ");
                    if (split.length != 2) {
                        throw new CalendricalException("LeapSeconds.txt has invalid line format");
                    }
                    LocalDate date = LocalDate.parse(split[0]);
                    int offset = Integer.parseInt(split[1]);
                    leaps.put(date.toModifiedJulianDays(), offset);
                }
            }
            long[] dates = new long[leaps.size()];
            int[] offsets = new int[leaps.size()];
            int i = 0;
            for (Entry<Long, Integer> entry : leaps.entrySet()) {
                long changeMjd = entry.getKey() - 1;  // subtract one to get date leap second is added
                int offset = entry.getValue();
                if (i > 0) {
                    int adjust = offset - offsets[i - 1];
                    if (adjust < -1 || adjust > 2 || adjust == 0) {
                        throw new CalendricalException("Leap adjustment must be from -1 to 2 inclusive and not 0");
                    }
                }
                dates[i] = changeMjd;
                offsets[i++] = offset;
            }
            Data data = new Data();
            data.dates = dates;
            data.offsets = offsets;
            return data;
        } catch (IOException ex) {
            try {
                in.close();
            } catch (IOException ignored) {
                // ignore
            }
            throw new CalendricalException("Exception reading LeapSeconds.txt", ex);
        }
    }

}
