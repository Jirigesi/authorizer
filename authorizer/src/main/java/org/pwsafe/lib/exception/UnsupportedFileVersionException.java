/*
 * $Id: UnsupportedFileVersionException.java 317 2009-01-26 20:20:54Z ronys $
 * 
 * Copyright (c) 2008-2009 David Muller <roxon@users.sourceforge.net>.
 * All rights reserved. Use of the code is allowed under the
 * Artistic License 2.0 terms, as specified in the LICENSE file
 * distributed with this code, or available from
 * http://www.opensource.org/licenses/artistic-license-2.0.php
 */
package org.pwsafe.lib.exception;

/**
 * An exception thrown to indicate that the file is in a format that is not
 * supported
 * by this software.
 *
 * @author Kevin Preece
 */
public class UnsupportedFileVersionException extends Exception
{
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     */
    public UnsupportedFileVersionException()
    {
    }
}
