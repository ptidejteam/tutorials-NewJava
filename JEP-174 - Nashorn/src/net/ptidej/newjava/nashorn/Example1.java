// https://www.baeldung.com/java-nashorn
package net.ptidej.newjava.nashorn;

import javax.script.Bindings;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Example1 {
	public static void main(final String[] args) throws ScriptException, NoSuchMethodException {
		Object result;

		final ScriptEngine engine = new ScriptEngineManager().getEngineByName("Nashorn");

		final Bindings bindings = engine.createBindings();
		bindings.put("name", "Nashorn");
		result = engine.eval("var greeting = 'hello world' + name;" + "print(greeting);" + "greeting");
		System.out.println(result);

		final Invocable invocable = (Invocable) engine;
		engine.eval("function composeGreeting(name) {" + "return 'Hello ' + name" + "}");
		result = invocable.invokeFunction("composeGreeting", "Nashorn");
		System.out.println(result);

		result = engine.eval("var HashMap = Java.type('java.util.HashMap');" + "var map = new HashMap();"
				+ "map.put('hello', 'world');" + "map");
		System.out.println(result);
	}
}
