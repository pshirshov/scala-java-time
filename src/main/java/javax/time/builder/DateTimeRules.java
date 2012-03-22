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

import javax.time.LocalDateTime;
import javax.time.calendrical.DateTimeRuleRange;

/**
 * Provides the implementation rules for date-time fields.
 * <p>
 * This interface is intended to be used only when implementing a new date-time field.
 * If implementing a field constrained to the date then use {@link DateRules}.
 * Similarly, time only fields should implement {@link TimeRules}.
 * Applications should query the rules via a {@link Chrono}.
 * 
 * @author Stephen Colebourne
 */
public interface DateTimeRules {

    DateTimeRuleRange range(DateTimeField field, LocalDateTime dateTime);

    long get(LocalDateTime dateTime, DateTimeField field);

    LocalDateTime set(LocalDateTime dateTime, DateTimeField field, long newValue);

    LocalDateTime setLenient(LocalDateTime dateTime, DateTimeField field, long newValue);

    LocalDateTime roll(LocalDateTime dateTime, DateTimeField field, long roll);

}
