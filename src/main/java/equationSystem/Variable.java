package equationSystem;

import java.util.Set;

public class Variable extends Term {
	
	private String name;
	
	public Variable(float value, String name){
		super(value);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public boolean hasName(String name) {
		return this.name.equals(name);
	}
	
	@Override
	public boolean hasName(Set<String> nameSet) {
		for(String name : nameSet){
			if (this.hasName(name))
				return true;
		}
		return false;
	}
	
	@Override
	public boolean equal(Term term) {
		return super.equal(term) && term.hasName(name);
	}
	
	@Override
	public Term clon() {
		return new Variable(this.getValue(), name);
	}
	
	@Override
	public String toString(){
		return super.toString() + name;
	}
	
	@Override
	public void dispatch(TermVisitor termVisitor) {
		termVisitor.visit(this);
	}
	
}
