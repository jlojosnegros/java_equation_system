package equationSystem;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ReductionMethod extends SolutionMethod {

	private Map<String, Equation> solutions;


	ReductionMethod() {
		this.solutions = new HashMap<String, Equation>();
	}

	/// el codigo depende de equationSystem pero queremos que deje de depender de el
	/// sin embargo no podemos hacer que todo deje de compilar asi que lo que hacemos es
	/// ir trayendonos todos los metodos del EquationSystem que se van utilizando aqui
	/// con objeto de eliminarlos despues de alli.
	/// Por ahora los vamos poniendo en esta clase porque no tenemos claro que los demas
	/// lo necesiten ...
	/// Cuando veamos los demas veremos si ellos lo necesitan y entonces actuaremos en consecuencia.
	@Override
	public void resolve() {
		assert this.getNameSet().size() == 2;
		Iterator<String> nameIterator = this.getNameSet().iterator();
		String firstName = nameIterator.next();	
		String secondName = nameIterator.next();
		float value1 = this.getLast(2).getValue(firstName);
		float value2 = this.getLast().getValue(firstName);
		this.copyBefore(2);
		this.getLast().multiply(value2);
		this.copyBefore(2);
		this.getLast().multiply(-value1);
		this.copyBefore();
		this.getLast().add(this.getLast(3));
		this.copyBefore();
		this.getLast().simplify(Side.LEFT, firstName);
		this.copyBefore();
		this.getLast().simplify(Side.LEFT, secondName);
		this.copyBefore();
		this.getLast().simplify(Side.RIGHT);;
		this.copyBefore();
		this.getLast().multiply(1/this.getLast(2).getValue(secondName));
		this.setSolution(secondName, this.getLast());
		this.copyBefore(9);
		this.getLast().apply(secondName, this.getLast(2).getValue(Side.RIGHT));
		this.copyBefore();
		this.getLast().add(new Constant(-this.getLast(2).getValue(Side.LEFT)));
		this.copyBefore();
		this.getLast().simplify(Side.LEFT);
		this.copyBefore();
		this.getLast().simplify(Side.RIGHT);
		this.copyBefore();
		this.getLast().multiply(1/this.getLast(2).getValue(firstName));
		this.setSolution(firstName, this.getLast());
	}

	private void copyBefore(){
		this.copyBefore(1);
	}
	private void copyBefore(int before){
		int index = this.equationList.size() - before;
		this.add(this.get(index).clon());
	}

	private Equation get(int index){
		return this.equationList.get(index);
	}

	public void add(Equation equation) {
		this.equationList.add(equation);
		for(String name : equation.getNameSet()){
			this.getNameSet().add(name);
		}
	}

	private Equation getLast(){
		return this.getLast(1);
	}

	private Equation getLast(int before){
		int index = this.equationList.size() - before;
		return this.equationList.get(index);
	}

	private Set<String> getNameSet() {
		return nameSet;
	}

	private void setSolution(String firstName, Equation equation) {
		this.solutions.put(firstName, equation);
	}

	@Override
	public float getSolution(String name) {
		return this.solutions.get(name).getValue(Side.RIGHT);
	}
}
