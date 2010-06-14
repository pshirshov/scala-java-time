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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.Serializable;
import java.util.Arrays;

import javax.time.calendar.LocalDate;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test SystemLeapSecondRules.
 *
 * @author Stephen Colebourne
 */
@Test
public class TestSystemLeapSecondRules {

    //-----------------------------------------------------------------------
    public void test_interfaces() {
        assertTrue(Serializable.class.isAssignableFrom(Duration.class));
    }

    //-----------------------------------------------------------------------
    // getName()
    //-----------------------------------------------------------------------
    public void test_getName() {
        LeapSecondRules rules = LeapSecondRules.system();
        assertEquals(rules.getName(), "System");
    }

    //-----------------------------------------------------------------------
    // getLeapSecond()
    //-----------------------------------------------------------------------
    @DataProvider(name="LeapSeconds")
    Object[][] leapSeconds() {
        return new Object[][] {
            {-1, 0, 10, "1858-11-16"},
            {0, 0, 10, "1858-11-17"},
            {1, 0, 10, "1858-11-18"},
            
            {41316, 0, 10, "1971-12-31"},
            {41317, 0, 10, "1972-01-01"},
            {41318, 0, 10, "1972-01-02"},
            
            {41497, 0, 10, "1972-06-29"},
            {41498, 1, 10, "1972-06-30"},
            {41499, 0, 11, "1972-07-01"},
            {41500, 0, 11, "1972-07-02"},
            
            {41681, 0, 11, "1972-12-30"},
            {41682, 1, 11, "1972-12-31"},
            {41683, 0, 12, "1973-01-01"},
            {41684, 0, 12, "1973-01-02"},
            
            {42046, 0, 12, "1973-12-30"},
            {42047, 1, 12, "1973-12-31"},
            {42048, 0, 13, "1974-01-01"},
            {42049, 0, 13, "1974-01-02"},
            
            {42411, 0, 13, "1974-12-30"},
            {42412, 1, 13, "1974-12-31"},
            {42413, 0, 14, "1975-01-01"},
            {42414, 0, 14, "1975-01-02"},
            
            {42776, 0, 14, "1975-12-30"},
            {42777, 1, 14, "1975-12-31"},
            {42778, 0, 15, "1976-01-01"},
            {42779, 0, 15, "1976-01-02"},
            
            {43142, 0, 15, "1976-12-30"},
            {43143, 1, 15, "1976-12-31"},
            {43144, 0, 16, "1977-01-01"},
            {43145, 0, 16, "1977-01-02"},
            
            {43507, 0, 16, "1977-12-30"},
            {43508, 1, 16, "1977-12-31"},
            {43509, 0, 17, "1978-01-01"},
            {43510, 0, 17, "1978-01-02"},
            
            {43872, 0, 17, "1978-12-30"},
            {43873, 1, 17, "1978-12-31"},
            {43874, 0, 18, "1979-01-01"},
            {43875, 0, 18, "1979-01-02"},
            
            {44237, 0, 18, "1979-12-30"},
            {44238, 1, 18, "1979-12-31"},
            {44239, 0, 19, "1980-01-01"},
            {44240, 0, 19, "1980-01-02"},
            
            {44784, 0, 19, "1981-06-29"},
            {44785, 1, 19, "1981-06-30"},
            {44786, 0, 20, "1981-07-01"},
            {44787, 0, 20, "1981-07-02"},
            
            {45149, 0, 20, "1982-06-29"},
            {45150, 1, 20, "1982-06-30"},
            {45151, 0, 21, "1982-07-01"},
            {45152, 0, 21, "1982-07-02"},
            
            {45514, 0, 21, "1983-06-29"},
            {45515, 1, 21, "1983-06-30"},
            {45516, 0, 22, "1983-07-01"},
            {45517, 0, 22, "1983-07-02"},
            
            {46245, 0, 22, "1985-06-29"},
            {46246, 1, 22, "1985-06-30"},
            {46247, 0, 23, "1985-07-01"},
            {46248, 0, 23, "1985-07-02"},
            
            {47159, 0, 23, "1987-12-30"},
            {47160, 1, 23, "1987-12-31"},
            {47161, 0, 24, "1988-01-01"},
            {47162, 0, 24, "1988-01-02"},
            
            {47890, 0, 24, "1989-12-30"},
            {47891, 1, 24, "1989-12-31"},
            {47892, 0, 25, "1990-01-01"},
            {47893, 0, 25, "1990-01-02"},
            
            {48255, 0, 25, "1990-12-30"},
            {48256, 1, 25, "1990-12-31"},
            {48257, 0, 26, "1991-01-01"},
            {48258, 0, 26, "1991-01-02"},
            
            {48802, 0, 26, "1992-06-29"},
            {48803, 1, 26, "1992-06-30"},
            {48804, 0, 27, "1992-07-01"},
            {48805, 0, 27, "1992-07-02"},
            
            {49167, 0, 27, "1993-06-29"},
            {49168, 1, 27, "1993-06-30"},
            {49169, 0, 28, "1993-07-01"},
            {49170, 0, 28, "1993-07-02"},
            
            {49532, 0, 28, "1994-06-29"},
            {49533, 1, 28, "1994-06-30"},
            {49534, 0, 29, "1994-07-01"},
            {49535, 0, 29, "1994-07-02"},
            
            {50081, 0, 29, "1995-12-30"},
            {50082, 1, 29, "1995-12-31"},
            {50083, 0, 30, "1996-01-01"},
            {50084, 0, 30, "1996-01-02"},
            
            {50628, 0, 30, "1997-06-29"},
            {50629, 1, 30, "1997-06-30"},
            {50630, 0, 31, "1997-07-01"},
            {50631, 0, 31, "1997-07-02"},
            
            {51177, 0, 31, "1998-12-30"},
            {51178, 1, 31, "1998-12-31"},
            {51179, 0, 32, "1999-01-01"},
            {51180, 0, 32, "1999-01-02"},
            
            {53734, 0, 32, "2005-12-30"},
            {53735, 1, 32, "2005-12-31"},
            {53736, 0, 33, "2006-01-01"},
            {53737, 0, 33, "2006-01-02"},
            
            {54830, 0, 33, "2008-12-30"},
            {54831, 1, 33, "2008-12-31"},
            {54832, 0, 34, "2009-01-01"},
            {54833, 0, 34, "2009-01-02"},
        };
    }

    @Test(dataProvider="LeapSeconds")
    public void test_leapSeconds(long mjd, int adjust, int offset, String checkDate) {
        assertEquals(mjd, LocalDate.parse(checkDate).toModifiedJulianDays(), "Invalid test");
        
        LeapSecondRules rules = LeapSecondRules.system();
        assertEquals(rules.getLeapSecondAdjustment(mjd), adjust);
        assertEquals(rules.getTAIOffset(mjd), offset);
        if (adjust != 0) {
            long[] leaps = rules.getLeapSecondDates();
            Arrays.sort(leaps);
            assertEquals(Arrays.binarySearch(leaps, mjd) >= 0, true);
        }
    }

    //-----------------------------------------------------------------------
    // convertToUTC(TAIInstant)
    //-----------------------------------------------------------------------
    public void convertToTAI() {
        LeapSecondRules rules = LeapSecondRules.system();
        for (int i = -1000; i < 1000; i++) {
            for (int j = 0; j < 10; j++) {
                UTCInstant utc = UTCInstant.ofModifiedJulianDay(36204 + i, j * 1000000000L + 2L);
                TAIInstant test = rules.convertToTAI(utc);
                assertEquals(test.getTAISeconds(), i * 24 * 60 * 60 + j + 10);
                assertEquals(test.getNanoOfSecond(), 2);
                assertEquals(rules.convertToUTC(test), utc);
            }
        }
    }

    @Test(expectedExceptions=NullPointerException.class)
    public void convertToTAI_null() {
        LeapSecondRules rules = LeapSecondRules.system();
        rules.convertToTAI((UTCInstant) null);
    }

    //-----------------------------------------------------------------------
    // convertToUTC(TAIInstant)
    //-----------------------------------------------------------------------
    public void test_convertToUTC_TAIInstant() {
        TAIInstant tai = TAIInstant.ofTAISeconds((44239L - 36204) * 24 * 60 * 60, 0);
        UTCInstant expected = UTCInstant.ofModifiedJulianDay(44239L, 0);
        LeapSecondRules rules = LeapSecondRules.system();
        assertEquals(rules.convertToUTC(tai), expected);
    }

    @Test(expectedExceptions=NullPointerException.class)
    public void convertToUTC_null() {
        LeapSecondRules rules = LeapSecondRules.system();
        rules.convertToUTC((TAIInstant) null);
    }

    //-----------------------------------------------------------------------
    // toString()
    //-----------------------------------------------------------------------
    public void test_toString() {
        assertEquals(LeapSecondRules.system().toString(), "LeapSecondRules[System]");
    }

}
