package org.aashish.chameleon.generator;

public abstract class AnnotationDecorator extends Generator {

	Generator generator;
	
	public AnnotationDecorator(Generator gen){
		generator = gen;
	}
}
