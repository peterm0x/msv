/*
 * @(#)$Id$
 *
 * Copyright 2001 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Sun Microsystems, Inc.  
 * Use is subject to license terms.
 * 
 */
package com.sun.msv.reader.datatype;

import org.relaxng.datatype.DataType;

/**
 * State can implement this method to be notified by DataType vocabulary
 * about the result of parsing.
 * 
 * @author <a href="mailto:kohsuke.kawaguchi@eng.sun.com">Kohsuke KAWAGUCHI</a>
 */
public interface TypeOwner {
	void onEndChild( DataType child );
}
