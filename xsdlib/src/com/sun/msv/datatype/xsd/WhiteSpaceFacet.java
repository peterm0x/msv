/*
 * @(#)$Id$
 *
 * Copyright 2001 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Sun Microsystems, Inc.  
 * Use is subject to license terms.
 * 
 */
package com.sun.msv.datatype;

import org.relaxng.datatype.ValidationContext;
import org.relaxng.datatype.DataTypeException;
import org.relaxng.datatype.ValidationContext;

/**
 * whiteSpace facet validator
 * 
 * @author Kohsuke KAWAGUCHI
 */
public class WhiteSpaceFacet extends DataTypeWithFacet {
	
	WhiteSpaceFacet( String typeName, DataTypeImpl baseType, TypeIncubator facets )
		throws BadTypeException {
		super(typeName, baseType, FACET_WHITESPACE, facets,
			WhiteSpaceProcessor.get( (String)facets.getFacet(FACET_WHITESPACE)) );
		
		// loosened facet check
		if( baseType.whiteSpace.tightness() > this.whiteSpace.tightness() ) {
			DataType d;
			d=baseType.getFacetObject(FACET_WHITESPACE);
			if(d==null)	d = getConcreteType();
			
			throw new BadTypeException(
				BadTypeException.ERR_LOOSENED_FACET,
				FACET_WHITESPACE, d.displayName() );
		}
		
		// consistency with minLength/maxLength is checked in DataTypeImpl.derive method.
	}
	
	protected boolean checkFormat( String content, ValidationContext context ) {
		return baseType.checkFormat(content,context);
	}
	public Object convertToValue( String content, ValidationContext context ) {
		return baseType.convertToValue(content,context);
	}
	
	/** whiteSpace facet never constrain anything */
	protected DataTypeException diagnoseByFacet(String content, ValidationContext context) {
		return null;
	}
}
