package org.abel.service.soa.message;

public class OperationDesc {
	String input;

	String output;

	String operation_name;



	public OperationDesc(String nm) {

		operation_name = nm;
	}

	public OperationDesc(String nm, String in, String out) {

		operation_name = nm;
		input = in;
		output = out;
	}

	public String getOperationName() {

		return operation_name;
	}


	public void setInputName(String ipt) {

		input = ipt;
	}

	public String getInputName() {

		return input;
	}

	public void setOutputName(String opt) {

		output = opt;
	}

	public String getOutputName() {

		return output;
	}
}
