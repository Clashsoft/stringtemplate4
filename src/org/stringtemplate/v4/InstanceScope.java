/*
 * [The "BSD license"]
 *  Copyright (c) 2011 Terence Parr
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *  1. Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *  3. The name of the author may not be used to endorse or promote products
 *     derived from this software without specific prior written permission.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.stringtemplate.v4;

import org.stringtemplate.v4.debug.EvalTemplateEvent;
import org.stringtemplate.v4.debug.InterpEvent;
import org.stringtemplate.v4.gui.STViz;

import java.util.ArrayList;
import java.util.List;

/** */
public class InstanceScope {
    /**
     * @deprecated since 4.3; use {@link #getParent()} instead
     */
    @Deprecated
    public final InstanceScope parent;

    /**
     * @deprecated since 4.3; use {@link #getST()} instead
     */
    @Deprecated
    public final ST st;

    /**
     * @deprecated since 4.3; use {@link #getInstructionPointer()} or {@link #setInstructionPointer(int)} instead
     */
    @Deprecated
    public int ip;

    /**
     * @deprecated since 4.3; use {@link #getEvents()} instead
     */
    @Deprecated
    public List<InterpEvent> events = new ArrayList<InterpEvent>();

    /**
     * @deprecated since 4.3; use {@link #getChildEvalTemplateEvents()} instead
     */
    @Deprecated
    public List<EvalTemplateEvent> childEvalTemplateEvents =
        new ArrayList<EvalTemplateEvent>();

    /**
     * @deprecated since 4.3; use {@link #isEarlyEval()} or {@link #setEarlyEval(boolean)} instead
     */
    @Deprecated
    public boolean earlyEval;

    public InstanceScope(InstanceScope parent, ST st) {
        this.parent = parent;
        this.st = st;
        this.earlyEval = parent != null && parent.earlyEval;
    }

    /**
     * Template that invoked us.
     */
    public InstanceScope getParent() {
        return parent;
    }

    /**
     * Template we're executing.
     */
    public ST getST() {
        return st;
    }

    /**
     * Current instruction pointer.
     */
    public int getInstructionPointer() {
        return ip;
    }

    public void setInstructionPointer(int ip) {
        this.ip = ip;
    }

    /**
     * Includes the {@link EvalTemplateEvent} for this template. This is a
     * subset of {@link Interpreter#events} field. The final
     * {@link EvalTemplateEvent} is stored in 3 places:
     *
     * <ol>
     *  <li>In {@link #getParent() parent}'s {@link #getChildEvalTemplateEvents() child eval template event list}</li>
     *  <li>In this list</li>
     *  <li>In the {@link Interpreter#events} list</li>
     * </ol>
     *
     * The root ST has the final {@link EvalTemplateEvent} in its list.
     * <p>
     * All events get added to the {@link #parent}'s event list.</p>
     */
    public List<InterpEvent> getEvents() {
        return events;
    }

    /**
     * All templates evaluated and embedded in this {@link ST}. Used
     * for tree view in {@link STViz}.
     */
    public List<EvalTemplateEvent> getChildEvalTemplateEvents() {
        return childEvalTemplateEvents;
    }

    public boolean isEarlyEval() {
        return earlyEval;
    }

    public void setEarlyEval(boolean earlyEval) {
        this.earlyEval = earlyEval;
    }
}
