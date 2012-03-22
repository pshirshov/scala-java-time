/*
 * Copyright (c) 2012, Stephen Colebourne & Michael Nascimento Santos
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
package javax.time.builder;

import javax.time.Duration;
import javax.time.LocalDate;
import javax.time.LocalDateTime;
import javax.time.LocalTime;
import javax.time.calendrical.DateTimeRuleRange;

/**
 * A calendar system.
 * 
 * @author Stephen Colebourne
 */
public interface Chrono {

    String getName();

    DateTimeRuleRange getDateValueRange(DateTimeField field, LocalDate date);

    DateTimeRuleRange getTimeValueRange(DateTimeField field, LocalTime time);

    DateTimeRuleRange getDateTimeValueRange(DateTimeField field, LocalDateTime dateTime);

    //-----------------------------------------------------------------------
    long getDateValue(LocalDate date, DateTimeField field);

    long getTimeValue(LocalTime time, DateTimeField field);

    long getDateTimeValue(LocalDateTime dateTime, DateTimeField field);

    //-----------------------------------------------------------------------
    LocalDate setDate(LocalDate date, DateTimeField field, long newValue);

    LocalTime setTime(LocalTime time, DateTimeField field, long newValue);

    LocalDateTime setDateTime(LocalDateTime dateTime, DateTimeField field, long newValue);

    //-----------------------------------------------------------------------
    LocalDate setDateLenient(LocalDate date, DateTimeField field, long newValue);

    LocalTime setTimeLenient(LocalTime time, DateTimeField field, long newValue);

    LocalDateTime setDateTimeLenient(LocalDateTime dateTime, DateTimeField field, long newValue);

    //-----------------------------------------------------------------------
    LocalDate rollDate(LocalDate date, DateTimeField field, long roll);

    LocalTime rollTime(LocalTime time, DateTimeField field, long roll);

    LocalDateTime rollDateTime(LocalDateTime dateTime, DateTimeField field, long roll);

    //-----------------------------------------------------------------------
    LocalDate addToDate(LocalDate date, PeriodUnit unit, long amount);

    LocalTime addToTime(LocalTime time, PeriodUnit unit, long amount);

    LocalDateTime addToDateTime(LocalDateTime dateTime, PeriodUnit unit, long amount);

    //-----------------------------------------------------------------------
    long getPeriodBetweenDates(PeriodUnit unit, LocalDate date1, LocalDate date2);

    long getPeriodBetweenTimes(PeriodUnit unit, LocalTime time1, LocalTime time2);

    long getPeriodBetweenDateTimes(PeriodUnit unit, LocalDateTime dateTime1, LocalDateTime dateTime2);

    Duration getEstimatedDuration(PeriodUnit unit);

}
