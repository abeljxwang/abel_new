package org.abel.webapp.policy.base;

public class Operator {

	public final static String RULE_EQ = "EQ";
	public final static String RULE_GT = "GT";
	public final static String RULE_LT = "LT";
	public final static String RULE_BT = "BT";
	public final static String RULE_AM = "AM";
	public final static String RULE_LK = "LK";

	public final static String BOOL_AND = "AND";
	public final static String BOOL_OR = "OR";

	//Properties pp = new Properties();

	//Vector oplist=new Vector();

	String value = "1";

	public void set(String valstr) {
		value = valstr;

	}

	public String get() {
		return value;

	}

	public static boolean getResult(Object a, Object b, String tag) {
		boolean result = false;
		if (a != null && b != null && tag != null) {

			String astr = null, bstr = null;
			astr = a.toString();
			bstr = b.toString();

			//System.out.println(a + "----" + b);
			if (tag.equals(RULE_EQ)) {
				if (b.equals(a))
					result = true;
			} else if (tag.equals(RULE_GT)) {
				if (astr.compareTo(bstr) >= 0)
					result = true;
			} else if (tag.equals(RULE_LT)) {
				if (bstr.compareTo(astr) >= 0)
					result = true;
			} else if (tag.equals(RULE_AM)) {
				if (bstr.indexOf(astr) >= 0)
					result = true;
			} else if (tag.equals(RULE_LK)) {
				if (bstr.indexOf(astr) >= 0)
					result = true;
			}
		}

		return result;

	}

	public static boolean getBooleanResult(boolean a, boolean b, String tag) {
		boolean result = false;

		if (tag.equals(BOOL_AND)) {
			result = a && b;
		} else if (tag.equals(BOOL_OR)) {
			result = a || b;
		} else
			result = false;

		return result;

	}

	public Operator(String valstr) {
		value = valstr;

	}

	public static void main(String[] args) {

		System.out.println(Operator.getResult("Abel", "Abel Wang", "EQ"));
		
		System.out.println(Operator.getBooleanResult(true, false, "AND"));
	}
}
