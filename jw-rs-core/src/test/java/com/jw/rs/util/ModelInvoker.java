package com.jw.rs.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Model test util class, which is used to do a quick invoke for all getters, setters, equals, hashCode and toString methods.
 * However custom extra methods are not included.
 *
 * @author JWang
 * @since Jan 02, 2018
 */
public class ModelInvoker {

	public void invokeModel(Object object, boolean skipEquals, Object...values) throws InstantiationException, IllegalAccessException {
		initObject(object, values);
		object.toString();
		if (!skipEquals) {
			Object other = initObject(object.getClass().newInstance(), values);
			assertTrue("object.equals(object)", object.equals(object));
			assertTrue("object.equals(other)", object.equals(other));
			assertTrue("!object.equals(null)", !object.equals(null));
			assertTrue("!object.equals(new Object())", !object.equals(new Object()));
			assertTrue("object.hashCode() == other.hashCode()", object.hashCode() == other.hashCode());
		}
	}

	private void assertTrue(String message, boolean test) {
		if (test == false) {
			throw new IllegalStateException("Fail - " + message);
		}
	}

	private Object initObject(Object target, Object...values) {
		Map<String, Method> getters = findGetters(target.getClass());
		Map<String, Method> setters = findSetters(target.getClass());
		for (String name : getters.keySet()) {
			Method getter = getters.get(name);
			Method setter = setters.get(name);
			if (setter != null) {
				Object value = null;
				Class<?> returnType = getter.getReturnType();
				if (returnType == Long.class || returnType == Long.TYPE) {
					value = new Long(0);
				} else if (returnType == Integer.class || returnType == Integer.TYPE) {
					value = new Integer(0);
				} else if (returnType == Short.class || returnType == Short.TYPE) {
					value = new Integer(0).shortValue();
				} else if (returnType == Byte.class || returnType == Byte.TYPE) {
					value = new Integer(0).byteValue();
				} else if (returnType == Double.class || returnType == Double.TYPE) {
					value = new Double(0);
				} else if (returnType == Float.class || returnType == Float.TYPE) {
					value = new Float(0);
				} else if (returnType == Character.class || returnType == Character.TYPE) {
					value = new Character('a');
				} else if (returnType == Boolean.class || returnType == Boolean.TYPE) {
					value = Boolean.FALSE;
				} else if (returnType == String.class) {
					value = "x";
				} else {
					for (Object val : values) {
						if (getter.getReturnType() == val.getClass()) {
							value = val;
							break;
						}
					}
				}
				setValue(setter, target, value);
				Object result = getValue(getter, target);
				if (result != null && !result.equals(value)) {
					throw new IllegalStateException("Set/Get values did not match for field " + name);
				}
			}
		}
		return target;
	}

	private void setValue(Method setter, Object target, Object value) {
		try {
			setter.invoke(target, value);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			String msg = "could not invoke " + setter.getDeclaringClass() + "." + setter.getName() + " - " + e;
			throw new IllegalStateException(msg);
		}
	}

	private Object getValue(Method getter, Object source) {
		try {
			return getter.invoke(source);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			String msg = "could not invoke " + getter.getDeclaringClass() + "." + getter.getName() + " - " + e;
			throw new IllegalStateException(msg);
		}
	}

	private Map<String, Method> findGetters(Class<?> sourceClass) {
		Method[] methods = sourceClass.getMethods();
		Map<String, Method> getters = new HashMap<String, Method>();
		for (Method method : methods) {
			String name = getterProperty(method);
			if (name != null) {
				getters.put(name, method);
			}
		}
		return getters;
	}

	private Map<String, Method> findSetters(Class<?> sourceClass) {
		Method[] methods = sourceClass.getMethods();
		Map<String, Method> setters = new HashMap<String, Method>();
		for (Method method : methods) {
			String name = setterProperty(method);
			if (name != null) {
				setters.put(name, method);
			}
		}
		return setters;
	}

	private String getterProperty(Method method) {
		if (method.getParameterTypes().length != 0) {
			return null;
		}
		String name = method.getName();
		if (name.startsWith("get")) {
			return firstLower(name.substring(3));
		} else if (name.startsWith("is")) {
			return firstLower(name.substring(2));
		} else {
			return null;
		}
	}

	private String setterProperty(Method method) {
		if (method.getParameterTypes().length != 1) {
			return null;
		}
		String name = method.getName();
		if (name.startsWith("set")) {
			return firstLower(name.substring(3));
		} else {
			return null;
		}
	}

	private String firstLower(String name) {
		if (name.length() > 1) {
			return Character.toLowerCase(name.charAt(0)) + name.substring(1);
		} else {
			return name.toLowerCase();
		}
	}
}
