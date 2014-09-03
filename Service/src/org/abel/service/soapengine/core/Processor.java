/*
 * Created on 2005-4-30
 */
package org.abel.service.soapengine.core;

/**
 * Interface for backend transaction call throw EAI API
 */
public interface Processor {
	public Object execute(Object bizObj) throws Exception;
}
