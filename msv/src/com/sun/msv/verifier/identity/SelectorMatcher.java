/*
 * @(#)$Id$
 *
 * Copyright 2001 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Sun Microsystems, Inc.  
 * Use is subject to license terms.
 * 
 */
package com.sun.msv.verifier.identity;

import com.sun.msv.grammar.xmlschema.IdentityConstraint;
import com.sun.msv.grammar.xmlschema.XPath;
import org.relaxng.datatype.Datatype;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * XPath matcher that tests the selector of an identity constraint.
 * 
 * This object is created whenever an element with identity constraints is found.
 * XML Schema guarantees that we can see if an element has id constraints at the
 * startElement method.
 * 
 * This mathcer then monitor startElement/endElement and find matches to the
 * specified XPath. Every time it finds a match ("target node" in XML Schema
 * terminology), it creates a FieldsMatcher.
 * 
 * @author <a href="mailto:kohsuke.kawaguchi@eng.sun.com">Kohsuke KAWAGUCHI</a>
 */
public class SelectorMatcher extends PathMatcher {
	
	protected IdentityConstraint idConst;

	SelectorMatcher(
				IDConstraintChecker owner, IdentityConstraint idConst,
				String namespaceURI, String localName ) throws SAXException {
		super(owner, idConst.selectors );
		this.idConst = idConst;

		super.start(namespaceURI,localName);
	}

	
	protected void onElementMatched( String namespaceURI, String localName ) throws SAXException {
		if( com.sun.msv.driver.textui.Debug.debug )
			System.out.println("find a match for a selector: "+idConst.localName);
			
		// this element matches the path.
		owner.add( new FieldsMatcher(owner,idConst, namespaceURI,localName) );
	}
	
	protected void onAttributeMatched(
		String namespaceURI, String localName, String value, Datatype type ) {
		
		// assertion failed:
		// selectors cannot contain attribute steps.
		throw new Error();
	}

}
