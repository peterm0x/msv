/*
 * @(#)$Id$
 *
 * Copyright 2001 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Sun Microsystems, Inc.  
 * Use is subject to license terms.
 * 
 */
package com.sun.msv.verifier;

import org.relaxng.datatype.Datatype;
import org.xml.sax.Locator;
import org.iso_relax.verifier.VerifierHandler;

/**
 * Interface of verifier.
 * 
 * @author <a href="mailto:kohsuke.kawaguchi@eng.sun.com">Kohsuke KAWAGUCHI</a>
 */
public interface IVerifier extends VerifierHandler {

	/**
	 * checks if the document was valid.
	 * This method may not be called before verification was completed.
	 */
	boolean isValid();
	
	/**
	 * returns current element type.
	 * 
	 * Actual java type depends on the implementation.
	 * This method works correctly only when called immediately
	 * after handling startElement event.
	 * 
	 * @return null
	 *		this method returns null when it doesn't support
	 *		type-assignment feature, or type-assignment is impossible
	 *		for the current element (for example due to the ambiguous grammar).
	 */
	Object getCurrentElementType();
	
	/**
	 * gets DataType that validated the last characters.
	 * 
	 * <p>
	 * This method works correctly only when called immediately
	 * after startElement and endElement method. When called, this method
	 * returns DataType object that validated the last character literals.
	 * 
	 * <p>
	 * For RELAX NG grammar, this method can return an array of length 2 or more.
	 * This happens when the last character matches &lt;list&gt; pattern.
	 * In that case, each type corresponds to each token (where tokens are the
	 * white-space separation of the last characters).
	 * 
	 * <p>
	 * For any other grammar, this method always returns an array of length 1
	 * (or null, if the type assignment failed).
	 * 
	 * <p>
	 * So when you are using VerifierFilter, you can call this method only
	 * in your startElement and endElement method.
	 * 
	 * @return null
	 *		if type-assignment was not possible.
	 */
	Datatype[] getLastCharacterType();


	Locator getLocator();
	VerificationErrorHandler getVerificationErrorHandler();
	void setVerificationErrorHandler( VerificationErrorHandler handler );
}
